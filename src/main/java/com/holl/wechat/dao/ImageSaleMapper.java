package com.holl.wechat.dao;

import java.util.List;

import com.holl.wechat.model.Image;

public interface ImageSaleMapper {

    public int insertImage(Image image);

    public Long getCount();

    public List<Image> getImagesById(String orderId);

    public void clearImage(String id);

}