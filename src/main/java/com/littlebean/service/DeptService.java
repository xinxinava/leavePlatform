package com.littlebean.service;

import com.github.pagehelper.PageInfo;
import com.littlebean.model.DeptInfo;
import com.littlebean.model.Node;

import java.util.List;

public interface DeptService {
    //查询全部
    PageInfo<DeptInfo> queryDeptAll(int page, int pageSize, DeptInfo deptInfo);
    //添加
    boolean saveInfo(DeptInfo deptInfo);
    //删除
    boolean deleteById(Integer id);
    //修改
    boolean updateInfo(DeptInfo deptInfo);
    //查询树
    List<Node> queryDeptTree();
}
