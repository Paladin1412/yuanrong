package com.yuanrong.admin.bean.resources;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.CollectionUtil;

/**
 * 用户图片的实体类
 *
 * @author MDA
 *
 */
public class UserImages extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private int userImagesId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 注册用户
     * 注册用户
     */
    private RegisteredUserInfo registeredUserInfo;
   
    /**
     * 图片地址
     * 图片地址
     */
    private String imgUrl;



    public int getUserImagesId() {
        return this.userImagesId;
    }
    public void setUserImagesId(int userImagesId) {
        this.userImagesId = userImagesId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public RegisteredUserInfo getRegisteredUserInfo() {
        return this.registeredUserInfo;
    }
    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo  = registeredUserInfo;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public static JSONArray getArray(List<UserImages> userImagesPageInfo){
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(userImagesPageInfo) > 0){
            for(UserImages userImages : userImagesPageInfo){
                JSONObject object = new JSONObject();
                object.put("imgUrl" , userImages.getImgUrl());
                result.add(object);
            }
        }
        return result;
    }
}
