package com.holl.wechat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holl.wechat.model.User;
import com.holl.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    public static String sendPost(String urlParam) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(urlParam);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");

        httpClient.executeMethod(postMethod);

        String result = postMethod.getResponseBodyAsString();

        postMethod.releaseConnection();
        return result;
    }
    public static String sendGet(String urlParam) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(urlParam);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.addRequestHeader("Content-Type", "application/json");

        httpClient.executeMethod(getMethod);

        String result = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return result;
    }

    private Map getOpenidAndSessionkey(String code) {
        String wxspAppid = "wx6dde262559f8bc01";
        String wxspSecret = "565605347be6a2fd3fd919bb0b2838ba";

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+wxspAppid+"&secret="+wxspSecret+"&js_code="+code+"&grant_type=authorization_code";

        try {
            String data = sendGet(url);

            ObjectMapper mapper = new ObjectMapper();
            Map jsonMap = mapper.readValue(data, Map.class);
            return jsonMap;
        } catch (IOException e) {
            return null;
        }
    }


    @RequestMapping(value="/login",method = RequestMethod.GET)
    public Map login(String code, String iv, String encryptedData) throws IOException {

        Map map = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        Map jsonMap = getOpenidAndSessionkey(code);
        ObjectMapper mapper = new ObjectMapper();

        String sessionKey = jsonMap.get("session_key").toString();
        try {
            String jsonData = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "utf-8");
            if (jsonData != null && jsonData.length() > 0) {
                Map userInfoJson = mapper.readValue(jsonData, Map.class);
                User user = new User();
                user.setCredit(0L);
                user.setId(userInfoJson.get("openId").toString());
                user.setName(userInfoJson.get("nickName").toString());
                user.setGender(Integer.valueOf(userInfoJson.get("gender").toString()));
                user.setCity(userInfoJson.get("city").toString());
                user.setProvince(userInfoJson.get("province").toString());
                user.setCountry(userInfoJson.get("country").toString());
                user.setAvatarUrl(userInfoJson.get("avatarUrl").toString());
                user.setLanguage(userInfoJson.get("language").toString());
                if (userService.insertUser(user) == 0) {
                    System.out.println(userService.updateUser(user));
                }
                map.put("openid", jsonMap.get("openid"));
                map.put("status", 200);
                map.put("msg", "login: OK");
            }
        } catch (Exception e) {
            map.put("status", 0);
            map.put("msg", "decode fail");
        }
        return map;
    }

    @RequestMapping("/user/getall")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @RequestMapping("/user/getUserById")
    public User getUserById(String id) {
        return userService.findUserById(id);
    }

}