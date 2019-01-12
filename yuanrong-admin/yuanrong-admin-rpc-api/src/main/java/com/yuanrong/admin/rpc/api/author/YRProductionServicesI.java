package com.yuanrong.admin.rpc.api.author;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumYRProductionStatus;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.result.YRAuthorInfoProResult;
import com.yuanrong.admin.result.YRAuthorInfoResult;
import com.yuanrong.admin.result.YRProductionLikeResult;
import com.yuanrong.admin.result.YRProductionResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.YRProductionListParam;
import com.yuanrong.admin.util.BaseModel;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 圆融作品的services接口
 * Created MDA
 */
public interface YRProductionServicesI extends BaseServicesI<YRProduction> {

    /**
     * 通过文章标题修改作品报价
     * @param map
     */
    int updateProductQuotedPriceByTitle(Map<String,Object> map);

    YRProduction getByTitle(@Param("title") String title);

    /**
     * 根据作者id查找代表作品
     * @param recId
     * @return
     */
    List<Map<String,Object>> getAuthorProList(Integer recId);
    /**
    * 条件查询作品
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/21 10:22
    */
    PageInfo<YRProduction> listByCondition(YRProductionListParam yrProductionListParam);
    /**
     * 根据作品id和用户id和上下架修改作品状态
     * @author      ShiLinghuai
     * @param       status：上架1，下架0
     * @return
     * @exception
     * @date        2018/5/21 16:55
     */
    Integer updateYRProductionStatusIndexByProIDAndUserIDAndUpDown(List<Integer> id,Integer registeredUserInforID,Integer status);

    /**
     * 修改
     * @param yrProduction
     */
    void update(YRProduction yrProduction);
    /**
    * 批量/修改价格
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/28 15:03
    */
    void updatePriceByID(List<Integer> id,BigDecimal price , BigDecimal sellPrice , Integer userId);

    /**
     * 批量修改产品状态
     * @param ids
     * @param yrProductionStatus
     */
    void updateYRProductionStatus(Integer[] ids ,EnumYRProductionStatus yrProductionStatus , String verifyFailReason);

    /**
     * 修改价格
     * @param ids
     * @param price
     */
    void changePrice(Integer[] ids ,BigDecimal price,BigDecimal sellPrice);

    /**
     * 审核产品
     * @param ids
     * @param yrProductionStatus
     */
    void verify (Integer[] ids ,EnumYRProductionStatus yrProductionStatus , String verifyFailReason,String auditUser);
    /**
    * 批量更新作品
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/31 10:04
    */
    void batchSaveProduction(List<YRProduction> list);

    /**
     * 删除代表作—假删
     * @param recId
     */
    void deleteAuthorPro(Integer recId);

    /**
     * 查找创作者的代表作
     * @param recId
     * @return
     */
    List<YRProduction> findAuthorMagnum(Integer recId);

    /**
     * 创作者的作品数、上架数、上架代表作数
     * @param recId
     * @return
     */
    Map<String,Object> getAuthorProNum(Integer recId);

    /**
     * 设为代表作
     *
     * @param
     * @return
     * @throws
     * @author songwq
     * @date
     */
     Integer updateYRProductionStatusIndexByProID(String recId,Integer setIsRepresentative);


    /**
     * C端—获取创作者的已上架作品列表（公开作品、非公开）
     * @param authorId
     * @param publishStatusIndex
     * @param contentFormId
     * @param baseModel
     * @return
     */
    PageInfo<YRAuthorInfoProResult> findAuthorProList(Integer authorId, Integer publishStatusIndex, Integer contentFormId, BaseModel baseModel);

    /**
     * 搜索c端作品
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo<YRProductionResult> listMall(YRProductionListParam data , BaseModel baseModel);

    /**
     * 获取营销场景列表
     * @return
     */
    List<Map> getMarketingScene();

    /**
     * 获取作品5个热搜索
     * @return
     */
    List<Map> getHotSearch();

    /**
     * 计算作品排序分数
     */
    void updateCalculateSortScore();

    /**
     * C端—查询创作者的公开作品数、未公开作品数
     * @param recId
     * @return
     */
    Map<String,Object> getAuthorProOpenNum(Integer recId);

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，对应的作品的访问次数+1
     */
    void updateProductInfo(YRProductionListParam data , BaseModel baseModel);

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，根据作者id查询作者的作品数量
     */
    int getAuthorProductCount(Integer yrAuthorId, BaseModel baseModel) ;

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，显示该作者最近的5篇已发布文章；当前文章除外
     */
    List<YRProduction>  getAuthorProductsList(YRProduction  data, BaseModel baseModel) ;

    /**
     * C端—创作者详情上架作品内容形式数
     * @param authorId
     * @param publishStatusIndex
     * @return
     */
    Map<String,Object> getAuthorProContentNum(Integer authorId, Integer publishStatusIndex);

    /**
     *@author songwq
     *@param
     *@date 2018/8/1
     *@description 根据作品ID查询买家信息
    */
    YRProduction getBuyerByProductionId(Integer recId,Integer loginUserId);
    /**
     * 获取相似作品数量
     * @param
     * @param
     * @return
     */
    List<Map<String,Object>> getLikeProductionNum(Integer categoryId, BigDecimal sortScore,Integer publishStatusIndex);

    /**
     * 获取相似作品列表—V1.3
     * @param a
     * @param b
     * @param categoryId
     * @param recId
     * @return
     */
    List<YRProductionLikeResult> getLikeProduction(Integer a, Integer b, Integer categoryId, Integer recId,Integer publishStatusIndex) ;


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
    boolean updateAccessTimes(YRProduction yRProduction);

    /**
     * 通过对文章标题模糊搜索查询：本用户未发布文章（上架、待审核状态均可）
     * @param yRProduction
     * @return
     */
    PageInfo<YRProduction> searchYRProductionByTitle(BaseModel baseModel, YRProduction yRProduction);

    int saveGetKey(YRProduction yrProduction);

    /**
     * 原创征稿—获取作品列表
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo<YRProduction> getDemandProduct(YRProductionListParam data, BaseModel baseModel);

    /**
     * tangz
     * 通过YRProduction的ID获取对象信息
     * @param id
     * @return
     */
    YRProduction getYRProductionById(Integer id);
    /**
     * swq
     * 通过订单号获取作品
     * @param orderSn
     * @return
     */
    YRProduction getYRProduction(String  orderSn);

    /**
     *@author songwq
     *@param
     *@date 2018/9/28
     *@description 生产临时导出部分作品
    */
    List<YRProduction> getList();

    /**
     * 将作者，作品的访问次数刷入数据库中
     */
    void updateFlushRedisAccessTimeToDb();

    /**
     * swq
     * 获取作品详情
     * @param data
     * @return
     */
    YRProduction getByProductionId(YRProductionListParam data);
}
