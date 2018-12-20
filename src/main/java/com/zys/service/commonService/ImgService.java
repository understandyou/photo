package com.zys.service.commonService;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.poi.hpsf.Thumbnail;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 图片上传服务
 */
@Service("imgService")
public class ImgService {
    //设置缩略图的宽度和高度
    public static final int witdth = 100;
    public static final int heigth = 100;

    /**
     * 生成缩略图并返回图片相对地址
     *
     * @return
     */
    public String imageController(CommonsMultipartFile file, String uploadPath) throws IOException {
        File upload = new File(uploadPath + "/imgs");

//        if(!upload.isDirectory()){
//            upload.mkdir();
//        }
        //缩略图保存的绝对地址
//        String imgPath = uploadPath+"/"+"imgs"+file.getOriginalFilename();
        //生成缩略图
        Thumbnails.of(file.getInputStream()).size(witdth, heigth).toFile(uploadPath);
        //返回相对地址
        return "imgs/" + file.getOriginalFilename();

    }

    /**
     * 上传图片
     */
    public String uploadImg(CommonsMultipartFile file, String uploadPath) throws IOException {
        FileOutputStream fout = null;
        String newFileName = "";
        try {
            fout = new FileOutputStream("/usr/myFile/log.txt", true);
            fout.write("------1---------".getBytes());

            File upload = new File(uploadPath);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            fout.write("------2---------".getBytes());
            //创建输入流
            InputStream input = file.getInputStream();
            //使用uuid，的随机数生成基本上不重复的文件名称+文件后缀名-->组成新的文件达到修改文件名称的目的
            newFileName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

            fout.write("------3---------".getBytes());
            //获得客服端文件的原始名称
            String outPath = uploadPath + "/" + newFileName;

            fout.write("------4---------".getBytes());
            OutputStream output = new FileOutputStream(outPath);
            fout.write("------5---------".getBytes());
            //缓冲区1m
            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }
            fout.write("------6---------".getBytes());
            output.close();
            input.close();
        }catch (Exception e){
            throw e;
        }finally {
            if(fout!=null)
            fout.close();
        }
        //返回相对路径
        return "/imgs/" + newFileName;

    }

    /**
     * 获得文件夹下的所有文件名
     */
    public static List<String> GetPathFile(String path) {
        File file = new File(path);
        //文件路径
        List<String> files = new ArrayList<>();
        if (!file.isDirectory()) {
            //获得文件下的文件名称，不包含路径
            String[] fileList = file.list();
            for (int i = 0; i < fileList.length; i++) {
                File readFile = new File("/" + fileList[i]);
                if (readFile.isFile()) {
                    files.add(readFile.getName());
                }
            }

        }
        return files;
    }

}
