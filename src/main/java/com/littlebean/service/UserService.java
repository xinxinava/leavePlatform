package com.littlebean.service;

import com.github.pagehelper.PageInfo;
import com.littlebean.model.User;

import java.util.List;

public interface UserService {
    PageInfo<User> queryUserAll(int page, int pageSize, User user);
    boolean saveInfo(User user);
    boolean updateInfo(User user);
    boolean deleteById(Integer id);
    /**
     * 根据Id查询用户
     */
    User queryById(Integer id);

    User queryUserByUserNameAndPwdAndType(String username, String password,
                                          String roleName);
    User queryUserInfoByTypeAndRoleAndDeptId(String type, String role, String deptId);
    int updatePassword(User user);
}
