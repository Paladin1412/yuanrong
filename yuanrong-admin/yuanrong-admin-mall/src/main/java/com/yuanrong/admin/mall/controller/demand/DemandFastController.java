package com.yuanrong.admin.mall.controller.demand;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDemandFastStatus;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.Enum.EnumStatusOfUser;
import com.yuanrong.admin.bean.base.SMSValidateCode;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
/**
 * 快速需求的controller
 * Created MDA
 */
@Controller
@RequestMapping("demandFast")
public class DemandFastController extends BaseController {
    private static final Logger logger = Logger.getLogger(DemandFastController.class);

    /**
     *  发布快速需求
     * @param demandFast 快速需求对象、ssmCode 短信验证码
     */
    @RequestMapping("demandFast_save")
    @ResponseBody
    public ResultTemplate save(DemandFast demandFast, String smsCode){
        if(getWebUser()==null){
            //step1：验证参数
            if(StringUtil.isBlank(smsCode)){
                return new ResultTemplate("短信验证码不能为空",null);
            }
            //从session中获取短信验证码、已经对应的手机号
            SMSValidateCode smsValidateCode = (SMSValidateCode)getSession().getAttribute("smsValidateCode");
            if(smsValidateCode==null || StringUtil.isBlank(smsValidateCode.getSmsCode())){
                return  new ResultTemplate("请先获取短信验证码!",null);
            }
            if(!smsCode.toLowerCase().equals(smsValidateCode.getSmsCode().toLowerCase())){
                return new ResultTemplate("输入短信验证码错误",null);
            }
        }
        if(demandFast.getNickname()==null){
            return new ResultTemplate("请输入称呼！",null);
        }
        if(demandFast.getNickname().length()>20 || demandFast.getNickname().length()<2){
            return new ResultTemplate("请输入称呼,长度2-20字！",null);
        }
        if(demandFast.getMobile()==null){
            return new ResultTemplate("快速需求:手机号不能为空",null);
        }
        if(demandFast.getDemandTypeIndex()==null){
            return new ResultTemplate("快速需求:需要什么不能为空",null);
        }
        if(getWebUser()!=null){
            demandFast.setRegisteredUserInfoId(getWebUser().getRecID());
        }else {
            List<RegisteredUserInfo> list = registeredUserInfoService.getByMobile(demandFast.getMobile());
            if(CollectionUtil.size(list) > 0){
                demandFast.setRegisteredUserInfoId(list.get(0).getRecID());
            }
        }
        if(  demandFast.getDemandTypeIndex() == EnumDemandType.ip代理.getIndex() ||
                demandFast.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex() ||
                demandFast.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex() ||
                demandFast.getDemandTypeIndex() == EnumDemandType.原创征稿.getIndex()  ){

        }else {
            return new ResultTemplate("需求类型传参错误",null);
        }
        demandFast.setDemandSn(orderSnFactoryServicesI.createDemandOrderSn());
        demandFast.setCreatedTime(new Date());
        demandFast.setStatusIndex(EnumStatusOfUser.账号可用.getIndex());
        demandFast.setSaleManId(Integer.valueOf(configurationServicesI.getbyKey(SystemParam.SALE_ID)));
        demandFast.setDemandFastStatusIndex(EnumDemandFastStatus.待处理.getIndex());
        demandFastServicesI.save(demandFast);
        return new ResultTemplate("",null);
    }
}
