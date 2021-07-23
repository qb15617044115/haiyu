package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.TxzhUser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ITxzhUserService  {
    /**
     * 根据管理着所在的机构id来查询机构以及其子机构下的所有成员
     * @return List<UserAccount>
     */
    Map<String,Object> findByDeptId(TxzhUser txzhUser);

    //重置
    int   UpdataPSWD(String  password,Long id);
    TxzhUser getByid( Long id);
    TxzhUser getByName(String username);

    //聊天
    List<TxzhUser> GetQueryImgName(TxzhUser txzhUser);
    List<TxzhUser> GetQueryById(Integer id);


    TxzhUser selectById(Long receiverId);

    AjaxResult messageOutPutExcel() throws IOException;

    AjaxResult insertTxzhUser(TxzhUser txzhUser, SysUser user) throws InterruptedException;

    AjaxResult blackTxzhUser(TxzhUser txzhUser);

    AjaxResult updateTxzhUser(TxzhUser txzhUser);

    AjaxResult getUserIntegral(TxzhUser txzhUser);
}
