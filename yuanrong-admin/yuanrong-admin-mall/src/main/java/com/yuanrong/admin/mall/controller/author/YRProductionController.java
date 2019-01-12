package com.yuanrong.admin.mall.controller.author;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.result.YRProductionLikeResult;
import com.yuanrong.admin.result.YRProductionResult;
import com.yuanrong.admin.seach.YRProductionListParam;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.FilterHtmlUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/7/9.
 */
@Controller
public class YRProductionController extends BaseController {
    @RequestMapping(value = "/author/yrProduction_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(YRProductionListParam data , BaseModel baseModel){
        data.setRegisterUserInfoId(getWebUser() == null ? null : getWebUser().getRecID());
        PageInfo<YRProductionResult> pageInfo = yRProductionServicesI.listMall(data , baseModel);
        JSONArray array = new JSONArray();
        if(CollectionUtil.size(pageInfo.getList()) > 0){
            for(YRProductionResult yrProduction : pageInfo.getList()){
                JSONObject ele = new JSONObject();
                ele.put("recId" , yrProduction.getRecId());
                ele.put("coverLocalUrl" , yrProduction.getCoverLocalUrl());
                ele.put("contentFormName" , yrProduction.getContentForm() == null ? "" : yrProduction.getContentForm().getContentFormName());
                ele.put("publishPlatform" , yrProduction.getPublishPlatform());
                ele.put("wordNum" , yrProduction.getWordNum());
                ele.put("imgNum" , yrProduction.getImgNum());
                ele.put("title" , yrProduction.getTitle());
                ele.put("contentEvaluationScore",yrProduction.getContentEvaluationScore());
                JSONObject authorInfo = new JSONObject();
                authorInfo.put("authorNickname" , yrProduction.getYrAuthor().getAuthorNickname());
                authorInfo.put("authorImg" , yrProduction.getYrAuthor().getAuthorImg());
                authorInfo.put("contentform" , yrProduction.getContentform());
                authorInfo.put("prnum" , yrProduction.getPrnum());
                authorInfo.put("yrAuthorCategory" , yrProduction.getYrAuthorCategory());
                authorInfo.put("accessNum" , yrProduction.getYrAuthor().getAccessNum());
                authorInfo.put("isAnonymous" , yrProduction.getYrAuthor().getIsAnonymous());
                authorInfo.put("yrAuthorId" , yrProduction.getYrAuthorId());
                authorInfo.put("yrAuthorStatus" , yrProduction.getYrAuthor().getAuthorStatus());
                authorInfo.put("yrAuthorStatusValue" , yrProduction.getYrAuthor().getEnumAuthorStatus().getName());
                ele.put("authorInfo" , authorInfo);
                ele.put("createdTime" ,yrProduction.getUpTime() ==null ? "" : DateUtil.format(yrProduction.getUpTime(),"yyyy-MM-dd HH:mm:ss"));
                ele.put("content" , FilterHtmlUtil.before200Char(yrProduction.getLocalcontent()));
                ele.put("yrCategory" , yrProduction.getYrCategory() == null ? "" : yrProduction.getYrCategory().getName());
                ele.put("marketingScene" , yrProduction.getMarketingScene());
                ele.put("tags" , yrProduction.getTags());
                if(getWebUser() == null){//用户未登陆
                    ele.put("price" ,"--");
                }else {
                    ele.put("price" , yrProduction.getSellPrice());
                }
                ele.put("yrIndex" , yrProduction.getContentScore());
                ele.put("sellCount" , yrProduction.getSellcount());
                ele.put("isAddCart" , yrProduction.getIsAddCart());
                ele.put("originalScore",yrProduction.getOriginalScore());
//                Object accsNum = redisTemplate.opsForValue().get(YRProduction.class.getName()+":accessNum:"+yrProduction.getRecId());
//                ele.put("accessNum",accsNum == null ? yrProduction.getAccessNum() : accsNum);
                ele.put("contentEvaluationScore" , yrProduction.getContentEvaluationScore()==null?0:yrProduction.getContentEvaluationScore());
                array.add(ele);
            }
        }
        return new ResultTemplate(pageInfo , array);
    }
    @RequestMapping(value = "/author/yrProduction/hotSearch" )
    @ResponseBody
    public ResultTemplate getHotSearch(){
        return new ResultTemplate(yRProductionServicesI.getHotSearch());
    }
    /**
     * 获取营销场景列表
     */
    @RequestMapping("/author/getMarketingScene")
    @ResponseBody
    public ResultTemplate getMarketingScene(){
        List<Map> result = yRProductionServicesI.getMarketingScene();
        if(CollectionUtil.size(result) > 0 ){
            return new ResultTemplate("" , result);
        } else {
            return new ResultTemplate();
        }
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，对应的作品的访问次数+1
    */
    @RequestMapping("/author/getProductInfo")
    @ResponseBody
    public ResultTemplate getProductInfo(YRProductionListParam data , BaseModel baseModel){
        if(StringUtils.isEmpty(data)){
            return new ResultTemplate("作品ID不存在");
        }
        if(StringUtils.isEmpty(data.getYpRecId())){
            return new ResultTemplate("作品ID不存在");
        }
        data.setRegisterUserInfoId(getWebUser() == null ? null : getWebUser().getRecID());
        YRProduction yRProduction = yRProductionServicesI.getByProductionId(data);
        if(yRProduction==null){
            return new ResultTemplate("作品不存在");
        }
        //根据作者id获取作者的作品数
        Integer yrAuthorId =  yRProduction.getYrAuthorId();
        Integer num=0;
        if (!StringUtils.isEmpty(yrAuthorId)) {
             num = yRProductionServicesI.getAuthorProductCount(yrAuthorId,baseModel);
        }
        YRAuthor yRAuthor = yRAuthorServicesI.getById(yrAuthorId);
        if(yRAuthor==null){
            return new ResultTemplate("创作者不存在");
        }
        //根据作者id获取作者最近的5篇已发布文章；当前文章除外
        List<YRProduction> productionList = yRProductionServicesI.getAuthorProductsList(yRProduction,baseModel);


        //判断作品类型(0未发布，1已发布)
        if(yRProduction.getPublishStatusIndex().equals("0")){
            //作品是否上架（0下架，1待审核，2上架，4售罄）
            if(yRProduction.getYrProductionStatusIndex().equals("0") || yRProduction.getYrProductionStatusIndex().equals("1")){
                //作品作者是本人
                if(StringUtils.isEmpty(getWebUser())){
                    return new ResultTemplate("作品未发布且未登录，无法查看");
                }
                Integer loginUserId =getWebUser().getRecID();
                //是否作者本人
                if(yRProduction.getRegisteredUserInfoId().compareTo(loginUserId)==0){
                    //返回30%内容
                    return new ResultTemplate(YRProduction.getProductionJSONObject(yRProduction,num,productionList,yRAuthor,30,1,1,0));
                }else{
                    return new ResultTemplate("您不是作者本人，无法查看，跳转异常页面");
                }
            }else if(yRProduction.getYrProductionStatusIndex().equals("2")){
                //可见30%
                return new ResultTemplate(YRProduction.getProductionJSONObject(yRProduction,num,productionList,yRAuthor,30,1,0,0));
            }else if(yRProduction.getYrProductionStatusIndex().equals("4")){
                //作品作者是本人
                if(StringUtils.isEmpty(getWebUser())){
                    return new ResultTemplate("作品未发布且未登录，无法查看");
                }
                Integer loginUserId =getWebUser().getRecID();
                //是否购买者
                //判断作品是否本人购买
                YRProduction production = yRProductionServicesI.getBuyerByProductionId(yRProduction.getRecId(),loginUserId);
                if (StringUtils.isEmpty(production)) { //返回30%
                    return new ResultTemplate(YRProduction.getProductionJSONObject(yRProduction,num,productionList,yRAuthor,30,1,0,0));
                    //return new ResultTemplate("您未购买该作品，无法查看");
                }
                if (production.getRegisteredUserInfoId().compareTo(loginUserId)==0 && production.getIsRead()==1) {//本人且该作品已被查看
                    //返回100%
                    return new ResultTemplate(YRProduction.getProductionJSONObject(yRProduction,num,productionList,yRAuthor,100,1,0,1));
                }else  if (production.getRegisteredUserInfoId().compareTo(loginUserId)==0 && production.getIsRead()==0) {//本人且该作品未被查看
                    //返回30%
                    return new ResultTemplate(YRProduction.getProductionJSONObject(yRProduction,num,productionList,yRAuthor,30,0,0,1));
                } else {
                    return new ResultTemplate("您未购买此作品，无法查看，跳转异常页面");
                }
            }
        //已发布
        }else if(yRProduction.getPublishStatusIndex().equals("1")) {
            //作品是否上架（0下架，2上架）
            if (!yRProduction.getYrProductionStatusIndex().equals("2")) {
                //作品作者是本人
                if(StringUtils.isEmpty(getWebUser())){
                    return new ResultTemplate("作品未发布且未登录，无法查看");
                }
                Integer loginUserId =getWebUser().getRecID();
                //是否作者本人
                if (yRProduction.getRegisteredUserInfoId().compareTo(loginUserId) == 0) {
                    //返回100%内容
                    return new ResultTemplate(YRProduction.getProductionJSONObject(yRProduction,num,productionList,yRAuthor,100,1,1,0));
                } else {
                    //是否购买者
                    //判断作品是否本人购买
                    YRProduction production = yRProductionServicesI.getBuyerByProductionId(yRProduction.getRecId(),loginUserId);
                    if (StringUtils.isEmpty(production)) {
                        return new ResultTemplate("您未购买该作品，无法查看");
                    }
                    if (production.getRegisteredUserInfoId().compareTo(loginUserId)==0) {//本人
                        //返回100%内容
                        return new ResultTemplate(YRProduction.getProductionJSONObject(yRProduction,num,productionList,yRAuthor,100,1,0,1));
                    } else {
                        //跳转异常页面
                        return new ResultTemplate("您未购买，无法查看，跳转异常页面");
                    }
                }
            } else if (yRProduction.getYrProductionStatusIndex().equals("2")) {
                    //返回100%内容
                return new ResultTemplate(YRProduction.getProductionJSONObject(yRProduction,num,productionList,yRAuthor,100,1,0,0));
            } else {
                return new ResultTemplate("作品发布状态异常，无法查看");
            }
        }
        return new ResultTemplate("作品发布状态不对");
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 作品详情获取相似的15个作品
     *  */
    @RequestMapping("/author/getSimilarProducts")
    @ResponseBody
    public ResultTemplate getSimilarProducts(YRProductionListParam data , BaseModel baseModel) throws ParseException {
        if(StringUtils.isEmpty(data)){
            return new ResultTemplate("作品ID不存在");
        }
        if(StringUtils.isEmpty(data.getYpRecId())){
            return new ResultTemplate("作品ID不存在");
        }
        data.setRegisterUserInfoId(getWebUser() == null ? null : getWebUser().getRecID());
        YRProduction yRProduction = yRProductionServicesI.getByProductionId(data);
        if(yRProduction==null){
            return new ResultTemplate("作品不存在","");
        }
        Integer publishStatusIndex = yRProduction.getPublishStatusIndex()==null?null:Integer.valueOf(yRProduction.getPublishStatusIndex());
        //获取相似作者的总条数，和当前创作者所在id
        List<Map<String ,Object>> numMap = yRProductionServicesI.getLikeProductionNum(yRProduction.getYrCategoryId(), yRProduction.getSortScore(),publishStatusIndex);
        Integer a = 0,b=0;
        if(numMap !=null){
            Integer rowsn = Integer.parseInt(numMap.get(0).get("rowsn").toString());//所在行数
            Integer num = Integer.parseInt(numMap.get(0).get("num").toString());//总数
            if(num >= 16){
                if(num - rowsn > 7 && rowsn - 1 > 7){
                    a = rowsn - 8;
                    b = 15;
                }else if (num - rowsn <= 7 && rowsn - 1 > 7){
                    a = num - 16;//(rowsn-7-(7-(num-rowsn)-1))
                    b = 15;
                }else if (num - rowsn > 7 && rowsn - 1 <= 7){
                    a = 0;
                    b = 15;//(rowsn + 7 + 7 - (rowsn - 1)+1)
                }
            }else{
                a = 0;
                b = num;
            }
        }
        //获取15条记录(自己除外)
        List<YRProductionLikeResult> likeAuthorList = yRProductionServicesI.getLikeProduction(a,b,yRProduction.getYrCategoryId(), Integer.valueOf(data.getYpRecId()),publishStatusIndex);
        for(YRProductionLikeResult yRProductionLikeResult : likeAuthorList){
            if(yRProductionLikeResult.getPublishTime()!=null){
                yRProductionLikeResult.setPublishTime(DateUtil.format(DateUtil.parseDate(yRProductionLikeResult.getPublishTime(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
            }
            if(yRProductionLikeResult.getLocalContent()!=null){
                String localContent =FilterHtmlUtil.Html2Text(yRProductionLikeResult.getLocalContent());
                if(localContent.length()>200){
                    yRProductionLikeResult.setLocalContent(localContent.substring(0,200));
                }
            }
        }
        return new ResultTemplate(likeAuthorList);
    }
    
    /**
     *@author songwq
     *@param
     *@date 2018/8/6
     *@description 查看完整作品
    */
    @RequestMapping("/author/getCompleteContent")
    @ResponseBody
    public ResultTemplate getCompleteContent(YRProduction data) {
        if(StringUtils.isEmpty(data)){
            return new ResultTemplate("作品ID不存在");
        }
        if(StringUtils.isEmpty(data.getRecId())){
            return new ResultTemplate("作品ID不存在");
        }
        if(data.getRecId()!=0){
            YRProduction yRProduction = yRProductionServicesI.getById(data.getRecId());
            //返回数据之前将文章的阅读状态改为已读
            yRProductionServicesI.updateProductionReadStatus(data.getRecId());
            return new ResultTemplate("",yRProduction.getLocalcontent());
        }
        return new ResultTemplate();
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/6
     *@description 判断进入的作品详情页
    */
    @RequestMapping("/contentBank/article_detail_{recId}.html")
    public String enterDetailPage(HttpServletRequest request , @PathVariable Integer  recId) {
        if(!StringUtils.isEmpty(recId)){
            YRProduction yRProduction = yRProductionServicesI.getById(recId);
            if(StringUtils.isEmpty(yRProduction)){
                //未发布异常
                return "contentBank/work_detail_abnormal";
            }

            //判断作品类型(0未发布，1已发布)
            if(yRProduction.getPublishStatusIndex().equals("0")){
                //作品是否上架（0下架，1待审核，2上架，4售罄）
                if(yRProduction.getYrProductionStatusIndex().equals("0") || yRProduction.getYrProductionStatusIndex().equals("1")){
                    //未登录
                    if(StringUtils.isEmpty(getWebUser())){
                        return "contentBank/work_detail_abnormal";
                    }
                    Integer loginUserId =getWebUser().getRecID();
                    //是否作者本人
                    if(yRProduction.getRegisteredUserInfoId().compareTo(loginUserId)==0){
                        //返回30%内容
                        yRProduction = YRProduction.getProduction(yRProduction,30,1,1);
                    }else{
                        return "contentBank/work_detail_abnormal";
                    }
                }else if(yRProduction.getYrProductionStatusIndex().equals("2")){
                    //可见30%
                    yRProduction = YRProduction.getProduction(yRProduction,30,1,0);
                }else if(yRProduction.getYrProductionStatusIndex().equals("4")){
                    //未登录
                    if(StringUtils.isEmpty(getWebUser())){
                        return "contentBank/work_detail_abnormal";
                    }
                    Integer loginUserId =getWebUser().getRecID();
                    //是否购买者
                    //判断作品是否本人购买
                    YRProduction production = yRProductionServicesI.getBuyerByProductionId(yRProduction.getRecId(),loginUserId);
                    if (StringUtils.isEmpty(production)) {
                        yRProduction = YRProduction.getProduction(yRProduction,30,1,0);
                        //return "contentBank/work_detail_abnormal";
                    }else  if (production.getRegisteredUserInfoId()!=null && production.getRegisteredUserInfoId().compareTo(loginUserId)==0 && production.getIsRead()==1) {//本人且该作品已被查看
                        //返回100%
                        yRProduction = YRProduction.getProduction(yRProduction,100,1,0);
                    }else  if (production.getRegisteredUserInfoId()!=null && production.getRegisteredUserInfoId().compareTo(loginUserId)==0 && production.getIsRead()==0) {//本人且该作品未被查看
                        //返回30%
                        yRProduction = YRProduction.getProduction(yRProduction,30,0,0);
                    } else {
                        return "contentBank/work_detail_abnormal";
                    }
                }else {
                    return "contentBank/work_detail_abnormal";
                }
                //已发布
            }else if(yRProduction.getPublishStatusIndex().equals("1")) {
                //作品是否上架（0下架，2上架）
                if (!yRProduction.getYrProductionStatusIndex().equals("2")) {
                    //作品作者是本人
                    if(StringUtils.isEmpty(getWebUser())){
                        return "contentBank/work_detail_abnormal";
                    }
                    Integer loginUserId =getWebUser().getRecID();
                    //是否作者本人
                    if (yRProduction.getRegisteredUserInfoId()!=null && yRProduction.getRegisteredUserInfoId().compareTo(loginUserId) == 0) {
                        //返回100%内容
                        yRProduction = YRProduction.getProduction(yRProduction,100,1,1);
                    } else {
                        //是否购买者
                        //判断作品是否本人购买
                        YRProduction production = yRProductionServicesI.getBuyerByProductionId(yRProduction.getRecId(),loginUserId);
                        if (StringUtils.isEmpty(production)) {
                            //yRProduction = YRProduction.getProduction(yRProduction,30,1,0);
                            return "contentBank/work_detail_abnormal";
                        }
                        if (production.getRegisteredUserInfoId()!=null && production.getRegisteredUserInfoId().compareTo(loginUserId)==0) {//购买者
                            //返回100%内容
                            yRProduction  = YRProduction.getProduction(yRProduction,100,1,0);
                        } else {
                            //跳转异常页面
                            return "contentBank/work_detail_abnormal";
                        }
                    }
                } else if (yRProduction.getYrProductionStatusIndex().equals("2")) {
                    //返回100%内容
                    yRProduction = YRProduction.getProduction(yRProduction,100,1,0);
                } else {
                    return "contentBank/work_detail_abnormal";
                }
            }
            //修改浏览量
            yRProductionServicesI.updateAccessTimes(yRProduction);
            //通过上述流程，正常返回数据及页面
            request.setAttribute("yRProduction",yRProduction);
            request.setAttribute("accessNum", redisTemplate.opsForValue().get(YRProduction.class.getName()+":accessNum:"+yRProduction.getRecId()));
            return "contentBank/work_detail";
            /*//0未发布，1已发布
            String publishStatus = yRProduction.getPublishStatusIndex();
            //0下架,1待审核，2上架，3审核不通过，4下架
            String yrProductionStatusIndex = yRProduction.getYrProductionStatusIndex();
            if(publishStatus.equals("0") && yrProductionStatusIndex.equals("2")){
                //未发布正常页面
                return "contentBank/work_detail";
            }else if (publishStatus.equals("0")){
                //未发布异常页面
                return "contentBank/work_detail_abnormal";
            }else if(publishStatus.equals("1") && yrProductionStatusIndex.equals("2")){
                //已发布正常页面
                return "contentBank/work_detail";
            }else if(publishStatus.equals("1")){
                //已发布异常页面
                return "contentBank/work_detail_abnormal";
            }*/
        }
        return "contentBank/original_work";
    }


    @RequestMapping(value = "/production/searchYRProductionByTitle" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate searchYRProductionByTitle(String demandSn,YRProduction data , BaseModel baseModel){
//        data.setRegisterUserInfoId(getWebUser() == null ? null : getWebUser().getRecID());
        if(getWebUser() == null){
            return  new ResultTemplate("该用户没有登录",null);
        }
        Demand demand = demandServicesI.getByDemandSn(demandSn);
        if(demand==null){
            return new ResultTemplate("该需求单号不存在" ,null);
        }
        data.setRegisteredUserInfoId(getWebUser().getRecID());

        //已报名
        List<OrderInfoSeller> osList = orderInfoSellerServicesI.getReferIdByDemandSn(demandSn,getWebUser().getRecID());

        PageInfo<YRProduction> pageInfo = yRProductionServicesI.searchYRProductionByTitle(baseModel,data);
        JSONArray result = new JSONArray();
        if(!CollectionUtil.isEmpty(pageInfo.getList())){
            for (YRProduction yRProduction : pageInfo.getList()){
                JSONObject object = new JSONObject();
                object.put("title",yRProduction.getTitle());
                object.put("createdTime",StringUtil.isBlank(yRProduction.getCreatedTime().toString())?null:DateUtil.format(yRProduction.getCreatedTime(),"yyyy-MM-dd HH:mm"));
                object.put("recId",yRProduction.getRecId());
                object.put("imgNum",yRProduction.getImgNum());
                object.put("wordNum",yRProduction.getWordNum());
                for(OrderInfoSeller os :osList){
                    if(os.getReferId().equals(yRProduction.getRecId())){
                        object.put("isSign",true);
                        break;
                    }else{
                        object.put("isSign",false);
                    }
                }
                // 处理报名记录为空的情况
                if(osList.size()==0){
                    object.put("isSign",false);
                }

                result.add(object);
            }
        }
        return  new ResultTemplate(pageInfo,result);
    }
}
