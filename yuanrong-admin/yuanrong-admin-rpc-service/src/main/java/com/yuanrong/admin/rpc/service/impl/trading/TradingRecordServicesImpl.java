package com.yuanrong.admin.rpc.service.impl.trading;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.Enum.EnumBaseStatus;
import com.yuanrong.admin.Enum.EnumMethodParamUpDown;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.result.TradingRecordResult;
import com.yuanrong.admin.rpc.api.trading.TradingRecordServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.TradingRecordSearch;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 交易记录的services实现类
 * Created MDA
 */
@Service
public class TradingRecordServicesImpl extends BaseServicesAbstract<TradingRecord> implements TradingRecordServicesI {
    @Override
    public Integer saveGetKey(JSONArray result) {
        int num = 0;
        if(result.size()>0){
            for(int i = 0; i<result.size(); i++){
                num++;
                JSONObject jb = result.getJSONObject(i);
                TradingRecord tradingRecord = new TradingRecord();
                tradingRecord.setNickName(jb.getString("nickName"));
                tradingRecord.setTradingDate(jb.getString("tradingDate"));
                tradingRecord.setSellerAccount(jb.getString("sellerAccount"));
                tradingRecord.setReferPlatform(jb.getString("referPlatform"));
                tradingRecord.setServicesContent(jb.getString("servicesContent"));
                tradingRecord.setMoney(jb.getString("money"));
                tradingRecord.setCooPerationBrand(jb.getString("cooPerationBrand"));
                tradingRecord.setChannelIndex(Integer.parseInt(jb.getString("channelIndex")));
                tradingRecord.setBuyerName(jb.getString("buyerName"));
                tradingRecord.setHeir(jb.getString("heir"));
                if(!StringUtil.isBlank(jb.getString("registeredUserInfoId"))){
                    tradingRecord.setRegisteredUserInfoId(Integer.parseInt(jb.getString("registeredUserInfoId")));
                }
                tradingRecordDaoI.saveGetKey(tradingRecord);
            }
        }
        return num;
    }

    @Override
    public TradingRecord getById(Integer id) {
        return tradingRecordDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        tradingRecordDaoI.deleteById(id);
    }
    @Override
    public void save(TradingRecord object) {
        tradingRecordDaoI.save(object);
    }
    @Override
    public List<TradingRecord> getAll() {
        return tradingRecordDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    @Override
    public PageInfo<TradingRecordResult> tradingRecordList(TradingRecordSearch tradingRecordSearch, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "tradingRecordId desc");
        //处理平台参数

        List<TradingRecordResult> result = tradingRecordDaoI.tradingRecordlist(tradingRecordSearch);
        return new PageInfo(result);
    }

    @Override
    public void update(TradingRecord tradingRecord) {
        tradingRecordDaoI.update(tradingRecord);
    }

    @Override
    public void batch(TradingRecordSearch tradingRecordSearch) {
        if(tradingRecordSearch.getTodo().equals(EnumMethodParamUpDown.删除.getIndex())){
            tradingRecordSearch.setStatusIndex(EnumBaseStatus.FORBIDDEN.getIndex());
            tradingRecordDaoI.update(tradingRecordSearch);
        }
    }
}
