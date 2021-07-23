package com.ruoyi.system.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.system.domain.TxzhConfigAll;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserTradingLog;
import com.ruoyi.system.domain.vo.UserChargeWithdrawal;
import com.ruoyi.system.mapper.TxzhConfigAllMapper;
import com.ruoyi.system.mapper.TxzhUserMapper;
import com.ruoyi.system.mapper.UserChargeWithdrawalMapper;
import com.ruoyi.system.service.IUserChargeWithdrawalService;
import com.ruoyi.system.service.IUserTradingLogService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserChargeWithdrawalServiceImpl implements IUserChargeWithdrawalService {

    @Autowired
    private UserChargeWithdrawalMapper userChargeWithdrawalMapper;
    @Autowired
    private TxzhUserMapper txzhUserMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private IUserTradingLogService userTradingLogService;
    @Autowired
    private TxzhConfigAllMapper txzhConfigAllMapper;
    @Override
    public Map<String,Object> getTopUpList(TxzhUser txzhUser) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(txzhUser.getId() == null){
            throw new Exception("参数异常");
        }
        List<UserChargeWithdrawal> userChargeWithdrawals = userChargeWithdrawalMapper.listByUserId(txzhUser, 1);
        int i = userChargeWithdrawalMapper.countByUserId(txzhUser, 1);
        map.put("list",userChargeWithdrawals);
        map.put("count",i);
        return map;
    }

    @Override
    @Transactional
    public void updateTopUpState(UserChargeWithdrawal userChargeWithdrawal) throws Exception {
        TxzhUser txzhUser = txzhUserMapper.selectByUserChargeWithdrawalId(userChargeWithdrawal);
        if(txzhUser == null){
            throw new Exception("参数异常");
        }
        userChargeWithdrawalMapper.updateTopUpStateById(userChargeWithdrawal);
        // 根据 id 查出对应的记录
        UserChargeWithdrawal template = userChargeWithdrawalMapper.selectById(userChargeWithdrawal.getId());
        // 修改充值请求状态
        userChargeWithdrawalMapper.updateCompleteById(userChargeWithdrawal.getId());
        TxzhUser user = txzhUserMapper.getByid(template.getUserId());
        // 修改当前用户钱包
        txzhUserMapper.updateMoneyById(txzhUser.getId(),template.getMoney());
        // 获取用户信息
        TxzhUser byid = txzhUserMapper.getByid(template.getUserId());
        // 获取用户等级
        List<TxzhConfigAll> list = txzhConfigAllMapper.listIntegralConfig();
        for (TxzhConfigAll txzhConfigAll : list) {
            if(byid.getPoint() >= Integer.parseInt(txzhConfigAll.getConfigContent())){
                byid.setLevelName(txzhConfigAll.getTitleName());
            }
        }
        // 修改缓存
        redisTemplate.opsForValue().set("user-info:" + byid.getId(), JSON.toJSONString(byid));
        // 添加支付记录
        UserTradingLog userTradingLog = new UserTradingLog();
        userTradingLog.setUserId(byid.getId());
        userTradingLog.setTradingTime(new Date());
        userTradingLog.setTradingType(2);
        userTradingLog.setTradingAmount(template.getMoney());
        userTradingLog.setTradingSource("充值成功");
        userTradingLog.setTradingBeforeMoney(user.getMoney());
        userTradingLog.setTradingAfterMoney(byid.getMoney());
        userTradingLog.setId(Long.parseLong(new Date().getTime() + "" + RandomUtil.randomNumbers(3)));
        userTradingLogService.insertTradingLog(userTradingLog);
    }

    @Override
    public Map<String,Object> getWithdrawalList(TxzhUser txzhUser) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(txzhUser.getId() == null){
            throw new Exception("参数异常");
        }
        List<UserChargeWithdrawal> userChargeWithdrawals = userChargeWithdrawalMapper.listByUserId(txzhUser, 2);
        int i = userChargeWithdrawalMapper.countByUserId(txzhUser, 2);
        for (UserChargeWithdrawal userChargeWithdrawal : userChargeWithdrawals) {
            String lastFour = userChargeWithdrawalMapper.selectBankCardByBankId(userChargeWithdrawal.getBankId());
            if(StringUtils.isNotBlank(lastFour)){
                lastFour.substring(lastFour.length() - 5,lastFour.length() - 1);
                userChargeWithdrawal.setLastFour(lastFour.substring(lastFour.length() - 5,lastFour.length() - 1));
            }
        }
        map.put("list",userChargeWithdrawals);
        map.put("count",i);
        return map;
    }

    @Override
    @Transactional
    public void updateWithdrawalList(UserChargeWithdrawal userChargeWithdrawal) throws Exception {
        if(userChargeWithdrawal.getId() == null){
            throw new Exception("参数异常");
        }
        if(userChargeWithdrawal.getState() != 2 && userChargeWithdrawal.getState() != 3){
            throw new Exception("参数异常");
        }
        // 根据 id 查出对应的记录
        UserChargeWithdrawal template = userChargeWithdrawalMapper.selectById(userChargeWithdrawal.getId());
        if(template == null){
            throw new Exception("参数异常");
        }
        // 修改提现记录状态
        template.setState(userChargeWithdrawal.getState());
        int count = userChargeWithdrawalMapper.updateWithdrawalById(template);
        if(count < 1){
            throw new Exception("参数异常");
        }
        // 如果状态为已拒绝
        if(userChargeWithdrawal.getState() == 3){
            TxzhUser user = txzhUserMapper.getByid(template.getUserId());
            // 修改用户的金额和冻结金额
            txzhUserMapper.updateMoneyAndFreezeMoney(template);
            TxzhUser byid = txzhUserMapper.getByid(template.getUserId());
            // 修改缓存
            List<TxzhConfigAll> list = txzhConfigAllMapper.listIntegralConfig();
            for (TxzhConfigAll txzhConfigAll : list) {
                if(byid.getPoint() >= Integer.parseInt(txzhConfigAll.getConfigContent())){
                    byid.setLevelName(txzhConfigAll.getTitleName());
                }
            }
            redisTemplate.opsForValue().set("user-info:" + byid.getId(), JSON.toJSONString(byid));
            // 添加支付日志
            UserTradingLog userTradingLog = new UserTradingLog();
            userTradingLog.setUserId(byid.getId());
            userTradingLog.setTradingAmount(template.getMoney());
            userTradingLog.setTradingTime(new Date());
            userTradingLog.setTradingType(2);
            userTradingLog.setTradingBeforeMoney(user.getMoney());
            userTradingLog.setTradingAfterMoney(byid.getMoney());
            userTradingLog.setTradingSource("拒绝体现，返回退款");
            userTradingLog.setUserId(Long.parseLong(new Date().getTime() + "" + RandomUtil.randomNumbers(3)));
            userTradingLogService.insertTradingLog(userTradingLog);
        }
    }

    @Override
    @Transactional
    public void completeWithdrawal(@RequestBody UserChargeWithdrawal userChargeWithdrawal) throws Exception {
        if(userChargeWithdrawal.getId() == null){
            throw new Exception("参数异常");
        }
        if(userChargeWithdrawal.getState() == 1){
            throw new Exception("非法请求");
        }
        // 根据 id 查出对应的记录
        UserChargeWithdrawal template = userChargeWithdrawalMapper.selectById(userChargeWithdrawal.getId());
        if(template == null){
            throw new Exception("参数异常");
        }
        // 将当前提现记录改为完成
        int count = userChargeWithdrawalMapper.updateCompleteById(template.getId());
        if(count < 1){
            throw new Exception("提交失败");
        }
        // 将用户的冻结金额减少
        int updateCount = txzhUserMapper.updateFreezeMoney(template);
        TxzhUser byid = txzhUserMapper.getByid(template.getUserId());
        // 修改缓存
        redisTemplate.opsForValue().set("user-info:" + byid.getId(), JSON.toJSONString(byid));
        if(updateCount < 1){
            throw new Exception("提交失败");
        }
    }
}
