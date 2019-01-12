package com.yuanrong.admin.rpc.service.impl.author;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.dao.author.YRProductionDaoI;
import com.yuanrong.admin.exception.ErrorMessageData;
import com.yuanrong.admin.exception.ParmException;
import com.yuanrong.admin.result.YRAuthorInfoProResult;
import com.yuanrong.admin.result.YRAuthorInfoResult;
import com.yuanrong.admin.result.YRProductionLikeResult;
import com.yuanrong.admin.result.YRProductionResult;
import com.yuanrong.admin.rpc.api.author.YRProductionServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.YRProductionListParam;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 圆融作品的services实现类
 * Created MDA
 */
@Service
public class YRProductionServicesImpl extends BaseServicesAbstract<YRProduction> implements YRProductionServicesI {
    @Override
    public YRProduction getById(Integer id) {
        return yRProductionDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        yRProductionDaoI.deleteById(id);
    }

    @Override
    public List<YRProduction> getAll() {
        return yRProductionDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(), "recId desc");
        List<YRProduction> result = yRProductionDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public int updateProductQuotedPriceByTitle(Map<String, Object> map) {
        return this.yRProductionDaoI.updateProductQuotedPriceByTitle(map);
    }

    @Override
    public YRProduction getByTitle(String title) {
        return this.yRProductionDaoI.getByTitle(title);
    }

    /**
     * 根据作者Id查找代表作品
     *
     * @param recId
     * @return
     */
    @Override
    public List<Map<String, Object>> getAuthorProList(Integer recId) {
        return yRProductionDaoI.getAuthorProList(recId);
    }

    /**
     * 前台作品列表查询
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/21 14:44
     */
    @Override
    public PageInfo<YRProduction> listByCondition(YRProductionListParam yrProductionListParam) {
        PageHelper.startPage(yrProductionListParam.getCp(), yrProductionListParam.getRows());

        return new PageInfo<YRProduction>(yRProductionDaoI.listByCondition(yrProductionListParam));
    }

    /**
     * 根据id修改作品状态
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/21 16:57
     */
    @Override
    public Integer updateYRProductionStatusIndexByProIDAndUserIDAndUpDown(List<Integer> list,Integer registeredUsserInforID,Integer status) {

        Integer productionStatus = null;
        Integer representativeStaus = null;
        //进行上架业务
        if(status == EnumMethodParamUpDown.上架.getIndex()){
            productionStatus = EnumYRProductionStatus.待审核.getIndex();
        }else if (status == EnumMethodParamUpDown.下架.getIndex()){
            productionStatus = EnumYRProductionStatus.下架.getIndex();
        }else if(status == EnumMethodParamUpDown.设为代表作.getIndex()){
            representativeStaus = EnumYesOrNo.NORMAL.getIndex();
        }else if(status == EnumMethodParamUpDown.取消代表作.getIndex()){
            representativeStaus = EnumYesOrNo.FORBIDDEN.getIndex();
        }
        //获取当前用户的作者列表
       YRAuthor yrAuthor = new YRAuthor();
        yrAuthor.setRegisteredUserInfoID(registeredUsserInforID);
        List<YRAuthor> authors = yRAuthorDaoI.getAuthorByObject(yrAuthor);
        //获取用户列表ids
        List<Integer> authorIDs = new ArrayList<Integer>();
        for (YRAuthor yrauthor: authors
             ) {
            authorIDs.add(yrauthor.getRecId());

        }
        if(authorIDs.size()<1){
            return EnumYesOrNo.FORBIDDEN.getIndex();
        }
        if(list!=null){
            if(productionStatus!=null){
                yRProductionDaoI.updateYRProductionStatusIndexByID(list,authorIDs,productionStatus);
            }
            if(representativeStaus !=null){
                yRProductionDaoI.updateYRProductionRepresentativeStatusIndexByID(list,authorIDs,representativeStaus);
            }
        }
        return null;

    }

