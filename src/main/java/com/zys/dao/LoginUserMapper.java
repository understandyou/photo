package com.zys.dao;

import com.zys.entitys.LoginUser;

public interface LoginUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_user
     *
     * @mbggenerated
     */
    int insert(LoginUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_user
     *
     * @mbggenerated
     */
    int insertSelective(LoginUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_user
     *
     * @mbggenerated
     */
    LoginUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LoginUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LoginUser record);
}