package com.holl.wechat.service;

import java.util.List;

import com.holl.wechat.model.Image;

public interface ImageService {
    
    public int insertImage(Image image);

    public Long getCount();

    public List<Image> getImagesById(String orderId);
}