package com.ruoyi.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.vo.UserChargeWithdrawal;
import com.ruoyi.system.service.*;
import com.ruoyi.web.controller.customer.AllMessageVO;
import com.ruoyi.web.controller.customer.Constant;
import com.ruoyi.web.controller.customer.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/system/customers")
public class SysCustomersController {
    @Autowired
    private ITxzhUserService txzhUserService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IUserChargeWithdrawalService userChargeWithdrawalService;
    @Autowired
    private IUserTradingLogService userTradingLogService;
    @Autowired
    private IUserTransferRecordService userTransferRecordService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/list")
    public AjaxResult list(@RequestBody TxzhUser txzhUser){
        return AjaxResult.success(txzhUserService.findByDeptId(txzhUser));
    }

    @PreAuthorize("@ss.hasPermi('system:live:permission')")
    @PostMapping("/addUserLivePermission")
    public AjaxResult addUserLivePermission(@RequestBody SysUser user) throws IOException {
        return userService.addUserLivePermission(user);
    }

    /**
     * 重置密码
     */
    @Log(title="",businessType = BusinessType.UPDATE)
    @PostMapping("/resetPassword")
    @ResponseBody
    public AjaxResult  UpdateUserPassword(@RequestBody TxzhUser user){
        String password = user.getPassword();
        String  pwd = SecurityUtils.encryptPassword(password);
        int ppww=txzhUserService.UpdataPSWD(pwd,user.getId());
        return AjaxResult.success(ppww);
    }


