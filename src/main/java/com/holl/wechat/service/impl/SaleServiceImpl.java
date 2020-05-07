package com.holl.wechat.service.impl;

import com.holl.wechat.dao.SaleMapper;
import com.holl.wechat.model.Sale;
import com.holl.wechat.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleMapper saleMapper;

    @Override
    public List<Sale> findAll(){
        return saleMapper.findAll();
    }

    @Override
    public void publishSale(Sale sale){
        saleMapper.publishSale(sale);
    }

    @Override
    public void updateSale(Sale sale){
        saleMapper.updateSale(sale);
    }

    @Override
    public void cancelSale(long id){
        saleMapper.cancelSale(id);
    }

    @Override
    public List<Sale> findSaleByOpenId(String openId){ return saleMapper.findSaleByOpenId(openId); }

}
