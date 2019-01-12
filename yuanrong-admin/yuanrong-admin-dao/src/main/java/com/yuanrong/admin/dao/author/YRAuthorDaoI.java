package com.yuanrong.admin.dao.author;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.YRAuthorInfoResult;
import com.yuanrong.admin.seach.AuthorListParamSeach;
import com.yuanrong.admin.seach.AuthorListParamSeachMall;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 圆融创作者的dao
 * Created MDA
 */
@Repository
public interface YRAuthorDaoI extends BaseDaoI<YRAuthor> {
    /**
     * 通过作者名称判断作者是否存在
     * @param authorName
     * @param recID
     * @return
     */
    YRAuthor getYRAuhorByName(@Param("authorName") String authorName, @Param("recID") Integer recID);

    /**
     * 列表查询
     * @param data
     * @return
     */
    List<Map> listResutMap(@Param("data") AuthorListParamSeach data);

    /**
     * 前台-获取创作者详情
     * @param recId
     * @return
     */
    List<Map<String,Object>> getAuthorDetailInfo(@Param("recId") Integer recId);

    /**
     * 创作者—内容形式关联表
     * @param userId
     * @param contentformId
     */
    void saveAuthorContentForm(@Param("userId") Integer userId, @Param("contentformId") Integer contentformId);

    /**
     * 保存创作者—内容分类关联表
     * @param yrAuthorId
     * @param categoryId
     */
    void saveAuthorCategory(@Param("yrAuthorId") Integer yrAuthorId, @Param("categoryId") Integer categoryId);

    /**
     * 保存创作者—使用场景关联表
     * @param userId
     * @param scenesId
     */
    void saveAuthorScenes(@Param("userId") Integer userId,@Param("scenesId") Integer scenesId);

    /**
     * 保存创作者—标签(内容属性、表现风格)关联表
     * @param userId
     * @param lableId
     */
    void saveAuthorLable(@Param("userId") Integer userId, @Param("lableId") Integer lableId);

    /**
     * 保存创作者—IP标签关联表
     * @param yrAuthorId
     * @param iPLableId
     */
    void saveAuthorIPLable(@Param("yrAuthorId") Integer yrAuthorId,@Param("iPLableId") Integer iPLableId);

    /**
     * 批量修改价格
     * @param authorIds
     * @param pricePercent
     */
    void updateBatchPrice(@Param("authorIds") String[] authorIds, @Param("pricePercent") BigDecimal pricePercent);

    /**
     * 修改创作者信息
     * @param yRAuthor
     */
    void updateAuthor(YRAuthor yRAuthor);

    /**
     * 删除创作者-内容形式 | 内容分类 | 使用场景关联关系
     * @param recId
     */
    void deleteAuthorRelation(@Param("recId") Integer recId);

    /**
     * 删除创作者-标签 | IP标签关联关系
     * @param recId
     */
    void deleteAuthorLableRelation(@Param("recId") Integer recId);

    /**
     * 批量上下架
     * @param split
     * @param authorStatus
     * @param status
     */
    void batchApply(@Param("split") String[] split, @Param("authorStatus") Integer authorStatus, @Param("status") Integer status,@Param("userId") Integer userId);

    /**
     * 创作者编辑页面
     * @param recId
     * @return
     */
    List<Map<String,Object>> getAuthorInfoUpdate(@Param("recId") Integer recId);

//    YRAuthor getByNickname(@Param("nickname") String nickname);

    /**
     * 通过创作者id获取创作者信息及其用户信息
     * @param recId
     * @return
     */
    YRAuthor getUserByAuthorId(@Param("recId") Integer recId);

    /**
     * 批量修改创作者审核失败原因
     * @param split
     * @param reason
     * @param status
     * @param realName
     */
    void batchFailReason(@Param("split") String[] split, @Param("reason") String reason, @Param("status") Integer status, @Param("realName") String realName);
    /**
    * 更新价格通过用户id，手机号，和作者名称
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/30 11:11
    */
    void updatePriceByUserIDMoblieName(YRAuthor yrAuthor);

    /**
     * 通过id|手机和作者获取信息(非通用)
    * 获取作者信息通过作者表和注册用户表(非通用)
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/30 14:56
    */
    YRAuthor getByUIDOrPhoneAndNickname(YRAuthor yrAuthor);



    /**
     * 创作者下架原因
     * @param split
     * @param reason
     */
    void batchAuthorUnderReason(@Param("split") String[] split, @Param("reason") String reason, @Param("status") Integer status,@Param("userId") Integer userId);