    /**
     * 聊天记录
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/queryChat")
    public AjaxResult queryChat(@RequestBody TxzhUser txzhuser){
        if(txzhuser.getId() == null){
            return AjaxResult.error("userId 参数异常");
        }
        // 拼接 key
        String key = Constant.USER_MESSAGE + txzhuser.getId();
        // 获取到该用户的所有聊天记录
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        Set<Object> set = entries.keySet();
        Map<String, MessageTemplate> map = new HashMap<>();
        for (Object o : set) {
            map.put(o.toString(),JSON.parseObject(entries.get(o).toString(),MessageTemplate.class));
        }
        // 创建一个 set 来存储与用户有聊天记录的 id
        Set<Long> receiverIds = new HashSet<>();
        for (Map.Entry<String, MessageTemplate> stringMessageTemplateEntry : map.entrySet()) {
            receiverIds.add(Long.parseLong(stringMessageTemplateEntry.getKey().split(":")[0]));
        }
        List<AllMessageVO> list = new ArrayList<>();
        // 使用 set 中的 id 创建出对应的 allMessageVO
        for (Long receiverId : receiverIds) {
            AllMessageVO allMessageVO = new AllMessageVO();
            TxzhUser txzhUser = txzhUserService.selectById(receiverId);
            allMessageVO.setReceiverId(receiverId);
            allMessageVO.setHeadImg(txzhUser.getHeadImg());
            allMessageVO.setNickName(txzhUser.getNickname());
            list.add(allMessageVO);
        }
        // 将对应的聊天信息放到每个 allMessageVO 中的 messageTemplates 中
        for (AllMessageVO allMessageVO : list) {
            for (Map.Entry<String, MessageTemplate> stringMessageTemplateEntry : map.entrySet()) {
                if (allMessageVO.getReceiverId() == Long.parseLong(stringMessageTemplateEntry.getKey().split(":")[0])){
                    allMessageVO.getMessageTemplates().add(stringMessageTemplateEntry.getValue());
                }
            }
            // 排序
            allMessageVO.getMessageTemplates().sort(new Comparator<MessageTemplate>() {
                @Override
                public int compare(MessageTemplate o1, MessageTemplate o2) {
                    return o1.getCreateTime().compareTo(o2.getCreateTime());
                }
            });
        }
        // 如果判断条件不为空
        if(StringUtils.isNotBlank(txzhuser.getCondition())){
            // 创建一个新的 list
            List<AllMessageVO> conditionList = new ArrayList<>();
            // 遍历原先返回的 list
            for (AllMessageVO allMessageVO : list) {
                AllMessageVO messageVO = new AllMessageVO();
                messageVO.setHeadImg(allMessageVO.getHeadImg());
                messageVO.setNickName(allMessageVO.getNickName());
                messageVO.setReceiverId(allMessageVO.getReceiverId());
                for (MessageTemplate messageTemplate : allMessageVO.getMessageTemplates()) {
                    if(messageTemplate.getContent().contains(txzhuser.getCondition())){
                        messageVO.getMessageTemplates().add(messageTemplate);
                    }
                }
                conditionList.add(messageVO);
            }
            return AjaxResult.success(conditionList);
        }
        return AjaxResult.success(list);
    }

    // 获取当前用户所有的充值请求
    @PostMapping("/getTopUpList")
    public AjaxResult getTopUpList(@RequestBody TxzhUser txzhUser) throws Exception {
        return AjaxResult.success(userChargeWithdrawalService.getTopUpList(txzhUser));
    }

    // 完成当前充值请求
    @PostMapping("/updateTopUpState")
    public AjaxResult updateTopUpState(@RequestBody UserChargeWithdrawal userChargeWithdrawal) throws Exception {
        userChargeWithdrawalService.updateTopUpState(userChargeWithdrawal);
        return AjaxResult.success();
    }
    // 获取当前用户所有的体现请求
    @PostMapping("/getWithdrawalList")
    public AjaxResult getWithdrawalList(@RequestBody TxzhUser txzhUser) throws Exception {
        return AjaxResult.success(userChargeWithdrawalService.getWithdrawalList(txzhUser));
    }
    // 处理提现请求
    @PostMapping("/updateWithdrawalList")
    public AjaxResult updateWithdrawalList(@RequestBody UserChargeWithdrawal userChargeWithdrawal) throws Exception {
        userChargeWithdrawalService.updateWithdrawalList(userChargeWithdrawal);
        return AjaxResult.success();
    }
    // 完成提现并将用户的冻结金额清0
    @PostMapping("/completeWithdrawal")
    public AjaxResult completeWithdrawal(@RequestBody UserChargeWithdrawal userChargeWithdrawal) throws Exception {
        userChargeWithdrawalService.completeWithdrawal(userChargeWithdrawal);
        return AjaxResult.success();
    }

    // 支付记录列表查询
    @PostMapping("/payList")
    public AjaxResult payList(@RequestBody TxzhUser txzhUser) throws Exception {
        return userTradingLogService.payList(txzhUser);
    }

    @PostMapping("/messageOutPutExcel")
    public AjaxResult messageOutPutExcel(@RequestBody TxzhUser txzhUser) throws IOException {
        return txzhUserService.messageOutPutExcel();
    }

    @PostMapping("/giveRedPacketsLog")
    public AjaxResult giveRedPacketsLog(@RequestBody TxzhUser txzhUser){
        return userTransferRecordService.giveRedPacketsLog(txzhUser);
    }

    @PostMapping("/insertTxzhUser")
    public AjaxResult insertTxzhUser(@RequestBody TxzhUser txzhUser) throws InterruptedException {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        return txzhUserService.insertTxzhUser(txzhUser,user);
    }

    @PostMapping("/blackTxzhUser")
    public AjaxResult blackTxzhUser(@RequestBody TxzhUser txzhUser){
        return txzhUserService.blackTxzhUser(txzhUser);
    }

    @PostMapping("/updateTxzhUser")
    public AjaxResult updateTxzhUser(@RequestBody TxzhUser txzhUser){
        return txzhUserService.updateTxzhUser(txzhUser);
    }

    @PostMapping("/getUserIntegral")
    public AjaxResult getUserIntegral(@RequestBody TxzhUser txzhUser){
        return txzhUserService.getUserIntegral(txzhUser);
    }
}
