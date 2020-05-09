package com.holl.wechat.util;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
public class AesCbcUtil { 
    static {
        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {
    //initialize();

    //被加密的数据
        byte[] dataByte = Base64.decodeBase64(data.getBytes());
    //加密秘钥
        byte[] keyByte = Base64.decodeBase64(key.getBytes());
    //偏移量
        byte[] ivByte = Base64.decodeBase64(iv.getBytes());
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            return null;
        } catch (Exception e) {
            System.out.println("e");
            return null;
        }
    }
}