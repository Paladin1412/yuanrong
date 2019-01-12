package com.yuanrong.admin.mall.controller.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDictInfoType;
import com.yuanrong.admin.Enum.EnumPlatform;
import com.yuanrong.admin.Enum.EnumPlatformIPAccountStatus;
import com.yuanrong.admin.Enum.EnumSex;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.account.ShortVideoPlatformInfo;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.seach.PlatformIpAccountSearchMall;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/5/30.
 */
@Controller
public class PlatformIPAccountController extends BaseController {
    @RequestMapping("/shortVideoCategoryInfo/getWeiboyiWeiXinCategory")
    @ResponseBody
    public ResultTemplate getWeiboyiWeiXinCategory(){
        List<DictInfo> dictInfoList = dictInfoServicesI.getDictInfoByType(EnumDictInfoType.圆融分类.getIndex());
        return  new ResultTemplate("" ,this.getArrayByDictInfo(dictInfoList));
    }

    @RequestMapping(value = "/ip/getWeixinUserList" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getWeixinUserList(PlatformIpAccountSearchMall data , BaseModel baseModel){
        data.setPlatFormId(12);
        data.setRegisterUserInfoId(getWebUser() == null ? null : getWebUser().getRecID());
        PageInfo<PlatformIPAccountResult> result = platformIPAccountServicesI.listMall(data , baseModel);
        JSONArray resultArray = new JSONArray();
        if(CollectionUtil.size(result.getList()) > 0){
            for(PlatformIPAccountResult ele : result.getList()){
                JSONObject object = new JSONObject();
                object.put("AvgReadNum" , ele.getAvgReadCount());
                object.put("WeiXinAccount_Id" , ele.getAccountID());
                object.put("WeiXinAccount" , ele.getName());
                object.put("QRCode" , ele.getqRcodeImageUrlLocal());
                object.put("HeadImgLocal" , ele.getHeadImageUrlLocal());
                object.put("AvgRetweetLikeNum" , ele.getAvgLikeCount());
                object.put("platformInfos" , ele.getPriceInfoJSONArray());
                object.put("Type" ,ele.getDictInfoCategory() == null ? "" : ele.getDictInfoCategory().getName());
                object.put("Fans", ele.getFans());
                object.put("accountOnlyId" , ele.getAccountOnlyId());
                object.put("YuanrongIndex",ele.getYrIndex());
                object.put("isAddCart" , ele.getIsAddCart());
                resultArray.add(object);
            }
        }
        return  new ResultTemplate(result , resultArray);
    }

    @RequestMapping("/shortVideoCategoryInfo/getWeiboyiWeiBoCategory")
    @ResponseBody
    public ResultTemplate getWeiboyiWeiBoCategory(){
        List<DictInfo> dictInfoList = dictInfoServicesI.getDictInfoByType(EnumDictInfoType.圆融分类.getIndex());
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(dictInfoList) > 0){
            for(DictInfo dictInfo : dictInfoList){
                JSONObject ele = new JSONObject();
                ele.put("CategoryID" , dictInfo.getId());
                ele.put("typeName" , dictInfo.getName());
                result.add(ele);
            }
        }
        return new ResultTemplate(result);
    }
    @RequestMapping(value = "/ip/getWeiBoUserLists" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getWeiBoUserLists(PlatformIpAccountSearchMall data , BaseModel baseModel){
        data.setPlatFormId(13);
        PageInfo<PlatformIPAccountResult> result = platformIPAccountServicesI.listMall(data , baseModel);
        JSONArray resultArray = new JSONArray();
        if(CollectionUtil.size(result.getList()) > 0){
            for(PlatformIPAccountResult ele : result.getList()){
                JSONObject object = new JSONObject();
                object.put("WeiBoAccount" , ele.getAccountID());
                object.put("WeiBoName" , ele.getName());
                object.put("Type" ,ele.getDictInfoCategory() == null ? "" : ele.getDictInfoCategory().getName());
                object.put("Gender" , ele.getSexEnum() == null ? "" : ele.getSexEnum().getIndex() == EnumSex.女.getIndex() ? 0 : ele.getSexEnum().getIndex());
                object.put("Introduction" , ele.getIntroduction());
                object.put("HeadURLLocal" , ele.getHeadImageUrlLocal());
                object.put("platformInfos" , ele.getPriceInfoJSONArray());
                object.put("Fans", ele.getFans());
                object.put("YuanrongIndex",ele.getYrIndex());
                object.put("AvgRetweetSendNum" , ele.getAvgForwardCount());
                object.put("AvgRetweetPostsNum" , ele.getAvgCommontCount());
                object.put("AvgRetweetLikeNum" , ele.getAvgLikeCount());
                object.put("DetailUrl" , ele.getIndexUrl());
                object.put("accountOnlyId" , ele.getAccountOnlyId());
                object.put("isAddCart" , ele.getIsAddCart());
                resultArray.add(object);
            }
        }
        return new ResultTemplate(result , resultArray);
    }

    @RequestMapping("/shortVideoCategoryInfo/getShortVideooCategory")
    @ResponseBody
    public ResultTemplate getShortVideooCategory(){
        List<DictInfo> dictInfoList = dictInfoServicesI.getDictInfoByType(EnumDictInfoType.短视频分类.getIndex());
        return  new ResultTemplate("" ,this.getArrayByDictInfo(dictInfoList));
    }

    private JSONArray getArrayByDictInfo(List<DictInfo> dictInfoList){
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(dictInfoList) > 0){
            for(DictInfo dictInfo : dictInfoList){
                JSONObject ele = new JSONObject();
                ele.put("CategoryID" , dictInfo.getId());
                ele.put("CategoryName" , dictInfo.getName());
                result.add(ele);
            }
        }
        return result;
    }

