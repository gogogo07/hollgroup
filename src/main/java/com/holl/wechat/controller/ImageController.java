package com.holl.wechat.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.holl.wechat.model.Image;
import com.holl.wechat.service.ImageService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;


@RestController
@RequestMapping("/image")
public class ImageController {
    private static  final Logger LOGGER = Logger.getLogger(OrderController.class);
    
    @Autowired
    private ImageService imageService;

    @Value("${image.upload.path}")
    private String path;

    //
    @RequestMapping("setImage")
    public String setImage(String orderId, String imageName) {
        LOGGER.debug("image/setImage?orderId="+orderId+"&imageName="+imageName);
        Image image = new Image(Long.valueOf(orderId), imageName);
        if (imageService.insertImage(image) != 0) {
            return "上传成功";
        } else {
            return "上传失败";
        }
    }

    @RequestMapping("upload")
    public String upload(HttpServletRequest request, String orderId, MultipartFile file) {
        LOGGER.debug("image/upload?orderId="+orderId);
        try {
            request.setCharacterEncoding("utf-8");
            MultipartFile oldMultipartFile = file;
            if (!file.isEmpty()) {
                System.out.println(orderId);
                String fileName = String.valueOf(imageService.getCount() + 10000) + ".jpg";
                Image image = new Image(Long.valueOf(orderId), fileName);
                imageService.insertImage(image);
                String realPath = path + fileName;
                try{
                    System.out.println(realPath);
                    Thumbnails.of(file.getInputStream())
                    .scale(1f)
                    .outputQuality(0.2f)
                    .outputFormat("jpg")
                    .toFile(realPath);
                } catch (IOException e) {
                    oldMultipartFile.transferTo(new File(realPath));
                }
                
                return "上传成功";
            } else {
                System.out.println("文件为空");
                return "文件为空";
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/download", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] download(String fileName) {
        LOGGER.debug("image/download?fileName="+fileName);
        String filePath = path + fileName;
        File file = new File(filePath);
        System.out.println(filePath);
        try{
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            return bytes;
        } catch (IOException e) {
            return null;
        }
    }
}