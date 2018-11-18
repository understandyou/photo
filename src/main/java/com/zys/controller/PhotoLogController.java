package com.zys.controller;

import com.zys.entitys.ImageInfo;
import com.zys.entitys.LoginUser;
import com.zys.entitys.PhotoLog;
import com.zys.service.ImageInfoService;
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
    //图片信息操作
    @Autowired
    ImageInfoService imageInfoService;

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
            Integer userId = Integer.valueOf(request.getParameter("userId"));

            //根据相对路径获得绝对路径
            String url =  request.getServletContext().getRealPath("/");
            for (int i=0;i<image.length;i++) {
                //可以讲上传的CommonsMultipartFile文件对方复制一份到file中,根据demo看会自动写到磁盘中，未测试
//                image[i].transferTo(file);
                    //获取上传后原图的相对地址
                String realUploadPath = imgService.uploadImg(image[i], request.getServletContext().getRealPath("/"));
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setLoginId(userId);
                imageInfo.setImgUrl(realUploadPath);
                //将绝对路径保存到数据库
                int imageSave = imageInfoService.addImageInfo(imageInfo);
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
    public Map<String, Object> checkNameAuthority(String loginName, HttpSession session,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //检查用户是否存在
        LoginUser loginUser = photoLogService.CheckLoginName(loginName);
        if (loginUser!=null) {
            //检查是否存在照片
            List<ImageInfo> imageInfos = photoLogService.searchName(loginName);
            map.put("imageExists", imageInfos.size() > 0 ? true : false);
            Random random = new Random();
            String key = UUID.randomUUID().toString() + random.nextInt();
            //回发到客户端的key
            map.put("userKey", key);
            map.put("userId", loginUser.getId());
            //将验证客户端的key放入sessio中
            session.setAttribute("userKey", key);
        }
        map.put("userExists", loginUser!=null ? true : false);
        return map;
    }

    /**
     * 显示主页，跳转
     *
     * @return
     */
    @RequestMapping(value = "/showPhoto.action",method = RequestMethod.POST)
    public ModelAndView showPhoto(HttpSession session, String userKey,Integer userId) {
        ModelAndView modelAndView = new ModelAndView("index");
        if (session.getAttribute("userKey") != null) {
            boolean isok = session.getAttribute("userKey").equals(userKey);
            if (isok) {
                //验证是否通过key一致
                //查询用户的图片
                List<ImageInfo> images = imageInfoService.searchIdToUrl(userId);
                modelAndView.addObject("images", images);
                modelAndView.addObject("verify", true);
                return modelAndView;
            }
        }
        modelAndView.addObject("verify", false);
        return modelAndView;
    }

}