    /**
     * 前台商城—创作者列表
     * @param data
     * @return
     */
    List<Map> authorList(@Param("data") AuthorListParamSeachMall data);

    /**
     * 前台商城—创作者\作品详情
     * @param recId
     * @param userId
     * @return
     */
    YRAuthorInfoResult getAuthorInfo(@Param("recId") Integer recId,@Param("registeredUserInfoId") Integer userId);

    /**
     * 通过作者名称和注册用户模糊查询前6条数据
     * @param data
     * @return
     */
    List<YRAuthor> getAuthorsByName(@Param("data") YRAuthor data);
    /**
    * 通过对象的值进行并查找
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/6/6 20:29
    */
    List<YRAuthor> getAuthorByObject(YRAuthor yrAuthor);

    /**
     * 后台—创作者上架，代表作上架
     * @param split
     * @param authorStatus
     * @param status
     * @param realName
     */
    void batchApplyUp(@Param("split") String[] split, @Param("authorStatus") Integer authorStatus, @Param("status") Integer status,@Param("realName") String realName);


    /**
     * 根据注册用户ID获取当前注册用户的所有创作者
     * @param registerUserInfoId
     * @return
     */
    List<YRAuthor> getByRegisterUserId( @Param("registerUserInfoId") Integer registerUserInfoId);
//    void batchApplyUp(@Param("split") String[] split, @Param("authorStatus") Integer authorStatus, @Param("status") Integer status);

    /**
     * 获取相似创作者列表
     * @param a
     * @param b
     * @param categoryId
     * @return
     */
    List<YRAuthorInfoResult> getLikeAuthor(@Param("a") Integer a, @Param("b") Integer b,@Param("categoryId") Integer categoryId, @Param("recid") Integer recid);

    /**
     * 获取相似创作者列表数量
     * @param categoryId
     * @param sortScore
     * @return
     */
    List<Map<String,Object>> getLikeAuthorNum(@Param("categoryId") Integer categoryId, @Param("sortScore") BigDecimal sortScore);


    /**
     * 获取批量创作者
     * @param ids
     * @return
     */
    List<YRAuthor> list_yrAuthorByIds(@Param("ids") String ids);

    /**
     * 后台—需求选购创作者列表
     * @param demandId
     * @param date
     * @param likeName
     * @return
     */
    List<Map> getDemandAuthorList(@Param("demandId") Integer demandId, @Param("likeName") String[] likeName ,@Param("authorStatus") Integer authorStatus ,@Param("date") String date);


    /**
     * C端—创作者列表—V1.3
     * @param map
     * @return
     */
    List<YRAuthorInfoResult> getAuthorAndProList(@Param("data") AuthorListParamSeachMall map);

    /**
     * C端—创作者列表—获取对应上架作品列表
     * @param recIds
     * @return
     */
    List<YRProduction> getAuthorProductionList(@Param("recIds") String[] recIds);

    /**
     * 计算作品排序分数
     */
    void calculateSortScore();

    /**
     * C端—修改创作者访问次数
     * @param i
     * @param recId
     */
    void updateAuthorAccessTimes(@Param("i") int i, @Param("recId") Integer recId);

    /**
     * 通过购物车id获取yrAuthor信息
     * @param registeredUserInfoId
     * @param shopCartIds
     * @return
     */
    List<YRAuthor> getyrAuthorByshoppingCartId(@Param("registeredUserInfoId") int registeredUserInfoId, @Param("shopCartIds") String shopCartIds);


    List<YRAuthor>  getYRAuhorByName_LikeSearch(@Param("data")YRAuthor  data);

    /**
     * 通过作者Id和注册用户Id获取得到作者
      * @param data
     * @return
     */
    YRAuthor  getYRAuthorById_RegUserId(@Param("data") YRAuthor  data);

    YRAuthor getByAuthorId(@Param("id") Integer id);

    /**
     * 通过创作者ID查询创作者信息——判断
     * @param recId
     * @return
     */
    YRAuthor getAuthorInfoById(@Param("recId") Integer recId);

    /**
     * 批量修改访问次数
     * @param data
     */
    void batchUpdateAccessTime(@Param("data") Map<Integer , Long> data);

    /**
     * 批量修改访问次数
     * @param data
     */
    void batchUpdateAccessNums(@Param("data") Map<Integer , Long> data);
}
