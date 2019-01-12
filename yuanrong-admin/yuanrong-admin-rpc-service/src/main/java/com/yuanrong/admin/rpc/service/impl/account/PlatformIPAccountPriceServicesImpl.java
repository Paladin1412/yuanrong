package com.yuanrong.admin.rpc.service.impl.account;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.account.PlatformIPAccountPrice;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountPriceServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/27.
 */
@Service
public class PlatformIPAccountPriceServicesImpl extends BaseServicesAbstract<PlatformIPAccountPrice> implements PlatformIPAccountPriceServicesI{
    @Override
    public PlatformIPAccountPrice getById(Integer id) {
        return platformIPAccountPriceDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(PlatformIPAccountPrice object) {

    }

    @Override
    public List<PlatformIPAccountPrice> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    @Override
    public List<PlatformIPAccountPrice> getAccountPriceByIPAccountID(int ipAccountID) {
        return platformIPAccountPriceDaoI.getAccountPriceByIPAccountID(ipAccountID);
    }

    @Override
    public void batchUpdatePrice(List<PlatformIPAccount> ipAccount) {
            for(PlatformIPAccount account : ipAccount){
                platformIPAccountDaoI.update(account);
                for(PlatformIPAccountPrice price : account.getIpAccountPrices()){
                    platformIPAccountPriceDaoI.update(price);
                }
            }
    }

    @Override
    public List<Map<String, Object>> getGroupPriceByIPAccountIDs(int[] iPAccountIDs, int sourceId) {
        return platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(iPAccountIDs, sourceId);
    }
}
