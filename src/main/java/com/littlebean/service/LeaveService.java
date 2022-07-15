package com.littlebean.service;

import com.github.pagehelper.PageInfo;
import com.littlebean.model.LeaveInfo;
import com.littlebean.model.TongJi;
import com.littlebean.model.User;

import java.util.List;

public interface LeaveService {
    PageInfo<LeaveInfo> queryLeaveAll(Integer page, Integer pageSize,LeaveInfo leaveInfo);

    /**
     * 添加
     */
    boolean saveInfo(LeaveInfo leaveInfo);
    int deleteByIds(Integer id);
    LeaveInfo queryById(Integer id);

    int updateInfo(LeaveInfo leaveInfo);
    LeaveInfo getLeaveInfoTaskId(String proId);
    List<TongJi> queryTongjiCounts();

}
