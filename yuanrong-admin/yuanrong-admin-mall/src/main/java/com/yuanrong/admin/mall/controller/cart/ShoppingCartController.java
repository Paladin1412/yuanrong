package com.yuanrong.admin.mall.controller.cart;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.account.Account;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.result.ShoppingCartResult;
import com.yuanrong.admin.seach.ShoppingCartSearch;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ExcelUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.SystemParam;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * 购物车的controller
 * Created MDA
 */
@Controller
@RequestMapping("cart")
public class ShoppingCartController extends BaseController {
    private static final Logger logger = Logger.getLogger(ShoppingCartController.class);

    /**
     * 购物车作者列表
     * @param data
     */
    @RequestMapping( value = "shoppingCart_authorList" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate authorList(ShoppingCartSearch data){
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        List<ShoppingCartResult> shoppingCartPageInfo = shoppingCartServicesI.listAuthorShoppingCart(data);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(shoppingCartPageInfo) > 0){
            for(ShoppingCartResult cartResult : shoppingCartPageInfo){
                JSONObject ele = new JSONObject();
                ele.put("productId" ,cartResult.getProductId());
                ele.put("shoppingCartId" , cartResult.getShoppingCartId());
                ele.put("authorImg" , cartResult.getYrAuthor().getAuthorImg());
                ele.put("authorNickname" , cartResult.getYrAuthor().getAuthorNickname());
                ele.put("introduction" , cartResult.getYrAuthor().getIntroduction());
                ele.put("createdPrice" , cartResult.getYrAuthor().getCreatedPrice());
                ele.put("authorStatus" , cartResult.getYrAuthor().getAuthorStatus());
                ele.put("authorStatusName" , cartResult.getYrAuthor().getEnumAuthorStatus().getName());
                //是否失效
                ele.put("isInvalid" , cartResult.getYrAuthor().getAuthorStatus() == EnumAuthorStatus.上架.getIndex() ? 1 : "0");
                result.add(ele);
            }
        }
        return new ResultTemplate( result);
    }

