package com.yuanrong.admin.server.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumBaseStatus;
import com.yuanrong.admin.Enum.EnumStatusOfUser;
import com.yuanrong.admin.bean.base.SmsRecord;
import com.yuanrong.admin.bean.system.AdminRole;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.server.controller.BaseController;

import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.module.shortmessage.SMSSend;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.MD5Util;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tangz on 2018/4/27.
 */
@Controller
@RequestMapping("adminUser")
public class AdminUserController extends BaseController {
    private static final Logger logger = Logger.getLogger(AdminUserController.class);

    /**
     * 用户登录
     * @param userName
     * @param passWord
     */
    @RequestMapping(value = "ex_login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate login(String userName, String passWord){
        AdminUser adminUser = adminUserServicesI.login(userName,passWord);
        if(adminUser == null){
            String logStr = "用户名或密码错误,username 登录失败, 登录用户名为: "+userName +";PassWord为:"+passWord;
            logger.info(logStr);
            return new ResultTemplate("用户名或密码错误",null);
        }else{

            String underUser = adminUserServicesI.getMyUnderling(adminUser.getRecID());
            adminUser.setUnderUser(StringUtil.isNoneBlank(underUser) ? (underUser+","+adminUser.getRecID()) : adminUser.getRecID()+"");
            getSession().setAttribute("admin",adminUser);
            //返回给前端
            Map<String,Object> map = new HashMap<>();
            map.put("recID",adminUser.getRecID());
            Map<String,Object> rntmap =   adminUserServicesI.getAdminUserById(map);
//            writeString("",adminUser);
            String logStr="username 登录成功,用户名为: "+userName;

            List<SystemMenu> list = systemMenuServicesI.selectMenu(adminUser.getRecID());
            getSession().setAttribute("menus",list);
            logger.info(logStr);
            return new ResultTemplate("",rntmap);
        }
    }

    /**
     * 退出登录
     */
    @RequestMapping("ex_logout")
    @ResponseBody
    public ResultTemplate exs_logout(){
        getSession().invalidate();
        return new ResultTemplate("",null);
    }



