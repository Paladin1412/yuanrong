package com.yuanrong.admin.bean.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.BaseBean;

import java.util.List;

/**
 * 内容形式的实体类
 *
 * @author MDA
 *
 */
public class ContentForm extends BaseBean implements java.io.Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer Id;
    /*****自定义属性区域begin******/
   
   
    /**
     * 内容形式名称
     * 内容形式名称
     */
    private String contentFormName;
   
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
    public void setId(Integer id) {
        this.Id = id;
    }
    /*****自定义属性区域begin.get/set******/
   
    public String getContentFormName() {
        return this.contentFormName;
    }
    public void setContentFormName(String contentFormName) {
        this.contentFormName = contentFormName;
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
     * 封装数据——内容形式
     * @param contentForm
     * @return JSONObject
     */
    public static JSONObject packageContentForm(ContentForm contentForm){
        JSONObject json = new JSONObject();
        json.put("id",contentForm.getId());
        json.put("name",contentForm.getContentFormName());
        return json;
    }

    /**
     * 封装数据——内容形式
     * @param contentFormList
     * @return JSONArray
     */
    public static JSONArray packageContentForm(List<ContentForm> contentFormList){
        for (ContentForm cnt : contentFormList){
            if (cnt.getContentFormName().equals("其它")){
                contentFormList.remove(cnt);
                contentFormList.add(cnt);
                break;
            }
        }
        JSONArray result = new JSONArray();
        for (ContentForm cont : contentFormList){
            result.add(packageContentForm(cont));
        }
        return result;
    }
}
