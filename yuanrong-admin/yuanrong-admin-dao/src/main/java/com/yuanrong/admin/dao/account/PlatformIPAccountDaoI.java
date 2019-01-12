package com.yuanrong.admin.dao.account;

import com.yuanrong.admin.bean.account.Account;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.account.ShortVideoPlatformInfo;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.seach.PlatformIpAccountSearchMall;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/27.
 */
@Repository
public interface PlatformIPAccountDaoI extends BaseDaoI<PlatformIPAccount> {
    /**
     * 获取平台分类信息
     * @return
     */
    public List<ShortVideoPlatformInfo> getPlatFormInfo();

    /**
     * 查询无IP的已有账号
     * @param platformIPAccount
     * @return
     */
    List<PlatformIPAccount> ipFindAccount(@Param("data") PlatformIPAccount platformIPAccount);

    /**
     * 批量添加无IP的已有账号
     * @param list
     */
    void updateAccount(@Param("listAccout") List<PlatformIPAccount> list);

    /**
     * 列表页搜索
     * @param platformIpAccountSeach
     * @return
     */
    public List<Map> list(@Param("data") PlatformIpAccountSeach platformIpAccountSeach);

    /**
     * 通过ID获取平台账号信息
     * @param id
     * @return
     */
    public Map<String , Object> getPlatFormIPAccountInfoById(@Param("id") int id);

    /**
     * 修改
     * @param platformIPAccount
     */
    void update(@Param("data") PlatformIPAccount platformIPAccount);

    /**
     * 根据指定条件，查询账号信息
     * @param data
     * @return
     */
    PlatformIPAccount getByParam(@Param("data") Map<String,Object> data);

    /**
     * 通过平台唯一标识和注册用户查找是否存在
     * @param pid
     * @param registerUserId
     * @return
     */
    PlatformIPAccount getByPidAndRegisterUserId(@Param("pid") String pid ,@Param("registerUserId") int registerUserId);

    /**
     * 批量上下架
     * @param data
     */
    void batchUpperLowerShelves(@Param("data") PlatformIpAccountSeach data);

    /**
     * 批量修改有效期
     * @param data
     */
    void batchUpdateInvalidTime(@Param("data") PlatformIpAccountSeach data);

    /**
     * 批量改价百分比波动
     * @param data
     */
    void batchUpdatePrice(@Param("data")PlatformIpAccountSeach data);

    /**
     * 前台账号搜索
     * @param data
     * @return
     */
    List<PlatformIPAccountResult> listMall(@Param("data") PlatformIpAccountSearchMall data);
    /**
    * 根据用户id更新待实名状态为上架
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/6/19 17:36
    */
    void updateAccountStatusByUserIDs(@Param("ids")List<Integer> ids);
    /**
     * 通过账号ID和平台id获取账号信息
     * @return
     */
    PlatformIPAccount getAccountInfoByPlatformAndAccountID(@Param("accountID") String accountID , @Param("platformId") Integer platformId);

    /**
     * 获取上架了的短视频平台
     * @return
     */
    List<Map<String , Object>> getShortVideoPlatformInfo();

    /**
     * 获取平台id,根据类别
     * @param category
     * @return
     */
    Map<String , Object> getPlatformIdsByCategorys(@Param("category") String category);

    /**
     *
     * @param pid
     * @param platformId
     * @return
     */
    Account getAccountByPidAndPlatformId(@Param("pid")String pid , @Param("platformId")int platformId);

    /**
     * 保存账号
     * @param account
     */
    void saveAccount(@Param("account") Account account);

    /**
     * 通过id获取唯一账号
     * @param id
     * @return
     */
    Account getAccountById(@Param("id") int id);

    /**
     * 获取批量账号
     * @param ids
     * @return
     */
    List<PlatformIPAccount> list_PlatformIPAccountByIds(@Param("ids") int[] ids);

    /**
     * 通过唯一平台id获取账号信息
     * @param accountOnlyId
     * @return
     */
    List<PlatformIPAccount> getShelvesbyAccountId(@Param("accountOnlyId") int accountOnlyId);

    /**
     * 通过购物车id获取平台IP账号信息
     * @param registeredUserInfoId 注册用户id
     * @param shopCartIds  购物车ids
     */
    List<PlatformIPAccount> list_PlatformIPAccountByShopCartIds(@Param("registeredUserInfoId") int registeredUserInfoId, @Param("shopCartIds") String shopCartIds);

    /**
     * 模糊查询账号
     * @param data
     * @return
     */
    List<PlatformIPAccount>  getPlatformIPAccountByNameLikeSearch(@Param("data") PlatformIPAccount data);

    /**
     * 通过Id、注册用户Id 获得PlatformIPAccount
     * @param data
     * @return
     */
    PlatformIPAccount getPlatformIPAccountById_RegistId(@Param("data")PlatformIPAccount data );
    /**
     * 修改是否代理
     */
    void updateAgent(@Param(value="data")PlatformIpAccountSeach data);
}
