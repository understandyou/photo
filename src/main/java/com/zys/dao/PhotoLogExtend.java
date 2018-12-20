package com.zys.dao;

import com.zys.entitys.PhotoLog;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

public interface PhotoLogExtend extends PhotoLogMapper {
    /**
     *
     */
    public int updateEndTimeByUserId(PhotoLog photoLog);
}
