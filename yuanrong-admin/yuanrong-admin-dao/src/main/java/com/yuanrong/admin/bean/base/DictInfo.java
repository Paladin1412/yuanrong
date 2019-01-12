package com.yuanrong.admin.bean.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumDictInfoType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.util.EnumUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhonghang on 2018/4/28.
 * 数据字典
 */
public class DictInfo extends BaseBean implements Serializable{
    private int id;
    private int dictID;
    private int type ;// 字典类型
    private EnumDictInfoType dictInfoType;
    private String name ; //	名称
    private  int orderNum ; //	排序

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDictID() {
        return dictID;
    }

    public void setDictID(int dictID) {
        this.dictID = dictID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.dictInfoType = (EnumDictInfoType) EnumUtil.valueOf(EnumDictInfoType.class , type);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public EnumDictInfoType getDictInfoType() {
        return dictInfoType;
    }

    public void setDictInfoType(EnumDictInfoType dictInfoType) {
        this.type = dictInfoType.getIndex();
        this.dictInfoType = dictInfoType;
    }

    public static JSONArray pareToJSONObject(List<DictInfo> result){
        JSONArray jsonArray = new JSONArray();
        for(DictInfo data : result){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id" , data.getId());
            jsonObject.put("name" , data.getName());
            jsonArray.add(jsonObject);
        }
        return  jsonArray;
    }
}
