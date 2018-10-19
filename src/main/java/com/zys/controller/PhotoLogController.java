package com.zys.controller;

import com.zys.entitys.PhotoLog;
import com.zys.service.PhotoLogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.util.Date;

@Controller
public class PhotoLogController {
    @Autowired
    PhotoLogService photoLogService;

    @RequestMapping(value = "/addLog.action",method = RequestMethod.POST )
    public void addLog(PhotoLog photoLog,@RequestParam(required = true) String first){
        if(first.equals(("yes"))){
            photoLog.setStartDate(new Date());
            //sql文中会自动排除空值
            photoLogService.addPhotoLogSelective(photoLog);
        }else
        {
            photoLog.setRemain(new Date());
            //sql文中会自动排除空值
            photoLogService.addPhotoLogSelective(photoLog);
        }
    }
}
