package com.yuanrong.admin.rpc.api.author;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.result.YRAuthorInfoResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.AuthorListParamSeach;
import com.yuanrong.admin.seach.AuthorListParamSeachMall;
import com.yuanrong.admin.util.BaseModel;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 圆融创作者的services接口
 * Created MDA
 */
public interface YRAuthorServicesI extends BaseServicesI<YRAuthor> {
    /**
     * 通过同用户作者名称判断作者是否存在
     * @param authorName
     * @param recID
     * @return
     */
    YRAuthor getYRAuhorByName(String authorName, Integer recID);

    /**
     * 通过作者名称判断作者是否存在
     * @param authorName
     * @return
     */
    YRAuthor getYRAuhorByName(String authorName);

    /**
     * 列表查询
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo<Map> list(AuthorListParamSeach data , BaseModel baseModel);

    /**
     * 获取创作者详情信息
     * @param recId
     * @return
     */
    List<Map<String,Object>> getAuthorDetailInfo(Integer recId);

    /**
     * 添加创作者
     * @param yRAuthor
     */
    Integer saveAuthor(YRAuthor yRAuthor);

    /**
     * 保存创作者—内容形式关联表
     * @param userId
     * @param contentformId
     */
    void saveAuthorContentForm(Integer userId, Integer contentformId);

    /**
     * 保存创作者—内容分类关联表
     * @param yrAuthorId
     * @param categoryId
     */
    void saveAuthorCategory(Integer yrAuthorId, Integer categoryId);

    /**
     * 保存创作者—使用场景关联表
     * @param userId
     * @param scenesId
     */
    void saveAuthorScenes(Integer userId, Integer scenesId);

    /**
     * 批量修改创作者价格
     * @param ids
     * @param pricePercent
     */
    void updateBatchPrice(String ids, BigDecimal pricePercent);

    /**
     * 修改创作者
     * @param yRAuthor
     */
    void updateAuthor(YRAuthor yRAuthor,Integer flag);

    /**
     * 批量操作（修改作者状态——上下架）
     * @param ids
     * @param status
     * @param recID
     */
    void batchApply(String ids, Integer status, Integer recID);

    /**
     * 创作者编辑页面
     * @param recId
     * @return
     */
    List<Map<String,Object>> getAuthorInfoUpdate(Integer recId);

    /**
     * 后台—批量操作(申请上架、下架、审核通过、审核不通过)
     * @param ids
     * @param status
     * @param reason
     * @param realName
     */
    void batchApplyBack(String ids, Integer status,String reason, String realName);


    /**
    * 批量保存作者，主要掉save（）方法
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/30 9:30
    */
    void batchSaveAuthor(List<YRAuthor> list);

    /**
    * 批量更新作者价格
    * @author      ShiLinghuai
    * @param        list 需要修改的信息
    * @return
    * @exception
    * @date        2018/5/30 17:30
    */
    void batchUpdateAuthorPrice(List<YRAuthor> list);


    /**
     * 前台商城—创作者列表
     * @param map
     * @param baseModel
     * @return
     */
    PageInfo<Map> authorList(AuthorListParamSeachMall map, BaseModel baseModel);

    /**
     * 前台商城—创作者\作品详情
     * @param recid
     * @param userId
     * @return
     */
    YRAuthorInfoResult getAuthorInfo(Integer recid,Integer userId);
    /**
    *批量添加合法的作者并返回错误的信息
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/6/5 10:08
    */
    JSONObject batchGetSaveSucceedInfoAndErrorInfoByList(List<List<String>> result);
    /**
     *批量添加合法的作者并返回错误的信息
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/6/5 10:08
     */
    JSONObject batchGetUpdatePriceSucceedInfoAndErrorInfoByList(List<List<String>> result);

    /**
     * 通过作者名称和注册用户模糊查询前6条数据
     * @param data
     * @return
     */
    List<YRAuthor> getAuthorsByName(YRAuthor data);

    /**
     * 获取创作者的字典信息（内容形式、使用场景、擅长领域）
     * @return
     */
    JSONObject getAuthorDicinfo();

    /**
     * 根据注册用户ID获取当前注册用户的所有创作者
     * @param registerUserInfoId
     * @return
     */
    List<YRAuthor> getByRegisterUserId( Integer registerUserInfoId);

    /**
     * 根据注册用户ID、创造者名称 ，获取当前注册用户的所有创作者
     * @param data
     * @return
     */
    PageInfo<YRAuthor> getYRAuthorListByRegisterUserId( YRAuthor data, BaseModel baseModel);


    /**
     * C端—创作者列表
     * @param authorParamSeach
     * @param baseModel
     * @return
     */
    PageInfo<YRAuthorInfoResult> getAuthorAndProList(AuthorListParamSeachMall authorParamSeach, BaseModel baseModel);

    /**
     * 获取相似创作者列表
     * @param a
     * @param b
     * @param categoryId
     * @param recid
     * @return
     */
    List<YRAuthorInfoResult> getLikeAuthor(Integer a, Integer b ,Integer categoryId ,Integer recid);

    /**
     * 获取相似创作者数量
     * @param categoryId
     * @param sortScore
     * @return
     */
    List<Map<String,Object>> getLikeAuthorNum(Integer categoryId, BigDecimal sortScore);

    /**
     * 获取批量创作者
     * @param ids
     * @return
     */
    PageInfo<JSONObject> list_yrAuthorByIds(String ids,BaseModel baseModel);

    /**
     * 后台—需求选购创作者列表
     * @param demandId
     * @param baseModel
     * @param likeName
     * @param authorStatus
     * @return
     */
    PageInfo<Map> getDemandAuthorList(Integer demandId,String[] likeName, Integer authorStatus, BaseModel baseModel);

    /**
     * 计算创作者排序分数
     */
    void updateCalculateSortScore();

    /**
     * C端—修改创作者访问次数
     */
    void updateAuthorAccessTimes(YRAuthor yrAuthor);

    /**
     * 通过购物车id获取yrAuthor信息
     * @param registeredUserInfoId
     * @param shopCartIds
     * @return
     */
    List<YRAuthor> getyrAuthorByshoppingCartId(int registeredUserInfoId,  String shopCartIds);

    /**
     * 通过作者Id和注册用户Id获取得到作者
     * @param data
     * @return
     */
    YRAuthor  getYRAuthorById_RegUserId( YRAuthor  data);

    /**
     * 通过创作者ID查询创作者信息——判断
     * @param recId
     * @return
     */
    YRAuthor getAuthorInfoById(Integer recId);

    /**
     * 将作者，作品的访问次数刷入数据库中
     */
    void updateFlushRedisAccessTimeToDb();
}
