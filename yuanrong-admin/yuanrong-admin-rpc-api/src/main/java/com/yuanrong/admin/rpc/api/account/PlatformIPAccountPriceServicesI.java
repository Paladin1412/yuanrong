package com.yuanrong.admin.rpc.api.account;

import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.account.PlatformIPAccountPrice;
import com.yuanrong.admin.rpc.BaseServicesI;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/27.
 */
public interface PlatformIPAccountPriceServicesI extends BaseServicesI<PlatformIPAccountPrice> {
    /**
     * 通过IP账号获取该账号下所有价格信息
     * @param ipAccountID
     * @return
     */
    List<PlatformIPAccountPrice> getAccountPriceByIPAccountID(int ipAccountID);

    /**
     * 批量修改价格和有效期
     * @param ipAccounts
     */
    void batchUpdatePrice (List<PlatformIPAccount>  ipAccounts);

    /**
     *
     * @param iPAccountIDs
     * @param sourceId
     * @return
     */
    List<Map<String , Object>> getGroupPriceByIPAccountIDs(int[] iPAccountIDs ,int sourceId);

}
