package com.yuanrong.admin.dao.author;

import com.yuanrong.admin.Enum.EnumYRProductionStatus;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.YRAuthorInfoProResult;
import com.yuanrong.admin.result.YRProductionLikeResult;
import com.yuanrong.admin.result.YRProductionResult;
import com.yuanrong.admin.seach.YRProductionListParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 圆融作品的dao
 * Created MDA
 */
@Repository
public interface YRProductionDaoI extends BaseDaoI<YRProduction> {

    /**
     * 通过文章标题修改作品报价
     *
     * @param map
     */
    int updateProductQuotedPriceByTitle(Map<String, Object> map);

    /**
     * 通过标题，获取YRProduction
     *
     * @param title
     * @return
     */
    YRProduction getByTitle(@Param("title") String title);

    /**
     * 根据作者id查找代表作品
     *
     * @param recId
     * @return
     */
    List<Map<String, Object>> getAuthorProList(@Param("recId") Integer recId);

    /**
     * 条件查询作品
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/21 10:23
     */
    List<YRProduction> listByCondition(YRProductionListParam yrProductionListParam);

    /**
     * 更新作品的状态
     *
     * @param id
     */
    void updateYRProductionStatusIndexByID(@Param("id") List<Integer> id, @Param("authorIDs") List<Integer> authorIDs, @Param("status") Integer status);

    /**
     * 修改
     *
     * @param yrProduction
     */
    void update(@Param("data") YRProduction yrProduction);

    /**
     *
     */
    void updatePriceByID(@Param("id") List<Integer> id, @Param("price") BigDecimal price,@Param("sellPrice") BigDecimal sellPrice,@Param("userId") Integer userId);

    /**
     * 批量修改产品状态
     *
     * @param ids
     * @param yrProductionStatus
     */
    void updateYRProductionStatus(@Param("ids") Integer[] ids, @Param("yrProductionStatus") EnumYRProductionStatus yrProductionStatus, @Param("verifyFailReason") String verifyFailReason);

    /**
     * 修改价格
     *
     * @param ids
     * @param price
     */
    void changePrice(@Param("ids") Integer[] ids, @Param("price") BigDecimal price,@Param("sellPrice") BigDecimal sellPrice);

    /**
     * 审核产品
     *
     * @param ids
     * @param yrProductionStatus
     */
    void verify(@Param("ids") Integer[] ids, @Param("yrProductionStatus") EnumYRProductionStatus yrProductionStatus, @Param("verifyFailReason") String verifyFailReason,@Param("auditUser")String auditUser);

    /**
     * 删除代表作—假删
     *
     * @param recId
     */
    void deleteAuthorPro(@Param("recId") Integer recId);

    /**
     * 查询创作者代表族
     * @param recId
     * @return
     */
    List<YRProduction> findAuthorMagnum(@Param("recId") Integer recId);

    /**
     * 创作者的作品数、上架数、上架代表作数
     * @param recId
     * @return
     */
    Map<String,Object> getAuthorProNum(@Param("yrAuthorId") Integer recId);
    /**
     * 更新作品的代表作状态
     *
     * @param id
     */
    void updateYRProductionRepresentativeStatusIndexByID(@Param("id") List<Integer> id, @Param("authorIDs") List<Integer> authorIDs, @Param("status") Integer status);

    /**
     * 设为代表作
     *
     * @param
     * @return
     * @throws
     * @author songwq
     * @date
     */
    Integer updateYRProductionStatusIndexByProID(@Param(value="recId") String recId,@Param(value="setIsRepresentative")Integer setIsRepresentative);


    /**
     * C端—获取创作者的已上架作品列表（公开作品、非公开）
     * @param authorId
     * @param publishStatusIndex
     * @param contentFormId
     * @return
     */
    List<YRAuthorInfoProResult> findAuthorProList(@Param("authorId") Integer authorId, @Param("publishStatusIndex") Integer publishStatusIndex, @Param("contentFormId") Integer contentFormId);

    /**
     * 搜索c端作品
     * @param data
     * @return
     */
    List<YRProductionResult> listMall(@Param("data") YRProductionListParam data);

