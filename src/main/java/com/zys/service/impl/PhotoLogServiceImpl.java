package com.zys.service.impl;

import com.zys.dao.PhotoLogMapper;
import com.zys.entitys.PhotoLog;
import com.zys.service.PhotoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("photoLogService")
public class PhotoLogServiceImpl implements PhotoLogService {
    @Autowired
    PhotoLogMapper photoLogMapper;
    @Override
    public int addPhotoLog(PhotoLog photoLog){
        return photoLogMapper.insert(photoLog);
    }

    @Override
    public int addPhotoLogSelective(PhotoLog photoLog) {
        return photoLogMapper.insertSelective(photoLog);
    }

    @Override
    public List<PhotoLog> getAllPhotoLog() {
        return null;
    }
}
