package com.holl.wechat.controller;

import com.holl.wechat.model.Sale;
import com.holl.wechat.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @RequestMapping("/findAll")
    public List<Sale> findAll() {
        return saleService.findAll();
    }

    @RequestMapping("/publish")//TODO：idnumber要自增
    public Map publish(String openId,String title,String detail,String picture,float money,String phone) {
        //Global.lock.lock();

        //long idNumber=10000;

        Sale sale = new Sale();
        //sale.setId(idNumber);
        sale.setOpenId(openId);
        sale.setTitle(title);
        sale.setDetail(detail);
        sale.setPicture(picture);
        sale.setMoney(money);
        sale.setPhone(phone);

        Map map=new HashMap();
        saleService.publishSale(sale);
        map.put("msg","提交成功");



        //Global.lock.unlock();
        return map;
    }


    @RequestMapping("/update")//获取发布者id、标题、描述、图片url、价格
    public Map update(long id,String title,String detail,String picture,float money,String phone) {
        //Global.lock.lock();

        Sale sale = new Sale();
        sale.setId(id);
        sale.setTitle(title);
        sale.setDetail(detail);
        sale.setPicture(picture);
        sale.setMoney(money);
        sale.setPhone(phone);

        Map map=new HashMap();
        saleService.updateSale(sale);
        map.put("msg","修改成功");


        //Global.lock.unlock();
        return map;
    }

    @RequestMapping("/cancel")
    public Map cancel(long id) {
        //Global.lock.lock();

        Map map=new HashMap();
        saleService.cancelSale(id);
        map.put("msg","取消成功");

        //Global.lock.unlock();
        return map;
    }

    @RequestMapping("/findSaleByOpenId")
    public List<Sale> findSaleByOpenId(String openId) {
        System.out.println(openId);
        return saleService.findSaleByOpenId(openId);
    }


}
