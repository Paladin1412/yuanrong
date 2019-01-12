package com.yuanrong.admin.server.controller.author;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.base.*;
import com.yuanrong.admin.seach.AuthorListParamSeach;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.ExcelUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
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
    public ResultTemplate list(AuthorListParamSeach data , BaseModel baseModel){
        data.setCurrLoginUser(getUser());
        PageInfo<Map> yRAuthorPageInfo = yRAuthorServicesI.list(data , baseModel);
        JSONArray resultList = new JSONArray();
        if(!CollectionUtil.isEmpty(yRAuthorPageInfo.getList())){
            for(Map<String ,Object> ele : yRAuthorPageInfo.getList()){
                resultList.add(packageJSON(ele));
            }
        }
        return new ResultTemplate(yRAuthorPageInfo,resultList);
    }

    /**
     * 返回结果封装
     * @param ele
     * @return
     */
    private Object packageJSON(Map<String, Object> ele) {
        JSONObject json = new JSONObject();
        //创作者ID
        json.put("recId",ele.get("recId"));
        //创作者名称
        json.put("authorNickname",ele.get("authorNickname"));
        //创作者上架状态
        json.put("authorStatus",ele.get("authorStatus") == null ? "" : EnumUtil.valueOf(EnumAuthorStatus.class,ele.get("authorStatus").toString()).getName());
        //创作者上架状态值
        json.put("authorStatusIndex",ele.get("authorStatus"));
        /*//创作者已发布作品上架数
        json.put("publishUpNum",ele.get("publishUpNum") == null ? 0: ele.get("publishUpNum"));
        //创作者已发布作品总数
        json.put("publishNum",ele.get("publishNum") ==null ? 0 : ele.get("publishNum"));
        //创作者未发布作品上架数
        json.put("notPublishUpNum",ele.get("notPublishUpNum") ==null ? 0 : ele.get("notPublishUpNum"));
        //创作者未发布作品总数
        json.put("notPublishNum",ele.get("notPublishNum") == null ? 0 : ele.get("notPublishNum"));*/
        //用户简称
        json.put("UserName",ele.get("NickName"));
        //媒介经理
        json.put("RealName",ele.get("RealName"));
        //用户审核状态值
        json.put("userStatusIndex",ele.get("sellerStatusValue"));
        //用户审核状态
        json.put("userStatus",ele.get("sellerStatusValue") == null ? "" : EnumUtil.valueOf(EnumUserRoleLicenseStatus.class, ele.get("sellerStatusValue").toString()).getName());
        //创作者审核失败原因
        json.put("authorAuditFailReason",ele.get("authorAuditFailReason"));
        //用户Id
        json.put("userId",ele.get("userId"));
        //创作者上架代表作个数
        json.put("RepresentativeNum",ele.get("RepresentativeNum") == null ? 0 : ele.get("RepresentativeNum"));
        //创作者作品数
        json.put("proNum",ele.get("proNum") ==null ? 0 : ele.get("proNum"));
        //创作者上架作品数
        json.put("proUpNum",ele.get("proUpNum") == null ? 0 : ele.get("proUpNum"));
        //创作者入库时间
        try {
            json.put("createdTime",ele.get("createdTime") == null || "".equals(ele.get("createdTime")) ? "" : DateUtil.format(DateUtil.parseDate(ele.get("createdTime").toString(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //创作者创建来源
        json.put("createSourceIndex",ele.get("createSource"));
        json.put("createSource",ele.get("createSource") == null ? "" : EnumUtil.valueOf(EnumChannel.class,ele.get("createSource").toString()).getName());
        //创作者是否匿名
        json.put("isAnonymousIndex",ele.get("isAnonymous"));
        //创作者是否匿名
        json.put("isAnonymous",ele.get("isAnonymous") == null ? "" : EnumUtil.valueOf(EnumYesOrNo.class,ele.get("isAnonymous").toString()).getName());
        //创作者下架原因
        json.put("authorUnderReason",ele.get("authorUnderReason"));
        return json;
    }

    /**
     * 后台—创作者详情
     * @param recId
     */
    @RequestMapping("authorDetail")
    @ResponseBody
    public ResultTemplate getAuthorDetailInfo(Integer recId){
        if(recId ==null || recId <=0){
            return new ResultTemplate("参数输入有误",null);
        }
        JSONObject result = new JSONObject();
        JSONArray authorProList = new JSONArray();
        //创作者详情信息
        List<Map<String, Object>> authorlist = yRAuthorServicesI.getAuthorDetailInfo(recId);
        if(CollectionUtil.size(authorlist) < 1){
            return new ResultTemplate("创作者信息不存在",null);
        }
        //创作者信息封装
        JSONObject authorInfo =YRAuthor.packageAuthorParam(authorlist);
        //母IP
        authorInfo.put("parentIP",authorlist.get(0).get("parentIP"));
        //子IP
        authorInfo.put("sonIP",authorlist.get(0).get("sonIP"));
        result.put("authorInfo",authorInfo );
        /*//创作者的代表作品列表
        List<Map<String, Object>> authorProduction = yRProductionServicesI.getAuthorProList(recId);
        if(CollectionUtil.size(authorProduction) > 0){
            //代表作品封装
            authorProList = YRProduction.packageAuthorProduction(authorProduction);
        }
        result.put("authorProList",authorProList);*/
        Map<String , Object> map = yRProductionServicesI.getAuthorProNum(recId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proNum",map.get("proNum"));
        jsonObject.put("upNum",map.get("upNum"));
        jsonObject.put("RepresentativeNum",map.get("RepresentativeNum"));
        result.put("authorProNum",jsonObject);
        return new ResultTemplate("",result);
    }

    /**
     * 后台—创作者新增
     * @param author
     */
    @RequestMapping(value = "yRAuthor_save",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate save(YRAuthor author){
        //判断参数
        String str = judgeParam(author);
        if(str !=null && !"".equals(str)){
            return new ResultTemplate(str,null);
        }
        //再次判断作者名称是否存在
        YRAuthor authorinfo = yRAuthorServicesI.getYRAuhorByName(author.getAuthorNickname(),author.getRegisteredUserInfoID());
        if(authorinfo !=null){
            return new ResultTemplate("作者名称已存在",null);
        }
        author.setAuthorStatus(EnumAuthorStatus.待审核.getIndex());
        author.setCreateSource(EnumChannel.后台创建.getIndex());
        author.setIsAnonymous(EnumYesOrNo.FORBIDDEN.getIndex());
        JSONObject jsonObject = new JSONObject();
        Integer authorId = yRAuthorServicesI.saveAuthor(author);
        jsonObject.put("authorId",authorId);
        systemLogServicesI.log(YRAuthor.class.getName(),authorId ,"创作者新增",getUser().getRealName());
        return new ResultTemplate("",jsonObject);
    }

    /**
     * 判断参数
     * @param author
     * @return
     */
    private String judgeParam(YRAuthor author) {
        //用户ID
        if(author.getRegisteredUserInfoID() == null){
            return "用户ID不能为空";
        }
        if(author.getRegisteredUserInfoID() != null){
            if(registeredUserInfoService.getById(author.getRegisteredUserInfoID()) == null){
                return "用户不存在";
            }
        }
        //创作者名称
        if(StringUtil.isBlank(author.getAuthorNickname())){
            return "创作者名称不能为空";
        }
        //创作者头像
//        if(StringUtil.isBlank(author.getAuthorImg())){
//            return "创作者头像不能为空";
//        }
        //创作者简介
        if(StringUtil.isBlank(author.getIntroduction())){
            return "创作者简介不能为空";
        }
        //创作形式
        if(StringUtil.isBlank(author.getContentForm())){
            return "内容类型不能为空";
        }
        if(StringUtil.isNotBlank(author.getContentForm())){
            if(author.getContentForm().contains("其它")){
                if(author.getContentForm().split(",").length >4){
                    return "内容类型最多只能选择4个";
                }
            }else {
                if(author.getContentForm().split(",").length >3){
                    return "内容类型最多只能选择3个";
                }
            }
            for (String st : author.getContentForm().split(",")){
                if(st.equals("")){
                    return "内容类型参数有问题";
                }
            }
        }
        //使用场景
        if (StringUtil.isBlank(author.getScenes())){
            return "创作题材不能为空";
        }
        if(StringUtil.isNotBlank(author.getScenes())){
            if(author.getScenes().contains("其它")){
                if(author.getScenes().split(",").length >4){
                    return "创作题材最多只能选择4个";
                }
            }else {
                if(author.getScenes().split(",").length >3){
                    return "创作题材最多只能选择3个";
                }
            }
            for(String str : author.getScenes().split(",")){
                if(str.equals("")){
                    return "创作题材参数有问题";
                }
            }
        }
        //创作者原创用时
        if(author.getAuthorCreationTime() == null){
            return "原创用时不能为空";
        }
        if(author.getAuthorCreationTime() != null && (author.getAuthorCreationTime() < 1 || author.getAuthorCreationTime() > 30)){
            return "创作者原创用时的范围1-30之间";
        }
        //原创报价
        if(author.getCreatedPrice() == null){
            return "原创报价不能为空";
        }
        if(author.getCreatedPrice() != null && (author.getCreatedPrice().compareTo(BigDecimal.ZERO) <=0 || author.getCreatedPrice().compareTo(new BigDecimal(999999999)) > 0)){
            return "原创报价的范围是1-999999999之间";
        }
        //擅长领域
        if(StringUtil.isBlank(author.getCategory())){
            return "擅长领域不能为空";
        }
        if(StringUtil.isNotBlank(author.getCategory())){
            if(author.getCategory().split(",").length >3){
                return "擅长领域最多只能选择3个";
            }
            for (String str : author.getCategory().split(",")){
                DictInfo dic = dictInfoServicesI.getById(Integer.parseInt(str));
                if(dic == null){
                    return "擅长领域不存在";
                }
            }
        }
        /*//作品标签的内容属性
        if(StringUtil.isBlank(author.getContentLable())){
            return "作品标签的内容属性为空";
        }
        if(StringUtil.isNotBlank(author.getContentLable())){
            if(author.getContentLable().split(",").length >5){
                return "内容属性最多只能选择5个";
            }
            for (String str : author.getContentLable().split(",")){
                Lable lable =  lableServicesI.getById(Integer.parseInt(str));
                if(lable == null){
                    return "作品标签不存在";
                }
            }
        }
        //作品标签的表现风格
        if(StringUtil.isBlank(author.getStyleLable())){
            return "作品标签的表现风格为空";
        }
        if(StringUtil.isNotBlank(author.getStyleLable())){
            if(author.getStyleLable().split(",").length >5){
                return "表现风格最多只能选择5个";
            }
            for (String str : author.getStyleLable().split(",")){
                Lable lable =  lableServicesI.getById(Integer.parseInt(str));
                if(lable == null){
                    return "作品标签不存在";
                }
            }
        }*/
       /* //母IP
        if (StringUtil.isNotBlank(author.getParentIP())) {
            if(author.getParentIP().split(",").length >3){
                return "母IP最多只能选择3个";
            }
            for(String str : author.getParentIP().split(",")){
                IPLable ipLable =  iPLableServicesI.getById(Integer.parseInt(str));
                if(ipLable == null){
                    return "IP标签不存在";
                }
            }
        }
        //子IP
        if (StringUtil.isNotBlank(author.getSonIP())) {
            if(author.getSonIP().split(",").length >5){
                return "子IP最多只能选择5个";
            }
            for(String str : author.getSonIP().split(",")){
                IPLable ipLable =  iPLableServicesI.getById(Integer.parseInt(str));
                if(ipLable == null){
                    return "IP标签不存在";
                }
            }
        }*/
        return "";
    }

    /**
     * 后台—创作者修改页面
     * @param recId
     */
    @RequestMapping(value = "yRAuthor_update",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate update(Integer recId){
        if(recId == null){
            return new ResultTemplate("创作者Id不能为空！",null);
        }
        //查询创作者信息
        List<Map<String, Object>> authorlist = yRAuthorServicesI.getAuthorInfoUpdate(recId);
        if(CollectionUtil.size(authorlist) < 1){
            return new ResultTemplate("创作者信息不存在",null);
        }
        JSONObject authorInfo = YRAuthor.packageAuthorParamUpdate(authorlist);
        //创作者标签—内容属性
        authorInfo.put("lableContentIds",authorlist.get(0).get("lableContentIds"));
        //创作者标签—表现风格
        authorInfo.put("lableStyleIds",authorlist.get(0).get("lableStyleIds"));
        //创作者IP—母IP
        authorInfo.put("parentIPIds",authorlist.get(0).get("parentIPIds"));
        //创作者IP—子IP
        authorInfo.put("sonIPIds",authorlist.get(0).get("sonIPIds"));
        //用户简称
        authorInfo.put("NickName",authorlist.get(0).get("NickName"));
        //用户注册手机号
        authorInfo.put("Mobile",authorlist.get(0).get("Mobile"));
        return new ResultTemplate("",authorInfo);
    }

    /**
     * 后台—创作者修改提交
     * @param yrAuthor
     */
    @RequestMapping( value = "yRAuthor_updateOk"  , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateOk(YRAuthor yrAuthor){
        if (yrAuthor.getRecId() == null){
            return new ResultTemplate("创作者Id有误",null);
        }
        YRAuthor author = yRAuthorServicesI.getById(yrAuthor.getRecId());
        if(yrAuthor.getRecId() != null){
            if(author== null){
                return new ResultTemplate("创作者信息不存在",null);
            }
        }
        //参数验证
        String str = judgeParam(yrAuthor);
        if(StringUtil.isNotBlank(str)){
            return new ResultTemplate(str,null);
        }
        yRAuthorServicesI.updateAuthor(yrAuthor,1);
        systemLogServicesI.log(YRAuthor.class.getName(),yrAuthor.getRecId() ,"创作者修改",getUser().getRealName());
        return new ResultTemplate("",null);
    }

    /**
     * 获取创作者上架状态字典
     */
    @RequestMapping(value = "exs_getYRAuthorStatus")
    @ResponseBody
    public ResultTemplate getYRAuthorStatus(){
        return new ResultTemplate("", EnumAuthorStatus.getMapInfo());
    }

    /**
     * 获取创作者创建渠道字典
     */
    @RequestMapping(value = "exs_getYRAuthorChannel")
    @ResponseBody
    public ResultTemplate getYRAuthorChannel(){
        return new ResultTemplate("", EnumChannel.getMapInfo());
    }

    /**
     * 通过作者名称判断作者是否存在
     * @param AuthorName
     * @param recID
     * @param authorId
     */
    @RequestMapping(value = "getYRAuhorByName",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getYRAuhorByName(String AuthorName, Integer recID, Integer authorId){
        if(recID ==null){
            return new ResultTemplate("参数有误",null);
        }
        if(recID != null && registeredUserInfoService.getById(recID) == null){
            return new ResultTemplate("用户信息不存在",null);
        }
        if(authorId != null){ //编辑
            YRAuthor author = yRAuthorServicesI.getById(authorId);
            if (author == null){
                return new ResultTemplate("创作者信息不存在",null);
            }else if(author !=null){
                if(!author.getAuthorNickname().equals(AuthorName)){
                    if(yRAuthorServicesI.getYRAuhorByName(AuthorName,recID) !=null){
                        return new ResultTemplate("作者名称已存在",null);
                    }else {
                        return new ResultTemplate("",null);
                    }
                }else {
                    return new ResultTemplate("",null);
                }
            }
        }else if(authorId == null){//新增
            YRAuthor yrAuthor = yRAuthorServicesI.getYRAuhorByName(AuthorName,recID);
            if(yrAuthor !=null){
                return new ResultTemplate("作者名称已存在",null);
            }else {
                return new ResultTemplate("",null);
            }
        }
        return new ResultTemplate("",null);
    }

    /**
     * 后台—批量操作(申请上架、下架、审核通过、审核不通过)
     * @param ids
     * @param status (2-上架 | 0-下架 | 1-审核通过 | 3-审核不通过)
     * @param reason 审核失败原因/作品下架原因
     */
    @RequestMapping(value = "batchApply",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate applyBatch(String ids, Integer status,String reason){

        if(StringUtil.isBlank(ids) || status == null){
            return new ResultTemplate("参数有误！",null);
        }

        if(StringUtil.isNotBlank(ids)){
            String[] split = ids.split(",");
            for (String str : split){
                YRAuthor yrAuthor = yRAuthorServicesI.getAuthorInfoById(Integer.parseInt(str));
                if(yrAuthor == null){
                    return new ResultTemplate(str + "创作者信息不存在",null);
                }
                if(status.compareTo(EnumAuthorStatus.上架.getIndex()) ==0) {
                    if (StringUtil.isBlank(yrAuthor.getContentForm()) || StringUtil.isBlank(yrAuthor.getScenes())
                            || StringUtil.isBlank(yrAuthor.getCategory()) || yrAuthor.getCreatedPrice() == null
                            || StringUtil.isBlank(yrAuthor.getIntroduction()) || yrAuthor.getAuthorCreationTime() == null) {
                        return new ResultTemplate("创作者信息不完整，请补全信息", null);
                    }
                }
            }
        }
        EnumAuthorStatus enumAuthorStatus = (EnumAuthorStatus)EnumUtil.valueOf(EnumAuthorStatus.class , status);
        if( enumAuthorStatus== null){
            return new ResultTemplate("状态错误！",null);
        }
        if(EnumAuthorStatus.审核不通过.getIndex() == status){//审核不通过
            if (StringUtil.isBlank(reason) ){
                return new ResultTemplate("请选择审核失败理由",null);
            }
        }
        if(EnumAuthorStatus.未上架.getIndex() == status){//下架
            if (StringUtil.isBlank(reason)){
                return new ResultTemplate("请选择下架理由",null);
            }
            /*if(reason != null){
                DictInfo dictInfo = dictInfoServicesI.getDictInfoByTypeAndID(EnumDictInfoType.作者作品下架原因.getIndex(),Integer.parseInt(reason));
                if(dictInfo == null){
                    return new ResultTemplate("下架原因不存在",null);
                }
            }*/
        }
        /*if(status == EnumAuthorStatus.上架.getIndex()){
            for(String str : ids.split(",")){
                //查询代表作，如果创作者代表作的个数小于3，状态不改变(不上架)
                if(yRProductionServicesI.findAuthorMagnum(Integer.parseInt(str)).size() < 3){
                    return new ResultTemplate("创作者代表作品小于3，不允许上架",null);
                }
            }
        }*/
        /*if(status == EnumAuthorStatus.审核不通过.getIndex() || status == EnumAuthorStatus.待审核.getIndex()){//如果是审核通过和审核失败，判断当前登陆用户的角色是不是运营
            if(getUser().getAdminRoleID() != EnumAdminUserRole.产品运营.getIndex()){
                return new ResultTemplate("对不起，您的权限不足",null);
            }
        }*/
        yRAuthorServicesI.batchApplyBack(ids, status,reason,getUser().getRealName());
        if(status == EnumAuthorStatus.上架.getIndex()){
            for (String str : ids.split(",")){
                systemLogServicesI.log(YRAuthor.class.getName(),Integer.parseInt(str) ,"将创作者状态修改为"+EnumAuthorStatus.待审核.getName(),getUser().getRealName());
            }
        }else {
            for (String str : ids.split(",")){
                systemLogServicesI.log(YRAuthor.class.getName(),Integer.parseInt(str) ,"将创作者状态修改为"+EnumUtil.valueOf(EnumAuthorStatus.class , status).getName() + "，下架理由为："+reason,getUser().getRealName());
            }
        }
        return new ResultTemplate("",null);
    }

    /**
    * 批量添加创作者
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/28 16:54
    */
    @RequestMapping("exs_batchSaveAuthor")
    @ResponseBody
    public ResultTemplate batchSaveAuthor(MultipartFile file){
        if(file == null){
            return new ResultTemplate("文件信息为空！" , null);
        }
        List<List<String>> result = null;
        try {
            String fileName = file.getOriginalFilename();
            if(!".xlsx".equals(fileName.substring(fileName.indexOf(".")))){
                return new ResultTemplate("文件格式有问题！" , null);
            }
            result = ExcelUtil.read(file, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        if (CollectionUtil.size(result) <= 1) {
            return new ResultTemplate("数据不存在！", null);
        }
        if(result.get(0).size() < 17){
            return new ResultTemplate("表格数据不完整，无法导入！", null);
        }
        // Object[] objects = yRAuthorServicesI.getErrorAndAuthorsByParsedList(result);
        JSONObject jsonR = yRAuthorServicesI.batchGetSaveSucceedInfoAndErrorInfoByList(result);
        return new ResultTemplate("",jsonR);
    }
    /**
    * 批量修改作者报价
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/30 8:43
    */
    @RequestMapping( value ="exs_batchUpdateAuthorPrice")
    @ResponseBody
    public ResultTemplate batchUpdateAuthorQuotedPrice(MultipartFile file){
        if(file == null){
            return new ResultTemplate("文件信息为空！" , null);
        }
        List<List<String>> result = null;
        try {
            String fileName = file.getOriginalFilename();
            if(!".xlsx".equals(fileName.substring(fileName.indexOf(".")))){
                return new ResultTemplate("文件格式有问题！" , null);
            }
            result = ExcelUtil.read(file, 0);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultTemplate("文件格式有问题！" , null);
        }
        //
        if (CollectionUtil.size(result) <= 1) {
            return new ResultTemplate("数据不存在！", null);
        }
        if(result.get(0).size() < 4){
            return new ResultTemplate("表格数据不完整，无法导入！", null);
        }
        JSONObject data = yRAuthorServicesI.batchGetUpdatePriceSucceedInfoAndErrorInfoByList(result);
        return new ResultTemplate("",data);
    }

    /**
     * 根据ID获取创作者的部分信息
     */
    @RequestMapping(value = "exs_getYRAuthorInfoById")
    @ResponseBody
    public ResultTemplate getYRAuthorInfoById(Integer yrAuthorId){
        if(yrAuthorId == null || yrAuthorId <=0){
            return new ResultTemplate("参数错误" , null);
            
        }
        YRAuthor yrAuthor = yRAuthorServicesI.getById(yrAuthorId);
        if(yrAuthor == null){
            return new ResultTemplate("作者不存在" , null);
            
        }
        JSONObject result = new JSONObject();
        result.put("yrAuthorId" , yrAuthor.getRecId());
        result.put("yrAuthorNickName" , yrAuthor.getAuthorNickname());
        result.put("registerNickName" , yrAuthor.getRegisteredUserInfo() == null ? "" : yrAuthor.getRegisteredUserInfo().getNickName());
        result.put("registerMobile" ,yrAuthor.getRegisteredUserInfo() == null ? "" : yrAuthor.getRegisteredUserInfo().getMobile());
        result.put("registerUserId" , yrAuthor.getRegisteredUserInfoID());
        return new ResultTemplate("" , result);
    }

    /**
     * 获取创作者的字典信息（内容形式、使用场景、擅长领域）
     */
    @RequestMapping(value = "getAuthorDicinfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getAuthorDicinfo(){
        JSONObject jsonObject = yRAuthorServicesI.getAuthorDicinfo();
        return new ResultTemplate("",jsonObject);
    }

    /**
     * 删除代表作—假删
     * @param recId
     */
    @RequestMapping(value = "deleteAuthorPro",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate deleteAuthorPro(Integer recId){
        if(recId == null){
            return new ResultTemplate("参数有误",null);
        }
        if(recId != null){
            if(yRProductionServicesI.getById(recId) == null){
                return new ResultTemplate("作品不存在",null);
            }
        }
        yRProductionServicesI.deleteAuthorPro(recId);
        String name = yRProductionServicesI.getById(recId).getYrAuthor() == null ? "" : yRProductionServicesI.getById(recId).getYrAuthor().getAuthorNickname();
        systemLogServicesI.log(YRAuthor.class.getName(),recId ,"将创作者" + name +"的代表作删除",getUser().getRealName());
        return new ResultTemplate("",null);
    }

    /**
     * 后台—编辑+审核
     * @param yrAuthor
     */
    @RequestMapping("yRAuthor_updateAudit")
    @ResponseBody
    public ResultTemplate updateAudit(YRAuthor yrAuthor){
        if(yrAuthor.getRecId() == null ){
            return new ResultTemplate("创作者Id错误",null);
        }
        YRAuthor author = yRAuthorServicesI.getById(yrAuthor.getRecId());
        if(author == null){
            return new ResultTemplate("创作者信息不存在",null);
        }
        if(yrAuthor.getAuthorStatus() == null){
            return new ResultTemplate("请选择操作按钮",null);
        }
        if(author.getAuthorStatus() != EnumAuthorStatus.待审核.getIndex()){
            return new ResultTemplate("当前创作者状态有误，不可进行审核操作",null);
        }
        if(yrAuthor.getAuthorStatus() == EnumAuthorStatus.待审核.getIndex()){//审核通过
            //参数验证
            String str = judgeParam(yrAuthor);
            if(StringUtil.isNotBlank(str)){
                return new ResultTemplate(str,null);
            }
            yRAuthorServicesI.updateAuthor(yrAuthor,1);
            yRAuthorServicesI.batchApplyBack(String.valueOf(yrAuthor.getRecId()),yrAuthor.getAuthorStatus() ,"",getUser().getRealName());
            systemLogServicesI.log(YRAuthor.class.getName(),yrAuthor.getRecId() ,"将创作者状态修改为"+ EnumAuthorStatus.上架.getName() ,getUser().getRealName());
        }else if(yrAuthor.getAuthorStatus() == EnumAuthorStatus.审核不通过.getIndex()){//审核不通过
            if (StringUtil.isBlank(yrAuthor.getAuthorAuditFailReason())){
                return new ResultTemplate("请选择审核失败理由",null);
            }
            yRAuthorServicesI.batchApplyBack(String.valueOf(yrAuthor.getRecId()),yrAuthor.getAuthorStatus() ,yrAuthor.getAuthorAuditFailReason(),getUser().getRealName());
            systemLogServicesI.log(YRAuthor.class.getName(),yrAuthor.getRecId() ,"将创作者状态修改为"+EnumAuthorStatus.审核不通过.getName() +"，失败理由为："+yrAuthor.getAuthorAuditFailReason(),getUser().getRealName());
        }
        return new ResultTemplate("",null);
    }

    @RequestMapping("exs_getMyYrAuthor")
    @ResponseBody
    public ResultTemplate getMyYrAuthor(Integer registerUserInfoId){
        if(registerUserInfoId == null || registerUserInfoId.intValue() <=0 ){
            return new ResultTemplate("注册用户为空" ,null);
        }
        List<YRAuthor> yrAuthors = yRAuthorServicesI.getByRegisterUserId(registerUserInfoId);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(yrAuthors) > 0){
            for(YRAuthor author : yrAuthors){
                JSONObject object = new JSONObject();
                object.put("value" , author.getAuthorNickname());
                object.put("id" , author.getRecId());
                result.add(object);
            }
        }
        return new ResultTemplate("",result);
    }
}
