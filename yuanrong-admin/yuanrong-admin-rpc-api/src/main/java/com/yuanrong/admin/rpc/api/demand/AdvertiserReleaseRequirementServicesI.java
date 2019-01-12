package com.yuanrong.admin.rpc.api.demand;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.demand.AdvertiserReleaseRequirement;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.util.BaseModel;

import java.util.List;
import java.util.Map;

public interface AdvertiserReleaseRequirementServicesI extends BaseServicesI<AdvertiserReleaseRequirement> {
   List<Map<String, Object>> lastDemand();

   /**
    * 需求列表查询
    * @param data
    * @param baseModel
    * @return
    */
   PageInfo<Map<String, Object>>  lastDemand(AdvertiserReleaseRequirement data, BaseModel baseModel,Map<String,Object> map);

   /**
    * 需求大厅、围观人数ImgUrl
    * @param map
    * @return
    */
   List<Map<String, Object>> getOnLookerImgUrl(Map<String, Object> map);

   /**
    * 发布需求信息同步到表中V1.1
    * @param map
    * @return
    */
   int addReleaseRequirement(Map<String, Object> map);

   /**
    * 发布需求：内容形式中间表
    * @param map
    * @return
    */
   int insertAdvertiserReleaseRequirement_contentForm(Map<String,Object> map);


}
