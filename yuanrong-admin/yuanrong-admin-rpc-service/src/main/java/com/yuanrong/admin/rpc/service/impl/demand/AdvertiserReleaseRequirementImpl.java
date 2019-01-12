package com.yuanrong.admin.rpc.service.impl.demand;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.demand.AdvertiserReleaseRequirement;
import com.yuanrong.admin.rpc.api.demand.AdvertiserReleaseRequirementServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.DateUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdvertiserReleaseRequirementImpl extends BaseServicesAbstract<AdvertiserReleaseRequirement> implements AdvertiserReleaseRequirementServicesI {

    @Override
    public AdvertiserReleaseRequirement getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(AdvertiserReleaseRequirement object) {

    }

    @Override
    public List<AdvertiserReleaseRequirement> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

//    @Override
//    public List<Map<String, Object>> lastDemand(Map<String, Object> map) {
//        return this.advertiserReleaseRequirementDaoI.lastDemand(map);
//    }

    @Override
    public List<Map<String, Object>> lastDemand() {
        return null;
    }

    /**
     * 需求列表查询
     * @param data
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> lastDemand(AdvertiserReleaseRequirement data, BaseModel baseModel,Map<String, Object> map) {
        PageHelper.startPage(baseModel.getCp(),baseModel.getRows(),"a.CreateTime desc");

        List<Map<String, Object>> result = advertiserReleaseRequirementDaoI.lastDemand(map);
        //围观人数图片

        //结束时间
        for(int i=0 ;i<result.size();i++){
            try {
                //当前时间
                Date date = new Date();
                SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
                String nowTime = sdf.format(date);
                String endTime = result.get(i).get("EndTime").toString();
                result.get(i).put("EndTime",endTime);
                String createTime = result.get(i).get("CreateTime").toString();
                result.get(i).put("CreateTime",createTime);
                long endDay =  DateUtil.getDaySub(nowTime,endTime);
                result.get(i).put("endDay",endDay);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(result.get(i).containsKey("RecID") && map.get("flag").equals("yes")){
                int id =(Integer)result.get(i).get("RecID");
                Map<String,Object> imgMap = new HashMap<String, Object>();
                imgMap.put("advertiserReleaseRequirementId",id);
                List<Map<String, Object>> onLookerImgUrlList  = advertiserReleaseRequirementDaoI.getOnLookerImgUrl(imgMap);
                result.get(i).put("onLookerImgUrlList",onLookerImgUrlList);
            }

        }
        return new PageInfo(result);
    }

    @Override
    public List<Map<String, Object>> getOnLookerImgUrl(Map<String, Object> map) {
        return advertiserReleaseRequirementDaoI.getOnLookerImgUrl(map);
    }

    /**
     * 发布需求信息同步到表中V1.1
     * @param map
     * @return
     */
    @Override
    public int addReleaseRequirement(Map<String, Object> map) {
        advertiserReleaseRequirementDaoI.addReleaseRequirement(map);
        Integer  recID = Integer.parseInt(String.valueOf(  map.get("RecID")));
        System.out.println(recID);
        return recID;
//        return advertiserReleaseRequirementDaoI.addReleaseRequirement(map);
    }

    @Override
    public int insertAdvertiserReleaseRequirement_contentForm(Map<String, Object> map) {
        return advertiserReleaseRequirementDaoI.insertAdvertiserReleaseRequirement_contentForm(map);
    }


}

