package com.yuanrong.admin.mall.controller.order;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.OrderTotalAmountRoundUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * 卖家订单的controller
 * 报名Controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class OrderInfoSellerController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrderInfoSellerController.class);

    /**
     * tangzheng
     * 征稿报名
     * @param data
     * @return
     */
    @RequestMapping( value = "save" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate save(OrderInfoSeller data ){
        if(getWebUser()==null){
            return new ResultTemplate("用户未登陆",null);
        }
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        //验证数据  需求单号 、referId、
       RegisteredUserInfo theRegisteredUserInfo = registeredUserInfoService.getById(getWebUser().getRecID());
       Demand theDemand =  data.getDemandSn()==null ? null :  demandServicesI.getByDemandSn(data.getDemandSn());

        if(theDemand == null){
            return new ResultTemplate("不存在此需求单号！！",null);
        }
        data.setDemandId(theDemand.getDemandId());
        //验证商品号：referId
        YRProduction  yrProduction = (data.getReferId()==null ||data.getReferId()<0) ? null : yRProductionServicesI.getYRProductionById(data.getReferId());
        if(yrProduction == null || (!getWebUser().getRecID().equals(yrProduction.getRegisteredUserInfoId()))){
            return new ResultTemplate("该用户下商品号不存在！",null);
        }
        //订单类型
       if (!data.getOrderTypeValue().equals(EnumDemandType.原创征稿.getIndex())){
           return new ResultTemplate("原创征稿需求类型错误",null);
        }

//        if(StringUtil.isBlank(data.getProduction())){
//            return new ResultTemplate("标题不能为空",null);
//        }

        //流水号
        data.setOrderSn(orderSnFactoryServicesI.createSellerOrderSn());
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        data.setStatusIndex(1);
        data.setProduction(yrProduction.getTitle());
        data.setSaleUser(adminUserServicesI.getById(theRegisteredUserInfo.getAdminUserSalesID()).getRealName());
        data.setMediaUser(adminUserServicesI.getById(theRegisteredUserInfo.getAdminUserMediaID()).getRealName());
        data.setOrderTypeValue(EnumOrderSellerType.原创征稿.getIndex());
        data.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());
        data.setSellerServiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_SELLER_COLLECTION)));
        data.setBuyerServiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_COLLECTION)));
        data.setReferPrice(theDemand.getBudgetMoney());
        data.setSaleUserId(theRegisteredUserInfo.getAdminUserSalesID() );
        data.setMediaUserId(theRegisteredUserInfo.getAdminUserMediaID());
        data.setInvoiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_COLLECTION)));//买家税率
        data.setBuyerOrderPrice(OrderTotalAmountRoundUtil.getRoundAmount(data.getReferPrice().add(data.getReferPrice().multiply(data.getBuyerServiceRate().divide(new BigDecimal("100"))))));//买家订单金额
        data.setSellerPrice(theDemand.getBudgetMoney());//卖家应约价
        orderInfoSellerServicesI.save(data);
        JSONObject jb = new JSONObject();
        jb.put("title",yrProduction.getTitle());
        jb.put("id",yrProduction.getRecId());
        jb.put("wordNum",yrProduction.getWordNum());
        jb.put("imgNum",yrProduction.getImgNum());
        return  new ResultTemplate("",jb);
    }

    /**
     * 选用已有作品报名（每次报名：一个或者多个）
     * @param data
     */
    @RequestMapping( value = "saveMultProduct" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveMultProduct(OrderInfoSeller data ){
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        //验证数据  需求单号 、referId、
        RegisteredUserInfo theRegisteredUserInfo = registeredUserInfoService.getById(getWebUser().getRecID());
        Demand theDemand =  data.getDemandSn()==null ? null :  demandServicesI.getByDemandSn(data.getDemandSn());

        if(theDemand == null){
            return new ResultTemplate("此需求单号不存在！！",null);
        }
        data.setDemandId(theDemand.getDemandId());
        //验证商品号(用户上传多个)：referIds
        List<OrderInfoSeller> list = new ArrayList<>();
        if(data.getReferIds().length>0){
            for(int i =0 ;i<data.getReferIds().length ;i++){
                OrderInfoSeller orderInfoSeller = new OrderInfoSeller();
                orderInfoSeller.setDemandId(theDemand.getDemandId());

               Integer temp = data.getReferIds()[i];
                YRProduction  yrProduction = (temp==null ||temp<0) ? null : yRProductionServicesI.getYRProductionById(temp);
                if(yrProduction == null || (!getWebUser().getRecID().equals(yrProduction.getRegisteredUserInfoId()))){
                    return new ResultTemplate("该用户下商品号:"+temp+"不存在！",null);
                }
                //订单类型
                if (!data.getOrderTypeValue().equals(EnumDemandType.原创征稿.getIndex())){
                    return new ResultTemplate("商品号："+temp+",原创征稿需求类型错误",null);
                }
                //orderInfoSeller
                orderInfoSeller.setOrderSn(orderSnFactoryServicesI.createSellerOrderSn());
                orderInfoSeller.setRegisteredUserInfoId(getWebUser().getRecID());
                orderInfoSeller.setProduction(yrProduction.getTitle());
                orderInfoSeller.setStatusIndex(1);
                orderInfoSeller.setReferId(temp);
                orderInfoSeller.setSaleUser(adminUserServicesI.getById(theRegisteredUserInfo.getAdminUserSalesID()).getRealName());
                orderInfoSeller.setMediaUser(adminUserServicesI.getById(theRegisteredUserInfo.getAdminUserMediaID()).getRealName());
                orderInfoSeller.setOrderTypeValue(EnumOrderSellerType.原创征稿.getIndex());
                orderInfoSeller.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());
                orderInfoSeller.setSellerServiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_SELLER_COLLECTION)));
                orderInfoSeller.setBuyerServiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_COLLECTION)));
                orderInfoSeller.setSaleUserId(theRegisteredUserInfo.getAdminUserSalesID() );
                orderInfoSeller.setMediaUserId(theRegisteredUserInfo.getAdminUserMediaID());
                orderInfoSeller.setReferPrice(theDemand.getBudgetMoney());
                orderInfoSeller.setBuyerOrderPrice(OrderTotalAmountRoundUtil.getRoundAmount(orderInfoSeller.getReferPrice().add(orderInfoSeller.getReferPrice().multiply(orderInfoSeller.getBuyerServiceRate().divide(new BigDecimal("100"))))));//买家订单金额
                orderInfoSeller.setSellerPrice(theDemand.getBudgetMoney());//卖家应约价
                orderInfoSeller.setInvoiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_COLLECTION)));//买家税率
                list.add(orderInfoSeller);
            }
        }
        orderInfoSellerServicesI.saveMultProduct(list);
        return  new ResultTemplate("",null);
    }

    /**
     * 定制内容报名
     * @param data
     * @return
     */
    @RequestMapping( value = "saveContent" , method = RequestMethod.POST)
    @ResponseBody
    public   ResultTemplate saveContent(OrderInfoSeller data ,String offerProject,String demandSn) {
        if (getWebUser() == null) {
            return new ResultTemplate("用户未登陆", null);
        }
        if(StringUtil.isBlank(data.getUsableDate()) || Integer.parseInt(data.getUsableDate()) >30 ||  Integer.parseInt(data.getUsableDate()) <0){
            return new ResultTemplate("创作用时不能为空,并且创作用时为1-30天的正整数", null);
        }
        if(!(Integer.parseInt(data.getUsableDate()) < 31 && Integer.parseInt(data.getUsableDate()) >0)){
            return new ResultTemplate("创作用时不能为空,并且创作用时为1-30天的正整数", null);
        }
        //验证商品id(作品、创作者、账号)  ---referId
        YRAuthor yrAuthorData = new YRAuthor();
        yrAuthorData.setRecId(data.getReferId());
        yrAuthorData.setRegisteredUserInfoID(getWebUser().getRecID());
        YRAuthor theYRAuthor = (data.getReferId()==null || data.getReferId()<0) ? null :yRAuthorServicesI.getYRAuthorById_RegUserId(yrAuthorData);
        if(theYRAuthor==null ){
            return new ResultTemplate("此用户下的作者id不存在", null);
        }

        //验证报价项、需求编号
        if(StringUtil.isBlank(offerProject) ){
            return new ResultTemplate("创作者报价不能为空！", null);
        }
        if( StringUtil.isBlank(demandSn)){
            return new ResultTemplate("需求编号不能为空！", null);
        }

        Demand theDemand =  demandSn==null ? null :  demandServicesI.getByDemandSn(demandSn);
        if(theDemand == null){
            return new ResultTemplate("此需求单号不存在！",null);
        }
        if(theDemand.getDemandTypeIndex() != EnumDemandType.定制内容.getIndex()){
            return new ResultTemplate("需求类型不正确！",null);
        }
        String[] array_ExpectOffer =   theDemand.getExpectOffer().split("_-,-_");
        String[] array_Offer = offerProject.split("_-,-_");
        if(array_ExpectOffer.length != array_Offer.length ){
            return new ResultTemplate("价格项不全！",null);
        }
        for(int i=0; i<array_Offer.length ;i++){
           int tmp =Integer.parseInt( array_Offer[i] );
           if(tmp<1 || tmp>999999999 ){
               return new ResultTemplate("报价为1-999999999的正整数！",null);
           }
        }
        List<OrderInfoOffer> list = new ArrayList();
        for(int i=0; i<array_ExpectOffer.length ;i++){
            OrderInfoOffer orderInfoOffer = new OrderInfoOffer();
            orderInfoOffer.setPriceName(array_ExpectOffer[i]);
            orderInfoOffer.setPrice(new BigDecimal(array_Offer[i]));
            list.add(orderInfoOffer);
        }

        data.setDemandId(theDemand.getDemandId());
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        data.setOrderSn(orderSnFactoryServicesI.createSellerOrderSn());
        data.setStatusIndex(1);
        data.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());
        data.setOrderTypeValue(EnumOrderSellerType.定制内容.getIndex());
        data.setBuyerServiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_AUTHOR)));//买家服务费率
        data.setSellerServiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_SELLER_AUTHOR)));//卖家服务费率
        data.setInvoiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_AUTHOR)));//买家税率

        data.setSaleUserId(registeredUserInfoService.getById(getWebUser().getRecID()).getAdminUserSalesID() );
        data.setMediaUserId(registeredUserInfoService.getById(getWebUser().getRecID()).getAdminUserMediaID());
        /**
         *  step1 : 将data数据存入到 OrderInfoSeller；
         *  step2 : 将数据组件插入 List<OrderInfoOffer> list
          */
         orderInfoSellerServicesI.saveContentOffer(data,list);
        return  new ResultTemplate("",null);
    }

    /**
     * 营销分发--报名
     * @param data
     * @return
     */
    @RequestMapping( value = "saveMarker" , method = RequestMethod.POST)
    @ResponseBody
    public   ResultTemplate saveMarker(OrderInfoSeller data ,String offerProject,String demandSn) {
        if (getWebUser() == null) {
            return new ResultTemplate("用户未登陆", null);
        }
        if(StringUtil.isBlank(data.getUsableDate())){
            return new ResultTemplate("可用排期不能为空", null);
        }

        //验证报价项、需求编号
        if(StringUtil.isBlank(offerProject) ){
            return new ResultTemplate("创作者报价不能为空！", null);
        }
        if( StringUtil.isBlank(demandSn)){
            return new ResultTemplate("需求编号不能为空！", null);
        }

        //验证商品id(作品、创作者、账号)  ---referId
        PlatformIPAccount platformIPAccount = new PlatformIPAccount();
        platformIPAccount.setRegisteredUserInfoID(getWebUser().getRecID());
        platformIPAccount.setId(data.getReferId());
       PlatformIPAccount thePlatformIPAccount = (data.getReferId()==null ||data.getReferId()<0) ? null :platformIPAccountServicesI.getPlatformIPAccountById_RegistId(platformIPAccount);
        if(thePlatformIPAccount == null){
            return new ResultTemplate("该用户下账号Id不存在！", null);
        }
        Demand theDemand =  demandSn==null ? null :  demandServicesI.getByDemandSn(demandSn);
        if(theDemand == null){
            return new ResultTemplate("此需求单号不存在！",null);
        }
        if(theDemand.getDemandTypeIndex() != EnumDemandType.营销分发.getIndex()){
            return new ResultTemplate("需求类型不正确！",null);
        }
        String[] array_ExpectOffer =   theDemand.getExpectOffer().split("_-,-_");
        String[] array_Offer = offerProject.split("_-,-_");
        if(array_ExpectOffer.length != array_Offer.length ){
            return new ResultTemplate("价格项不全！",null);
        }
        for(int i=0; i<array_Offer.length ;i++){
            int tmp =Integer.parseInt( array_Offer[i] );
            if(tmp<1 || tmp>999999999 ){
                return new ResultTemplate("报价为1-999999999的正整数！",null);
            }
        }
        List<OrderInfoOffer> list = new ArrayList();
        for(int i=0; i<array_ExpectOffer.length ;i++){
            OrderInfoOffer orderInfoOffer = new OrderInfoOffer();
            orderInfoOffer.setPrice(new BigDecimal(array_Offer[i]));
            orderInfoOffer.setPriceName(array_ExpectOffer[i]);
            list.add(orderInfoOffer);
        }

        data.setDemandId(theDemand.getDemandId());
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        data.setOrderSn(orderSnFactoryServicesI.createSellerOrderSn());
        data.setStatusIndex(1);
        data.setOrderTypeValue(EnumOrderSellerType.营销分发.getIndex());
        data.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());
        data.setBuyerServiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_ACCOUNT)));//买家服务费率
        data.setSellerServiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_SELLER_ACCOUNT)));//卖家服务费率
        data.setInvoiceRate(new BigDecimal(configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_ACCOUNT)));//买家税率
        data.setSaleUserId(registeredUserInfoService.getById(getWebUser().getRecID()).getAdminUserSalesID());
        data.setMediaUserId(registeredUserInfoService.getById(getWebUser().getRecID()).getAdminUserMediaID());
        orderInfoSellerServicesI.saveContentOffer(data,list);//
        return  new ResultTemplate("",null);
    }


}
