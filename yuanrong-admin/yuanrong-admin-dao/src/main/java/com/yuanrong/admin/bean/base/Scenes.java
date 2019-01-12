package com.yuanrong.admin.bean.base;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.BaseBean;

/**
 * 使用场景的实体类
 *
 * @author MDA
 *
 */
public class Scenes extends BaseBean implements java.io.Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer Id;
    /*****自定义属性区域begin******/
   
   
    /**
     * 场景名称
     * 场景名称
     */
    private String scenesName;
   
    /**
     * 创建时间
     * 创建时间
     */
    private String createTime;
   
    /**
     * 使用状态
     * 使用状态
     */
    private Integer statusValue;
    public Integer getId() {
        return this.Id;
    }
    public void setId(Integer Id) {
        this.Id = Id;
    }
    /*****自定义属性区域begin.get/set******/
   
    public String getScenesName() {
        return this.scenesName;
    }
    public void setScenesName(String scenesName) {
        this.scenesName = scenesName;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public Integer getStatusValue() {
        return this.statusValue;
    }
    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }

    /**
     * 封装数据——使用场景
     * @param scenes
     * @return JSONObject
     */
    public static JSONObject packageScenes(Scenes scenes){
        JSONObject json = new JSONObject();
        json.put("id",scenes.getId());
        json.put("name",scenes.getScenesName());
        return json;
    }

    /**
     * 封装数据——使用场景
     * @param scenesList
     * @return JSONArray
     */
    public static JSONArray packageScenes(List<Scenes> scenesList){
        for (Scenes sce : scenesList){
            if(sce.getScenesName().equals("其它")){
                scenesList.remove(sce);
                scenesList.add(sce);
                break;
            }
        }
        JSONArray result= new JSONArray();
        for (Scenes scenes : scenesList){
            result.add(packageScenes(scenes));
        }
        return result;
    }
}
