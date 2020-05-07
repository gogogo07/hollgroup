package com.holl.wechat.service;

import com.holl.wechat.model.Deal;

import java.util.List;

public interface DealService {

    List<Deal> selectAll();

    List<Deal> selectPublishedDeal();

    List<Deal> selectMyPublishedDeal(String from_id);

    List<Deal> selectMyUnfinishDeal(String from_id);

    List<Deal> selectOtherUnfinishDeal(String to_id);

    List<Deal> selectHistoryDeal(String from_id);

    int publishDeal(Deal deal);

    int startDeal(Long orderId, String openId);

    int finishDeal(String id);
}
