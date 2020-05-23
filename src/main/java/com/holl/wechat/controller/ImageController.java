package com.holl.wechat.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.holl.wechat.model.Image;
import com.holl.wechat.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    private final String path = "C:\\Users\\ouyuj\\Desktop\\tmp\\接收的图片\\";

    @RequestMapping("upload")
    public String upload(HttpServletRequest request, String orderId, MultipartFile file) {
        try {
            request.setCharacterEncoding("utf-8");
            if (!file.isEmpty()) {
                System.out.println(orderId);
                String fileName = String.valueOf(imageService.getCount() + 10000) + ".jpg";
                Image image = new Image(Long.valueOf(orderId), fileName);
                imageService.insertImage(image);
                String realPath = path + fileName;
                file.transferTo(new File(realPath));
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
        String filePath = path + fileName;
        File file = new File(filePath);
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