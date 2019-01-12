package com.yuanrong.admin.mall.controller.trading;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuanrong.common.util.ResultTemplate;

import java.util.List;

/**
 * 交易记录的controller
 * Created MDA
 */
@Controller
@RequestMapping("trading")
public class TradingRecordController extends BaseController {
    private static final Logger logger = Logger.getLogger(TradingRecordController.class);

    @RequestMapping(value = "tradingRecord_list", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(TradingRecord data, BaseModel baseModel) {
        PageInfo tradingRecordPageInfo = tradingRecordServicesI.list(data, baseModel);
        JSONArray array = new JSONArray();
        if (CollectionUtil.size(tradingRecordPageInfo.getList()) > 0) {
        }
        return new ResultTemplate(tradingRecordPageInfo, array);
    }

    @RequestMapping("tradingRecord_save")
    public String save(TradingRecord user) {

        return "trading/systemUser_save";
    }

    @RequestMapping(value = "tradingRecord_saveOk", method = RequestMethod.POST)
    public void saveOk(TradingRecord theTradingRecord) {
        tradingRecordServicesI.save(theTradingRecord);
    }

    @RequestMapping("tradingRecord_update")
    public String update() {
        return "trading/tradingRecord_update";
    }

    @RequestMapping(value = "tradingRecord_updateOk", method = RequestMethod.POST)
    public void updateOk() {
    }
}
