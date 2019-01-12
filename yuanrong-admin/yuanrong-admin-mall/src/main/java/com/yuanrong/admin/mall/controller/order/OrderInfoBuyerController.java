package com.yuanrong.admin.mall.controller.order;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.resources.UserImages;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.seach.OrderInfoBuyerSearch;
import com.yuanrong.admin.seach.OrderSureSearch;
import com.yuanrong.admin.seach.ShoppingCartSearch;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 买家订单的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class OrderInfoBuyerController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrderInfoBuyerController.class);
    @RequestMapping( value = "orderInfoBuyer/placeTheProductOrder" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate placeTheProductOrder(ShoppingCartSearch data){
        if(data.getShoppingCartIds() == null || CollectionUtil.size(data.getShoppingCartIds()) <= 0){
            return new ResultTemplate("请选择购物车商品后再提交",null);
        }
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        data.setCartType(EnumCartType.作品);
        //提交订单
        List<OrderInfoBuyer> orderInfoBuyers = orderInfoBuyerServicesI.savePlaceTheProductOrder(data);
        JSONArray result = new JSONArray();
        for(OrderInfoBuyer orderInfoBuyer : orderInfoBuyers){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderSn" , orderInfoBuyer.getOrderSn());
            result.add(jsonObject);
        }
        return new ResultTemplate("" , result);
    }

    @RequestMapping( value = "orderInfoBuyer/buyNow" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate buyNow(YRProduction data){
        YRProduction production = data.getRecId() == null ? null : yRProductionServicesI.getById(data.getRecId());
        if(production == null){
            return new ResultTemplate("作品不存在",null);
        }

        if(production.getYrProductionStatus().getIndex() != EnumYRProductionStatus.上架.getIndex()){
            return new ResultTemplate("很抱歉，作品未上架或已售罄",null);
        }
        production.setCurrRegisterUserInfoId(getWebUser().getRecID());
        OrderInfoBuyer orderInfoBuyer = orderInfoBuyerServicesI.saveBuyNow(production);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderSn" , orderInfoBuyer.getOrderSn());
        return new ResultTemplate(jsonObject);
    }

    /**
     * 确认订单：
     *   情况一： 流水单号 type 1
     *
     *   情况二：立即下单  type 2
     *
     *   情况三： 购物车 type 3
     * @param data
     * @return
     */
    @RequestMapping( value = "orderInfoBuyer/sureOrder" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate sureOrder(OrderSureSearch data){
        // 验证数据
        if(getWebUser()==null){
            return  new ResultTemplate("用户未登陆");
        }
        if(data.getType()==null){
            return new ResultTemplate("订单确定类型不能为空");
        }

        // 从报名流水表中
        if(EnumOrderSure.从报名表.getIndex() == data.getType()){
            String[] orderSns = data.getOrderSns();
            if(orderSns==null){
                return new ResultTemplate("流水单号为空");
            }
        // 作品购买立即下单  2
        }else if(data.getType()== EnumOrderSure.从立即下单.getIndex()){
            if(data.getYrProductionId()==null){
                return new ResultTemplate("作品id为空");
            }
       //从购物车购买作品
        }else if(data.getType()==EnumOrderSure.从购物车.getIndex()){ // 3
            // data.getShoppingCarts()  判断是否为空
            if(data.getShoppingCarts()==null){
                return new ResultTemplate("购物车Id不能为空！");
            }
            // 验证购物车Id是否是当前用户购物数据
            for(int i=0; i<data.getShoppingCarts().length; i++){
                ShoppingCart shoppingCart = shoppingCartServicesI.getById(data.getShoppingCarts()[i]);
                if(shoppingCart==null ){
                    return  new ResultTemplate("该购物车id:"+ data.getShoppingCarts()[i]+"在此用户下不存在！");
                }
                if(shoppingCart.getCartTypeIndex()!=2  || !shoppingCart.getRegisteredUserInfoId().equals(getWebUser().getRecID())){
                   return new ResultTemplate("type类型错误，或者该购物车Id不在当前用户下！");
                }
            }

        }else {
            return new ResultTemplate("订单确定类型参数错误");
        }
        //返回结果
        JSONObject   result = orderInfoBuyerServicesI.sureOrder(data);

        return  new ResultTemplate("",result);
    }

    /**
     * 再次购买---需求订单
     * @param orderInfoSeller
     * @return
     */
    @RequestMapping( value = "orderInfoBuyer/rePlaceTheProductOrder" )
    @ResponseBody
    public ResultTemplate rePlaceTheProductOrder(OrderInfoSeller orderInfoSeller){
        if(StringUtil.isBlank(orderInfoSeller.getOrderSn())){
            return new ResultTemplate("报名号为空");
        }
        Demand demand = new Demand();
        demand.setRegisteredUserInfoId(getWebUser().getRecID());
        orderInfoSeller.setDemand(demand);
        OrderInfoSeller infoSeller =orderInfoSellerServicesI.getByParam(orderInfoSeller);
        if(infoSeller == null){
            return new ResultTemplate("该报名记录不存在");
        }
        OrderInfoBuyer orderInfoBuyer = orderInfoBuyerServicesI.savePlaceTheDemandOrder(infoSeller);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderSn" , orderInfoBuyer.getOrderSn());
        return new ResultTemplate(jsonObject);
    }

    /**
     *  前台—原创征稿——生成订单
     * @param orderInfoSeller
     * @return
     */
    @RequestMapping(value = "orderInfoBuyer/saveProductOrder" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveProductOrder(OrderInfoSeller orderInfoSeller){
        if(getWebUser() == null){
            return new ResultTemplate("请重新登录");
        }
        if(orderInfoSeller.getOrderInfoSn() == null || orderInfoSeller.getOrderInfoSn().length <=0){
            return new ResultTemplate("报名号不能为空");
        }
        for (String str : orderInfoSeller.getOrderInfoSn()){
            if(StringUtil.isBlank(str)){
                return new ResultTemplate("报名号不能为空");
            }
            UserInfo u =getWebUser();
            OrderInfoSeller infoSeller =orderInfoSellerServicesI.getByOrderSn(str);
            if(infoSeller == null || infoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.原创征稿.getIndex()) != 0
                                    || infoSeller.getOrderStatusValue().compareTo(EnumOrderSellerStatus.待买家确认.getIndex()) !=0
                                    || (infoSeller.getDemand() != null && infoSeller.getDemand().getRegisteredUserInfoId().compareTo(getWebUser().getRecID()) !=0)){
                return new ResultTemplate("报名记录不存在或者报名记录有误");
            }
        }
        //生成订单
        List<OrderInfoBuyer> orderInfoBuyerList =demandServicesI.saveProOrderSn(orderInfoSeller.getOrderInfoSn(),getWebUser().getUserName());
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(orderInfoBuyerList) > 0){
           for (OrderInfoBuyer infoBuyer  : orderInfoBuyerList){
               JSONObject jsonObject = new JSONObject();
               jsonObject.put("orderSn",infoBuyer.getOrderSn());
               result.add(jsonObject);
           }
        }
        return new ResultTemplate(result);
    }

}
