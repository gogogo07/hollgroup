package com.holl.wechat.dao;

import com.holl.wechat.model.Deal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealMapper {

    List<Deal> selectAll();

    List<Deal> selectFromDeal();

    List<Deal> selectToDeal();

    int publishDeal(Deal deal);

    int startFromDeal(String id, String openId);

    int startToDeal(String id, String openId);

    int deleteDeal(String id);

    int insertIntoOld(String id);
}
