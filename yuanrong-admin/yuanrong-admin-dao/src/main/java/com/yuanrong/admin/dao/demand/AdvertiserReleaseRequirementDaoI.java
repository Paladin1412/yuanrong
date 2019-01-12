package com.yuanrong.admin.dao.demand;


import com.yuanrong.admin.bean.demand.AdvertiserReleaseRequirement;
import com.yuanrong.admin.dao.BaseDaoI;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface AdvertiserReleaseRequirementDaoI extends BaseDaoI<AdvertiserReleaseRequirement> {
    List<Map<String, Object>> lastDemand(Map<String,Object> map);

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
