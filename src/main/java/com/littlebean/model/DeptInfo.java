package com.littlebean.model;

import java.io.Serializable;

public class DeptInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept_info.id
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept_info.name
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept_info.parent_id
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept_info.type
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept_info.sort
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept_info.status
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dept_info
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept_info.id
     *
     * @return the value of dept_info.id
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept_info.id
     *
     * @param id the value for dept_info.id
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept_info.name
     *
     * @return the value of dept_info.name
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept_info.name
     *
     * @param name the value for dept_info.name
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept_info.parent_id
     *
     * @return the value of dept_info.parent_id
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept_info.parent_id
     *
     * @param parentId the value for dept_info.parent_id
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept_info.type
     *
     * @return the value of dept_info.type
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept_info.type
     *
     * @param type the value for dept_info.type
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept_info.sort
     *
     * @return the value of dept_info.sort
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept_info.sort
     *
     * @param sort the value for dept_info.sort
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept_info.status
     *
     * @return the value of dept_info.status
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept_info.status
     *
     * @param status the value for dept_info.status
     *
     * @mbggenerated Fri May 20 16:29:05 CST 2022
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


}