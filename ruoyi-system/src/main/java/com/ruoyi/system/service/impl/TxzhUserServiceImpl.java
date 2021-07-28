package com.ruoyi.system.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TxzhConfigAll;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserBankCard;
import com.ruoyi.system.domain.UserIntegralLog;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.ITxzhUserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class TxzhUserServiceImpl implements ITxzhUserService {
    @Autowired
    private TxzhUserMapper txzhUserMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Value("${live.authCode}")
    private String authCode;
    @Value("${live.liveUrl}")
    private String liveUrl;
    @Autowired
    private UserBankCardMapper userBankCardMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserIntegralLogMapper userIntegralLogMapper;
    @Autowired
    private TxzhConfigAllMapper txzhConfigAllMapper;

    @Override
    public Map<String,Object> findByDeptId(TxzhUser txzhUser) {
        // 查询出所有与此机构 id 有关联的子机构
        Map<String,Object> map = new HashMap<>();
        List<SysDept> sysDepts = sysDeptMapper.findAllDeptIdByParentDeptId(txzhUser.getDeptId().longValue());
        List<Long> deptIds = new ArrayList<>();
        // list 为空,返回一个空的集合
        if (sysDepts.isEmpty()){
            map.put("list",new ArrayList<>());
            map.put("count",0);
            return map;
        }
        // 遍历得到的集合
        for (SysDept sysDept : sysDepts) {
            // 将客户集进行分割后存到 list 中
            deptIds.add(sysDept.getDeptId());
        }
        // 在 user_account 表中查询出用户信息
        List<TxzhUser> list = txzhUserMapper.BatchFindByUserId(deptIds,txzhUser);
        // 查询出数量
        int count = txzhUserMapper.countFindByUserId(deptIds,txzhUser);
        map.put("list",list);
        map.put("count",count);
        return map;
    }

    @Override
    public int  UpdataPSWD(String  password,Long id) {
        return txzhUserMapper.UpdatePSWD(password,id);
    }

    @Override
    public TxzhUser getByid(Long id) {
        return txzhUserMapper.getByid(id);
    }

    @Override
    public TxzhUser getByName(String username) {
        return txzhUserMapper.getByName(username);
    }


    @Override
    public List<TxzhUser> GetQueryImgName(TxzhUser txzhUser) {
        return txzhUserMapper.GetQueryImgName(txzhUser);
    }
    @Override
    public List<TxzhUser> GetQueryById(Integer id) {
        return txzhUserMapper.GetQueryById(id);
    }

    @Override
    public TxzhUser selectById(Long receiverId) {
        return txzhUserMapper.selectById(receiverId);
    }

    @Override
    public AjaxResult messageOutPutExcel() throws IOException {
        // 新建一张工作表
        HSSFWorkbook wb = new HSSFWorkbook();
        // 新建一个表格
        HSSFSheet sheet = wb.createSheet("test");
        // 新建一行
        HSSFRow row = sheet.createRow(0);
        // 新建一个单元格
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("测试单元格");
        File file = new File("C:\\message\\test.xls");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream out = new FileOutputStream("C:\\test.xls");
        wb.write(out);
        out.close();
        wb.close();
        return null;
    }

    @Override
    @Transactional
    public AjaxResult insertTxzhUser(TxzhUser txzhUser, SysUser user) throws InterruptedException {
        // 判断必要参数
        if(StringUtils.isBlank(txzhUser.getUsername())){
            return AjaxResult.error("用户名不能为空");
        }
        if(StringUtils.isBlank(txzhUser.getNickname())){
            return AjaxResult.error("用户昵称不能为空");
        }
        if(StringUtils.isBlank(txzhUser.getPassword())){
            return AjaxResult.error("用户密码不能为空");
        }
        // 查看用户名是否重复
        int count = txzhUserMapper.selecCountByUsername(txzhUser);
        if (count > 0){
            return AjaxResult.error("用户名已存在");
        }
        // 对密码进行加密
        String password = txzhUser.getPassword();
        txzhUser.setPassword(SecurityUtils.encryptPassword(password));
        // 生成 id
        String s = RandomUtil.randomNumbers(3);
        txzhUser.setId(Long.parseLong(new Date().getTime() + s));
        Long deptId = user.getDept().getDeptId();
        txzhUser.setDeptId(deptId.intValue());
        // 进行添加操作
        txzhUserMapper.insertTxzhUser(txzhUser);
        // 添加银行卡
        if(txzhUser.getUserBankCard() != null){
            UserBankCard userBankCard = txzhUser.getUserBankCard();
            if(StringUtils.isBlank(userBankCard.getBankUserName())){
                return AjaxResult.error("真实姓名不能为空");
            }if(StringUtils.isBlank(userBankCard.getIdCard())){
                return AjaxResult.error("身份证号不能为空");
            }else{
                // 判断身份证长度
                if(userBankCard.getIdCard().length() != 18){
                    return AjaxResult.error("身份证格式错误");
                }
            }
            List<UserBankCard> list = new ArrayList<>();
            if(!txzhUser.getUserBankCard().getBankCards().isEmpty()){
                userBankCard.setCreateBy(txzhUser.getId());
                userBankCard.setBankUserId(txzhUser.getId());
                for (String bankCard : txzhUser.getUserBankCard().getBankCards()) {
                    UserBankCard card = new UserBankCard();
                    String s1 = new Date().getTime() + RandomUtil.randomNumbers(3);
                    card.setId(Long.parseLong(s1));
                    Thread.sleep(1);
                    card.setBankCard(bankCard);
                    card.setBankUserName(userBankCard.getBankUserName());
                    card.setBankUserId(txzhUser.getId());
                    card.setIdCard(userBankCard.getIdCard());
                    card.setCreateBy(txzhUser.getId());
                    list.add(card);
                }
                userBankCardMapper.batchInsertBankCard(list);
            }
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult blackTxzhUser(TxzhUser txzhUser) {
        // 判断参数是否正确
        if(txzhUser.getStatus() == null){
            return AjaxResult.error("参数错误,请检查参数");
        }
        // 拉黑用户
        txzhUserMapper.blackTxzhUser(txzhUser);
        // 生成 token 的 key 和用户信息的 key
        String tokenKey = "user-token:" + txzhUser.getId();
        String infoKey = "user-info:" + txzhUser.getId();
        if(txzhUser.getStatus() != null){
            if(txzhUser.getStatus().equals(1)){
                // 删除缓存
                redisTemplate.delete(tokenKey);
                redisTemplate.delete(infoKey);
                // 发送消息到客户端
                System.err.println("用户 : " + txzhUser.getId() + "已被拉黑");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId",txzhUser.getId());
                jsonObject.put("type",500);
                HttpUtil.createPost("172.31.8.126:8183/liveShielding").body(JSON.toJSONString(jsonObject)).execute().body();
            }else if(txzhUser.getStatus().equals(2)){
                TxzhUser byid = txzhUserMapper.getByid(txzhUser.getId());
                List<TxzhConfigAll> list = txzhConfigAllMapper.listIntegralConfig();
                for (TxzhConfigAll txzhConfigAll : list) {
                    if(byid.getPoint() >= Integer.parseInt(txzhConfigAll.getConfigContent())){
                        byid.setLevelName(txzhConfigAll.getTitleName());
                    }
                }
                redisTemplate.opsForValue().set(infoKey, JSON.toJSONString(byid));
                // 发送客户端消息
                System.err.println("用户 : " + txzhUser.getId() + "已被禁用直播功能");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId",txzhUser.getId());
                jsonObject.put("type",501);
                HttpUtil.createPost("172.31.8.126:8183/liveShielding").body(JSON.toJSONString(jsonObject)).execute().body();
            }else{
                // 修改缓存
                TxzhUser byid = txzhUserMapper.getByid(txzhUser.getId());
                List<TxzhConfigAll> list = txzhConfigAllMapper.listIntegralConfig();
                for (TxzhConfigAll txzhConfigAll : list) {
                    if(byid.getPoint() >= Integer.parseInt(txzhConfigAll.getConfigContent())){
                        byid.setLevelName(txzhConfigAll.getTitleName());
                    }
                }
                redisTemplate.opsForValue().set(infoKey, JSON.toJSONString(byid));
            }
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult updateTxzhUser(TxzhUser txzhUser) {
        if(txzhUser.getId() == null){
            return AjaxResult.error("用户id不能为空");
        }
        if(StringUtils.isNotBlank(txzhUser.getPassword())){
            // 加密
            String password = txzhUser.getPassword();
            txzhUser.setPassword(SecurityUtils.encryptPassword(password));
        }
        // 进行修改
        txzhUserMapper.updateTxzhUser(txzhUser);
        // 修改缓存
        String key = "user-info:" + txzhUser.getId();
        TxzhUser byid = txzhUserMapper.getByid(txzhUser.getId());
        redisTemplate.opsForValue().set(key, JSON.toJSONString(byid));
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getUserIntegral(TxzhUser txzhUser) {
        Map<String,Object> map = new HashMap<>();
        // 查询出列表
        List<UserIntegralLog> list = userIntegralLogMapper.listByUserId(txzhUser);
        map.put("list",list);
        int count = userIntegralLogMapper.countById(txzhUser);
        map.put("count",count);
        return AjaxResult.success(map);
    }
}














