package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.DiscoveryPageManagement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDiscoveryPageManagementService {

      AjaxResult updateDiscoveryPageManagement(DiscoveryPageManagement list);

      List<DiscoveryPageManagement> queryconfig(DiscoveryPageManagement discoveryPageManagement,Integer id);
      void updatefound(Integer id, Integer state);

      List<DiscoveryPageManagement> querys(Integer id);

      List<DiscoveryPageManagement> queryById(Integer id);

      List<DiscoveryPageManagement> querymoduleChoice(DiscoveryPageManagement discoveryPageManagement);

      int  insertDiscovery(DiscoveryPageManagement discoveryPageManagement);

      int deleteDiscovery(DiscoveryPageManagement discoveryPageManagement);

      int updateDiscovery(DiscoveryPageManagement discoveryPageManagement);

      int queryDiscoveryName(String  menuname);

      List<DiscoveryPageManagement> queryAllBychoice(DiscoveryPageManagement discoveryPageManagement);

      List<String> querytupian(String  menutype);

      List<String> querymoduleChoice2(String  menutype);

      List<DiscoveryPageManagement> queryByid(DiscoveryPageManagement discoveryPageManagement);

      List<DiscoveryPageManagement> queryByQid(Integer  id);
}
