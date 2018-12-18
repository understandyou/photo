package com.zys.service.impl;


import com.zys.dao.*;
import com.zys.entitys.ImageInfo;
import com.zys.entitys.LoginUser;
import com.zys.entitys.PhotoLog;
import com.zys.service.PhotoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("photoLogService")
public class PhotoLogServiceImpl implements PhotoLogService {
    @Autowired
    PhotoLogExtend photoLogMapper;
    @Autowired
    LoginUserExtend loginUserExtend;
    @Autowired
    ImageInfoMapper imageInfoMapper;
//    @Autowired
//    LoginUserMapper loginUserMapper;
    /**
     * 添加观看的日志不排空
     *
     * @param photoLog
     * @return
     */
    @Override
    public int addPhotoLog(PhotoLog photoLog){
        return photoLogMapper.insert(photoLog);
    }

    /**
     * 添加观看的日志排除空值
     * @param photoLog
     * @return 添加后的主键id
     */
    @Override
    public Integer addPhotoLogSelective(PhotoLog photoLog) {
        System.out.println("添加"+photoLog.getId());
        int isrows =  photoLogMapper.insertSelective(photoLog);
        System.out.println("添加完成"+photoLog.getId());
        return photoLog.getId();
    }

    /**
     * 查询所有日志
     * @return
     */
    @Override
    public List<PhotoLog> getAllPhotoLog() {
        return null;
    }

    /**
     * 检查是否存在,已更改sql不以主键查询，直接用名称查询
     *
     * @param name
     * @return
     */
    @Override
    public LoginUser CheckLoginName(String name) {
        return loginUserExtend.selectLoginName(name);
        //return loginUserMapper.selectByPrimaryKey(1) != null ? true : false;
    }

    /**
     * 根据名称查询图片ulr
     *
     * @param name
     * @return
     */
    @Override
    public List<ImageInfo> searchName(String name) {
        return imageInfoMapper.searchName(name);
    }

    /**
     * 根据用户id跟新结束时间
     * @return
     */
    @Override
    public int updateEndTimeByUserId(PhotoLog photoLog){
        if(photoLog==null){
            return 0;
        }else
        {
            //根据用户id更新结束时间
            return photoLogMapper.updateEndTimeByUserId(photoLog);
        }
    }
}
