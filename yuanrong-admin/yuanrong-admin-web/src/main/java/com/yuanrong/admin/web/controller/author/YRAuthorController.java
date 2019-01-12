package com.yuanrong.admin.web.controller.author;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.seach.AuthorListParamSeach;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.admin.web.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 圆融创作者的controller
 * Created MDA
 */
@Controller
@RequestMapping("author")
public class YRAuthorController extends BaseController {
    private static final Logger logger = Logger.getLogger(YRAuthorController.class);

    /**
     * 创作者列表
     * @param data
     * @param baseModel
     */
    @RequestMapping( value = "yRAuthor_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(AuthorListParamSeach data , BaseModel baseModel, HttpSession session){
        data.setCurrRegisteredUserInfo(registeredUserInfoService.getById(getWebUser(session).getRecID()));
        PageInfo<Map> yRAuthorPageInfo = yRAuthorServicesI.list(data , baseModel);
        JSONArray resultList = new JSONArray();
        if(!CollectionUtil.isEmpty(yRAuthorPageInfo.getList())){
            for(Map<String ,Object> ele : yRAuthorPageInfo.getList()){
                resultList.add(dealParmJSON(ele));
            }
        }
        return new ResultTemplate(yRAuthorPageInfo,resultList);
    }

    /**
     * 处理参数
     * @param data
     * @return
     */
    private Object dealParmJSON(Map<String, Object> data) {
        JSONObject json = new JSONObject();
        //创作者ID
        json.put("recId",data.get("recId"));
        //创作者名称
        json.put("authorNickname",data.get("authorNickname"));
        //创作者头像
        json.put("authorImg",data.get("authorImg"));
        //原创报价
        json.put("createdPrice", data.get("createdPrice"));
        //原创报价备注
        json.put("createdPriceRemark", data.get("createdPriceRemark"));
        /*//创作用时
        json.put("authorCreationTime", data.get("authorCreationTime"));*/
        //内容形式
        json.put("contentFormName",data.get("contentFormName"));
        /*//使用场景
        json.put("scenesName",data.get("scenesName"));*/
        //内容行业
        json.put("CategoryName",data.get("CategoryName"));
        //创作者状态值
        json.put("authorStatusIndex",data.get("authorStatus"));
        //创作者状态
        json.put("authorStatus",data.get("authorStatus") == null ? "" : EnumUtil.valueOf(EnumAuthorStatus.class,data.get("authorStatus").toString()).getName());
        //创作者上架代表作个数
        json.put("RepresentativeNum",data.get("RepresentativeNum") == null ? 0 : data.get("RepresentativeNum"));
        //用户审核状态值
        json.put("userStatusIndex",data.get("sellerStatusValue"));
        //用户审核状态
        json.put("userStatus", data.get("sellerStatusValue") == null ? "" : EnumUtil.valueOf(EnumUserRoleLicenseStatus.class, data.get("sellerStatusValue").toString()).getName());
        //创作者审核失败原因
        json.put("authorAuditFailReason",data.get("authorAuditFailReason"));
        //创作者作品数
        json.put("proNum",data.get("proNum") ==null ? 0 : data.get("proNum"));
        //创作者上架作品数
        json.put("proUpNum",data.get("proUpNum") == null ? 0 : data.get("proUpNum"));
        //创作者是否匿名
        json.put("isAnonymousIndex",data.get("isAnonymous"));
        //创作者是否匿名
        json.put("isAnonymous",data.get("isAnonymous") == null ? "" : EnumUtil.valueOf(EnumYesOrNo.class,data.get("isAnonymous").toString()).getName());//创作者下架原因
        //创作者下架原因
        json.put("authorUnderReason",data.get("authorUnderReason"));

        return json;
    }

