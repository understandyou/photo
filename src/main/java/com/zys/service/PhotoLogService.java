package com.zys.service;


import com.zys.entitys.PhotoLog;

import java.util.List;

public interface PhotoLogService {
    int addPhotoLog(PhotoLog photoLog);
    List<PhotoLog> getAllPhotoLog();
}
