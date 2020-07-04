package com.holl.wechat.service.impl;

import com.holl.wechat.dao.DealMapper;
import com.holl.wechat.model.Deal;
import com.holl.wechat.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    @Autowired
    DealMapper dealMapper;

    @Cacheable(value = "deal", key = "all")
    @Override
    public List<Deal> selectAll() {
        return dealMapper.selectAll();
    }

    @Override
    public List<Deal> selectPublishedDeal() {
        return dealMapper.selectPublishedDeal();
    }

    @Override
    public List<Deal> selectPublishedDealByType(String type) {
        return dealMapper.selectPublishedDealByType(type);
    }

    @Override
    public Deal selectDealById(String order_id) {
        Deal deal = dealMapper.selectDealById(order_id);
        if (deal == null) {
            deal = dealMapper.selectDealWithoutAcceptById(order_id);
        }
        return deal;
    }

    @Override
    public List<Deal> selectMyPublishedDeal(String from_id) {
        List<Deal> deals = dealMapper.selectMyPublishedDealWithoutAccept(from_id);
        deals.addAll(dealMapper.selectMyPublishedDeal(from_id));
        return deals;
    }

    @Override
    public List<Deal> selectMyAcceptDeal(String to_id) {
        return dealMapper.selectMyAcceptDeal(to_id);
    }

    @Override
    public List<Deal> selectHistoryDeal(String from_id) {
        return dealMapper.selectHistoryDeal(from_id);
    }

    @Override
    public Deal selectHistoryDealById(String orderId) {
        return dealMapper.selectHistoryDealById(orderId);
    }

    @Override
    public int publishDeal(Deal deal) {
        return dealMapper.publishDeal(deal);
    }

    @Override
    public int startDeal(Long orderId, String openId) {
        return dealMapper.startDeal(orderId, openId);
    }

    @Override
    public int finishDeal(String id) {
        Long my_id = Long.parseLong(id);
        int insert = dealMapper.insertIntoOld(my_id);
        int delete = dealMapper.deleteDeal(my_id);
        if (insert == 1 && delete == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int cancelDeal(String id) {
        Long my_id = Long.parseLong(id);
        int delete = dealMapper.deleteDeal(my_id);
        if (delete == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