    /**
     * 获取营销场景列表
     * @return
     */
    List<Map> getMarketingScene();

    /**
     * 修改未发布作品为售罄
     * @param yrProductionIds
     */
    void unPublishSellOut(@Param("yrProductionIds") List<Integer> yrProductionIds);

    /**
     * 将指定订单的未发布作品，调整为上架
     * @param orderInfoBuyerId
     */
    void unPublishProductShelf(@Param("orderInfoBuyerId") Integer orderInfoBuyerId);

    /**
     * 获取作品5个热搜索
     * @return
     */
    List<Map> getHotSearch();

    /**
     * 计算作品排序分数
     */
    void calculateSortScore();

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，对应的作品的访问次数+1
     */
    void updateProductInfo(@Param("data")YRProductionListParam data) ;

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，根据作者id查询作者的作品数量
     */
    int getAuthorProductCount(@Param("yrAuthorId")Integer yrAuthorId) ;
    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，显示该作者最近的5篇已发布文章；当前文章除外
     */
    List<YRProduction> getAuthorProductsList(@Param("data") YRProduction data) ;

    /**
     * C端—查询创作者的公开作品数、未公开作品数
     * @param recId
     * @return
     */
    Map<String,Object> getAuthorProOpenNum(@Param("recId") Integer recId);

    /**
     * C端—创作者详情上架作品内容形式数
     * @param authorId
     * @param publishStatusIndex
     * @return
     */
    Map<String,Object> getAuthorProContentNum(@Param("authorId") Integer authorId,@Param("publishStatusIndex") Integer publishStatusIndex);

    /**
     *@author songwq
     *@param
     *@date 2018/8/1
     *@description 根据作品ID查询买家信息
    */
    List<YRProduction> getBuyerByProductionId(@Param("yrProductionId")Integer yrProductionId);

    /**
     * 获取相似作品数量
     * @param
     * @param
     * @return
     */
    List<Map<String,Object>> getLikeProductionNum(@Param("categoryId")Integer categoryId, @Param("sortScore")BigDecimal sortScore,@Param("publishStatusIndex")Integer publishStatusIndex);

    /**
     * 获取相似作品列表—V1.3
     * @param a
     * @param b
     * @param categoryId
     * @param recId
     * @return
     */
    List<YRProductionLikeResult> getLikeProduction(@Param("a")Integer a, @Param("b")Integer b, @Param("categoryId")Integer categoryId, @Param("recId")Integer recId,@Param("publishStatusIndex")Integer publishStatusIndex) ;

    /**
     *@author songwq
     *@param
     *@date 2018/8/6
     *@description 修改作品为已阅读
     */
    boolean updateProductionReadStatus(Integer recId);

    /**
     *@author songwq
     *@param
     *@date 2018/8/8
     *@description 修改访问次数
     */
    boolean updateAccessTimes(@Param("yRProduction")YRProduction yRProduction);

    /**
     * tangz
     * 通过对文章标题模糊搜索查询：本用户未发布文章（上架、待审核状态均可）
     * @param data
     * @return
     */
    List<YRProduction> searchYRProductionByTitle(@Param("data") YRProduction data);

    /**
     * tangz
     * 通过YRProduction的ID获取对象信息
     * @param id
     * @return
     */
    YRProduction getYRProductionById(@Param("id") Integer id);

    /**
     * swq
     * 通过订单号获取作品
     * @param orderSn
     * @return
     */
    YRProduction getYRProduction(@Param(value="orderSn")String  orderSn);

    /**
     *@author songwq
     *@param
     *@date 2018/9/28
     *@description 生产临时导出部分作品
    */
    List<YRProduction> getList();

    /**
     * 批量修改访问次数
     * @param data
     */
    void batchUpdateAccessTime(@Param("data") Map<Integer , Long> data);

    /**
     * 批量修改访问次数
     * @param data
     */
    void batchUpdateAccessNums(@Param("data") List<YRProduction> data);

    YRProduction getById(Integer id);
    /**
     * swq
     * 获取作品详情
     * @param data
     * @return
     */
    YRProduction getByProductionId(@Param("data")YRProductionListParam data);
}
