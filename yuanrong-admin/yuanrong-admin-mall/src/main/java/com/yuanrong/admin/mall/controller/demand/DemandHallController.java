package com.yuanrong.admin.mall.controller.demand;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDemandStatus;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.Enum.EnumYRProductionStatus;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 需求大厅
 * @author Tangz
 * @date 2018/9/4 16:03
 */

@Controller
@RequestMapping("demandHall")
public class DemandHallController extends BaseController {

    private static final Logger logger = Logger.getLogger(DemandHallController.class);

    /**
     * 需求大厅
     * @param data
     * @param baseModel
     * @return
     * @throws ParseException
     */
    @RequestMapping("getDemandHall")
    @ResponseBody
    public ResultTemplate getDemandHall(Demand data,BaseModel baseModel) throws ParseException {
        PageInfo<Demand> demandPageInfo =  demandServicesI.getDemandHall(data , baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(demandPageInfo.getList())>0){
            for(Demand demand : demandPageInfo.getList()){
                Map<String,Object> map = new HashMap<>();
                map.put("demandSn",demand.getDemandSn());
                map.put("demandTypeIndex",demand.getDemandTypeIndex());
                demand.setCnt_num(orderInfoSellerServicesI.cnt_num(map));
                result.add(Demand.packageDemandHall(demand,false));
            }
        }
        return new ResultTemplate(demandPageInfo,result);
    }

    @RequestMapping("demandTypeCnt")
    @ResponseBody
    public ResultTemplate demandTypeCnt() {
        List<Map<String,Integer>> list = demandServicesI.demandTypeCnt();
        return new ResultTemplate(list);
    }


    /**
     * 通过订单号获取订详情
     * @param demandSn
     * @return
     * @throws ParseException
     */
    @RequestMapping("getByDemandSn")
    @ResponseBody
    public ResultTemplate getByDemandSn(String demandSn) throws ParseException {

        Demand demand =StringUtil.isBlank(demandSn) ? null :   demandServicesI.getByDemandSn(demandSn);
        if(demand == null  || demand.getDemandStatusIndex()== EnumDemandStatus.待审核.getIndex() || demand.getDemandStatusIndex()== EnumDemandStatus.已取消.getIndex()){
            return new ResultTemplate("需求不存在");
        }
        //已报名
        List<OrderInfoSeller> osList =  orderInfoSellerServicesI.getReferIdByDemandSn(demandSn,null);
        if(CollectionUtil.size(osList) >0) {
            int num = 0;
            if (demand.getEnumDemandType().getIndex() == EnumDemandType.原创征稿.getIndex()) {
                for (OrderInfoSeller orderInfoSeller : osList) {
                    if (orderInfoSeller.getyRProduction().getYrProductionStatus().getIndex() == EnumYRProductionStatus.待审核.getIndex()
                            || orderInfoSeller.getyRProduction().getYrProductionStatus().getIndex() == EnumYRProductionStatus.审核不通过.getIndex()) {
                        num++;
                    }
                }
            }
            demand.setCnt_num(osList.size() - num);
        }
        JSONArray result = new JSONArray();
        result.add(Demand.packageDemandHall(demand,true));
        return new ResultTemplate("",result);
    }
}
