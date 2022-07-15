package com.littlebean.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlebean.dao.DeptInfoMapper;
import com.littlebean.model.DeptInfo;
import com.littlebean.model.Node;
import com.littlebean.service.DeptService;
import com.littlebean.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptInfoMapper deptDao;

    @Override
    public PageInfo<DeptInfo> queryDeptAll(int page, int pageSize, DeptInfo deptInfo) {
        PageHelper.startPage(page,pageSize);
        List<DeptInfo> list=deptDao.queryDeptAll(deptInfo);
        PageInfo<DeptInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean saveInfo(DeptInfo deptInfo) {
        int num=deptDao.insert(deptInfo);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        DeptInfo deptInfo=deptDao.selectByPrimaryKey(id);
        int num=0;
        if(deptInfo.getType()==2) {
            //院系
            List<DeptInfo> list = deptDao.queryByFId(id);
            for (DeptInfo d : list) {
                num += deptDao.deleteByPrimaryKey(d.getId());
            }
        }
        num+=deptDao.deleteByPrimaryKey(id);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public boolean updateInfo(DeptInfo deptInfo) {
        int num=deptDao.updateByPrimaryKey(deptInfo);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public List<Node> queryDeptTree() {
        List<Node> list = deptDao.queryDeptTree();
        TreeUtil util=new TreeUtil();
        List<Node> treeList=util.build(list);
        return treeList;
    }
}
