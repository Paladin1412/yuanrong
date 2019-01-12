package com.yuanrong.admin.mall.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumDictInfoType;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/28.
 */
@Controller
@RequestMapping("dictInfo")
public class DictInfoController extends BaseController {

    @RequestMapping("getDictInfoByType")
    @ResponseBody
    public ResultTemplate getDictInfoByType(DictInfo dictInfo){
        if(dictInfo.getDictInfoType() == null || dictInfo.getDictInfoType().getIndex() < 0){
            return new ResultTemplate("参数错误" , null);
        }
       List<DictInfo> result =  dictInfoServicesI.getDictInfoByType(dictInfo.getDictInfoType().getIndex());
       if(CollectionUtil.size(result) <= 0){
           return new ResultTemplate("没有查到数据" , null);
       }else{
           return new ResultTemplate("" ,DictInfo.pareToJSONObject(result));
       }
    }

    /**
     * 前台商城—创作者页面获取字典信息（使用场景、内容形式、内容行业）
     */
    @RequestMapping("getAuthorDic")
    @ResponseBody
    public ResultTemplate getAuthorDic(){
        JSONObject result = new JSONObject();
        JSONArray useScenes = new JSONArray();
        JSONArray contentForm = new JSONArray();
        JSONArray contentType = new JSONArray();
//        JSONArray contentStyle = new JSONArray();
        //获取创作者使用场景
        List<Map<String, Object>> useScenesList = scenesServicesI.getAuthorScenes();
        if(CollectionUtils.size(useScenesList) <1){
            return new ResultTemplate("使用场景不存在",null);
        }
        useScenes.addAll(dealType(useScenesList));
        //根据作者数量获取内容形式字典
        List<Map<String, Object>> contentFormList = contentFormServicesI.getAuthorContentForm();
        if(CollectionUtils.size(contentFormList) <1){
            return new ResultTemplate("内容形式不存在",null);
        }
        contentForm.addAll(dealType(contentFormList));
        //根据作者数量获取内容行业字典
        List<Map<String, Object>> contentTypeList = dictInfoServicesI.getAuthorContentType(EnumDictInfoType.圆融分类.getIndex());
        if(CollectionUtils.size(contentTypeList) <1){
            return new ResultTemplate("内容领域不存在",null);
        }
        contentType.addAll(dealType(contentTypeList));
        //根据作者数量获取创作风格字典
//        List<Map<String, Object>> contentStyleList = lableServicesI.getAuthorContentStyle();
//        if(CollectionUtils.size(contentStyleList) <1){
//            writeString("内容领域不存在",null);
//            return;
//        }
//        contentStyle.addAll(dealType(contentStyleList));
        result.put("useScenes",useScenes);
        result.put("contentForm",contentForm);
        result.put("contentType" , contentType);
//        result.put("contentStyle" , contentStyle);
        return new ResultTemplate("",result);
    }

    /**
     * 处理分类中其它的位置
     * @param list
     * @return
     */
    public List<Map<String, Object>> dealType(List<Map<String, Object>> list){

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListFlag = new ArrayList<Map<String, Object>>();
        //首先排序将其它置于最后
        for (Map<String ,Object> map:list) {
            if("其它".equals(map.get("CategoryName"))){
                list.remove(map);
                list.add(map);
                break;
            }
        }
        //有作者信息的其它分类和无作者信息其它分类进行排序
        for (Map<String ,Object> map:list) {
            if("1".equals(map.get("flag").toString())){
                resultList.add(map);
            }else {
                resultListFlag.add(map);
            }
        }
        resultList.addAll(resultListFlag);
        return resultList;
    }
}
