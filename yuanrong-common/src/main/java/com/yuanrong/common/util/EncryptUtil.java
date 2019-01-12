package com.yuanrong.common.util;

import java.security.MessageDigest;

/**
* @Description:    加密类
* @Author:         ShiLinghuai
* @CreateDate:     2018/6/8 15:22
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/6/8 15:22
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public  class EncryptUtil {
    public static String  encryptString(String string){
       return MD5Util.md5(MD5Util.md5(string+"#yr"));
    }
    public static  void main(String[] args){
        System.out.println(encryptString("123456"));
        Integer a = 1;
        Integer b = 1;
        System.out.println(a.equals(b));
    }
}
