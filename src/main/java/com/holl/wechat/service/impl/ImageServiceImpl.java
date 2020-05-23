package com.holl.wechat.service.impl;

import java.util.List;

import com.holl.wechat.dao.ImageMapper;
import com.holl.wechat.model.Image;
import com.holl.wechat.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public int insertImage(Image image) {
        return imageMapper.insertImage(image);
    }

    @Override
    public Long getCount() {
        return imageMapper.getCount();
    }

    @Override
    public List<Image> getImagesById(String orderId) {
        return imageMapper.getImagesById(orderId);
    }
    
}