    /**
     * 购物车作品列表
     * @param data
     */
    @RequestMapping( value = "shoppingCart_productList" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate productList(ShoppingCartSearch data ){
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        List<ShoppingCartResult> shoppingCartResults = shoppingCartServicesI.listProductShoppingCart(data);
        JSONObject result = new JSONObject();
        JSONArray content = new JSONArray();
        BigDecimal unPublishMoney = BigDecimal.ZERO;
        BigDecimal publishMoney = BigDecimal.ZERO;
        if(CollectionUtil.size(shoppingCartResults) > 0){
            for(ShoppingCartResult ele : shoppingCartResults){
                JSONObject object = new JSONObject();
                object.put("shoppingCartId" , ele.getShoppingCartId());
                object.put("productId" , ele.getProductId());
                object.put("coverLocalUrl" , ele.getYrProduction().getCoverLocalUrl());
                object.put("publishStatusIndex" , ele.getYrProduction().getPublishStatus().getIndex());
                object.put("publishStatusName" , ele.getYrProduction().getPublishStatus().getName());
                object.put("title" , ele.getYrProduction().getTitle());
                object.put("authorNickname" , ele.getYrProduction().getYrAuthor().getAuthorNickname());
                object.put("quotedPrice" , ele.getYrProduction().getProductQuotedPrice());
                if(ele.getYrProduction().getPublishStatus().getIndex() == EnumPublishStatus.未公开.getIndex()){
                    unPublishMoney = unPublishMoney.add(ele.getYrProduction().getProductQuotedPrice());
                }else{
                    publishMoney = publishMoney.add(ele.getYrProduction().getProductQuotedPrice());
                }
                //是否失效
                object.put("isInvalid" , ele.getYrProduction().getYrProductionStatus().getIndex() == EnumYRProductionStatus.上架.getIndex() ? 1 : 0);
                content.add(object);
            }
        }

        result.put("content" , content);
        result.put("unPublishMoney" , unPublishMoney);
        result.put("publishMoney" , publishMoney);
        return new ResultTemplate("" , result);
    }

    /**
     * 购物车账号列表
     * @param data
     */
    @RequestMapping( value = "shoppingCart_accountList" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate accountList(ShoppingCartSearch data ){
        data.setRegisteredUserInfoId(getWebUser().getRecID());
        List<ShoppingCartResult> cartResults = shoppingCartServicesI.listAccountShoppingCart(data);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(cartResults) > 0){
            for (ShoppingCartResult ele : cartResults){
                JSONObject object = new JSONObject();
                object.put("shoppingCartId" , ele.getShoppingCartId());
                object.put("fans" , ele.getPlatformIPAccount().getFans());
                object.put("accountName" , ele.getPlatformIPAccount().getName());
                object.put("headImageUrl" , ele.getPlatformIPAccount().getHeadImageUrlLocal());
                object.put("accountId" , ele.getPlatformIPAccount().getAccountID());
                object.put("icoUrl" , ele.getPlatformIPAccount().getShortVideoPlatformInfo().getIcoUrl());
                object.put("isInvalid" ,ele.getShelvesNum() > 0 ? 1 : 0);
                object.put("priceInfo" , PlatformIPAccount.getJSONArraybyStringPrice(ele.getPriceInfo()));
                object.put("productId" , ele.getProductId());
                result.add(object);
            }
        }
        return new ResultTemplate("" , result);
    }

    /**
     * 增加作者到购物车
     * @param shoppingCart
     */
    @RequestMapping("shoppingCart_addAuthor")
    @ResponseBody
    public ResultTemplate addAuthor(ShoppingCart shoppingCart){
        YRAuthor yrAuthor = shoppingCart.getProductId() == null ? null :yRAuthorServicesI.getById(shoppingCart.getProductId());
        if(yrAuthor == null){
            return new ResultTemplate("创作者不存在",null);
        }
        if(yrAuthor.getEnumAuthorStatus().getIndex() != EnumAuthorStatus.上架.getIndex()){
            return new ResultTemplate("很抱歉，创作者未上架",null);
        }
        shoppingCart.setCartType(EnumCartType.创作者);
        JSONObject result = new JSONObject();

        //验证该商品是否已经存在于购物车中
        shoppingCart.setRegisteredUserInfoId(getWebUser().getRecID());
        ShoppingCart check = shoppingCartServicesI.isExistProduct(shoppingCart);
        if(check != null){
            result.put("shoppingCartId" , check.getShoppingCartId());
            return new ResultTemplate(result);
        }
        int maxNum = Integer.parseInt(configurationServicesI.getbyKey(SystemParam.SHOPPING_CART_MAX_NUM));
        if(shoppingCartServicesI.cartNumByType(shoppingCart).intValue() >= maxNum){
            return new ResultTemplate("最多可选择"+maxNum+"创作者加入选购车! ");
        }
        shoppingCart.setNum(BigDecimal.ONE);
        ShoppingCart cart = shoppingCartServicesI.saveShoppingCart(shoppingCart);
        result.put("shoppingCartId" , cart.getShoppingCartId());
        return new ResultTemplate("",result);
    }

    /**
     * 增加作品到购物车
     * @param shoppingCart
     */
    @RequestMapping("shoppingCart_addProduction")
    @ResponseBody
    public ResultTemplate addProduction(ShoppingCart shoppingCart){
        YRProduction production = shoppingCart.getProductId() == null ? null : yRProductionServicesI.getById(shoppingCart.getProductId());
        if(production == null){
            return new ResultTemplate("作品不存在",null);
        }

        if(production.getYrProductionStatus().getIndex() != EnumYRProductionStatus.上架.getIndex()){
            return new ResultTemplate("很抱歉，作品未上架或已售罄",null);
        }
        shoppingCart.setCartType(EnumCartType.作品);
        shoppingCart.setRegisteredUserInfoId(getWebUser().getRecID());
        //验证该商品是否已经存在于购物车中
        if(shoppingCartServicesI.isExistProduct(shoppingCart)!= null){
            return new ResultTemplate("购物车已经存在该商品，无需多次添加",null);
        }

        int maxNum = Integer.parseInt(configurationServicesI.getbyKey(SystemParam.SHOPPING_CART_MAX_NUM));
        if(shoppingCartServicesI.cartNumByType(shoppingCart).intValue() >= maxNum){
            return new ResultTemplate("最多可选择"+maxNum+"个作品加入选购车!  ");
        }
        shoppingCart.setNum(BigDecimal.ONE);
        ShoppingCart cart = shoppingCartServicesI.saveShoppingCart(shoppingCart);
        JSONObject result = new JSONObject();
        result.put("shoppingCartId" , cart.getShoppingCartId());
        return new ResultTemplate("",result);
    }

    /**
     * 增加账号到购物车
     * @param shoppingCart
     */
    @RequestMapping("shoppingCart_addAccount")
    @ResponseBody
    public ResultTemplate addAccount(ShoppingCart shoppingCart){
        Account account = shoppingCart.getProductId() == null ? null : platformIPAccountServicesI.getAccountById(shoppingCart.getProductId());
        if(account == null){
            return new ResultTemplate("账号不存在",null);
        }

        //验证是否有上架的账号
        List<PlatformIPAccount> platformIPAccountList = platformIPAccountServicesI.getShelvesbyAccountId(account.getAccountId());
        if(CollectionUtil.size(platformIPAccountList) <=0){
            return new ResultTemplate("账号不存在",null);
        }

        boolean flage = true;
        for(PlatformIPAccount ipAccount : platformIPAccountList){
            if(ipAccount.getPlatformIPAccountStatus().getIndex() == EnumPlatformIPAccountStatus.上架.getIndex()){
                flage = false;
                break;
            }
        }
        if(flage){
            return new ResultTemplate("账号未上架，无法加入购物车",null);
        }
        shoppingCart.setCartType(EnumCartType.营销分发);
        shoppingCart.setRegisteredUserInfoId(getWebUser().getRecID());
        JSONObject result = new JSONObject();
        //验证该商品是否已经存在于购物车中
        ShoppingCart check = shoppingCartServicesI.isExistProduct(shoppingCart);
        if(check!= null){
            result.put("shoppingCartId" , check.getShoppingCartId());
            return new ResultTemplate(result);
        }

        int maxNum = Integer.parseInt(configurationServicesI.getbyKey(SystemParam.SHOPPING_CART_MAX_NUM));
        if(shoppingCartServicesI.cartNumByType(shoppingCart).intValue() >= maxNum){
            return new ResultTemplate("最多可选择"+maxNum+"个账号加入选购车！");
        }
        shoppingCart.setNum(BigDecimal.ONE);
        ShoppingCart cart = shoppingCartServicesI.saveShoppingCart(shoppingCart);

        result.put("shoppingCartId" , cart.getShoppingCartId());
        return new ResultTemplate("",result);
    }

    @RequestMapping("removeShoppingCartProduct")
    @ResponseBody
    public ResultTemplate removeShoppingCartProduct(ShoppingCartSearch shoppingCart){
        if(CollectionUtil.size(shoppingCart.getShoppingCartIds()) <= 0){
            return new ResultTemplate("商品信息为空，请勾选商品信息后再提交");
        }
        shoppingCartServicesI.batchDelete(getWebUser().getRecID(),shoppingCart.getShoppingCartIds());
        return new ResultTemplate();
    }

    @RequestMapping("removeFailureProduct")
    @ResponseBody
    public ResultTemplate removeFailureProduct(ShoppingCartSearch shoppingCart){
        if(shoppingCart.getCartTypeIndex() == null){
            return new ResultTemplate("参数错误",null);
        }
        shoppingCart.setRegisteredUserInfoId(getWebUser().getRecID());
        if(shoppingCart.getCartTypeIndex().intValue() == EnumCartType.作品.getIndex()){
            shoppingCartServicesI.deleteFailureProduction(shoppingCart);
        }else if(shoppingCart.getCartTypeIndex().intValue() == EnumCartType.创作者.getIndex()){
            shoppingCartServicesI.deleteFailureYrAuthor(shoppingCart);
        }else if(shoppingCart.getCartTypeIndex().intValue() == EnumCartType.营销分发.getIndex()){
            shoppingCartServicesI.deleteFailureAccount(shoppingCart);
        }else{
            return new ResultTemplate("参数错误",null);
        }
        return new ResultTemplate("",null);
    }

    @RequestMapping("num")
    @ResponseBody
    public ResultTemplate num(){
        List<Map> shoppingNum = shoppingCartServicesI.shoppingCartNum(getWebUser().getRecID());
        JSONArray reslut = new JSONArray();
        for(EnumCartType type : EnumCartType.values()){
            boolean flage = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cartTypeIndex" , type.getIndex());
            jsonObject.put("cartTypeName" , type.getName());
             if(CollectionUtil.size(shoppingNum) > 0){
                 for (int i = 0; i < shoppingNum.size(); i++) {
                     if(type.getIndex() == Integer.parseInt(shoppingNum.get(i).get("cartTypeIndex").toString())){
                         jsonObject.put("num" , Integer.parseInt(shoppingNum.get(i).get("num").toString()));
                         flage = false;
                         break;
                     }
                 }
             }
             if(flage){
                 jsonObject.put("num" , 0);
             }
             reslut.add(jsonObject);
        }
        return new ResultTemplate(reslut);
    }

    @RequestMapping("accountTypeNum")
    @ResponseBody
    public ResultTemplate accountTypeNum(){
        List<Map> accountTypeNum = shoppingCartServicesI.getAccountTypeCount(getWebUser().getRecID());
        JSONObject result = new JSONObject();
        int shortVideoNum = 0; int weixinNum = 0 ; int weiboNum = 0;
        if(CollectionUtil.size(accountTypeNum) > 0){
            for (int i = 0; i < accountTypeNum.size(); i++) {
                if(accountTypeNum.get(i).get("PlatformName").toString().contains("微信")){
                    weixinNum = Integer.parseInt(accountTypeNum.get(i).get("num").toString());
                }else if(accountTypeNum.get(i).get("PlatformName").toString().contains("微博")){
                    weiboNum = Integer.parseInt(accountTypeNum.get(i).get("num").toString());
                } else {
                    shortVideoNum +=   Integer.parseInt(accountTypeNum.get(i).get("num").toString());
                }
            }
        }
        result.put("weixinNum" , weixinNum);
        result.put("weiBoNum" , weiboNum);
        result.put("shortVideoNum" , shortVideoNum);
        return new ResultTemplate(result);
    }

    @RequestMapping("removeAll")
    @ResponseBody
    public ResultTemplate removeAll(){
        shoppingCartServicesI.removeAllByRegisterUserId(getWebUser().getRecID());
        return new ResultTemplate();
    }

    @RequestMapping("download/shopptingCartAccount")
    @ResponseBody
    public ResultTemplate downloadShopptingCartAccount(ShoppingCartSearch shoppingCart){
        if(CollectionUtil.size(shoppingCart.getShoppingCartIds()) <= 0){
            return new ResultTemplate("商品信息为空，请勾选商品信息后再提交");
        }
        shoppingCart.setRegisteredUserInfoId(getWebUser().getRecID());
        List<ShoppingCartResult> cartResults = shoppingCartServicesI.listAccountShoppingCart(shoppingCart);

        if(CollectionUtil.size(cartResults) <= 0){
            return new ResultTemplate("没有查询到数据");
        }

        Workbook wb = new SXSSFWorkbook();
        //标题
        Vector<String> titleWeixin = new Vector<>();
        titleWeixin.addAll(Arrays.asList(new String[]{"编号","账号名称","微信号","简介","粉丝数(万人)","平均阅读数","平均点赞数","价格有效期"}));
        Vector<String>  titleWeiBo = new Vector<>();
        titleWeiBo.addAll(Arrays.asList(new String[]{"编号","账号名称","简介","链接","粉丝数(万人)","平均点赞数","平均评论数","平均转发数 ","价格有效期"}));
        Vector<String>  titleVdieo = new Vector<>();
        titleVdieo.addAll(Arrays.asList(new String[]{"编号","平台","账号名称","ID","简介","链接","粉丝数(万人)","平均播放量","平均评论数","平均点赞数","价格有效期"}));
        //结果集
        Vector<Vector<Object>> contentWeixin = new Vector<>();
        Vector<Vector<Object>> contentWeibo = new Vector<>();
        Vector<Vector<Object>> contentVideo = new Vector<>();
        int weixinIndex = 1;
        int weiboIndex = 1;
        int voideIndex = 1;
        for(ShoppingCartResult ele : cartResults){
            String[] priceInfo = ele.getPriceInfo().split("-_-");
            //微信
            if(ele.getPlatformIPAccount().getPlatformID() == 12){
                Vector<Object> content = new Vector<>(titleWeixin.size());
                content.add(weixinIndex ++);
                content.add(ele.getPlatformIPAccount().getName());
                content.add(ele.getPlatformIPAccount().getAccountID());
                content.add(ele.getPlatformIPAccount().getIntroduction());
                content.add(ele.getPlatformIPAccount().getFans()/10000.0);
                content.add(ele.getPlatformIPAccount().getAvgReadCount());
                content.add(ele.getPlatformIPAccount().getAvgLikeCount());
                content.add(ele.getPlatformIPAccount().getInvalidTime());
                dealPriceTitle(content , priceInfo , titleWeixin);
                contentWeixin.add(content);
            } else if(ele.getPlatformIPAccount().getPlatformID() == 13){ //微博
                Vector<Object> content = new Vector<>(titleWeiBo.size());
                content.add(weiboIndex ++ );
                content.add(ele.getPlatformIPAccount().getName());
                content.add(ele.getPlatformIPAccount().getIntroduction());
                content.add(ele.getPlatformIPAccount().getIndexUrl());
                content.add(ele.getPlatformIPAccount().getFans()/10000.0);
                content.add(ele.getPlatformIPAccount().getAvgLikeCount());
                content.add(ele.getPlatformIPAccount().getAvgCommontCount());
                content.add(ele.getPlatformIPAccount().getAvgForwardCount());
                content.add(ele.getPlatformIPAccount().getInvalidTime());
                dealPriceTitle(content , priceInfo , titleWeiBo);
                contentWeibo.add(content);
            }else { //短视频
                Vector<Object> content = new Vector<>(titleVdieo.size());
                content.add(voideIndex ++ );
                content.add(ele.getPlatformIPAccount().getShortVideoPlatformInfo().getPlatformname());
                content.add(ele.getPlatformIPAccount().getName());
                content.add(ele.getPlatformIPAccount().getAccountID());
                content.add(ele.getPlatformIPAccount().getIntroduction());
                content.add(ele.getPlatformIPAccount().getIndexUrl());
                content.add(ele.getPlatformIPAccount().getFans()/10000.0);
                content.add(ele.getPlatformIPAccount().getAvgPlayCount());
                content.add(ele.getPlatformIPAccount().getAvgCommontCount());
                content.add(ele.getPlatformIPAccount().getAvgLikeCount());
                content.add(ele.getPlatformIPAccount().getInvalidTime());
                dealPriceTitle(content , priceInfo , titleVdieo);
                contentVideo.add(content);
            }
        }

        if(CollectionUtil.size(contentWeixin) > 0){
            ExcelUtil.created(wb,null,"微信账号",titleWeixin,contentWeixin);
        }
        if(CollectionUtil.size(contentWeibo) > 0){
            ExcelUtil.created(wb,null,"微博账号",titleWeiBo,contentWeibo);
        }
        if(CollectionUtil.size(contentVideo) > 0){
            ExcelUtil.created(wb,null,"短视频账号",titleVdieo,contentVideo);
        }

        //下载文件
        download("圆融账号报价单_"+ DateUtil.format(new Date(),"yyyyMMddHHmmss")+".xlsx" , wb);
        return null;
    }

    private void dealPriceTitle(Vector<Object> content ,String[] priceInfo ,Vector<String> title ){
        for(String price : priceInfo){
            String[] priceTemp = price.split("_-_");
            if(!title.contains(priceTemp[0]+"(元)")){
                title.add(priceTemp[0]+"(元)");
            }
            for (int i = content.size(); i < title.size() ; i++) {
                content.add("");
            }
            content.add(title.indexOf(priceTemp[0]+"(元)") , priceTemp[1] + (priceTemp[2] == null || priceTemp[2].equals("1") ? "(含原创)" : ""));
        }
    }

    @RequestMapping("download/shopptingCartAuthor")
    @ResponseBody
    public ResultTemplate downloadShopptingCartAuthor(ShoppingCartSearch shoppingCart){
        if(CollectionUtil.size(shoppingCart.getShoppingCartIds()) <= 0){
            return new ResultTemplate("商品信息为空，请勾选商品信息后再提交");
        }
        shoppingCart.setRegisteredUserInfoId(getWebUser().getRecID());
        List<ShoppingCartResult> shoppingCarInfo = shoppingCartServicesI.listAuthorShoppingCart(shoppingCart);
        if(CollectionUtil.size(shoppingCarInfo) <= 0 ){
            return new ResultTemplate("没有查询到数据");
        }
        Vector<String> title = new Vector<>();
        title.addAll(Arrays.asList(new String[]{"编号","创作者名称","简介","链接","内容形式","擅长领域","原创参考价(元)"}));

        Workbook wb = new SXSSFWorkbook();
        Vector<Vector<Object>> resultContent = new Vector<>();
        int num = 1;
        for(ShoppingCartResult ele : shoppingCarInfo){
            Vector<Object> content = new Vector<>(title.size());
            content.add(num++);
            content.add(ele.getYrAuthor().getAuthorNickname());
            content.add(ele.getYrAuthor().getIntroduction());
            content.add("http://yuanrongbank.com/contentBank/abnormal_author_detail_"+ele.getProductId()+".html");
            content.add(ele.getYrAuthor().getContentForm());
            content.add(ele.getYrAuthor().getCategory());
            content.add(ele.getYrAuthor().getCreatedPrice());
            resultContent.add(content);
        }
        ExcelUtil.created(wb,null,"创作者",title,resultContent);
        //下载文件
        download("圆融创作者报价单_"+ DateUtil.format(new Date(),"yyyyMMddHHmmss")+".xlsx" , wb);
        return null;
    }

    @RequestMapping("download/shopptingCartProduct")
    @ResponseBody
    public ResultTemplate downloadShopptingCartProduct(ShoppingCartSearch shoppingCart){
        if(CollectionUtil.size(shoppingCart.getShoppingCartIds()) <= 0){
            return new ResultTemplate("商品信息为空，请勾选商品信息后再提交");
        }
        shoppingCart.setRegisteredUserInfoId(getWebUser().getRecID());
        List<ShoppingCartResult> shoppingCartResults = shoppingCartServicesI.listProductShoppingCart(shoppingCart);
        if(CollectionUtil.size(shoppingCartResults) <= 0 ){
            return new ResultTemplate("没有查询到数据");
        }
        Vector<String> title = new Vector<>();
        title.addAll(Arrays.asList(new String[]{"编号","作品标题","字数","创作者","链接","作品形式","作品分类","是否发布","转载使用价格(元)","买断价格(元)"}));

        Workbook wb = new SXSSFWorkbook();
        Vector<Vector<Object>> resultContent = new Vector<>();
        int num = 1;
        for(ShoppingCartResult ele : shoppingCartResults){
            Vector<Object> content = new Vector<>(title.size());
            content.add(num++);
            content.add(ele.getYrProduction().getTitle());
            content.add(ele.getYrProduction().getWordNum());
            content.add(ele.getYrProduction().getYrAuthor().getAuthorNickname());
            content.add("http://www.yuanrongbank.com/contentBank/article_detail_"+ele.getProductId()+".html");
            content.add(ele.getYrProduction().getContentForm().getContentFormName());
            content.add(ele.getYrProduction().getYrCategory().getName());
            content.add(ele.getYrProduction().getPublishStatus().getName());
            if(ele.getYrProduction().getPublishStatus().getIndex() == EnumPublishStatus.未公开.getIndex()){
                content.add("-");
                content.add(ele.getYrProduction().getProductQuotedPrice());
            }else{
                content.add(ele.getYrProduction().getProductQuotedPrice());
                content.add("-");
            }
            resultContent.add(content);
        }
        ExcelUtil.created(wb,null,"作品",title,resultContent);
        //下载文件
        download("圆融作品报价单_"+ DateUtil.format(new Date(),"yyyyMMddHHmmss")+".xlsx" , wb);
        return null;
    }

    /**
     * C端—确认购买信息
     * @param shoppingCartIds
     * @return
     */
    @RequestMapping("confirmBuy")
    @ResponseBody
    public ResultTemplate confirmBuy(Integer[] shoppingCartIds){
        if(shoppingCartIds== null || shoppingCartIds.length <= 0){
            return new ResultTemplate("购物车Id参数有误");
        }
        if(shoppingCartIds.length > 0){
            for (Integer id : shoppingCartIds){
                ShoppingCart shoppingCart = shoppingCartServicesI.getById(id);
                if(shoppingCart == null){
                    return new ResultTemplate("购物车信息不存在");
                }
            }
        }
        ShoppingCartSearch shoppingCartSearch = new ShoppingCartSearch();
        shoppingCartSearch.setShoppingCartIds(shoppingCartIds);
        shoppingCartSearch.setRegisteredUserInfoId(getWebUser().getRecID());
        List<ShoppingCartResult> shoppingCartResultList = orderInfoBuyerServicesI.getProductByShppingCartIdssAndUserId(shoppingCartSearch);
        if (CollectionUtil.size(shoppingCartResultList) < 0 || shoppingCartResultList.size() != shoppingCartIds.length){
            return new ResultTemplate("购物车部分商品无效，请刷新页面重新提交订单");
        }
        JSONObject result = new JSONObject();
        if(CollectionUtil.size(shoppingCartResultList) > 0){
            //成本价
            BigDecimal costMoney = BigDecimal.ZERO;
            for (ShoppingCartResult shoppingCartResult : shoppingCartResultList){
                costMoney = costMoney.add(shoppingCartResult.getYrProduction().getProductQuotedPrice());
            }
            //服务费
            BigDecimal servicesFeePercent = new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_PERCENT)).divide(new BigDecimal("100"));
            BigDecimal servicesFee = costMoney.multiply(servicesFeePercent).setScale(0,BigDecimal.ROUND_UP);
            //发票费
            BigDecimal invoiceFeePercent = new BigDecimal(configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER)).divide(new BigDecimal("100"));
            BigDecimal invoiceFee = (((costMoney.add(servicesFee)).divide(BigDecimal.ONE.subtract(invoiceFeePercent),2,BigDecimal.ROUND_UP)).subtract(costMoney).subtract(servicesFee)).setScale(0,BigDecimal.ROUND_UP);
            result.put("num",shoppingCartResultList.size());
            result.put("costMoney",costMoney);
            result.put("servicesFee",servicesFee);
            result.put("invoiceFee",invoiceFee);
            result.put("totalMoney",costMoney.add(servicesFee).add(invoiceFee));
        }
        return new ResultTemplate(result);
    }

    /**
     * 确认购买作品
     * @param goodsId
     * @return
     */
    @RequestMapping("confirmBuyPro")
    @ResponseBody
    public ResultTemplate confirmBuyPro(Integer goodsId){
        if (goodsId == null){
            return new ResultTemplate("商品Id为空");
        }
        YRProduction yrProduction = yRProductionServicesI.getById(goodsId);
        if (yrProduction == null || Integer.valueOf(yrProduction.getYrProductionStatusIndex()) != EnumYRProductionStatus.上架.getIndex()){
            return new ResultTemplate("商品信息不存在");
        }
        JSONObject result = new JSONObject();
        if (yrProduction != null){
            //成本价
            BigDecimal costMoney = yrProduction.getProductQuotedPrice();
            //服务费
            BigDecimal servicesFeePercent = new BigDecimal(configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_PERCENT)).divide(new BigDecimal("100"));
            BigDecimal servicesFee = costMoney.multiply(servicesFeePercent).setScale(0,BigDecimal.ROUND_UP);
            //发票费
            BigDecimal invoiceFeePercent = new BigDecimal(configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER)).divide(new BigDecimal("100"));
            BigDecimal invoiceFee = (((costMoney.add(servicesFee)).divide(BigDecimal.ONE.subtract(invoiceFeePercent),2,BigDecimal.ROUND_UP)).subtract(costMoney).subtract(servicesFee)).setScale(0,BigDecimal.ROUND_UP);
            result.put("num",1);
            result.put("costMoney",costMoney);
            result.put("servicesFee",servicesFee);
            result.put("invoiceFee",invoiceFee);
            result.put("totalMoney",costMoney.add(servicesFee).add(invoiceFee));
        }
        return new ResultTemplate(result);
    }
}
