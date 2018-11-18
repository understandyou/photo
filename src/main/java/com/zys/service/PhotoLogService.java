package com.zys.service;


import com.zys.entitys.ImageInfo;
import com.zys.entitys.LoginUser;
import com.zys.entitys.PhotoLog;

import java.util.List;

public interface PhotoLogService {
    int addPhotoLog(PhotoLog photoLog);
    int addPhotoLogSelective(PhotoLog photoLog);
    List<PhotoLog> getAllPhotoLog();

    LoginUser CheckLoginName(String name);

    /**
     * 根据名称查询图片ulr
     *
     * @param name
     * @return
     */
    List<ImageInfo> searchName(String name);
}
