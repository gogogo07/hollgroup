package com.holl.wechat.service;

import com.holl.wechat.model.Sale;

import java.util.List;

public interface SaleService {

    List<Sale> findAll();

    void publishSale(Sale sale);

    void updateSale(Sale sale);

    void cancelSale(long id);

    List<Sale> findSaleByOpenId(String openId);

    Sale findSaleByOrderId(long id);
}
