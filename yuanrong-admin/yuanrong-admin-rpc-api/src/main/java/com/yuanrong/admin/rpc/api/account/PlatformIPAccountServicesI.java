package com.yuanrong.admin.rpc.api.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.account.Account;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.account.PlatformIPAccountPrice;
import com.yuanrong.admin.bean.account.ShortVideoPlatformInfo;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.seach.PlatformIpAccountSearchMall;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import com.yuanrong.admin.util.BaseModel;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/27.
 */
public interface PlatformIPAccountServicesI extends BaseServicesI<PlatformIPAccount> {
    /**
     * 获取平台分类信息
     * @return
     */
    public List<ShortVideoPlatformInfo> getPlatFormInfo();

    /**
     * 保存平台账号
     * @param data
     * @return
     */
    public void savePlatformIpAccount(PlatformIPAccount data , List<PlatformIPAccountPrice> ipAccountPrices);

    /**
     * 查询无IP的已有账号
     * @param platformIPAccount
     * @param baseModel
     * @return
     */
    PageInfo ipFindAccount(PlatformIPAccount platformIPAccount, BaseModel baseModel);

    /**
     * 批量添加无IP的已有账号
     * @param accountId
     * @param ipID
     */
    void updateAccount(String accountId, Integer ipID);
    /**
     * 列表页搜索
     * @param
     * @return
     */
    public PageInfo<Map> list(PlatformIpAccountSeach platformIpAccountSeach, BaseModel baseModel);

    /**
     * 通过ID获取平台账号信息
     * @param id
     * @return
     */
    public Map<String , Object> getPlatFormIPAccountInfoById(int id);

    /**
     * 修改IP账号信息，及价格信息
     * @param data
     */
    public void updatePlatFormIPAccountAndPrice(PlatformIPAccount data);

    /**
     * 批量保存
     * @param data
     */
    public void batchSavePlatformIpAccount(List<PlatformIPAccount> data);

    /**
     * 根据指定条件，查询账号信息
     * @param param
     * @return
     */
    PlatformIPAccount getByParam( Map<String,Object> param);

    /**
     * 通过平台唯一标识和注册用户查找是否存在
     * @param pid
     * @param registerUserId
     * @return
     */
    PlatformIPAccount getByPidAndRegisterUserId( String pid ,int registerUserId);

    /**
     * 批量上下架
     * @param data
     */
    void batchUpperLowerShelves(PlatformIpAccountSeach data);

    /**
     * 批量修改有效期
     * @param data
     */
    void batchUpdateInvalidTime(PlatformIpAccountSeach data);

    /**
     * 批量改价百分比波动
     * @param data
     */
    void batchUpdatePrice(PlatformIpAccountSeach data);

    /**
     * 前台账号搜索
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo<PlatformIPAccountResult> listMall(PlatformIpAccountSearchMall data , BaseModel baseModel);

    /**
     * 根据用户id更新待实名状态为上架
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/6/19 17:36
     */
    void updateAccountStatusByUserIDs(RegisteredUserAndCompany registeredUserAndCompany);

    /**
     * 获取上架了的短视频平台
     * @return
     */
    List<Map<String , Object>> getShortVideoPlatformInfo();


    /**
     * 通过id获取唯一账号
     * @param id
     * @return
     */
    Account getAccountById(int id);

    /**
     * 通过唯一平台id获取账号信息
     * @param accountOnlyId
     * @return
     */
    List<PlatformIPAccount> getShelvesbyAccountId(int accountOnlyId);
    /**
     * 获取批量创作者
     * @param ids
     * @return
     */
    PageInfo<JSONObject> list_platformIPAccount(int[] ids, BaseModel baseModel);


    /**
     * 通过账号ID和平台id获取账号信息
     * @return
     */
    PlatformIPAccount getAccountInfoByPlatformAndAccountID(String accountID , Integer platformId);

    /**
     * 处理消息队列的数据入库
     * @param queInfo
     */
    List<PlatformIPAccount> saveQueueMQ(JSONObject queInfo) throws InterruptedException;

    /**
     * 生成Excel 并发送消息
     * @param accountList
     */
     void createdExcelSendMsg(List<PlatformIPAccount> accountList , String fileName , String toUser) throws WxErrorException, IOException;

    /**
     * 通过购物车id获取平台IP账号信息
     * @param registeredUserInfoId 注册用户id
     * @param shopCartIds  购物车ids
     */
    PageInfo<JSONObject> list_PlatformIPAccountByShopCartIds(int registeredUserInfoId,  String shopCartIds,BaseModel baseModel);
    /**
     * 列表页搜索
     * @param
     * @return
     */
    PageInfo<Map> getList(PlatformIpAccountSeach platformIpAccountSeach, BaseModel baseModel);

    PageInfo<PlatformIPAccount> getPlatformIPAccountByNameLikeSearch(PlatformIPAccount data,BaseModel baseModel);


    /**
     * 通过Id、注册用户Id 获得PlatformIPAccount
     * @param data
     * @return
     */
    PlatformIPAccount getPlatformIPAccountById_RegistId(PlatformIPAccount data );
    /**
     * 修改是否代理
     */
    void updateAgent(PlatformIpAccountSeach data);

    /**
     * 获取平台id,根据类别
     * @param category
     * @return
     */
    Map<String , Object> getPlatformIdsByCategorys(String category);
}