    /**
     * 蓝色 所有人 上架账号 、注册用户
     * 橙色 媒介 待处理订单
     * 绿色 销售 待处理需求
     * 粉色 运营 待审核作者、待审核作品、待审核用户
     * 首页统计数据
     */
    @RequestMapping(value = "exs_indexCnt",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate indexCnt(){
        String s = getUser().getUnderUser();
        Map<String, Object> rnt = adminUserServicesI.indexCnt(getUser().getUnderUser());
        return new ResultTemplate("",rnt);
    }
    /**
     * 查询该用户
     * @param adminUserId
     */
    @RequestMapping(value = "exs_getById")
    @ResponseBody
    public ResultTemplate getById(Integer adminUserId){
        AdminUser adminUser = adminUserServicesI.getById(adminUserId);
        if(adminUser == null){
            return new ResultTemplate("该id不存在："+adminUserId,null);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("AdminRoleID",adminUser.getAdminRoleID());
        jsonObject.put("IPhoneNum",adminUser.getIPhoneNum());
        jsonObject.put("RealName",adminUser.getRealName());
        jsonObject.put("UserName",adminUser.getUserName());
        jsonObject.put("RecID",adminUser.getRecID());
        jsonObject.put("StatusValue",adminUser.getStatusValue());
        jsonObject.put("QrCodeWx",adminUser.getQrCodeWx());
        jsonObject.put("Email",adminUser.getEmail());
        jsonObject.put("CpWeiXinId",adminUser.getCpWeiXinId());
        return new ResultTemplate("",jsonObject);
    }

    /**
     * 增添该用户，一并开通菜单、数据权限
     * @param adminUser
     */
    @RequestMapping(value = "exs_save")
    @ResponseBody
    public ResultTemplate save(AdminUser adminUser){
        try {
            if(StringUtil.isBlank(adminUser.getUserName())){
                return new ResultTemplate("用户名(拼音)不能为空",null);
            }
            if(StringUtil.isBlank(adminUser.getRealName())){
                return new ResultTemplate("真实姓名不能为空",null);
            }
            if(StringUtil.isBlank(adminUser.getIPhoneNum())){
                return new ResultTemplate("手机号码不能为空",null);
            }
            if(!SMSSend.isMobile(adminUser.getIPhoneNum())){
                return new ResultTemplate("手机号码格式错误",null);
            }
            if(StringUtil.isBlank(adminUser.getEmail())){
                return new ResultTemplate("邮箱不能为空",null);
            }
            if(StringUtil.isBlank(adminUser.getAdminRoleID().toString())){
                return new ResultTemplate("用户名角色不能为空",null);
            }

            //用户名唯一  、手机号唯一
            Map<String,Object> map = new HashMap<>();
            map.put("userName",adminUser.getUserName());
            AdminUser adminUser1 = adminUserServicesI.getByRealNameOrIPhoneNum(map);
            if(adminUser1!=null){
                return new ResultTemplate("用户名已存在，请换一个用户名！",null);
            }
            Map<String,Object> map1 = new HashMap<>();
            map1.put("iPhoneNum",adminUser.getIPhoneNum());
            AdminUser adminUser2 = adminUserServicesI.getByRealNameOrIPhoneNum(map1);
            if(adminUser2!=null){
                return new ResultTemplate("手机号已存在，请换一个手机号！",null);
            }
            //产生随机6位数密码，发短信告知用户
            String password = SMSSend.getRandomSix();

            adminUser.setPassWord(MD5Util.md5(MD5Util.md5(password+"#yr")));
            int adminUserId =  adminUserServicesI.saveKey(adminUser);
            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setSource("后台增加用户");
            smsRecord.setStatus(1);
            smsRecord.setMobile(adminUser.getIPhoneNum());
            String content = "欢迎加入圆融大家庭，登陆圆融后台管理系统admin.yuanrongbank.com，用户名是："
                    +adminUser.getUserName()+",密码是："+password+"。";
            smsRecordServicesI.insertSendMessage(smsRecord,content);
            return new ResultTemplate("",null);



        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResultTemplate("保存出错",null);
        }

    }

    /**
     * 编辑该用户
     * @param adminUser
     */
    @RequestMapping(value = "exs_updateAdminUserByID")
    @ResponseBody
    public ResultTemplate updateAdminUserByID(AdminUser adminUser,boolean flag, boolean flagPasswd, String imgcode){
        try {
            if(StringUtil.isBlank(adminUser.getRecID().toString())){
                return new ResultTemplate("用户名id不能为空",null);
            }
            if(flag){
                if(StringUtil.isBlank(adminUser.getUserName())){
                    return new ResultTemplate("用户名(拼音)不能为空",null);
                }
                if(StringUtil.isBlank(adminUser.getRealName())){
                    return new ResultTemplate("真实姓名不能为空",null);
                }
                if(StringUtil.isBlank(adminUser.getIPhoneNum())){
                    return new ResultTemplate("手机号码不能为空",null);
                }
                if(!SMSSend.isMobile(adminUser.getIPhoneNum())){
                    return new ResultTemplate("手机号码格式错误",null);
                }
                if(StringUtil.isBlank(adminUser.getEmail())){
                    return new ResultTemplate("邮箱不能为空",null);
                }
                if(StringUtil.isBlank(adminUser.getAdminRoleID().toString())){
                    return new ResultTemplate("用户名角色不能为空",null);
                }

                //用户名唯一  、手机号唯一
                Map<String,Object> map = new HashMap<>();
                map.put("userName",adminUser.getUserName());
                map.put("recID",adminUser.getRecID());

                AdminUser adminUser1 = adminUserServicesI.getByRealNameOrIPhoneNum(map);
                if(adminUser1!=null){
                    return new ResultTemplate("用户名已存在，请换一个用户名！",null);
                }
                Map<String,Object> map1 = new HashMap<>();
                map1.put("iPhoneNum",adminUser.getIPhoneNum());
                map1.put("recID",adminUser.getRecID());
                AdminUser adminUser2 = adminUserServicesI.getByRealNameOrIPhoneNum(map1);
                if(adminUser2!=null){
                    return new ResultTemplate("手机号已存在，请换一个手机号！",null);
                }
            }

            if(flagPasswd){
                if(StringUtil.isBlank(adminUser.getIPhoneNum())){
                    return new ResultTemplate("手机号码不能为空",null);
                }
                if(!SMSSend.isMobile(adminUser.getIPhoneNum())){
                    return new ResultTemplate("手机号码格式错误",null);
                }
                if(StringUtil.isBlank(imgcode)){
                    return new ResultTemplate("输入的图像验证码不能为空",null);
                }

                String imgCodeSession = (String) getSession().getAttribute("getImgValidateCode");

                if(StringUtil.isBlank(imgCodeSession)){
                    return new ResultTemplate("请先获取图像验证码",null);
                }

                if(!imgcode.toLowerCase().equals(imgCodeSession.toLowerCase())){
                    return new ResultTemplate("输入的图像验证码不正确",null);
                }


                //产生随机6位数密码，发短信告知用户
                String password = SMSSend.getRandomSix();
                SmsRecord smsRecord = new SmsRecord();
                smsRecord.setSource("后台更改后台用户密码");
                smsRecord.setStatus(1);
                smsRecord.setMobile(adminUser.getIPhoneNum());
                String content = "圆融后台管理员为您更改登录圆融后台管理系统admin.yuanrongbank.com密码，用户名是："
                        +adminUser.getUserName()+",新密码是："+password+"。";
                smsRecordServicesI.insertSendMessage(smsRecord,content);
                adminUser.setPassWord(MD5Util.md5(MD5Util.md5(password+"#yr")));
                getSession().removeAttribute("getImgValidateCode");
            }

            adminUserServicesI.updateAdminUserByID(adminUser);
            return new ResultTemplate("",null);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultTemplate("更新失败",null);
        }
    }


    /**
     * 后台用户列表
     * @param baseModel
     */
    @RequestMapping(value = "exs_listAdminUser")
    @ResponseBody
    public ResultTemplate listAdminUser(AdminUser adminUser,BaseModel baseModel){
//        PageInfo pageInfo = adminUserServicesI.list(adminUser,baseModel );
        PageInfo<AdminUser> pageInfo = adminUserServicesI.listAdminUserByCondtion(adminUser,baseModel);
        List<JSONObject> rntList = new ArrayList<JSONObject>();
        List<AdminRole> adminRoleList = adminRoleServicesI.listAdminRole();
        Map<Integer,Object> map = new HashMap<>();
        for(AdminRole adminRole : adminRoleList){
            map.put(adminRole.getRoleID(),adminRole.getRoleName());
        }
        for( AdminUser rntAdminUser : pageInfo.getList()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("adminRoleID",StringUtil.isBlank(rntAdminUser.getAdminRoleID().toString())?null: rntAdminUser.getAdminRoleID());
            jsonObject.put("adminRoleName",map.containsKey(rntAdminUser.getAdminRoleID()) ? map.get(rntAdminUser.getAdminRoleID()):null);
            jsonObject.put("createTime", StringUtil.isBlank( rntAdminUser.getCreateTime().toString())?null: DateUtil.format(rntAdminUser.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            jsonObject.put("recID",rntAdminUser.getRecID());
            jsonObject.put("realName",StringUtil.isBlank(rntAdminUser.getRealName())?null:rntAdminUser.getRealName());
            jsonObject.put("iPhoneNum",StringUtil.isBlank(rntAdminUser.getIPhoneNum())?null:rntAdminUser.getIPhoneNum());
            jsonObject.put("userName",StringUtil.isBlank( rntAdminUser.getUserName())?null: rntAdminUser.getUserName());
            jsonObject.put("qrCodeWx",StringUtil.isBlank( rntAdminUser.getQrCodeWx())?null: rntAdminUser.getQrCodeWx());
            jsonObject.put("email",StringUtil.isBlank( rntAdminUser.getEmail())?null:rntAdminUser.getEmail());
            if(rntAdminUser.getStatusValue()==1){
                jsonObject.put("statusValue",EnumBaseStatus.NORMAL.getName());
            }else {
                jsonObject.put("statusValue",EnumBaseStatus.FORBIDDEN.getName());
            }
            rntList.add(jsonObject);
        }
        return new ResultTemplate( pageInfo,rntList);
    }

    /**
     * 后台用户状态值
     */
    @RequestMapping(value = "exs_getAdminStatus")
    @ResponseBody
    public ResultTemplate getAdminStatus(){
        return  new ResultTemplate("", EnumBaseStatus.getMapInfo());
    }

}
