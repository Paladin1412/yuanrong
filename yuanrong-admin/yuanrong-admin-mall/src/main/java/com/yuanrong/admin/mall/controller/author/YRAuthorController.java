package com.yuanrong.admin.mall.controller.author;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sun.imageio.plugins.common.I18N;
import com.yuanrong.admin.Enum.EnumAuthorStatus;
import com.google.gson.JsonObject;
import com.yuanrong.admin.Enum.EnumAuthorStatus;
import com.yuanrong.admin.Enum.EnumPublishStatus;
import com.yuanrong.admin.Enum.EnumYRProductionStatus;
import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.result.YRAuthorInfoProResult;
import com.yuanrong.admin.result.YRAuthorInfoResult;
import com.yuanrong.admin.seach.AuthorListParamSeachMall;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName YRAuthorController
 * @Author Jss
 * @Date 2018/5/30
 */
@Controller
public class YRAuthorController extends BaseController{
    private static final Logger logger = Logger.getLogger(YRAuthorController.class);

    /**
     * @Description:
     * 创作者列表——V1.0
     * @author: Jss
     * @param authorParamSeach
     * @param baseModel
     * @return: void
     */
    @RequestMapping( value = "/author/getAuthorAndProList" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(AuthorListParamSeachMall authorParamSeach, BaseModel baseModel){
        dealParam(authorParamSeach);
        PageInfo<Map> yRAuthorPageInfo = yRAuthorServicesI.authorList(authorParamSeach , baseModel);
        JSONArray resultList = new JSONArray();
        if(!CollectionUtil.isEmpty(yRAuthorPageInfo.getList())){
            for(Map<String ,Object> ele : yRAuthorPageInfo.getList()){
                resultList.add(dealJSON(ele));
            }
        }
        return new ResultTemplate(yRAuthorPageInfo,resultList);
    }

    /**
     * 创作者详情—V1.3
     * @param recid
     */
    @RequestMapping(value = "/author/getAuthorInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getAuthorInfo(Integer recid,HttpServletRequest request){
        JSONObject result = new JSONObject();
        //判断是否登陆
//        if(getWebUser() == null){
//            return new ResultTemplate("登录后可查看详情，请登录！");
//        }
        if (recid == null){
            return new ResultTemplate("参数有误",null);
        }
        YRAuthor yrAuthor = yRAuthorServicesI.getById(recid);
        if(yrAuthor == null){
            result.put("identify",0);
            return new ResultTemplate("创作者信息不存在",result);
        }
        //创作者详情
        Integer userId = null;
        if(getWebUser() != null){
            userId = getWebUser().getRecID();
        }
        YRAuthorInfoResult authorInfo = yRAuthorServicesI.getAuthorInfo(recid,userId);
        if(yrAuthor.getAuthorStatus().intValue() != EnumAuthorStatus.上架.getIndex()){//创作者暂未上架，此页仅供创作者自己预览，其他人不可见
            if(getWebUser() !=null && getWebUser().getRecID().compareTo(yrAuthor.getRegisteredUserInfoID()) ==0){//是本人显示信息
                result.put("identify",1);
            }else {//不是本人不显示
                result.put("identify",0);
                //相似创作者
//                result.put("likeAuthor",likeAuthor(authorInfo,true));
                return new ResultTemplate("创作者不存在或未上架",result);
            }
        }else {
            result.put("identify",2);
        }
        result.put("authorInfo",YRAuthorInfoResult.packageParam(authorInfo));
        //相似创作者
        result.put("likeAuthor",likeAuthor(authorInfo,false));
        return new ResultTemplate("",result);
    }

    /**
     * 相似创作者获取—通过随机取一个擅长领域，找相似创作者15条记录
     * @param authorInfo
     * @return
     */
    public JSONArray likeAuthor(YRAuthorInfoResult authorInfo,Boolean flag){
        JSONArray result = new JSONArray();
        //获取随机分类
        if (StringUtil.isNotBlank(authorInfo.getCategoryIds())) {
            String[] split = authorInfo.getCategoryIds().split("、");
            String rownum = split[(int) (Math.random() * split.length)];
            //获取相似作者的总条数，和当前创作者所在id
            List<Map<String, Object>> numMap = yRAuthorServicesI.getLikeAuthorNum(Integer.parseInt(rownum), authorInfo.getSortScore());
            Integer a = 0, b = 0;
            if (numMap != null) {
                Integer rowsn = Integer.parseInt(numMap.get(0).get("rowsn").toString());//所在行数
                Integer num = Integer.parseInt(numMap.get(0).get("num").toString());//总数
                if (num >= 16) {
                    if (num - rowsn > 7 && rowsn - 1 > 7) {
                        a = rowsn - 8;
                        b = 15;
                    } else if (num - rowsn > 7 && rowsn - 1 <= 7) {
                        a = 0;
                        b = 15;//(rowsn + 7 + 7 - (rowsn - 1)+1)
                    } else if (num - rowsn <= 7 && rowsn - 1 > 7) {
                        a = num - 16;//(rowsn-7-(7-(num-rowsn)-1))
                        b = 15;
                    }
                } else {
                    a = 0;
                    b = num;
                }
            }
            //获取15条记录(自己除外)
            List<YRAuthorInfoResult> likeAuthorList = yRAuthorServicesI.getLikeAuthor(a, b, Integer.parseInt(rownum), authorInfo.getRecId());
            if (flag == true) {
                JSONArray resultList = new JSONArray();
                if (!CollectionUtil.isEmpty(likeAuthorList)) {
                    for (YRAuthorInfoResult author : likeAuthorList) {
                        resultList.add(dealData(author));
                    }
                }
                result.addAll(resultList);
            } else {
                JSONArray resultList = new JSONArray();
                if (!CollectionUtil.isEmpty(likeAuthorList)) {
                    for (YRAuthorInfoResult author : likeAuthorList) {
                        resultList.add(dealLikeAuthorData(author));
                    }
                }
                result.addAll(resultList);
            }
        }
        return result;
    }

    /**
     * C端—相似创作者数据封装1.3
     * @param author
     * @return
     */
    public JSONObject dealLikeAuthorData(YRAuthorInfoResult author){
        JSONObject result = new JSONObject();
        result.put("recId",author.getRecId());
        result.put("authorNickname",author.getAuthorNickname());
        result.put("authorImg",author.getAuthorImg());
        result.put("Introduction",author.getIntroduction());
        return  result;
    }

    /**
     * C端—查询创作者的公开作品数、未公开作品数
     * @param authorId
     * @return
     */
    @RequestMapping("/author/getAuthorProOpenNum")
    @ResponseBody
    public ResultTemplate getAuthorProOpenNum(Integer authorId){

        if(authorId == null || yRAuthorServicesI.getById(authorId) == null){
            return new ResultTemplate("创作者Id为空或创作者信息不存在");
        }
        Map<String , Object> map = yRProductionServicesI.getAuthorProOpenNum(authorId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openNum",map.get("openNum"));
        jsonObject.put("notOpenNum",map.get("notOpenNum"));
        return new ResultTemplate(jsonObject);
    }
    /**
     * C端—获取创作者的已上架作品列表（公开作品、非公开）
     * @param authorId 创作者Id
     * @param publishStatusIndex 发布状态
     * @param contentFormId 内容形式Id
     */
    @RequestMapping("/author/getAuthorPro")
    @ResponseBody
    public ResultTemplate getAuthorProductList(Integer authorId, Integer publishStatusIndex, Integer contentFormId, BaseModel baseModel){

        if (authorId == null){
            return new ResultTemplate("创作者Id参数有误",null);
        }
        if(publishStatusIndex == null || (publishStatusIndex.intValue() != EnumPublishStatus.已公开.getIndex() && publishStatusIndex.intValue() != EnumPublishStatus.未公开.getIndex())){
            return new ResultTemplate("作品发布状态参数有误",null);
        }
        YRAuthor yrAuthor = yRAuthorServicesI.getById(authorId);
        if(yrAuthor == null){
            return new ResultTemplate("创作者信息不存在",null);
        }
        /*if(yrAuthor.getAuthorStatus().compareTo(EnumAuthorStatus.上架.getIndex()) !=0){
            if(getWebUser() !=null && getWebUser().getRecID().compareTo(yrAuthor.getRegisteredUserInfoID()) == 0) {//当前用户是自己可以显示创作者信息

            }else{
                return new ResultTemplate("创作者信息不存在",null);
            }
        }*/
        if(contentFormId !=null){
            ContentForm contentForm = contentFormServicesI.getById(contentFormId);
            if(contentForm == null){
                return new ResultTemplate("内容形式不存在",null);
            }
        }
        PageInfo<YRAuthorInfoProResult> authorProList = yRProductionServicesI.findAuthorProList(authorId, publishStatusIndex, contentFormId, baseModel);
        JSONArray jsonResult = new JSONArray();
        if(CollectionUtil.size(authorProList.getList()) >0){
            for (YRAuthorInfoProResult yrPro : authorProList.getList()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("recId",yrPro.getRecId());
                jsonObject.put("title",yrPro.getTitle());
                jsonObject.put("contentFormName",yrPro.getContentFormName());
                jsonObject.put("localcontent", FilterHtmlUtil.before200Char(yrPro.getLocalcontent()));
                jsonObject.put("coverLocalUrl",yrPro.getCoverLocalUrl());
                jsonObject.put("productUrl",yrPro.getProductUrl());
                jsonObject.put("charNum",yrPro.getWordNum());
                jsonObject.put("imgNum",yrPro.getImgNum());
                try {
                    jsonObject.put("publishTime", (yrPro.getPublishTime()== null || "".equals(yrPro.getPublishTime())) ? "" : DateUtil.format(yrPro.getPublishTime(),"yyy-MM-dd"));
                } catch (ParseException e) {
                    logger.error(e);
                    e.printStackTrace();
                }
                jsonObject.put("createdTime",yrPro.getCreatedTime() == null || "".equals(yrPro.getCreatedTime()) ? "" : DateUtil.format(yrPro.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
                jsonObject.put("tags",yrPro.getTags());
                jsonObject.put("productQuotedPrice",yrPro.getProductQuotedPrice());
                jsonObject.put("isRepresentative",yrPro.getIsRepresentative());
                if (yrPro.getContentEvaluationScore() != null){
                    jsonObject.put("contentScore",yrPro.getContentEvaluationScore());
                }
                jsonObject.put("originalScore" ,yrPro.getOriginalScore());
                if(StringUtil.isNotBlank(yrPro.getCalendarName())){
                    jsonObject.put("calendarName",yrPro.getCalendarName());
                }
                jsonObject.put("publishPlatform",yrPro.getPublishPlatform());
                jsonObject.put("accessNum",yrPro.getAccessNum());
                jsonResult.add(jsonObject);
            }
        }
        //发布作品数、未发布作品数
        Map<String , Object> map = yRProductionServicesI.getAuthorProOpenNum(authorId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openNum",map.get("openNum"));
        jsonObject.put("notOpenNum",map.get("notOpenNum"));
        jsonObject.put("authorProList",jsonResult);
        //上架作品内容形式数
        Map<String,Object> contentNum = yRProductionServicesI.getAuthorProContentNum(authorId,publishStatusIndex);
        if (!org.springframework.util.StringUtils.isEmpty(contentNum)){
            jsonObject.put("contentFormId",contentNum.get("contentFormId"));
            jsonObject.put("contentFormName",contentNum.get("contentFormName"));
        }
        return new ResultTemplate(authorProList, jsonObject);
    }

    /**
     * 返回结果封装
     * @param ele
     * @return
     */
    private Object dealJSON(Map<String, Object> ele) {

        JSONObject jsonObject = new JSONObject();
        //作者ID
        jsonObject.put("recId", ele.get("recId"));
        //用户头像
        jsonObject.put("authorImg", ele.get("authorImg"));
        //昵称
        jsonObject.put("authorNickname", ele.get("authorNickname"));
        //原创报价
        jsonObject.put("createdPrice", ele.get("createdPrice"));
        //创作用时
        jsonObject.put("authorCreationTime", ele.get("authorCreationTime"));
        //简介
        jsonObject.put("Introduction", ele.get("Introduction"));
        //内容形式
        jsonObject.put("contentFormName",ele.get("contentFormName"));
        //使用场景
        jsonObject.put("scenesName",ele.get("scenesName"));
        //内容行业
        jsonObject.put("CategoryName",ele.get("CategoryName"));
        //作者标签
        jsonObject.put("lableName",ele.get("lableName"));
        //购物车标记
        jsonObject.put("flag",ele.get("flag"));
        //作品展示
        JSONArray productionArray = new JSONArray();
        if (ele.get("productId") !=null){
            String[] productId = String.valueOf(ele.get("productId")).split("-_-");
            String[] productUrl = ele.get("productUrl") == null ? null : String.valueOf(ele.get("productUrl")).split("-_-");
            String[] title = String.valueOf(ele.get("title")).split("-_-");
            String[] coverLocalUrl = ele.get("coverLocalUrl") == null ? null : String.valueOf(ele.get("coverLocalUrl")).split("-_-");
            for (int i = 0; i < productId.length; i++) {
                JSONObject productionObject = new JSONObject();
                productionObject.put("productionUrl","/contentBank/article_detail_"+productId[i]);
                productionObject.put("productUrl",productUrl == null || productUrl.length <1 ? "" : productUrl[i]);
                productionObject.put("productionImage",coverLocalUrl == null || coverLocalUrl.length < 1 ? "" : coverLocalUrl[i]);
                //作品title为空的话，内容形式和title都不展示
                String[] production = title[i].split("]");
                if(production.length<2){
                    productionObject.put("production","");
                }else {
                    productionObject.put("production",title[i]);
                }
                productionArray.add(productionObject);
            }
        }
        jsonObject.put("productionArray", productionArray);
        return jsonObject;
    }

    /**
     * 处理参数
     * @param authorParamSeach
     */
    private void dealParam(AuthorListParamSeachMall authorParamSeach) {

        /*//使用场景
        if (authorParamSeach.getScenes() != null) {
            map.put("scenes", authorParamSeach.getScenes());
        }
        //内容分类
        if (authorParamSeach.getCategory() != null) {
            map.put("category", authorParamSeach.getCategory());
        }
        //内容形式
        if (authorParamSeach.getContentForm() != null) {
            map.put("contentForm", authorParamSeach.getContentForm());
        }*/
        //处理原创价格搜索
        String creationPrice = authorParamSeach.getCreationPrice();
        if (creationPrice != null && !"".equals(creationPrice)) {
            String[] str = creationPrice.split("-");
            if (str.length > 1) { //区间价格
                authorParamSeach.setCreationPriceEnd(Integer.parseInt(str[1]));
                authorParamSeach.setCreationPriceStart(Integer.parseInt(str[0]));
            } else {//搜索指定值以上的
                authorParamSeach.setCreationPriceMax(Integer.parseInt(str[0]));
            }
        }

        //处理原创用时搜索
        String creationTime = authorParamSeach.getCreationTime();
        if (creationTime != null && !"".equals(creationTime)) {
            String[] str = creationTime.split("-");
            if (str.length > 1) { //区间用时
                authorParamSeach.setCreationTimeEnd(Integer.parseInt(str[1]));
                authorParamSeach.setCreationTimeStart(Integer.parseInt(str[0]));
            } else {//搜索指定值以上的
                authorParamSeach.setCreationTimeMax(Integer.parseInt(str[0]));
            }
        }
    }

    /**
     * C端—创作者列表—V1.2.2—V1.3
     * @param authorParamSeach
     * @param baseModel
     */
    @RequestMapping( value = "/author/getAuthorList" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getAuthorAndProList(AuthorListParamSeachMall authorParamSeach,BaseModel baseModel){
        dealParam(authorParamSeach);
        if(getWebUser() != null){
            authorParamSeach.setRegisteredUserInfoId(getWebUser().getRecID());
        }
        PageInfo<YRAuthorInfoResult> yRAuthorPageInfo = yRAuthorServicesI.getAuthorAndProList(authorParamSeach , baseModel);
        JSONArray resultList = new JSONArray();
        if(!CollectionUtil.isEmpty(yRAuthorPageInfo.getList())){
            for(YRAuthorInfoResult author: yRAuthorPageInfo.getList()){
                resultList.add(dealData(author));
            }
        }
        return new ResultTemplate(yRAuthorPageInfo,resultList);
    }

    /**
     * 封装数据—V1.2.2—V1.3
     * @param author
     * @return
     */
    private Object dealData(YRAuthorInfoResult author) {
        JSONObject result = new JSONObject();
        //作者ID
        result.put("recId", author.getRecId());
        //用户头像
        result.put("authorImg", author.getAuthorImg());
        //昵称
        result.put("authorNickname", author.getAuthorNickname());
        //原创报价
        if(getWebUser() == null){//判断是否登陆
            result.put("createdPrice", "--");
        }else {
            result.put("createdPrice", author.getCreatedPrice());
        }
        //创作用时
        result.put("authorCreationTime",author.getAuthorCreationTime());
        //简介
        result.put("Introduction", author.getIntroduction());
        //内容形式
        result.put("contentFormName",author.getContentForm());
        //使用场景
        result.put("scenesName",author.getScenes());
        //内容行业
        result.put("CategoryName", author.getCategory());
        //作者标签
        result.put("lableName", author.getContentLable());
        if(getWebUser() !=null){
            //购物车标记
            result.put("flag",author.getFlag());
        }
        //创作者作品
        JSONArray productionArray = new JSONArray();
        List<YRProduction> proList = author.getYrProductions();
        Integer representativeNum = author.getRepresentativeNum();
        if(CollectionUtil.size(proList) > 0){
            if(proList.size() > 3){
                if(representativeNum >= 3 ){
                    for (int i = 0; i < 3 ; i++){
                        if(proList.get(i).getIsRepresentative() == EnumYesOrNo.NORMAL.getIndex()){
                            productionArray.add(dealProData(proList.get(i)));
                        }
                    }
                }else if(representativeNum < 3){
                    for (int i = 0; i < 3 ; i++){
                        productionArray.add(dealProData(proList.get(i)));
                    }
                }
            }else if(proList.size() <= 3 ){
                for (int i = 0; i < proList.size() ; i++){
                    if(proList.get(i).getRecId() != null){
                        productionArray.add(dealProData(proList.get(i)));
                    }
                }
            }
        }
        result.put("productionArray",productionArray);
        return result;
    }

    /**
     * 处理创作者作品—V1.2.2—V1.3
     * @param pro
     * @return
     */
    public  JSONObject dealProData(YRProduction pro){
        JSONObject proJson = new JSONObject();
        proJson.put("productionUrl","/contentBank/article_detail_"+ pro.getRecId());
        proJson.put("productUrl",pro.getProductUrl());
        proJson.put("productionImage",pro.getCoverLocalUrl());
        //作品title为空的话，内容形式和title都不展示
        if(StringUtil.isNotBlank(pro.getTitle())){
            proJson.put("production",pro.getContentForm() == null ? "" : "[" + pro.getContentForm().getContentFormName() + "]" + pro.getTitle());
        }else {
            proJson.put("production","");
        }
        return proJson;
    }

    /**
     * 处理参数
     * @param map
     * @param request
     */
    private void dealParam(Map<String, Object> map, HttpServletRequest request) {

        //使用场景
        if (request.getParameter("scenes") != null) {
            map.put("scenes", request.getParameter("scenes"));
        }
        //内容分类
        if (request.getParameter("category") != null) {
            map.put("category", request.getParameter("category"));
        }
        //内容形式
        if (request.getParameter("contentForm") != null) {
            map.put("contentForm", request.getParameter("contentForm"));
        }
        //处理原创价格搜索
        String creationPrice = StringUtils.trim(request.getParameter("creationPrice"));
        if (creationPrice != null) {
            String[] str = creationPrice.split("-");
            if (str.length > 1) { //区间搜索粉丝数
                map.put("creationPriceEnd", str[1]);
                map.put("creationPriceStart", str[0]);
            } else {//搜索指定值以上的
                map.put("creationPriceMax", str[0]);
            }
        }

        //处理原创用时搜索
        String creationTime = StringUtils.trim(request.getParameter("creationTime"));
        if (creationTime != null) {
            String[] str = creationTime.split("-");
            if (str.length > 1) { //区间搜索粉丝数
                map.put("creationTimeEnd", str[1]);
                map.put("creationTimeStart", str[0]);
            } else {//搜索指定值以上的
                map.put("creationTimeMax", str[0]);
            }
        }
    }

    /**
     * 判断创作者状态和当前登录用户
     * @param recId
     * @return
     */
    @RequestMapping("/contentBank/author_detail_{recId}.html")
    public String judgeAuthorStatus(HttpServletRequest request , @PathVariable Integer  recId){
        if (recId == null) {
            return "/contentBank/abnormal_author_detail";
        }
        YRAuthor yrAuthor = yRAuthorServicesI.getById(recId);
        if (yrAuthor == null){
            return "/contentBank/abnormal_author_detail";
        }

        Integer userId = null;
        if(getWebUser() != null){
            userId = getWebUser().getRecID();
        }
        //访问次数+1
        yRAuthorServicesI.updateAuthorAccessTimes(yrAuthor);
        Object accsNum = redisTemplate.opsForValue().get(YRAuthor.class.getName()+":accessNum:"+yrAuthor.getRecId());
        request.setAttribute("accessNum", accsNum);
        YRAuthorInfoResult authorInfo = yRAuthorServicesI.getAuthorInfo(recId,userId);
        if (yrAuthor.getAuthorStatus().compareTo(EnumAuthorStatus.上架.getIndex()) !=0){//未上架创作者
            if(getWebUser() !=null && getWebUser().getRecID().compareTo(yrAuthor.getRegisteredUserInfoID()) == 0){//当前用户是自己可以显示创作者信息
                request.setAttribute("authorInfo",authorInfo);
                return "/contentBank/author_detail";
            }else {//非创作者本人不能看
                return "/contentBank/abnormal_author_detail";
            }
        }
        request.setAttribute("authorInfo",authorInfo);
        return "/contentBank/author_detail";
    }

    /**
     * 选择创作者： 要求账号可用、上架
     * @param
     * @return yrAuthor表中recId /  authorNickname
     */
    @RequestMapping("/author/getMyYrAuthor")
    @ResponseBody
    public ResultTemplate getMyYrAuthor(){
        if(getWebUser() == null  ){
            return new ResultTemplate("用户未登陆" ,null);
        }

        List<YRAuthor> yrAuthors = yRAuthorServicesI.getByRegisterUserId(getWebUser().getRecID());
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(yrAuthors) > 0){
            for(int i=0; i<yrAuthors.size();i++){
                if(yrAuthors.get(i).getStatusIndex()==EnumYesOrNo.NORMAL.getIndex() &&
                        yrAuthors.get(i).getAuthorStatus()==EnumAuthorStatus.上架.getIndex()){
                    JSONObject object = new JSONObject();
                    object.put("nickname" , yrAuthors.get(i).getAuthorNickname());
                    object.put("id" , yrAuthors.get(i).getRecId());
                    result.add(object);
                }
            }

        }
        return new ResultTemplate("",result);
    }

    /**
     * 选择创作者： 要求账号可用、上架
     * @param
     * @return yrAuthor表中recId /  authorNickname
     */
    @RequestMapping("/author/getMyYrAuthorContent")
    @ResponseBody
    public ResultTemplate getMyYrAuthorContent(String demandSn,BaseModel baseModel,YRAuthor data){
        if(getWebUser() == null  ){
            return new ResultTemplate("用户未登陆" ,null);
        }
        data.setRegisteredUserInfoID(getWebUser().getRecID());
        Demand demand = demandServicesI.getByDemandSn(demandSn);
        if(demand==null){
            return new ResultTemplate("该需求单号不存在" ,null);
        }
        //已报名
        List<OrderInfoSeller> osList =  orderInfoSellerServicesI.getReferIdByDemandSn(demandSn,getWebUser().getRecID());
        PageInfo<YRAuthor> pageInfo = yRAuthorServicesI.getYRAuthorListByRegisterUserId(data,baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(pageInfo.getList()) > 0){
            for(YRAuthor yrAuthor : pageInfo.getList()){

                    JSONObject object = new JSONObject();
                    object.put("headImg",yrAuthor.getAuthorImg());
                    object.put("nickname" , yrAuthor.getAuthorNickname());
                    object.put("id" , yrAuthor.getRecId());


                    for(OrderInfoSeller os : osList){
                        JSONArray jsonArray = new JSONArray();
                        if( os.getReferId().equals(  yrAuthor.getRecId())){
                           object.put("isSign",true);
                           object.put("usableDate",os.getUsableDate());
                           List<OrderInfoOffer> oofferList = orderInfoOfferServicesI.getByOrderInfoSellerId(os.getOrderInfoSellerId());

                           if(oofferList.size()>0){
                              for( OrderInfoOffer  orderInfoOffer : oofferList){
                                  JSONObject jb = new JSONObject();
                                  jb.put("priceName",orderInfoOffer.getPriceName());
                                  jb.put("price",orderInfoOffer.getPrice());
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

                    // 处理报名记录为空的情况
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
        return new ResultTemplate(pageInfo,result);
    }



}