    @Override
    public void update(YRProduction yrProduction) {
        String percent = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_PERCENT) ;
        BigDecimal fee = StringUtil.isBlank(percent) ? BigDecimal.ZERO : new BigDecimal(percent).multiply(new BigDecimal("0.01"));
        yrProduction.setSellPrice(yrProduction.getProductQuotedPrice().add(yrProduction.getProductQuotedPrice().multiply(fee)));
        yRProductionDaoI.update(yrProduction);
        systemLogDaoI.log(YRProduction.class.getName() , yrProduction.getRecId() , "修改作品" , yrProduction.getOperator());
    }
    /**
    * 批量/更新价格
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/28 15:04
    */
    public void updatePriceByID( List<Integer> id,BigDecimal price, BigDecimal sellPrice, Integer userId) {
        yRProductionDaoI.updatePriceByID(id,price,sellPrice,userId);
    }

    @Override
    public void updateYRProductionStatus(Integer[] ids, EnumYRProductionStatus yrProductionStatus , String verifyFailReason) {
        yRProductionDaoI.updateYRProductionStatus(ids , yrProductionStatus , verifyFailReason);
    }

    @Override
    public void changePrice(Integer[] ids, BigDecimal price,BigDecimal sellPrice) {
        yRProductionDaoI.changePrice(ids , price,sellPrice);
    }

    @Override
    public void verify(Integer[] ids, EnumYRProductionStatus yrProductionStatus, String verifyFailReason,String auditUser) {
        yRProductionDaoI.verify(ids , yrProductionStatus , verifyFailReason,auditUser);
        for (int i = 0; i < ids.length; i++) {
            systemLogDaoI.log(YRProduction.class.getName() , ids[i],yrProductionStatus.getName(),auditUser);
        }
    }
    /**
    * 批量更新作品
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/31 10:04
    */
    @Override
    public void batchSaveProduction(List<YRProduction> list) {
        for (YRProduction yrproduction:list ) {
            yRProductionDaoI.save(yrproduction);
        }
    }

    /**
     * 删除代表作—假删
     * @param recId
     */
    @Override
    public void deleteAuthorPro(Integer recId) {
        yRProductionDaoI.deleteAuthorPro(recId);
    }

    /**
     * 查找创作者的代表作
     * @param recId
     * @return
     */
    @Override
    public List<YRProduction> findAuthorMagnum(Integer recId) {
        return yRProductionDaoI.findAuthorMagnum(recId);
    }

    @Override
    public void save(YRProduction yrProduction) {
        if (yrProduction.getYrAuthor() == null || yrProduction.getYrAuthor().getRecId() == null){
            YRAuthor  yrAuthor = new YRAuthor();
            yrAuthor.setAuthorNickname("佚名");
            yrAuthor.setEnumChannel(EnumChannel.系统创建);
            yrAuthor.setEnumAuthorStatus(EnumAuthorStatus.未上架);
            yrAuthor.setEnumYesOrNo(EnumYesOrNo.NORMAL);
            yrAuthor.setRegisteredUserInfoID(yrProduction.getRegisteredUserInfoId());
            yRAuthorDaoI.save(yrAuthor);
            yrProduction.setYrAuthor(yrAuthor);
        }
        //计算售价
        if(yrProduction.getSellPrice() == null || yrProduction.getSellPrice().compareTo(BigDecimal.ZERO) <=0){
            String percent = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_PERCENT) ;
            BigDecimal fee = StringUtil.isBlank(percent) ? BigDecimal.ZERO : new BigDecimal(percent).multiply(new BigDecimal("0.01"));
            yrProduction.setSellPrice(yrProduction.getProductQuotedPrice().add(yrProduction.getProductQuotedPrice().multiply(fee)));
        }
        yRProductionDaoI.save(yrProduction);
        systemLogDaoI.log(YRProduction.class.getName(),yrProduction.getRecId() , "新增作品",yrProduction.getOperator());
    }

    @Override
    public int saveGetKey(YRProduction yrProduction) {
        save(yrProduction);
        return  yrProduction.getRecId();
    }

    @Override
    public YRProduction getYRProductionById(Integer id) {
        return yRProductionDaoI.getYRProductionById(id);
    }


    /**
     * 创作者的作品数、上架数、上架代表作数
     * @param recId
     * @return
     */
    @Override
    public Map<String, Object> getAuthorProNum(Integer recId) {
        return yRProductionDaoI.getAuthorProNum(recId);
    }
    /**
     * C端—获取创作者的已上架作品列表（公开作品、非公开）
     * @param authorId
     * @param publishStatusIndex
     * @param contentFormId
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo<YRAuthorInfoProResult> findAuthorProList(Integer authorId, Integer publishStatusIndex, Integer contentFormId, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows());
        List<YRAuthorInfoProResult> authorProList = yRProductionDaoI.findAuthorProList(authorId, publishStatusIndex, contentFormId);
        return new PageInfo(authorProList);
    }

    @Override
    public PageInfo<YRProductionResult> listMall(YRProductionListParam data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(),data.getSort());
        List<YRProductionResult> productionResults = yRProductionDaoI.listMall(data);
        return new PageInfo(productionResults);
    }

    @Override
    public List<Map> getMarketingScene() {
        return yRProductionDaoI.getMarketingScene();
    }

    @Override
    public List<Map> getHotSearch() {
        return yRProductionDaoI.getHotSearch();
    }

    @Override
    public void updateCalculateSortScore() {
        yRProductionDaoI.calculateSortScore();
    }

    /**
     * C端—查询创作者的公开作品数、未公开作品数
     * @param recId
     * @return
     */
    @Override
    public Map<String, Object> getAuthorProOpenNum(Integer recId) {
        return yRProductionDaoI.getAuthorProOpenNum(recId);
    }


    /**
     * 设为代表作
     *
     * @param
     * @return
     * @throws
     * @author songwq
     * @date
     */
    @Override
    public Integer updateYRProductionStatusIndexByProID(String recId, Integer setIsRepresentative) {
        Integer num = yRProductionDaoI.updateYRProductionStatusIndexByProID(recId,setIsRepresentative);
        return num;
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，对应的作品的访问次数+1
     */
    @Override
    public void updateProductInfo(YRProductionListParam data, BaseModel baseModel) {
        //修改访问次数+1
        yRProductionDaoI.updateProductInfo(data);

    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，根据作者id查询作者的作品数量
     */
    @Override
    public int getAuthorProductCount(Integer yrAuthorId, BaseModel baseModel) {
        //修改访问次数+1
        Integer num = yRProductionDaoI.getAuthorProductCount(yrAuthorId);
        return num;
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/31
     *@description 查看作品详情，显示该作者最近的5篇已发布文章；当前文章除外
     */
    @Override
    public  List<YRProduction>  getAuthorProductsList(YRProduction  data, BaseModel baseModel) {
        List<YRProduction>  productionList = yRProductionDaoI.getAuthorProductsList(data);
        return productionList;
    }

    /**
     * C端—创作者详情上架作品内容形式数
     * @param authorId
     * @param publishStatusIndex
     * @return
     */
    @Override
    public Map<String, Object> getAuthorProContentNum(Integer authorId, Integer publishStatusIndex) {
        return yRProductionDaoI.getAuthorProContentNum(authorId,publishStatusIndex);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/1
     *@description 根据作品ID查询买家信息
     */
    @Override
    public YRProduction getBuyerByProductionId(Integer recId,Integer loginUserId) {
        List<YRProduction> YRProductionList = yRProductionDaoI.getBuyerByProductionId(recId);
        for(YRProduction yRProduction :YRProductionList){
            if(yRProduction.getRegisteredUserInfoId().equals(loginUserId)){
                return yRProduction;
            }
        }
        return null;
    }


    /**
     * 获取相似作品数量
     * @param
     * @param
     * @return
     */
    @Override
    public List<Map<String, Object>> getLikeProductionNum(Integer categoryId, BigDecimal sortScore,Integer publishStatusIndex) {
        return yRProductionDaoI.getLikeProductionNum(categoryId,sortScore,publishStatusIndex);
    }

    /**
     * 获取相似作品列表—V1.3
     * @param a
     * @param b
     * @param categoryId
     * @param recId
     * @return
     */
    @Override
    public List<YRProductionLikeResult> getLikeProduction(Integer a, Integer b, Integer categoryId, Integer recId,Integer publishStatusIndex) {
        return yRProductionDaoI.getLikeProduction(a,b,categoryId,recId,publishStatusIndex);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/6
     *@description 修改作品为已阅读
     */
    @Override
    public boolean updateProductionReadStatus(Integer recId) {
        return yRProductionDaoI.updateProductionReadStatus(recId);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/8
     *@description 修改访问次数
     */
    @Override
    public boolean updateAccessTimes(YRProduction yRProduction) {
        String accessNumKey = YRProduction.class.getName()+":accessNum:"+yRProduction.getRecId();
        String accessTimesKey = YRProduction.class.getName()+":accessTimes:"+yRProduction.getRecId();
        long accessNum = getValue(accessNumKey);
        if(accessNum <=0){
            //保存数据库的值
            redisTemplate.opsForValue().set(accessNumKey ,yRProduction.getAccessNum() == null ? 0 : yRProduction.getAccessNum()+"");
            redisTemplate.opsForValue().set(accessTimesKey,yRProduction.getAccessTimes() == null ? 0 : yRProduction.getAccessTimes()+"");
        }
        //自增
        incr(accessNumKey);
        incr(accessTimesKey);
        return true;
    }

    @Override
    public PageInfo<YRProduction> searchYRProductionByTitle(BaseModel baseModel,YRProduction yRProduction) {
        PageHelper.startPage(baseModel.getCp(),baseModel.getRows(),"yrp.createdTime desc");
        List<YRProduction> yrProductions = yRProductionDaoI.searchYRProductionByTitle(yRProduction);
        return new PageInfo(yrProductions);
    }

    /**
     * 原创征稿—获取作品列表
     * @param data
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo<YRProduction> getDemandProduct(YRProductionListParam data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(), "yp.createdTime desc");
        List<YRProduction> result = yRProductionDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public YRProduction getYRProduction(String orderSn) {
        return  yRProductionDaoI.getYRProduction(orderSn);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/9/28
     *@description 导出生产部分作品
     */
    @Override
    public List<YRProduction> getList() {
        return yRProductionDaoI.getList();
    }

    @Override
    public void updateFlushRedisAccessTimeToDb() {
        String accessNumKey = YRProduction.class.getName()+":accessNum:*";
        String accessTimesKey = YRProduction.class.getName()+":accessTimes:*";
        Set<String> accessNumKeys = redisTemplate.keys(accessNumKey);
        Set<String> accessTimesKeys = redisTemplate.keys(accessTimesKey);

        List<YRProduction> accessNums = new ArrayList<>();
        Map<Integer , Long> accessTimes = new HashMap<>();
        for(String ele : accessNumKeys){
            int yrProductionId = Integer.parseInt(ele.replace(YRProduction.class.getName()+":accessNum:",""));
            YRProduction yrProduction = new YRProduction();
            yrProduction.setRecId(yrProductionId);
            yrProduction.setAccessNum(getValue(ele).intValue());
            accessNums.add(yrProduction);
//            accessNums.put( yrProductionId , getValue(ele));
        }

        for(String ele : accessTimesKeys){
            int yrProductionId = Integer.parseInt(ele.replace(YRProduction.class.getName()+":accessTimes:",""));
            accessTimes.put( yrProductionId , getValue(ele));
        }
        yRProductionDaoI.batchUpdateAccessNums(accessNums);
        yRProductionDaoI.batchUpdateAccessTime(accessTimes);
    }

    @Override
    public YRProduction getByProductionId(YRProductionListParam data) {
        return yRProductionDaoI.getByProductionId(data);
    }
}
