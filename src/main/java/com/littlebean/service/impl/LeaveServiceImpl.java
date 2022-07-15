package com.littlebean.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlebean.dao.LeaveInfoMapper;
import com.littlebean.model.LeaveInfo;
import com.littlebean.model.TongJi;
import com.littlebean.model.User;
import com.littlebean.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveInfoMapper leaveDao;

    @Override
    public PageInfo<LeaveInfo> queryLeaveAll(Integer page, Integer pageSize, LeaveInfo leaveInfo){
        PageHelper.startPage(page,pageSize);
        List<LeaveInfo> list=leaveDao.queryLeaveAll(leaveInfo);
        PageInfo<LeaveInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean saveInfo(LeaveInfo leaveInfo) {
        int num=leaveDao.insert(leaveInfo);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public int deleteByIds(Integer id) {
        return leaveDao.deleteByPrimaryKey(id);
    }

    @Override
    public LeaveInfo queryById(Integer id) {
        return leaveDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateInfo(LeaveInfo leaveInfo) {
        return leaveDao.updateByPrimaryKey(leaveInfo);
    }

    @Override
    public LeaveInfo getLeaveInfoTaskId(String proId) {
        return leaveDao.getLeaveInfoTaskId(proId);
    }

    @Override
    public List<TongJi> queryTongjiCounts() {
        return leaveDao.queryTongjiCounts();
    }


}
