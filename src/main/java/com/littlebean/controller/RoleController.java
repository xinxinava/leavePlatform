package com.littlebean.controller;

import com.littlebean.model.RoleInfo;
import com.littlebean.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/queryRoleAll")
    public List<RoleInfo> queryRoleAll(){
        return roleService.queryRoleAll();
    }
}