    /**
     * 创作者详情
     * @param recId
     */
    @RequestMapping("authorDetail")
    @ResponseBody
    public ResultTemplate authorDetail(Integer recId,HttpSession session){
        if(recId ==null || recId <=0){
            return new ResultTemplate("参数输入有误",null);
        }
        if(getWebUser(session) == null){
            return new ResultTemplate("请重新登录",null);
        }
        JSONObject result = new JSONObject();
        JSONArray authorProList = new JSONArray();
        //创作者详情信息
        List<Map<String, Object>> authorlist = yRAuthorServicesI.getAuthorDetailInfo(recId);
        if(CollectionUtil.size(authorlist) < 1){
            return new ResultTemplate("创作者信息不存在",null);

        }
        if(getWebUser(session).getRecID().compareTo(Integer.parseInt(authorlist.get(0).get("registeredUserInfoID").toString())) !=0){
            return new ResultTemplate("创作者信息不存在",null);

        }
        result.put("authorInfo", YRAuthor.packageAuthorParam(authorlist));
        /*//创作者的代表作品列表
        List<Map<String, Object>> authorProduction = yRProductionServicesI.getAuthorProList(recId);
        if(CollectionUtil.size(authorProduction) > 0){
            authorProList = YRProduction.packageAuthorProduction(authorProduction);
        }
        result.put("authorProList",authorProList);*/
        //创作者的作品数、上架数、上架代表作数
        Map<String , Object> map = yRProductionServicesI.getAuthorProNum(recId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proNum",map.get("proNum"));
        jsonObject.put("upNum",map.get("upNum"));
        jsonObject.put("RepresentativeNum",map.get("RepresentativeNum"));
        result.put("authorProNum",jsonObject);
        return new ResultTemplate("",result);
    }

    /**
     * 新建创作者
     * @param yRAuthor
     */
    @RequestMapping( value = "yRAuthor_save" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveOk(YRAuthor yRAuthor,HttpSession session){
        if(getWebUser(session) == null){
            return new ResultTemplate("请重新登录",null);
        }
        //参数处理
        String str = dealParamJudge(yRAuthor);
        if(!"".equals(str)){
            return new ResultTemplate(str,null);

        }
        //再次判断作者名称是否存在
        YRAuthor author = yRAuthorServicesI.getYRAuhorByName(yRAuthor.getAuthorNickname(),getWebUser(session).getRecID());
        if(author !=null){
            return new ResultTemplate("作者名称已存在",null);

        }
        yRAuthor.setRegisteredUserInfoID(getWebUser(session).getRecID());
        yRAuthor.setAuthorStatus(EnumAuthorStatus.待审核.getIndex());
        yRAuthor.setCreateSource(EnumChannel.前台创建.getIndex());
        yRAuthor.setIsAnonymous(EnumYesOrNo.FORBIDDEN.getIndex());
        JSONObject jsonObject = new JSONObject();
       Integer authorId = yRAuthorServicesI.saveAuthor(yRAuthor);
       jsonObject.put("authorId",authorId);
        systemLogServicesI.log(YRAuthor.class.getName(),authorId ,"创作者新增",getWebUser(session).getUserName());
        return new ResultTemplate("",jsonObject);
    }

    /**
     * 新增、修改参数处理
     * @param yRAuthor
     */
    public String dealParamJudge(YRAuthor yRAuthor){
        if(StringUtil.isBlank(yRAuthor.getAuthorNickname())){
            return "创作者名称不能为空";
        }
//        if (StringUtil.isBlank(yRAuthor.getAuthorImg())){
//            return "创作者头像不能为空";
//        }
        if (yRAuthor.getCreatedPrice() ==null){
            return "创作者原创参考价不能为空";
        }
        if(yRAuthor.getCreatedPrice() != null && (yRAuthor.getCreatedPrice().compareTo(BigDecimal.ZERO) <=0 || yRAuthor.getCreatedPrice().compareTo(new BigDecimal(999999999)) > 0)){
            return "原创报价的范围是1-999999999之间";
        }
        if (yRAuthor.getAuthorCreationTime() == null ){
            return "创作者原创用时不能为空";
        }
        if(yRAuthor.getAuthorCreationTime() != null && (yRAuthor.getAuthorCreationTime() < 1 || yRAuthor.getAuthorCreationTime() > 30)){
            return "创作者原创用时的范围1-30之间";
        }
        if (StringUtil.isBlank(yRAuthor.getIntroduction())){
            return "创作者简介不能为空";
        }
        if(StringUtil.isBlank(yRAuthor.getScenes())){
            return "创作题材不能为空";
        }else {
            String str = yRAuthor.getScenes();
            if(str.contains("其它")){
                if(yRAuthor.getScenes().split(",").length >4){
                    return "创作题材最多只能选择4个";
                }
            }else {
                if(yRAuthor.getScenes().split(",").length >3){
                    return "创作题材最多只能选择3个";
                }
            }
            String[] st = yRAuthor.getScenes().split(",");
            for (String s : st){
                if(s.equals("")){
                    return "创作题材参数有问题";
                }
            }
        }
        if(StringUtil.isBlank(yRAuthor.getContentForm())){
            return "内容类型不能为空";
        }else {
            String str = yRAuthor.getContentForm();
            if(str.contains("其它")){
                if(yRAuthor.getContentForm().split(",").length >4){
                    return "内容类型最多只能选择4个";
                }
            }else {
                if(yRAuthor.getContentForm().split(",").length >3){
                    return "内容类型最多只能选择3个";
                }
            }
            for (String s : str.split(",")){
                if(s.equals("")){
                    return "内容类型参数有问题";
                }
            }
        }
        if(StringUtil.isBlank(yRAuthor.getCategory())){
            return "擅长领域不能为空";
        }else {
            if(yRAuthor.getCategory().split(",").length >3){
                return "擅长领域最多只能选择3个";
            }
            for (String str : yRAuthor.getCategory().split(",")){
                DictInfo dic = null;
                try {
                    dic = dictInfoServicesI.getById(Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    logger.error(e);
                    return "擅长领域数据格式有误";
                }
                if(dic == null){
                    return "擅长领域不存在";
                }
            }
        }
        return "";
    }

    /**
     * 编辑创作者
     * @param recId
     */
    @RequestMapping("yRAuthor_update")
    @ResponseBody
    public ResultTemplate update(Integer recId,HttpSession session){
        if(recId == null){
            return new ResultTemplate("创作者Id不能为空！",null);
        }
        if(getWebUser(session) == null){
            return new ResultTemplate("请重新登录",null);
        }
        List<Map<String , Object>> listMap =  yRAuthorServicesI.getAuthorInfoUpdate(recId);
        if(CollectionUtil.size(listMap) < 1){
            return new ResultTemplate("创作者信息不存在",null);

        }
        if(getWebUser(session).getRecID().compareTo(Integer.parseInt(listMap.get(0).get("registeredUserInfoID").toString())) !=0){
            return new ResultTemplate("创作者信息不存在",null);

        }
        JSONObject authorInfo = YRAuthor.packageAuthorParamUpdate(listMap);
        return new ResultTemplate("",authorInfo);
    }

    /**
     * 修改创作者
     * @param yRAuthor
     */
    @RequestMapping(value = "yRAuthor_updateOK",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateOK(YRAuthor yRAuthor,HttpSession session){
        if(getWebUser(session) == null){
            return new ResultTemplate("请重新登录",null);
        }
        if(yRAuthor.getRecId() == null){
            return new ResultTemplate("创作者Id有误！",null);
        }
        if(yRAuthorServicesI.getById(yRAuthor.getRecId()) == null){
            return new ResultTemplate("创作者信息不存在！",null);
        }
        if(yRAuthorServicesI.getById(yRAuthor.getRecId()).getRegisteredUserInfoID().compareTo(getWebUser(session).getRecID()) != 0){
            return new ResultTemplate("创作者信息不存在！",null);
        }
        //参数处理
        String str = dealParamJudge(yRAuthor);
        if(!"".equals(str)){
            return new ResultTemplate(str,null);
        }
        yRAuthor.setRegisteredUserInfoID(getWebUser(session).getRecID());
        yRAuthorServicesI.updateAuthor(yRAuthor,null);
        systemLogServicesI.log(YRAuthor.class.getName(),yRAuthor.getRecId() ,"创作者新增",getWebUser(session).getUserName());
        return new ResultTemplate("",null);
    }

    /**
     * 获取创作者上架状态字典
     */
    @RequestMapping(value = "getYRAuthorStatus")
    @ResponseBody
    public ResultTemplate getYRAuthorStatus(){
        return new ResultTemplate("", EnumAuthorStatus.getMapInfo());
    }

    /**
     * 通过作者名称判断作者是否存在
     * @param AuthorName
     * @param recId
     */
    @RequestMapping("getYRAuhorByName")
    @ResponseBody
    public ResultTemplate getYRAuhorByName(String AuthorName,Integer recId,HttpSession session){
        if (registeredUserInfoService.getById(getWebUser(session).getRecID()) == null){
            return new ResultTemplate("用户信息不存在",null);
        }
        if(recId != null){ //编辑
            YRAuthor author = yRAuthorServicesI.getById(recId);
            if (author == null){
                return new ResultTemplate("创作者信息不存在",null);
            }else if(author !=null){
                if(!author.getAuthorNickname().equals(AuthorName)){
                    YRAuthor yrAuthor = yRAuthorServicesI.getYRAuhorByName(AuthorName,getWebUser(session).getRecID());
                    if(yrAuthor !=null){
                        return new ResultTemplate("作者名称已存在",null);
                    }else {
                        return new ResultTemplate("",null);
                    }
                }else {
                    return new ResultTemplate("",null);
                }
            }
        }else if(recId == null){//新增
            YRAuthor yrAuthor = yRAuthorServicesI.getYRAuhorByName(AuthorName,getWebUser(session).getRecID());
            if(yrAuthor !=null){
                return new ResultTemplate("作者名称已存在",null);
            }else {
                return new ResultTemplate("",null);
            }
        }
        return new ResultTemplate("",null);
    }

    /**
     * 批量操作(修改创作者状态)
     * @param ids
     * @param status
     */
    @RequestMapping(value = "batchApply",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate batchApply(String ids, Integer status,HttpSession session){
        if(getWebUser(session) == null){
            return new ResultTemplate("请重新登录",null);
        }
        if(StringUtil.isBlank(ids) || status == null){
            return new ResultTemplate("参数有误！",null);
        }
        if(StringUtil.isNotBlank(ids)){
            for (String str : ids.split(",")){
                YRAuthor yrAuthor = yRAuthorServicesI.getAuthorInfoById(Integer.parseInt(str));
                if(yrAuthor == null){
                    return new ResultTemplate("创作者信息不存在",null);
                }
                if(status.compareTo(EnumAuthorStatus.上架.getIndex()) ==0){
                    if(StringUtil.isBlank(yrAuthor.getContentForm()) || StringUtil.isBlank(yrAuthor.getScenes())
                            || StringUtil.isBlank(yrAuthor.getCategory()) || yrAuthor.getCreatedPrice() == null
                            || StringUtil.isBlank(yrAuthor.getIntroduction()) || yrAuthor.getAuthorCreationTime() == null){
                        return new ResultTemplate("创作者信息不完整，请补全信息",null);
                    }
                }
            }
        }
        EnumAuthorStatus enumAuthorStatus = (EnumAuthorStatus)EnumUtil.valueOf(EnumAuthorStatus.class , status);
        if( enumAuthorStatus== null){
            return new ResultTemplate("状态错误！",null);
        }
        if (status!=null && status !=EnumAuthorStatus.未上架.getIndex() && status !=EnumAuthorStatus.上架.getIndex()){
            return new ResultTemplate("状态错误！",null);
        }
        yRAuthorServicesI.batchApply(ids, status,getWebUser(session).getRecID());
        for (String str : ids.split(",")) {
            systemLogServicesI.log(YRAuthor.class.getName(), Integer.parseInt(str), "将创作者状态修改为" + EnumUtil.valueOf(EnumAuthorStatus.class , status).getName(), getWebUser(session).getUserName());
        }
        return new ResultTemplate("",null);
    }

    /**
     * 批量修改价格
     * @param ids
     * @param pricePercent
     */
    @RequestMapping(value = "updateBatchPrice",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateBatchPrice(String ids, BigDecimal pricePercent,HttpSession session){
        if(getWebUser(session) == null){
            return new ResultTemplate("请重新登录",null);
        }
        if(StringUtil.isBlank(ids)){
            return new ResultTemplate("请先选择账号！",null);
        }
        if(pricePercent == null){
            return new ResultTemplate("请填写价格！",null);
        }
        if(pricePercent.compareTo(BigDecimal.ZERO) <= 0 || pricePercent.compareTo(new BigDecimal(999999999)) >0){
            return new ResultTemplate("价格范围是1-999999999之间的整数！",null);
        }
        yRAuthorServicesI.updateBatchPrice(ids, pricePercent);
        for (String str : ids.split(",")) {
            systemLogServicesI.log(YRAuthor.class.getName(), Integer.parseInt(str), "创作者修改价格" , getWebUser(session).getUserName());
        }
        return new ResultTemplate("",null);
    }

    @RequestMapping(value = "getAuthorsByName" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getAuthorsByName(YRAuthor data,HttpSession session){
        if(StringUtil.isBlank(data.getAuthorNickname())){
            return new ResultTemplate("参数为空" ,null);

        }
        data.setWebUser(getWebUser(session));
        List<YRAuthor> yrAuthors = yRAuthorServicesI.getAuthorsByName(data);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(yrAuthors) > 0){
            for(YRAuthor ele : yrAuthors){
                JSONObject object = new JSONObject();
                object.put("recId" , ele.getRecId());
                object.put("authorNickname" , ele.getAuthorNickname());
                result.add(object);
            }
        }
        return new ResultTemplate("" , result);
    }

    /**
     * 获取创作者的字典信息（内容形式、使用场景、擅长领域）
     */
    @RequestMapping("getAuthorDicinfo")
    @ResponseBody
    public ResultTemplate getAuthorDicinfo(){
        JSONObject jsonObject = yRAuthorServicesI.getAuthorDicinfo();
        return new ResultTemplate("",jsonObject);
    }

    @RequestMapping("getMyYrAuthor")
    @ResponseBody
    public ResultTemplate getMyYrAuthor(HttpSession session){
        List<YRAuthor> yrAuthors = yRAuthorServicesI.getByRegisterUserId(getWebUser(session).getRecID());
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(yrAuthors) > 0){
            for(YRAuthor author : yrAuthors){
                JSONObject object = new JSONObject();
                object.put("id" , author.getRecId());
                object.put("value" , author.getAuthorNickname());
                result.add(object);
            }
        }
        return new ResultTemplate("",result);
    }

    /**
     * 删除代表作—假删
     * @param recId
     */
    @RequestMapping("deleteAuthorPro")
    @ResponseBody
    public ResultTemplate deleteAuthorPro(Integer recId,HttpSession session){
        if(getWebUser(session) == null){
            return new ResultTemplate("请重新登录",null);
        }
        if(recId == null){
            return new ResultTemplate("参数有误",null);
        }
        if(recId != null){
            YRProduction yrProduction = yRProductionServicesI.getById(recId);
            if(yrProduction == null){
                return new ResultTemplate("作品不存在",null);
            }
        }
        yRProductionServicesI.deleteAuthorPro(recId);
        String name = yRProductionServicesI.getById(recId).getYrAuthor() == null ? "" : yRProductionServicesI.getById(recId).getYrAuthor().getAuthorNickname();
        systemLogServicesI.log(YRAuthor.class.getName(),recId ,"将创作者--" + name +"的代表作删除",getWebUser(session).getUserName());
        return new ResultTemplate("",null);
    }
}
