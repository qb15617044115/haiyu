package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TxzhCustomerAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TxzhCustomerAdminMapper {


    List<TxzhCustomerAdmin> getAllCustomer(@Param("deptId")Long deptId);



}
