package com.holl.wechat.service;

import com.holl.wechat.model.Deal;

import java.util.List;

public interface DealService {

    List<Deal> selectAll();

    List<Deal> selectAllMyDeal();

    int publishDeal(Deal deal);

    int startDeal(String orderId, String type, String openId);

    int finishDeal(String id);
}
