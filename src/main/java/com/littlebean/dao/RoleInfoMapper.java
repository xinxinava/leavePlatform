package com.littlebean.dao;

import com.littlebean.model.RoleInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("roleDao")
public interface RoleInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int insert(RoleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int insertSelective(RoleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    RoleInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int updateByPrimaryKeySelective(RoleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    int updateByPrimaryKey(RoleInfo record);

    List<RoleInfo> queryRoleAll();
}