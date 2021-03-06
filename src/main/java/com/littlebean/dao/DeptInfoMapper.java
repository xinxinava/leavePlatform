package com.littlebean.dao;

import com.littlebean.model.DeptInfo;
import com.littlebean.model.Node;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("deptDao")
public interface DeptInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int insert(DeptInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int insertSelective(DeptInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    DeptInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int updateByPrimaryKeySelective(DeptInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int updateByPrimaryKey(DeptInfo record);

    /**
     * 高级查询所有的部门信息
     */
    List<DeptInfo> queryDeptAll(DeptInfo deptInfo);

    /**
     * 根据父节点的Id查询记录信息
     */
    List<DeptInfo> queryByFId(@Param("parentId") Integer parentId);

    /**
     * 查询树
     */
    List<Node> queryDeptTree();
}