package com.zys.dao;

import com.zys.entitys.PhotoLog;

public interface PhotoLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo_log
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo_log
     *
     * @mbggenerated
     */
    int insert(PhotoLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo_log
     *
     * @mbggenerated
     */
    int insertSelective(PhotoLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo_log
     *
     * @mbggenerated
     */
    PhotoLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo_log
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PhotoLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo_log
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PhotoLog record);
}