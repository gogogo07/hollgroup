package com.holl.wechat.controller;

import com.holl.wechat.model.Deal;
import com.holl.wechat.model.Image;
import com.holl.wechat.model.Sale;
import com.holl.wechat.service.ImageSaleService;
import com.holl.wechat.service.ImageService;
import com.holl.wechat.service.SaleService;
import com.holl.wechat.util.DealDetail;
import com.holl.wechat.util.PublishDealData;
import com.holl.wechat.util.SaleData;
import com.holl.wechat.util.SaleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private static  final Logger LOGGER = Logger.getLogger(SaleController.class);
    @Autowired
    private SaleService saleService;

    @RequestMapping("/findAll")
    public Map<String, List<SaleData>> findAll() {
        LOGGER.debug("sale/findAll");
        Map<String, List<SaleData>> orders = new HashMap<>();
        List<SaleData> marketOrder = new ArrayList<>();
        int i=0;
        for (Sale sale : saleService.findAll()) {
            List<Image> images = imageSaleService.getImagesById(String.valueOf(sale.getId()));
            System.out.println(String.valueOf(sale.getId()));
            if (images.size() > 0) {
                String url = images.get(0).getImageName();
                marketOrder.add(new SaleData(i++, sale, url));
            } else {
                marketOrder.add(new SaleData(i++, sale, "404.jpg"));
            }
        }
        orders.put("marketOrder", marketOrder);
        return orders;
    }

    @RequestMapping("/publish")
    public Map publish(String openId,String title,String detail,float money,String phone) {
        LOGGER.debug("sale/publish?openId="+openId+"&title="+title+"&detail="+detail+"&money="+money+"&phone="+phone);
        //Global.lock.lock();

        //long idNumber=10000;

        System.out.println("openId:"+ openId);


        Sale sale = new Sale();
        //sale.setId(idNumber);
        sale.setOpenId(openId);
        sale.setTitle(title);
        sale.setDetail(detail);
        sale.setMoney(money);
        sale.setPhone(phone);

        Map map=new HashMap();
        saleService.publishSale(sale);
        map.put("msg","提交成功");
        long id = sale.getId();
        map.put("orderId",id);
        System.out.println("id:"+id);


        //Global.lock.unlock();
        return map;
    }


    @RequestMapping("/update")//获取发布者id、标题、描述、图片url、价格
    public Map update(long id,String title,String detail,float money,String phone) {
        LOGGER.debug("sale/update?id="+id+"&title="+title+"&detail="+detail+"&money="+money+"&phone="+phone);
        //Global.lock.lock();

        Sale sale = new Sale();
        sale.setId(id);
        sale.setTitle(title);
        sale.setDetail(detail);
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
        LOGGER.debug("sale/cancel?id="+id);
        //Global.lock.lock();

        Map map=new HashMap();
        saleService.cancelSale(id);
        map.put("msg","取消成功");

        //Global.lock.unlock();
        return map;
    }

    @RequestMapping("/findSaleByOrderId")
    public SaleDetail findSaleByOrderId(long id) {
        LOGGER.debug("sale/findSaleByOrderId?id="+id);
        Sale sale = saleService.findSaleByOrderId(id);
        List<Image> images = imageSaleService.getImagesById(String.valueOf(sale.getId()));
        return new SaleDetail(sale,images);


        //return saleService.findSaleByOrderId(id);
    }

    @RequestMapping("/findSaleByOpenId")
    public List<SaleData> findSaleByOpenId(String openId) {
        LOGGER.debug("sale/findSaleByOpenId?openId="+openId);

        List<SaleData> marketOrder = new ArrayList<>();
        int i=0;
        for (Sale sale : saleService.findSaleByOpenId(openId)) {
            List<Image> images = imageSaleService.getImagesById(String.valueOf(sale.getId()));
            System.out.println(String.valueOf(sale.getId()));
            if (images.size() > 0) {
                String url = images.get(0).getImageName();
                marketOrder.add(new SaleData(i++, sale, url));
            } else {
                marketOrder.add(new SaleData(i++, sale, "404.jpg"));
            }
        }

        return marketOrder;

    }


    @Autowired
    private ImageSaleService imageSaleService;

    @Value("${image.upload.path}")
    private String path;

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, String orderId, MultipartFile file) {
        LOGGER.debug("sale/upload?orderId="+orderId);
        try {
            request.setCharacterEncoding("utf-8");
            MultipartFile oldMultipartFile = file;
            if (!file.isEmpty()) {
                System.out.println(orderId);
                String fileName = "s" + String.valueOf(imageSaleService.getCount() + 10000) + ".jpg";
                Image image = new Image(Long.valueOf(orderId), fileName);
                imageSaleService.insertImage(image);
                String realPath = path + fileName;
                System.out.println(realPath);
                try{
                    System.out.println(realPath);
                    Thumbnails.of(file.getInputStream())
                    .scale(1f)
                    .outputQuality(0.2f)
                    .outputFormat("jpg")
                    .toFile(realPath);
                } catch (IOException e) {
                    oldMultipartFile.transferTo(new File(realPath));
                }
                
                return "上传成功";
            } else {
                System.out.println("文件为空");
                return "文件为空";
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/download", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] download(String fileName) {
        LOGGER.debug("sale/download?fileName="+fileName);
        String filePath = path + fileName;
        File file = new File(filePath);
        System.out.println(filePath);
        try{
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            return bytes;
        } catch (IOException e) {
            return null;
        }
    }

    @RequestMapping("/updateImage")
    public String updateImage(HttpServletRequest request, String orderId, MultipartFile file) {
        LOGGER.debug("sale/updateImage?orderId="+orderId);
        imageSaleService.clearImage(orderId);
        upload(request,orderId,file);
        return "图片更新成功";
    }
    
}
