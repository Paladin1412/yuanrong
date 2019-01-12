package com.yuanrong.admin.dao.trading;
import com.alibaba.fastjson.JSONArray;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.TradingRecordResult;
import com.yuanrong.admin.seach.TradingRecordSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 交易记录的dao
 * Created MDA
 */
@Repository
public interface TradingRecordDaoI extends BaseDaoI<TradingRecord> {
    /**
     * TradingRecord添加
     * @param object
     */
    Integer saveGetKey(TradingRecord object);
    /**
    * 交易记录列表
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/8/20 17:14
    */
    List<TradingRecordResult> tradingRecordlist(TradingRecordSearch tradingRecordSearch);
    /**
    * 更新交易记录
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/8/21 15:11
    */
    void update(TradingRecord tradingRecord);
}
