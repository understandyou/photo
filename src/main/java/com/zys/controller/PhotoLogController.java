package com.zys.controller;

import com.zys.entitys.PhotoLog;
import com.zys.service.PhotoLogService;
import com.zys.service.commonService.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Controller
public class PhotoLogController {
    @Autowired
    PhotoLogService photoLogService;
    @Autowired
    ImgService imgService;

    @RequestMapping(value = "/addLog.action",method = RequestMethod.POST )
    public void addLog(PhotoLog photoLog, @RequestParam(required = true) String first){
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

    /**
     * 上传图片
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/addImages.action",method = RequestMethod.POST)
    public String addImages(@RequestParam(value = "image") CommonsMultipartFile[] image, HttpServletRequest request){
        try {
            System.out.println("-------------------------------------------------------->");
            //根据相对路径获得绝对路径
            String url =  request.getServletContext().getRealPath("/");

            for (int i=0;i<image.length;i++) {
                //获取上传后原图的相对地址
                String realUploadPath = imgService.uploadImg(image[i], request.getServletContext().getRealPath("/"));
            }
            //获取生成的缩略图的相对地址
            //String thumbImageUrl = imgService.imageController(image, realUploadPath);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/index";
    }

}
