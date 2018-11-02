package com.zys.controller;

import com.zys.entitys.ImageInfo;
import com.zys.entitys.PhotoLog;
import com.zys.service.PhotoLogService;
import com.zys.service.commonService.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class PhotoLogController {
    /**
     * 所有操作类服务
     */
    @Autowired
    PhotoLogService photoLogService;
    /**
     * 图片上传服务
     */
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
    @ResponseBody
    @RequestMapping(value = "/addImages.action",method = RequestMethod.POST)
    public Map<String,Object> addImages(@RequestParam(value = "file") CommonsMultipartFile[] image, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        try {
            System.out.println("-------------------------------------------------------->");
            //根据相对路径获得绝对路径
            String url =  request.getServletContext().getRealPath("/");

            for (int i=0;i<image.length;i++) {
                //可以讲上传的CommonsMultipartFile文件对方复制一份到file中,根据demo看会自动写到磁盘中，未测试
//                image[i].transferTo(file);
                //获取上传后原图的相对地址
                String realUploadPath = imgService.uploadImg(image[i], request.getServletContext().getRealPath("/"));
            }
            //获取生成的缩略图的相对地址
            //String thumbImageUrl = imgService.imageController(image, realUploadPath);
            result.put("result", "ok");

        } catch (IOException e) {
            e.printStackTrace();
            result.put("result","no");
        }
        return result;
    }

    /**
     * 检查是否存在用户
     * 由于Ajax检查，所以在session放入传到客户端的key,回传回来在做对比
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkNameAutoority.action", method = RequestMethod.POST)
    public Map<String, Object> checkNameAuthority(String loginName, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        //检查用户是否存在
        boolean isExists = photoLogService.CheckLoginName(loginName);
        if (isExists) {
            //检查是否存在照片
            List<ImageInfo> imageInfos = photoLogService.searchName(loginName);
            map.put("imageExists", imageInfos.size() > 0 ? "true" : "false");
            Random random = new Random();
            String key = UUID.randomUUID().toString() + random.nextInt();
            //回发到客户端的key
            map.put("userKey", key);
            //将验证客户端的key放入sessio中
            session.setAttribute("userKey", key);
        }
        map.put("userExists", isExists ? true : false);
        return map;
    }

    /**
     * 显示主页
     *
     * @return
     */
    @RequestMapping("/showPhoto.action")
    public ModelAndView showPhoto(HttpSession session, String userKey) {
        ModelAndView modelAndView = new ModelAndView("index");
        if (session.getAttribute("userKey") != null) {
            boolean isok = session.getAttribute("userKey").equals(userKey);
            if (isok) {
                //验证是否通过
                modelAndView.addObject("verify", true);
                return modelAndView;
            }
        }
        modelAndView.addObject("verify", false);
        return modelAndView;
    }

}
