package com.yuanrong.admin.web.controller.order;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SnapshotYrProduction;
import com.yuanrong.admin.rpc.api.order.OrderInfoSellerServicesI;
import com.yuanrong.common.util.Html2WordUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 作品快照的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class SnapshotYrProductionController extends BaseController {
    private static final Logger logger = Logger.getLogger(SnapshotYrProductionController.class);

    /**
     * @param buyerOrderSn
     * @author songwq
     * @data 2018/6/29
     * @description 买家已购作品订单下载
     */
    @RequestMapping(value = "getDownloadInfo")
    @ResponseBody
    public void getDownloadInfo(String buyerOrderSn,Integer percent, HttpServletRequest request ,HttpServletResponse response) throws Exception {
        if (buyerOrderSn != "") {
            OrderInfoBuyer orderInfoBuyer = orderInfoBuyerServicesI.getByOrderSn(buyerOrderSn);
            if(StringUtils.isEmpty(orderInfoBuyer)){
                return ;
            }
            if(!StringUtils.isEmpty(getWebUser(request.getSession()))){
                if(!orderInfoBuyer.getRegisteredUserInfoId().equals(getWebUser(request.getSession()).getRecID()) || orderInfoBuyer.getPayStatusValue()!=2){
                    return ;
                }
            }
            SnapshotYrProduction snapshotYrProduction = snapshotYrProductionServicesI.getDownloadInfo(buyerOrderSn);
            if(StringUtils.isEmpty(snapshotYrProduction)){
                return ;
            }
            //修改作品阅读状态
            yRProductionServicesI.updateProductionReadStatus(snapshotYrProduction.getYrProductionId());
            String localContent = snapshotYrProduction.getLocalcontent();
            String fileName= org.apache.commons.lang3.StringUtils.isBlank(snapshotYrProduction.getTitle())?"作品内容":snapshotYrProduction.getTitle();

            if(localContent.length()>0){
                //判断是否下载全部(0下载30%，1下载全部)
                if(percent==0){
                    localContent= YRProduction.getThirtyPercent(localContent);
                }
                download(fileName + ".doc",Html2WordUtil.html2Word(localContent,fileName,request,response));
            }else {
                return ;
            }
        }
    }


}
