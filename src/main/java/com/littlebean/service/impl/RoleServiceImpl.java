package com.littlebean.service.impl;

import com.littlebean.dao.RoleInfoMapper;
import com.littlebean.model.RoleInfo;
import com.littlebean.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleInfoMapper roleDao;

    @Override
    public List<RoleInfo> queryRoleAll() {
        return roleDao.queryRoleAll();
    }
}
