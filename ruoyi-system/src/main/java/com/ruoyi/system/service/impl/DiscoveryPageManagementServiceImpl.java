package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.DiscoveryPageManagement;
import com.ruoyi.system.mapper.DiscoveryPageManagementMapper;
import com.ruoyi.system.service.IDiscoveryPageManagementService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoveryPageManagementServiceImpl implements IDiscoveryPageManagementService {

    @Autowired
    private DiscoveryPageManagementMapper discoveryPageManagementMapper;

    @Override
    public List<DiscoveryPageManagement> queryconfig(DiscoveryPageManagement discoveryPageManagement,Integer id) {
        return discoveryPageManagementMapper.queryconfig(discoveryPageManagement,id);
    }

    @Override
    public List<DiscoveryPageManagement> querymoduleChoice(DiscoveryPageManagement discoveryPageManagement) {
        return discoveryPageManagementMapper.querymoduleChoice(discoveryPageManagement);
    }



    @Override
    public void updatefound(Integer id, Integer state) {
           discoveryPageManagementMapper.updatefound(id, state);
    }

    @Override
    public List<DiscoveryPageManagement> querys(Integer id) {
        return discoveryPageManagementMapper.querys(id);
    }

    @Override
    public List<DiscoveryPageManagement> queryById(Integer id ) {
        return discoveryPageManagementMapper.queryById(id);
    }

    @Override
    public AjaxResult updateDiscoveryPageManagement(DiscoveryPageManagement list) {
        if(list.getList().isEmpty()){
            return AjaxResult.error("参数异常");
        }
        for (DiscoveryPageManagement discoveryPageManagement : list.getList()) {
            discoveryPageManagementMapper.bathUpdate(discoveryPageManagement);
        }
        return AjaxResult.success();
    }

    @Override
    public int insertDiscovery(DiscoveryPageManagement discoveryPageManagement) {
        return discoveryPageManagementMapper.insertDiscovery(discoveryPageManagement);
    }

    @Override
    public int deleteDiscovery(DiscoveryPageManagement discoveryPageManagement) {
        return discoveryPageManagementMapper.deleteDiscovery(discoveryPageManagement);
    }

    @Override
    public int updateDiscovery(DiscoveryPageManagement discoveryPageManagement) {
        return discoveryPageManagementMapper.updateDiscovery(discoveryPageManagement);
    }

    @Override
    public int queryDiscoveryName(String   menuname) {
        return discoveryPageManagementMapper.queryDiscoveryName(menuname);
    }

    @Override
    public List<DiscoveryPageManagement> queryAllBychoice(DiscoveryPageManagement discoveryPageManagement) {
        return discoveryPageManagementMapper.queryAllBychoice(discoveryPageManagement);
    }

    @Override
    public List<String> querytupian(String  menutype) {
        return discoveryPageManagementMapper.querytupian(menutype);
    }

    @Override
    public List<String> querymoduleChoice2(String  menutype) {
        return discoveryPageManagementMapper.querymoduleChoice2(menutype);
    }

    @Override
    public List<DiscoveryPageManagement> queryByid(DiscoveryPageManagement discoveryPageManagement) {
        return discoveryPageManagementMapper.queryByid(discoveryPageManagement);
    }

    @Override
    public List<DiscoveryPageManagement> queryByQid(Integer id) {
        return discoveryPageManagementMapper.queryByQid(id);
    }

}
