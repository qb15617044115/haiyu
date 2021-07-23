package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DiscoveryPageManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscoveryPageManagementMapper {

    void bathUpdate(DiscoveryPageManagement discoveryPageManagement);

     List<DiscoveryPageManagement> queryconfig(DiscoveryPageManagement discoveryPageManagement,@Param("id") Integer id);

     void updatefound(@Param("id") Integer id,@Param("state") Integer state);

     List<DiscoveryPageManagement> querys(@Param("id") Integer id);

     List<DiscoveryPageManagement> queryById(@Param("id") Integer id);

    List<DiscoveryPageManagement> querymoduleChoice(DiscoveryPageManagement discoveryPageManagement);

    int insertDiscovery(DiscoveryPageManagement discoveryPageManagement);

    int deleteDiscovery(DiscoveryPageManagement discoveryPageManagement);

    int updateDiscovery(DiscoveryPageManagement discoveryPageManagement);

    int queryDiscoveryName(String  menuname);

    List<DiscoveryPageManagement> queryAllBychoice(DiscoveryPageManagement discoveryPageManagement);

    List<String> querytupian(@Param("menutype") String  menutype);

    List<String> querymoduleChoice2(@Param("menutype") String  menutype);

    List<DiscoveryPageManagement> queryByid(DiscoveryPageManagement discoveryPageManagement);

    List<DiscoveryPageManagement> queryByQid(@Param("id") Integer  id);

}
