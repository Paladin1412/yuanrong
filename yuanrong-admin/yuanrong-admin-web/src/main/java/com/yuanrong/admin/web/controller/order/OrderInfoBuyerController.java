package com.yuanrong.admin.web.controller.order;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderDetail;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SnapshotYrProduction;
import com.yuanrong.admin.rpc.api.author.YRProductionServicesI;
import com.yuanrong.admin.rpc.api.order.OrderInfoBuyerServicesI;
import com.yuanrong.admin.seach.OrderInfoBuyerSearch;
import com.yuanrong.admin.seach.OrderSureSearch;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.Html2WordUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    /**
     *@author songwq
     *@param
     *@data 2018/7/4
     *@description 查询订单列表
    */
    @RequestMapping(value = "orderInfoBuyer_list", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(OrderInfoBuyerSearch data, BaseModel baseModel, HttpSession session) {
        if(StringUtils.isEmpty(getWebUser(session))){
            return new ResultTemplate("请您先登录");
        }else{
            data.setRegisteredUserInfoId(getWebUser(session).getRecID());
        }
        PageInfo<OrderInfoBuyer> orderInfoBuyerPageInfo = orderInfoBuyerServicesI.list(data, baseModel);

        JSONArray result = new JSONArray();
        for(OrderInfoBuyer orderInfoBuyer : orderInfoBuyerPageInfo.getList()){
            JSONObject ele = new JSONObject();
            //总订单数
//            ele.put("totalNum",orderInfoBuyerPageInfo.getSize());
            //id
            ele.put("orderSn" , orderInfoBuyer.getOrderSn());
            //作品ID
            ele.put("productId" , orderInfoBuyer.getOrderDetail()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction().getYrProductionId());
            //名称
            ele.put("title" , orderInfoBuyer.getOrderDetail()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction().getTitle());
            //作品类型
            ele.put("publishStatusIndex" , orderInfoBuyer.getOrderDetail()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction().getPublishStatusValue());
            ele.put("publishStatusValue" , orderInfoBuyer.getOrderDetail()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction().getPublishStatus().getName());
            //作品类型
            ele.put("orderInfoType" , orderInfoBuyer.getOrderInfoType()==null?"":orderInfoBuyer.getOrderInfoType());
            ele.put("orderInfoTypeValue" , orderInfoBuyer.getEnumOrderSellerType()==null?"":orderInfoBuyer.getEnumOrderSellerType().getName());
            //订单金额
            //ele.put("receivableMoney" , orderInfoBuyer.getTotalMoney());
            //創建時間
            //ele.put("createdTime" , orderInfoBuyer.getCreatedTime());
            ele.put("createdTime" , StringUtils.isEmpty(orderInfoBuyer.getCreatedTime())?"":DateUtil.format(orderInfoBuyer.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
            ele.put("wordNum" , orderInfoBuyer.getOrderDetail()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction().getWordNum());
            ele.put("imgNum" , orderInfoBuyer.getOrderDetail()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction()==null?"":orderInfoBuyer.getOrderDetail().getSnapshotYrProduction().getImgNum());
            //剩余时间
            //ele.put("payInvalidTime" , orderInfoBuyer.getPayInvalidTime()==null?"":DateUtil.parse(orderInfoBuyer.getPayInvalidTime()).getTime());
            //ele.put("payInvalidTime" , org.apache.commons.lang3.StringUtils.isBlank(orderInfoBuyer.getPayInvalidTime())?"":DateUtil.format(DateUtil.parseDate(orderInfoBuyer.getPayInvalidTime()),"yyyy-MM-dd HH:mm:ss"));
            //ele.put("nowTime" ,System.currentTimeMillis());
            //订单状态
            //ele.put("orderStatus" , orderInfoBuyer.getOrderStatus());
            //ele.put("orderStatusValue" , orderInfoBuyer.getOrderStatusValue());
            // 支付状态
            //ele.put("payStatus" , orderInfoBuyer.getPayStatus());
            //ele.put("payStatusValue" , orderInfoBuyer.getPayStatusValue());
            //附加费用名称
            //ele.put("appendFeeName" ,orderInfoBuyer.getDictFee() == null?"":orderInfoBuyer.getDictFee().getName());
            //附加费用金额
            //ele.put("appendFeeMoney" ,orderInfoBuyer.getOrderCostInfo()==null?"":orderInfoBuyer.getOrderCostInfo().getMoney());
            /*String money = orderInfoBuyer.getMoneyDetail();
            String[] id = new String[3];
            String[] prices = new String[3];
            String[] record = new String[3];
            if(!StringUtil.isEmpty(money)){
                record = money.split(",");
                for (int i=0;i<3;i++){
                    id[i] = record[i].split("-")[0];
                    prices[i] = record[i].split("-")[1];
                    if(id[i].equals("240")){
                        ele.put("costPrice" , prices[i].equals("0.00")?"":prices[i]);
                    }else if(id[i].equals("241")){
                        ele.put("servicePrice" , prices[i].equals("0.00")?"":prices[i]);
                    }else if(id[i].equals("242")){
                        ele.put("invoicePrice" , prices[i].equals("0.00")?"":prices[i]);
                    }
                }
            }*/

            /*List<JSONObject> list = new ArrayList<>();

            //作品订单总金额
            //BigDecimal totalAmount = new BigDecimal(0);
            for(OrderInfoSeller orderInfoSeller : orderInfoBuyer.getOrderInfoSellerList()){
                JSONObject sellerEle = new JSONObject();
                //作品订单号
                sellerEle.put("orderInfoSellerId" , orderInfoSeller.getOrderInfoSellerId());
                //作品ID
                sellerEle.put("productId" , orderInfoSeller.getSnapshotYrProduction()==null?"":orderInfoSeller.getSnapshotYrProduction().getYrProductionId());
                //作品名称
                sellerEle.put("production" , orderInfoSeller.getProduction());
                //作品类型
                sellerEle.put("publishStatus" , orderInfoSeller.getSnapshotYrProduction()==null?"":orderInfoSeller.getSnapshotYrProduction().getPublishStatus());
                sellerEle.put("publishStatusValue" , orderInfoSeller.getSnapshotYrProduction()==null?"":orderInfoSeller.getSnapshotYrProduction().getPublishStatusValue());
                //单价
                sellerEle.put("price" , orderInfoSeller.getPrice());
                //是否已读
                sellerEle.put("isRead" , orderInfoSeller.getyRProduction()==null?"":orderInfoSeller.getyRProduction().getIsRead());
                //totalAmount = totalAmount.add( orderInfoSeller.getPrice());
                list.add(sellerEle);
            }
            //ele.put("totalAmount",totalAmount);
            ele.put("sellerOrder",list);*/
            result.add(ele);
        }
        return new ResultTemplate(orderInfoBuyerPageInfo , result);
    }



    /**
     *@author songwq
     *@param
     *@data 2018/7/4
     *@description 获取已购买作品的订单状态下拉列表
    */
    @RequestMapping(value="getEnumOrderBuyerStatus")
    @ResponseBody
    public ResultTemplate getEnumOrderBuyerStatus(){
        return new ResultTemplate("", EnumOrderBuyerStatus.getMapInfo());
    }
    /**
     *@author songwq
     *@param
     *@data 2018/7/4
     *@description 获取已购买作品的作品类型下拉列表
    */
    @RequestMapping(value="getEnumPublishStatus")
    @ResponseBody
    public ResultTemplate getEnumPublishStatus(){
        return new ResultTemplate("", EnumPublishStatus.getMapInfo());
    }
    /**
     *@author songwq
     *@param
     *@data 2018/7/4
     *@description 获取已购买作品的作品来源
    */
    @RequestMapping(value="getProductChannel")
    @ResponseBody
    public ResultTemplate getProductChannel(){
        return new ResultTemplate("", EnumProductChannelType.getMapInfo());
    }

    /**
     * @param orderSn
     * @author songwq
     * @data 2018/6/29
     * @description 买家已购作品订单下载
     */
    /*@RequestMapping(value = "getDownload")
    @ResponseBody
    public ResultTemplate getDownload(String orderSn, Integer percent, HttpServletRequest request , HttpServletResponse response){
        if(orderSn!=""){
            OrderInfoBuyer order = orderInfoBuyerServicesI.getByOrderSn(orderSn);
            if(StringUtils.isEmpty(order)){
                return new ResultTemplate("无法获取订单信息");
            }
            //根据订单查询作品
            YRProduction production = yRProductionServicesI.getYRProduction(orderSn);
            String localContent = production.getLocalcontent();
            String fileName= org.apache.commons.lang3.StringUtils.isBlank(production.getTitle())?"作品内容":production.getTitle();
            //判断是否下载全部(0下载30%，1下载全部)
            if(localContent.length()>0){
                if(percent==0){
                    localContent= YRProduction.getThirtyPercent(localContent);
                }
                Html2WordUtil.htmlToWord(localContent,fileName,request,response);
            }else {
                return new ResultTemplate("未查询到作品或者作品内容为空");
            }
        }
        return new ResultTemplate();
    }*/
    /**
     * 买家中心查询需求——订单详情
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/7/6 14:24
     */
    @RequestMapping("myOrderList")
    @ResponseBody
    public ResultTemplate myOrderList(Demand demand, BaseModel baseModel,HttpSession session) {
        demand.setWebUser(getWebUser(session));
        PageInfo<OrderInfoBuyer> orderInfoBuyerPageInfo = demandServicesI.getOrderList(demand,baseModel);
        JSONArray jsonArray = new JSONArray();
        //封装交易记录
        if(orderInfoBuyerPageInfo.getList().size()>0){
            for (OrderInfoBuyer orderInfoBuyer : orderInfoBuyerPageInfo.getList()) {
                JSONObject object = OrderInfoBuyer.packageToMy(orderInfoBuyer);
                jsonArray.add(object);
            }
        }
        return  new ResultTemplate(orderInfoBuyerPageInfo , jsonArray);
    }


    /**
     * 买家中心订单状态
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/10/10 16:08
     */
    @RequestMapping(value = "getOrderStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getOrderStatus() {
        return new ResultTemplate("", EnumOrderBuyerStatus.getMapInfoMy(false));
    }
    /**
     * 买家中心支付状态
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/10/10 16:08
     */
    @RequestMapping(value = "getPayStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getPayStatus() {
        return new ResultTemplate("", EnumPayStatus.getMapInfo());
    }
    /**
     * 买家中心订单类型
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/10/10 16:08
     */
    @RequestMapping(value = "getOrderType", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getOrderType() {
        return new ResultTemplate("", EnumOrderSellerType.getMapInfo());
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
    public ResultTemplate sureOrder(OrderSureSearch data,HttpSession session){
        // 验证数据
        if(data.getType()==null){
            return new ResultTemplate("订单确定类型不能为空");
        }
        if(getWebUser(session)==null){
            return  new ResultTemplate("用户未登陆");
        }
        // 从报名流水表中
        if(EnumOrderSure.从报名表.getIndex() == data.getType()){
            String[] orderSns = data.getOrderSns();
            if(orderSns==null){
                return new ResultTemplate("流水单号为空");
            }
        }
        // 作品购买立即下单
        if(data.getType()== EnumOrderSure.从立即下单.getIndex()){
            if(data.getYrProductionId()==null){
                return new ResultTemplate("作品id为空");
            }
        }
        //从购物车购买作品
        if(data.getType()==EnumOrderSure.从购物车.getIndex()){ // 3
            // data.getShoppingCarts()  判断是否为空
            if(data.getShoppingCarts()==null){
                return new ResultTemplate("购物车Id不能为空！");
            }
            // 验证购物车Id是否是当前用户购物数据
            for(int i=0; i<data.getShoppingCarts().length; i++){
                ShoppingCart shoppingCart = shoppingCartServicesI.getById(data.getShoppingCarts()[i]);
                if(shoppingCart==null ){
                    return  new ResultTemplate("该此用户的购物车id:"+ data.getShoppingCarts()[i]+"不存在！");
                }
                if(shoppingCart.getCartTypeIndex()!=2  || shoppingCart.getRegisteredUserInfoId()!=getWebUser(session).getRecID()){
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

}
