package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TxzhCustomerAdmin;
import com.ruoyi.system.mapper.TxzhCustomerAdminMapper;
import com.ruoyi.system.service.ITxchCustomerAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TxchCustomerAdminServiceImpl implements  ITxchCustomerAdminService {

    @Autowired
    private TxzhCustomerAdminMapper txzhCustomerAdminMapper;


    @Override
    public List<TxzhCustomerAdmin> getAllCustomer(Long deptId) {
        return txzhCustomerAdminMapper.getAllCustomer(deptId);
    }

}
