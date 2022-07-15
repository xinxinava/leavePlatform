package com.littlebean.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlebean.dao.UserMapper;
import com.littlebean.model.DeptInfo;
import com.littlebean.model.User;
import com.littlebean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;

    @Override
    public PageInfo<User> queryUserAll(int page, int pageSize, User user) {
        PageHelper.startPage(page,pageSize);
        List<User> list=userDao.queryUserAll(user);
        PageInfo<User> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean saveInfo(User user) {
        int num=userDao.insert(user);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public boolean updateInfo(User user) {
        int num=userDao.updateByPrimaryKey(user);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        int num=userDao.deleteByPrimaryKey(id);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public User queryById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User queryUserByUserNameAndPwdAndType(String username, String password, String roleName) {
        return userDao.queryUserByUserNameAndPwdAndType(username, password, roleName);
    }

    @Override
    public User queryUserInfoByTypeAndRoleAndDeptId(String type, String role, String deptId) {
        return userDao.queryUserInfoByTypeAndRoleAndDeptId(type, role, deptId);
    }
    @Override
    public int updatePassword(User user){
        return userDao.updatePassword(user);
    }
}
