package com.yuanrong.admin.dao.account;

import com.yuanrong.admin.bean.account.PlatformIPAccountPrice;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/27.
 */
@Repository
public interface PlatformIPAccountPriceDaoI extends BaseDaoI<PlatformIPAccountPrice> {
    /**
     * 修改
     * @param data
     */
    public void update(@Param("data") PlatformIPAccountPrice data);

    /**
     * 通过IP账号获取该账号下所有价格信息
     * @param ipAccountID
     * @return
     */
    List<PlatformIPAccountPrice> getAccountPriceByIPAccountID(@Param("ipAccountID") int ipAccountID);

    /**
     *根据报价公司和账号主键获取报价信息
     * @param iPAccountIDs
     * @param sourceId
     * @return
     */
    List<Map<String , Object>> getGroupPriceByIPAccountIDs(@Param("iPAccountIDs") int[] iPAccountIDs , @Param("sourceId") int sourceId);

    /**
     * @param pids
     * @return
     */
    List<Map<String , Object>> getGroupPriceByPids(@Param("pids") String[] pids);
}
