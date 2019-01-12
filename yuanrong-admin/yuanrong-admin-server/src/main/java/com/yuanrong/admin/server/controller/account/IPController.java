package com.yuanrong.admin.server.controller.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDictInfoType;
import com.yuanrong.admin.Enum.EnumPlatformIPAccountStatus;
import com.yuanrong.admin.bean.account.IP;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.seach.IPListParamSeach;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.server.controller.system.SystemUserController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.ExcelUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * IP列表
 * @ClassName IPController
 * @Author Jss
 * @Date 2018/4/26
 */
@Controller
@RequestMapping("ip")
public class IPController extends BaseController {
    private static final Logger logger = Logger.getLogger(SystemUserController.class);

    /**
     * IP列表显示
     * @param data
     * @param baseModel
     * @return
     */
    @RequestMapping(value = "ip_list",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(IPListParamSeach data , BaseModel baseModel){
        data.setCurrLoginUser(getUser());
        if (StringUtil.isNoneBlank(data.getiPName())){
            data.setiPNames(data.getiPName().split(" "));
        }
        if(StringUtil.isNoneBlank(data.getIds())){
            data.setRecIDs(data.getIds().toString().split(" "));
        }

        PageInfo<Map> IPPageInfo = ipServicesI.list(data , baseModel);
        JSONArray resultArray = new JSONArray();
        if(CollectionUtil.size(IPPageInfo.getList()) > 0){
            for(Map<String ,Object> map : IPPageInfo.getList()){
                resultArray.add(dealParamJSONObject(map));
            }
        }
        return new ResultTemplate(IPPageInfo, resultArray);
    }

    /**
     * 参数处理
     * @param map
     * @return
     */
    private Object dealParamJSONObject(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        //注册用户简称-用户ID
        jsonObject.put("IPId" , map.get("IPId"));
        //注册用户简称-用户名
        jsonObject.put("IPName" , map.get("IPName"));
        //账号-账号名
        jsonObject.put("iPWholeNetworkPrice" , map.get("iPWholeNetworkPrice"));
        try {
            //IP-报价有效时间
            jsonObject.put("invalidTime" , map.get("invalidTime") ==null ? "" : DateUtil.format(DateUtil.parseDate(map.get("invalidTime").toString(), "yyyy-MM-dd"),"yyyy-MM-dd"));
        }catch (Exception e){
            e.printStackTrace();
        }
        //IP-账号上架数
        jsonObject.put("putNum" , map.get("putNum") == null ? 0 : map.get("putNum"));
        if(jsonObject.getInteger("putNum") != 0){
            jsonObject.put("accountStatus" , EnumPlatformIPAccountStatus.上架.getIndex());
        }else{
            jsonObject.put("accountStatus" , "");
        }
        //IP-账号数
        jsonObject.put("num" , map.get("num") == null ? 0 : map.get("num"));
        //IP-用户简称
        jsonObject.put("NickName" , map.get("NickName"));
        //IP-用户ID
        jsonObject.put("userId" , map.get("userId"));
        //IP-媒介经理
        jsonObject.put("RealName" , map.get("RealName"));
        //IP-媒介经理
        jsonObject.put("categoryID" , map.get("categoryID"));

        return jsonObject;
    }

    /**
     * IP添加
     * @param data
     */
    @RequestMapping(value = "ip_save", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate IPSave(IP data){
        JSONObject json = new JSONObject();
        if(data !=null){
            if(StringUtil.isBlank(data.getiPName())){
                return new ResultTemplate("IP名称不能为空!" , null);
            }
            if(data.getRegisteredUserInfoID() <=0){
                return new ResultTemplate("用户不能为空!" , null);
            }
            List <IP> ipList = ipServicesI.getByIPName(data.getiPName(),data.getRegisteredUserInfoID());
            if(ipList.size() >0){
                return new ResultTemplate("此IP名称在本用户下已存在",null);
            }
            if ("".equals(data.getInvalidTime())){
                data.setInvalidTime(null);
            }
            try{
                Integer recId =ipServicesI.insertSelective(data);
                json.put("IPId",recId);
            }catch (Exception e){
                e.printStackTrace();
                return new ResultTemplate("数据库操作失败！" , null);
            }
        }
        return new ResultTemplate("" , json);
    }

    /**
     * IP详情
     * @param data
     * @param baseModel
     * @return
     */
    @RequestMapping(value = "ip_detail" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate ipDetail(IP data , BaseModel baseModel, HttpServletRequest request){
        String recID =request.getParameter("recID");
        if(recID !=null && !"".equals(recID)){
            JSONObject result = new JSONObject();
            JSONObject iPInfo = new JSONObject();
            JSONArray resultList = new JSONArray();
            //IP信息
            IP ip = ipServicesI.selectById(Integer.parseInt(recID));
            if(ip !=null){
                iPInfo.put("recID",ip.getRecID());      //IPID
                iPInfo.put("iPName",ip.getiPName());    //IP名称
                iPInfo.put("iPImageHeadUrl",ip.getiPImageHeadUrl() == null ? "" : ip.getiPImageHeadUrl());//IP头像
                iPInfo.put("iPFans",ip.getiPFans() == null ? "" : ip.getiPFans());    //IP粉丝
                iPInfo.put("iPIntroduction",ip.getiPIntroduction() == null ? "" : ip.getiPIntroduction());//IP简介
                iPInfo.put("iPWholeNetworkPrice",ip.getiPWholeNetworkPrice() == null ? "" : ip.getiPWholeNetworkPrice());//IP全网报价
                iPInfo.put("isOriginal",ip.getIsOriginal() == null ? 0 : ip.getIsOriginal());//是否原创
                try {
                    iPInfo.put("invalidTime",ip.getInvalidTime() ==null ? "" : DateUtil.format(DateUtil.parseDate(ip.getInvalidTime(), "yyyy-MM-dd"),"yyyy-MM-dd" ));//报价失效时间
                }catch (Exception e){
                    e.printStackTrace();
                }

                iPInfo.put("categoryID",ip.getCategoryID());//行业分类
            }else {
                return new ResultTemplate("IP参数有误",null);
            }
            //IP账号列表
            List<Map<String, Object>> accountList = ipServicesI.findAccountByIPId(recID);
            if(accountList!=null && accountList.size() > 0){
                for (Map<String, Object> account : accountList) {
                    JSONObject accountInfo = new JSONObject();
                    accountInfo.put("id",account.get("id"));
                    accountInfo.put("icoUrl",account.get("icoUrl"));
                    accountInfo.put("NAME",account.get("NAME"));
                    accountInfo.put("headImageUrlLocal",account.get("headImageUrlLocal"));
                    accountInfo.put("accountStatus",account.get("accountStatus"));
                    EnumPlatformIPAccountStatus status = (EnumPlatformIPAccountStatus) EnumUtil.valueOf(EnumPlatformIPAccountStatus.class , account.get("accountStatus").toString());
                    accountInfo.put("accountStatusTag",status.getName());
                    resultList.add(accountInfo);
                }
            }
//            resultList.addAll(accountList);
            result.put("iPInfo",iPInfo);
            result.put("accountList",resultList);
            return new ResultTemplate("",result);
        }else {
            return new ResultTemplate("IP参数有误",null);
        }
    }

    /**
     * 查询用户无IP归属的已有账号列表
     * @param data
     * @param baseModel
     * @return
     */
    @RequestMapping(value = "ip_findAccount", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate ipFindAccount(PlatformIPAccount data , BaseModel baseModel){
        if(data == null || data.getRegisteredUserInfoID() == 0){
            return new ResultTemplate("用户参数有误",null);
        }
        PageInfo<PlatformIPAccount> IPPageInfo = platformIPAccountServicesI.ipFindAccount(data , baseModel);
        return new ResultTemplate(IPPageInfo);
    }

    /**
     * 已有账号和IP关联
     * @param accountId
     * @param ipID
     */
    @RequestMapping(value = "ip_saveAccount", method = RequestMethod.POST )
    @ResponseBody
    public ResultTemplate ipSaveAccount(String accountId , Integer ipID){

        if(StringUtil.isBlank(accountId)){
            return new ResultTemplate("账号参数有误",null);
        }
        if(ipID ==null || ipID <= 0 ){
            return new ResultTemplate("IP参数有误",null);
        }
        platformIPAccountServicesI.updateAccount(accountId,ipID);
        return new ResultTemplate("" , null);
    }

    /**
     * 修改IP/移除IP对应的账号
     * @param ip
     * @param accountId
     */
    @RequestMapping(value = "ip_updateIpAcount" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate ipUpdateAccount(IP ip, String accountId){
        if (ip == null || ip.getRecID() ==null){
            return new ResultTemplate("IP参数有误",null);
        }
        if ("".equals(ip.getInvalidTime())){
            ip.setInvalidTime(null);
        }
        ipServicesI.updateIPAccount(ip, accountId);
        return new ResultTemplate("" , null);
    }

    /**
     * 通过IPID获取用户信息，IP信息
     * @param recID
     * @return
     */
    @RequestMapping("ip_ipUser")
    @ResponseBody
    public ResultTemplate findUserByIP(Integer recID){
        if(recID ==null || recID <= 0){
            return new ResultTemplate("IP参数有误" , null);
        }
        List<Map<String, Object>> list = ipServicesI.findUserByIP(recID);
        return new ResultTemplate("",list);
    }

    /**
     * 通过用户ID获取用户信息包括媒介经理(IP添加页面)
     * @param recID
     */
    @RequestMapping("ip_user")
    @ResponseBody
    public ResultTemplate findUserById(Integer recID){
        if(recID == null || recID <= 0){
            return new ResultTemplate("用户参数有误" , null);
        }
        List<Map<String ,Object>> list =registeredUserInfoService.getUserById(recID);
        return new ResultTemplate("",list);
    }

    /**
     * 验证IP Excel文件
     * userID	Ip名称	行业分类   IP粉丝	IP简介	IP全网发布报价（元）    价格有效期结束时间
     * @param file
     * @throws IOException
     */
    @RequestMapping(value = "ip_vilidateIp" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate vilidateIPExcel(MultipartFile file) {

        if(file == null){
            return new ResultTemplate("文件信息为空！" , null);
        }

        try {
            String fileName = file.getOriginalFilename();
            if(!".xlsx".equals(fileName.substring(fileName.indexOf(".")))){
                return new ResultTemplate("文件格式有问题！" , null);
            }
            List<List<String>> result = ExcelUtil.read(file , 0);
            if(CollectionUtil.size(result) <=1){
                return new ResultTemplate("数据不存在！" , null);
            }
            result.remove(0);   //删除表头
            JSONObject info = new JSONObject();
            JSONArray rightList = new JSONArray();
            JSONArray erroList = new JSONArray();
            for (int i = 0; i< result.size(); i++) {
//                JSONObject erroInfo = new JSONObject();
                JSONObject rightInfo = new JSONObject();
                StringBuilder str = new StringBuilder();
                //userID判断
                if(StringUtil.isBlank(result.get(i).get(0))){
                    JSONObject erroInfo = new JSONObject();
                    erroInfo.put("row",i+2);
                    erroInfo.put("column",1);
                    erroInfo.put("erroMsg","userID不能为空");
                    erroList.add(erroInfo);
                    if(str == null || str.length() <= 0){
                        str.append("userID不能为空");
                    }else {
                        str.append("，" + "userID不能为空");
                    }
                }else{
                    try {
                        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(Integer.parseInt(result.get(i).get(0)));
                        if( registeredUserInfo== null ){//验证用户是否存在
                            JSONObject erroInfo = new JSONObject();
                            erroInfo.put("row",i+2);
                            erroInfo.put("column",1);
                            erroInfo.put("erroMsg","userID对应用户信息不存在");
                            erroList.add(erroInfo);
                            if(str == null || str.length() <= 0){
                                str.append("userID不存在");
                            }else {
                                str.append("，" + "userID不存在");
                            }
                        }
                    }catch (Exception e){
//                        e.printStackTrace();
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column",1);
                        erroInfo.put("erroMsg","userID数据类型有问题");
                        erroList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("userID数据类型有问题");
                        }else {
                            str.append("，" + "userID数据类型有问题");
                        }
                    }
                }
                //Ip名称判断
                if(StringUtil.isBlank(result.get(i).get(1))){
                    JSONObject erroInfo = new JSONObject();
                    erroInfo.put("row",i+2);
                    erroInfo.put("column",2);
                    erroInfo.put("erroMsg","Ip名称不能为空");
                    erroList.add(erroInfo);
                    if(str == null || str.length() <= 0){
                        str.append("Ip名称不能为空");
                    }else {
                        str.append("，" + "Ip名称不能为空");
                    }
                }else {
                    try {
                        List<IP> ip = ipServicesI.getByIPName(result.get(i).get(1), Integer.parseInt(result.get(i).get(0)));
                        if(ip.size() >0 ){
                            JSONObject erroInfo = new JSONObject();
                            erroInfo.put("row",i+2);
                            erroInfo.put("column",2);
                            erroInfo.put("erroMsg","Ip名称在用户" + result.get(i).get(0) + "已存在");
                            erroList.add(erroInfo);
                            if(str == null || str.length() <= 0){
                                str.append("Ip名称在用户" + result.get(i).get(0) + "已存在");
                            }else {
                                str.append("，" + "Ip名称在用户" + result.get(i).get(0) + "已存在");
                            }
                        }
                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column",2);
                        erroInfo.put("erroMsg","Ip名称数据类型有问题");
                        erroList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("Ip名称数据类型有问题");
                        }else {
                            str.append("，" + "Ip名称数据类型有问题");
                        }
                    }
                }
                //行业分类
                if(!StringUtil.isBlank(result.get(i).get(2))){
                    if(dictInfoServicesI.getDictInfoByTypeAndName(EnumDictInfoType.圆融分类.getIndex() , result.get(i).get(2)) == null ){//验证行业分类否存在
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column",3);
                        erroInfo.put("erroMsg","行业分类信息不存在");
                        erroList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("行业分类信息不存在");
                        }else {
                            str.append("，" + "行业分类信息不存在");
                        }
                    }
                }
                //IP粉丝
                if(!StringUtil.isBlank(result.get(i).get(3))){
                    try {
                        Integer.parseInt(result.get(i).get(3));
                    }catch (Exception e){
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column",4);
                        erroInfo.put("erroMsg","IP粉丝必须为数字");
                        erroList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("IP粉丝必须为数字");
                        }else {
                            str.append("，" + "IP粉丝必须为数字");
                        }
                    }
                }
                //IP简介
                //IP全网发布报价（元）
                if(!StringUtil.isBlank(result.get(i).get(5))){
                    try {
                        BigDecimal prices = new BigDecimal(result.get(i).get(5)).setScale(2,BigDecimal.ROUND_HALF_UP);//保留两位小数
                        String ipPrice = prices.toString();
                        if(ipPrice.length() >12){
                            JSONObject erroInfo = new JSONObject();
                            erroInfo.put("row",i+2);
                            erroInfo.put("column",6);
                            erroInfo.put("erroMsg","IP全网发布报价范围0.00-999999999.00");
                            erroList.add(erroInfo);
                            if(str == null || str.length() <= 0){
                                str.append("IP全网发布报价范围0.00-999999999.00");
                            }else {
                                str.append("，" + "IP全网发布报价范围0.00-999999999.00");
                            }
                        }
                    }catch (Exception e){
//                        e.printStackTrace();
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column",6);
                        erroInfo.put("erroMsg","IP全网发布报价必须为数字");
                        erroList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("IP全网发布报价必须为数字");
                        }else {
                            str.append("，" + "IP全网发布报价必须为数字");
                        }
                    }
                }
                //价格有效期结束时间
                if(!StringUtil.isBlank(result.get(i).get(6))){
                    if(!result.get(i).get(6).contains("-")){
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column",7);
                        erroInfo.put("erroMsg","价格有效期时间格式有问题，格式为2018-06-01" );
                        erroList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("价格有效期时间格式有问题，格式为2018-06-01");
                        }else {
                            str.append("，" + "价格有效期时间格式有问题，格式为2018-06-01");
                        }
                    }
                }
                //判断是否格式有错，正确添加正确信息
                if(str == null || str.length() <= 0){
                    rightInfo.put("userID",result.get(i).get(0));   //用户ID
                    rightInfo.put("iPName",result.get(i).get(1));   //IP名称
                    rightInfo.put("iPCategory",result.get(i).get(2));//IP分类
                    rightInfo.put("iPFans",result.get(i).get(3));   //IP粉丝
                    rightInfo.put("iPIntroduction",result.get(i).get(4));   //IP简介
                    rightInfo.put("iPWholeNetworkPrice", result.get(i).get(5) == null || result.get(i).get(5).equals("") ? null :new BigDecimal(result.get(i).get(5)).setScale(2,BigDecimal.ROUND_HALF_UP) );//IP报价
                    rightInfo.put("iPInvalidTime",result.get(i).get(6));    //IP报价有效期
                    rightList.add(rightInfo);
                }/*else{
                    erroInfo.put("userID",result.get(i).get(0));
                    erroInfo.put("iPName",result.get(i).get(1));
                    erroInfo.put("iPFans",result.get(i).get(2));
                    erroInfo.put("iPIntroduction",result.get(i).get(3));
                    erroInfo.put("iPWholeNetworkPrice",result.get(i).get(4));
                    erroInfo.put("erroMsg",str);
                    erroList.add(erroInfo);
                }*/
            }
            info.put("rightList",rightList);
            info.put("erroList",erroList);
            if(erroList ==null || erroList.size() <= 0){//将正确数据信息添加道Session中
                getSession().setAttribute("ipRightList",rightList);
            }
            return new ResultTemplate("", info);
//            System.out.println("+++++++++++++" + info.toJSONString());
        } catch (Exception e) {
            return new ResultTemplate("文件读取失败！" , null);
        }
    }

    /**
     * 批量插入IP（通过Excel文件）
     * @param result
     */
    @RequestMapping(value = "ip_saveBatchIP" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveImportIP(String result){
        /*if(StringUtil.isBlank(result)){
            writeString("参数有误",null);
            return;
        }*/
        JSONArray rightList = (JSONArray) getSession().getAttribute("ipRightList");
        if(rightList == null){
            return new ResultTemplate("数据有问题",null);
        }
        ipServicesI.saveImportIP(rightList);
        getSession().removeAttribute("ipRightList");
        return new ResultTemplate("",null);
    }

    /**
     * 通过Ip名称判断同一个用户下面是否存在两个相同Ip
     * @param IpName
     */
    @RequestMapping(value = "ip_getByIPName" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getByIPName(String IpName,Integer userId){
        if(userId == null ){
            return new ResultTemplate("用户Id不能为空",null);
        }
        List<IP> ip = ipServicesI.getByIPName(IpName,userId);
        if(ip.size() >0){
            return new ResultTemplate("此IP名称在本用户下已存在",null);
        }
        return new ResultTemplate("",null);
    }

}
