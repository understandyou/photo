package com.zys.controller;

import com.zys.entitys.PhotoLog;
import com.zys.service.PhotoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

@Controller
public class PhotoLogController {
    @Autowired
    PhotoLogService photoLogService;

    @RequestMapping(value = "/addLog.action",method = RequestMethod.POST )
    public void addLog(PhotoLog photoLog){
        //sql文中会自动排除空值
        photoLogService.addPhotoLogSelective(photoLog);
    }
}
