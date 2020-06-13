package com.holl.wechat.service;

import com.holl.wechat.model.Deal;

import java.util.List;

public interface DealService {

    List<Deal> selectAll();

    List<Deal> selectPublishedDeal();

    List<Deal> selectPublishedDealByType(String type); 

    List<Deal> selectMyPublishedDeal(String from_id);

    Deal selectDealById(String order_id);

    List<Deal> selectMyAcceptDeal(String to_id);

    List<Deal> selectHistoryDeal(String from_id);

    Deal selectHistoryDealById(String orderId);

    int publishDeal(Deal deal);

    int startDeal(Long orderId, String openId);

    int finishDeal(String id);

    int cancelDeal(String id);
}
