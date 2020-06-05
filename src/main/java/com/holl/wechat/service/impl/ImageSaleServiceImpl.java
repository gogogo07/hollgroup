package com.holl.wechat.service.impl;

import java.util.List;


import com.holl.wechat.dao.ImageSaleMapper;
import com.holl.wechat.model.Image;
import com.holl.wechat.service.ImageSaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageSaleServiceImpl implements ImageSaleService {

    @Autowired
    private ImageSaleMapper imageSaleMapper;

    @Override
    public int insertImage(Image image) {
        return imageSaleMapper.insertImage(image);
    }

    @Override
    public Long getCount() {
        return imageSaleMapper.getCount();
    }

    @Override
    public List<Image> getImagesById(String orderId) {
        return imageSaleMapper.getImagesById(orderId);
    }

    @Override
    public void clearImage(String id){ imageSaleMapper.clearImage(id); }

}