    @RequestMapping(value = "/ip/getIPLists" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getIPLists(PlatformIpAccountSearchMall data , BaseModel baseModel){
        if(data.getPlatFormId() == null){
            data.setPlatFormId(-1);
        }
        PageInfo<PlatformIPAccountResult> result = platformIPAccountServicesI.listMall(data , baseModel);
        JSONArray resultArray = new JSONArray();
        if(CollectionUtil.size(result.getList()) > 0){
            for(PlatformIPAccountResult ele : result.getList()){
                JSONObject object = new JSONObject();
                object.put("Account_avatar_url" , ele.getHeadImageUrlLocal());
                object.put("Pack_name" , ele.getName());
                object.put("platformIcoUrl" , ele.getShortVideoPlatformInfo().getIcoUrl());
                object.put("platform" , ele.getShortVideoPlatformInfo().getPlatformname());
                object.put("DetailUrl" , ele.getIndexUrl());
                object.put("platFormPriceArray" , ele.getPriceInfoJSONArray());
                object.put("Area_name" , ele.getAreaName());
                object.put("IndexValue",ele.getYrIndex());
                object.put("Average_play_num" , ele.getAvgPlayCount());
                object.put("Average_like_num" , ele.getAvgLikeCount());
                object.put("Average_posts_num" , ele.getAvgCommontCount());
                object.put("Gender" , ele.getSexEnum() == null ? "" : ele.getSexEnum().getIndex() == EnumSex.女.getIndex() ? 0 : ele.getSexEnum().getIndex());
                object.put("Followers_count", ele.getFans());
                object.put("accountOnlyId" , ele.getAccountOnlyId());
                object.put("isAddCart" , ele.getIsAddCart());
                resultArray.add(object);
            }
        }
        return  new ResultTemplate(result , resultArray);
    }

    @RequestMapping("/shortVideoPlatformInfo/allInfo")
    @ResponseBody
    public ResultTemplate getPlatFormInfo(){
        List<Map<String ,Object>> result = platformIPAccountServicesI.getShortVideoPlatformInfo();
        if(CollectionUtil.size(result) >0){
            return new ResultTemplate("" ,result);
        }else{
            return new ResultTemplate("没有未上架的账号",null);
        }
    }

    /**
     * 获取所有平台信息
     * @return
     */
    @RequestMapping("/platForm/getPlatFormInfo")
    @ResponseBody
    public ResultTemplate getPlatFormInfoAll(){
        List<ShortVideoPlatformInfo> result = platformIPAccountServicesI.getPlatFormInfo();
        if(CollectionUtil.size(result) > 0){
            return new ResultTemplate("" , ShortVideoPlatformInfo.getArrrayByList(result));
        }else{
            return new ResultTemplate("" , null);
        }
    }

    /**
     * 获取营销分发账号信息
     * @return
     */
    @RequestMapping("/platForm/getPlatformIPAccountByNameLikeSearch")
    @ResponseBody
    public ResultTemplate getPlatformIPAccountByNameLikeSearch(String demandSn,PlatformIPAccount data,BaseModel baseModel){
        if(getWebUser() == null  ){
            return new ResultTemplate("用户未登陆" ,null);
        }
        data.setRegisteredUserInfoID(getWebUser().getRecID());
        Demand demand = demandServicesI.getByDemandSn(demandSn);
        if(demand==null){
            return new ResultTemplate("该需求单号不存在" ,null);
        }
        //账号平台
        if(StringUtil.isNotBlank(demand.getPlatformName())){
          String[] platformNameArray =   demand.getPlatformName().split(",");
          String categorys ="0";
          if(platformNameArray.length>0){
              for(int i=0; i< platformNameArray.length; i++){
                if( platformNameArray[i].equals("微信")){
                    categorys = categorys+","+EnumPlatform.微信.getIndex();
                  }else if( platformNameArray[i].equals("微博")){
                    categorys = categorys+","+EnumPlatform.微博.getIndex();
                 }else if(platformNameArray[i].equals("视频") || platformNameArray[i].equals("短视频")){
                    categorys = categorys + "," + EnumPlatform.短视频.getIndex();
                }
              }
             Map<String,Object> map = platformIPAccountServicesI.getPlatformIdsByCategorys(categorys);
             data.setCategorys(map.get("platformIDs").toString());
          }
        }
        //已报名
        List<OrderInfoSeller> osList =  orderInfoSellerServicesI.getReferIdByDemandSn(demandSn,getWebUser().getRecID());

        PageInfo<PlatformIPAccount> pageInfo =  platformIPAccountServicesI.getPlatformIPAccountByNameLikeSearch(data,baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(pageInfo.getList()) > 0){
            for(PlatformIPAccount platformIPAccount :pageInfo.getList()){
                    JSONObject object = new JSONObject();
                    object.put("fans",platformIPAccount.getFans());
                    object.put("headImageUrlLocal",platformIPAccount.getHeadImageUrlLocal());
                    object.put("name",platformIPAccount.getName());
                    object.put("platformName",platformIPAccount.getPlatformName());
                    object.put("icoUrl",platformIPAccount.getIcoUrl());
                    object.put("accountID",platformIPAccount.getAccountID());
                    object.put("id",platformIPAccount.getId());

                    for(OrderInfoSeller os : osList){
                        JSONArray jsonArray = new JSONArray();
                        if(os.getReferId().equals(platformIPAccount.getId())){
                            object.put("isSign",true);
                            object.put("usableDate",os.getUsableDate());
                            List<OrderInfoOffer> oofferList = orderInfoOfferServicesI.getByOrderInfoSellerId(os.getOrderInfoSellerId());

                            if(oofferList.size()>0){
                                for( OrderInfoOffer  orderInfoOffer : oofferList){
                                    JSONObject jb = new JSONObject();
                                    jb.put("price",orderInfoOffer.getPrice());
                                    jb.put("priceName",orderInfoOffer.getPriceName());
                                    jsonArray.add(jb);
                                }
                            }
                            object.put("expectOffer",jsonArray);
                            break;
                        }else {
                            String[] expectOffer = demand.getExpectOffer().split("_-,-_");
                            if(expectOffer.length>0){
                                for(int i=0; i<expectOffer.length;i++){
                                    JSONObject jb = new JSONObject();
                                    jb.put("priceName",expectOffer[i]);
                                    jsonArray.add(jb);
                                }

                            }

                            object.put("isSign",false);
                            object.put("expectOffer",jsonArray);
                        }
                    }
                    //处理报名记录为空的情况
                    if(osList.size()==0){
                        JSONArray jsonArray = new JSONArray();
                        String[] expectOffer = demand.getExpectOffer().split("_-,-_");
                        if(expectOffer.length>0){
                            for(int i=0; i<expectOffer.length;i++){
                                JSONObject jb = new JSONObject();
                                jb.put("priceName",expectOffer[i]);
                                jsonArray.add(jb);
                            }
                        }
                        object.put("isSign",false);
                        object.put("expectOffer",jsonArray);
                    }
                    result.add(object);
            }
        }
        return  new ResultTemplate(pageInfo,result);
    }
}
