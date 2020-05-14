package com.holl.wechat.dao;

import com.holl.wechat.model.Sale;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleMapper {

    List<Sale> findAll();

    void publishSale(Sale sale);

    void updateSale(Sale sale);

    void cancelSale(long id);

    List<Sale> findSaleByOpenId(String openId);

}
