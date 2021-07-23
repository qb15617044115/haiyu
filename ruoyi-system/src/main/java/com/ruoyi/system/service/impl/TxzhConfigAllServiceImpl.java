package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ConfigVO;
import com.ruoyi.system.domain.TxzhConfigAll;
import com.ruoyi.system.mapper.TxzhConfigAllMapper;
import com.ruoyi.system.service.ITxzhConfigAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TxzhConfigAllServiceImpl implements ITxzhConfigAllService {

    @Autowired
    private TxzhConfigAllMapper txzhConfigAllMapper;

    @Override
    public AjaxResult listConfigAll() {
        List<TxzhConfigAll> txzhConfigAlls = txzhConfigAllMapper.listConfigAll();
        for (TxzhConfigAll configAll : txzhConfigAlls) {
            configAll.setConfigVO(JSON.parseObject(configAll.getConfigContent(), ConfigVO.class));
            configAll.setConfigContent(null);
        }
        return AjaxResult.success(txzhConfigAlls);
    }

    @Override
    @Transactional
    public AjaxResult addConfigAll(TxzhConfigAll txzhConfigAll) {
        for (TxzhConfigAll configAll : txzhConfigAll.getTxzhConfigAllList()) {
            if(configAll.getConfigVO() == null){
                return AjaxResult.error();
            }
            if(StringUtils.isBlank(configAll.getCodeNumber())){
                return AjaxResult.error("参数异常");
            }
            int count = txzhConfigAllMapper.selectCountByCodeNumber(configAll);
            configAll.setConfigContent(JSON.toJSONString(configAll.getConfigVO()));
            if(count > 0){
                configAll.setCreateTime(new Date());
                txzhConfigAllMapper.updateConfigAll(configAll);
            }else{
                txzhConfigAllMapper.addConfigAll(configAll);
            }
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getRTK() {
        TxzhConfigAll rtk = txzhConfigAllMapper.getRTK();
        return AjaxResult.success(rtk);
    }

    @Override
    public AjaxResult updateRTKStatus(TxzhConfigAll txzhConfigAll) {
        if(txzhConfigAll.getStatus() != 1 && txzhConfigAll.getStatus() != 0){
            return AjaxResult.error("参数错误!!");
        }
        int i = txzhConfigAllMapper.updateRTKStatus(txzhConfigAll);
        if(i > 0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error("修改失败!!");
        }
    }

    @Override
    public AjaxResult listIntegralConfig() {
        List<TxzhConfigAll> list = txzhConfigAllMapper.listIntegralConfig();
        return AjaxResult.success(list);
    }

    @Override
    public AjaxResult updateIntegralConfig(TxzhConfigAll txzhConfigAll) {
        List<TxzhConfigAll> list = new ArrayList<>();
        for (TxzhConfigAll configAll : txzhConfigAll.getTxzhConfigAllList()) {
            if(configAll.getCodeNumber().equals("LEVEL")){
                list.add(configAll);
            }
        }
        // 批量修改
        for (TxzhConfigAll configAll : list) {
            txzhConfigAllMapper.batchUpdateLevel(configAll);
        }
        return AjaxResult.success();
    }
}
