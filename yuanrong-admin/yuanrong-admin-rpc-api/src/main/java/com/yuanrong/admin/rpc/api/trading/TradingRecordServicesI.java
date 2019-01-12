package com.yuanrong.admin.rpc.api.trading;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.result.TradingRecordResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.DemandListParamSearch;
import com.yuanrong.admin.seach.TradingRecordSearch;
import com.yuanrong.admin.util.BaseModel;

/**
 * 交易记录的services接口
 * Created MDA
 */
public interface TradingRecordServicesI extends BaseServicesI<TradingRecord> {
    /**
     * TradingRecord添加
     * @param result
     * @return
     */
    Integer saveGetKey(JSONArray result);
    /**
    * 交易记录列表
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/8/20 17:06
    */
    PageInfo tradingRecordList(TradingRecordSearch tradingRecordSearch, BaseModel baseModel);
    /**
    * 更新交易记录
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/8/20 17:06
    */
    void update(TradingRecord tradingRecord);

    /**
     * 批量操作
     * @param tradingRecordSearch
     */
    void batch(TradingRecordSearch tradingRecordSearch);
}
