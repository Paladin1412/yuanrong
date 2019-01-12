package com.yuanrong.admin.dao.usermanagement;

import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.seach.BatchUpdateSaleOrMedia;
import com.yuanrong.admin.seach.RegisterUserSearch;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.util.BaseModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RegisteredUserInfoDAOI extends BaseDaoI<RegisteredUserInfo> {
    List<RegisteredUserInfo> getByMobile( @Param("mobile") String mobile);

    List<Map<String,Object>> getUserById( @Param("recID") Integer recID);
    /**
    * 保存公司
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/7 11:53
    */
    void saveConpany(RegisteredUserAndCompany registeredUserAndCompany);
    /**
    * 保存用户
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/7 11:54
    */
    void saveRegisterUser(RegisteredUserAndCompany registeredUserAndCompany);

    /**
    * 查看用户详情
    * @author      ShiLinghuai
    * @param 
    * @return      
    * @exception   
    * @date        2018/5/8 15:01
    */
    RegisteredUserInfo getUserDetailByID(@Param("recID") Integer recID);
    /**
    * 按条件获取用户信息
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/7 11:54
    */
    List<RegisteredUserInfo> listByCondition(RegisterUserSearch registerUserSearch);

    /**
     * 获取后台用户添加的所有用户信息，多表关联
     * @param
     * @return
     */
    List<Map<String,Object>> getAllUserByAdmin(AdminUser adminUser);

    /**
    * 批量为用户更新销售
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/7 16:14
    */
    void batchUpdateSaleForUser(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia);

    /**
     * 批量为用户更新媒介
     * @param batchUpdateSaleOrMedia
     */
    void batchUpdateMediaForUser(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia);

    /**
     * 批量重置密码
     * @param batchUpdateSaleOrMedia
     */
    void batchResetPassword(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia);
    /**
    * 更新通过对象的id
    * @author      ShiLinghuai
    * @param
    * @return      
    * @exception   
    * @date        2018/5/9 9:35
    */
    void updateByID(RegisteredUserInfo registeredUserInfo);
    /**
    * 获取注册用户表里的手机记录条数
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/10 10:17
    */
    Integer getCountPhone(String phone);
    /**
    * 批量审核成功
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/16 19:26
    */
    void batchCheckedPass(List<Integer> ids);
    /**
    * 批量审核失败
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/16 19:27
    */
    void batchCheckedFail(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    RegisteredUserInfo login(@Param("username")String username , @Param("password")String password);

    /**
     * 判断用户是否存在
     * @author      Tangzheng
     * @param
     * @return
     * @exception
     * @date        2018/5/22 11:10
     */
    RegisteredUserInfo judeRegisteredUserInfo(RegisteredUserInfo registeredUserInfo);

    /**
     * 添加注册用户
     * @author      Tangzheng
     * @param
     * @return
     * @exception
     * @date        2018/5/22 13:48
     */
    int saveRegisteredUser(Map<String,Object> map);

    /**
     * 前台个人中心，通过注册用户ID查找下相关信息
     * @author      Tangzheng
     * @param
     * @return
     * @exception
     * @date        2018/5/26 11:31
     */
    List< Map<String,Object> > getMyInfoByID(Map<String,Object> map);

    /**
     * 前台个人中心，依据用户ID，更新个人相关信息
     * @author      Tangzheng
     * @param
     * @return
     * @exception
     * @date        2018/5/26 15:31
     */
    int updateMyInfoRegisterUserInfo(Map<String,Object> map);
    /**
     * 前台个人中心，依据用户ID，更新企业相关信息
     * @author      Tangzheng
     * @param
     * @return
     * @exception
     * @date        2018/5/28 19:51
     */
    int updateMyInfoRegisterUserInfoCompany(Map<String,Object> map);
    /**
    * 通过封装对象里的信息查询用户(通用)
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/30 14:19
    */
    RegisteredUserInfo getByObject(RegisteredUserInfo registeredUserInfo);
    /**
     * 前台个人中心， 卖家中心，统计注册用户:账号数、创作者数、作品数
     * @author      Tangzheng
     * @param
     * @return
     * @exception
     * @date        2018/5/30 12:11
     */
    Map<String,Object> cnt_registeredUserInfo (Map<String,Object> map);

    /**
     * 判断手机号码是否存
     * @param map
     * @return
     */
    int getCountMobile(Map<String, Object> map);

    /**
     * 保存发布需求的用户信息
     * @param map
     * @return
     */
    int addRegisteredUserInfo(Map<String, Object> map);

    /**
     * 根据用户ID更新手机号
     * @param map
     * @return
     */
    int updateMobileByRecID(Map<String, Object> map);
    /**
    * 批量审核成功和失败--个人
    * @author      ShiLinghuai
    * @param      status 0 失败 1成功
    * @return
    * @exception
    * @date        2018/6/6 14:52
    */
    void batchCheckPerson(@Param("ids") List<Integer> recID,@Param("status") Integer status);
    /**
     * 批量审核成功和失败--企业
     * @author      ShiLinghuai
     * @param      status 0 失败 1成功
     * @return
     * @exception
     * @date        2018/6/6 14:52
     */
    void batchCheckCompany(@Param("ids") List<Integer> recID,@Param("status") Integer status);
    /**
     * 批量审核成功和失败--卖家
     * @author      ShiLinghuai
     * @param      status 0 失败 1成功
     * @return
     * @exception
     * @date        2018/6/6 14:52
     */
    void batchCheckSellerStatus(@Param("ids") List<Integer> recID,@Param("status") Integer status);
    /**
     * 批量审核成功和失败--卖家
     * @author      ShiLinghuai
     * @param      status 0 失败 1成功
     * @return
     * @exception
     * @date        2018/6/6 14:52
     */
    void batchCheckBuyerStatus(@Param("ids") List<Integer> recID,@Param("status") Integer status);

    /**
     * 判断手机号码是否存
     * @param
     * @return
     */
    int getCountIDCardNumber(@Param("IDCardNumber") String IDCardNumber);

    /**
     * 获取用户简称的数量
     * @param
     * @return
     */
    int getCountNickName(@Param("nickName") String nickName,@Param("recID") Integer recID);

    /**
     * getUserByName
     * @param userInfo
     * @return
     */
    List<RegisteredUserInfo> getUserByName(@Param("data") RegisteredUserInfo userInfo);

    /**
     * 通过用户昵称搜索注册用户
     * @param nickName
     * @return
     */
    List<RegisteredUserInfo> getbyNickName(@Param("nickName")String nickName);
    /**
    * 根据主键和证件号去数据库里查重
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/18 19:32
    */
    Integer getCountByIDRID(@Param("IDNumber")String IDNumber,@Param("companyID")String companyID,@Param("recID")Integer recID);
}
