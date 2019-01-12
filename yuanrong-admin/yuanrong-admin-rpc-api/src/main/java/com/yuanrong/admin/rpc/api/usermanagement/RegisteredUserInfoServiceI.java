package com.yuanrong.admin.rpc.api.usermanagement;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.exception.ParmException;
import com.yuanrong.admin.seach.BatchUpdateSaleOrMedia;
import com.yuanrong.admin.seach.RegisterUserSearch;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.util.BaseModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @Description:
 * @Author:         ShiLinghuai
 * @CreateDate:     2018/5/4 16:11
 * @UpdateUser:     ShiLinghuai
 * @UpdateDate:     2018/5/4 16:11
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface RegisteredUserInfoServiceI extends BaseServicesI<RegisteredUserInfo> {
    /**
     * 通过手机号查重注册用户
     * @param mobile
     * @return
     */
    List<RegisteredUserInfo> getByMobile( @Param("mobile") String mobile);


    /**
     * 保存用户(企业用户)
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/7 11:54
     */
    void saveUser(RegisteredUserAndCompany registeredUserAndCompany);

    /**
     * 获取客户公司职位
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/7 11:55
     */
    List<DictInfo> gainCompanyPositionList();
    /**
     *
     * @author      未知
     * @param
     * @return
     * @exception
     * @date        2018/5/7 11:56
     */
    List<AdminUser> findListByRoleID(Integer roleID);

    /**
     * 通过用户ID获取用户信息包括媒介经理(IP添加页面)
     * @param recID
     * @return
     */
    List<Map<String,Object>> getUserById(Integer recID);
    //void getCompanyPositionList();会报错
    /**
     *按条件查询出用户信息
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/7 10:47
     */
    PageInfo listByCondition(RegisterUserSearch registerUserSearch, BaseModel baseModel);
    /**
     * 按条件查询出用户信息不使用分页
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/16 9:24
     */
    List<RegisteredUserInfo> listByConditionNoPage(RegisterUserSearch registerUserSearch);

    /**
     * 批量为用户更改销售或媒介
     * @param batchUpdateSaleOrMedia
     */
    void batchUpdateSaleOrMediaForUser(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia);

    /**
     * 批量重置密码
     * @param batchUpdateSaleOrMedia
     */
    void batchResetPassword(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia);
    /**
     * 获取全部用户信息
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/8 12:15
     */
    PageInfo<Map<String,Object>> getAllUser(AdminUser adminUser,BaseModel baseModel);
    /**
     * 获取用户详情
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/8 15:02
     */
    JSONObject getUserDetailByID( Integer recID);

    /**
     * 根据id获取用户详情前台
     * @param recID
     * @return
     */
    JSONObject getUserDetailByIDFront( Integer recID,RegisteredUserAndCompany registeredUserAndCompany);
    /**
     * 更新企业用户信息前台
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/9 10:16
     */
    void updateCompanyUserFront(RegisteredUserAndCompany registeredUserAndCompany) ;
    /**
     * 更新企业用户信息
     * @author      ShiLinghuai
     * @param        role 更新的是买家的企业还是卖家的企业
     * @return
     * @exception
     * @date        2018/5/9 10:16
     */
    void updateCompanyUser(RegisteredUserAndCompany registeredUserAndCompany,Integer role) ;

    /**
     * 更新个人用户信息
     * @author      ShiLinghuai
     * @param        role 更新的是买家的个人还是卖家的个人
     * @return
     * @exception
     * @date        2018/5/9 10:18
     */
    void updatePersonUser(RegisteredUserAndCompany registeredUserAndCompany,Integer role) ;

    /**
     * 获取手机的记录数
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/10 10:25
     */
    Integer getCountPhone(String phone);
    /**
     * 获取失败原因
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/16 11:26
     */
    List<DictInfo> gainFailedReason();
    /**
     * 批量审查通过
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/16 15:50
     */
    void batchCheckedPass(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia)throws Exception;
    /**
     * 批量审查失败
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/16 15:50
     */
    void batchCheckedFaid(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia)throws Exception;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    RegisteredUserInfo login(String username ,String password);
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
     * 前台个人中心，依据用户ID，更新相关信息
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
     * 前台个人中心， 卖家中心，统计注册用户:账号数、创作者数、作品数
     * @author      Tangzheng
     * @param
     * @return
     * @exception
     * @date        2018/5/30 12:11
     */
    Map<String,Object> cnt_registeredUserInfo (Map<String,Object> map);

    void updateByID(RegisteredUserInfo registeredUserInfo);

    /**
     * 判断手机号码是否存
     * @param map
     * @return
     */
    int getCountMobile(Map<String, Object> map);
    /**
     * 保存发布需求用户
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
     * 批量审核成功
     * @author      ShiLinghuai
     * @param        status 0 失败 1成功
     * @return
     * @exception
     * @date        2018/6/6 15:19
     */
    void batchCheck(List<Integer> ids,Integer status);
    /**
     * 审核用户信息，并返回失败信息
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/6/20 14:49
     */
    String checkInfo(RegisteredUserAndCompany registeredUserAndCompany);

    /**
     * 查看用户详情
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/5/8 15:01
     */
    RegisteredUserInfo getUserWithCompanyByID(Integer recID);

    /**
     * 获取用户简称的数量
     * @param
     * @return
     */
    int getCountNickName(String nickName,Integer recID);
    /**
     *第三版本的更新个人用户
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/9 14:11
     */
    void updatePersonUserThirdVersion(RegisteredUserAndCompany registeredUserAndCompany) ;
    /**
     * 第三版本的更新企业用户
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/9 14:53
     */
    void updateCompanyUserThirdVersion(RegisteredUserAndCompany registeredUserAndCompany);


    /**
     * 通过用户简称获取用户
     * @param userInfo
     * @return
     */
    List<RegisteredUserInfo> getUserByName(RegisteredUserInfo userInfo);

    /**
     * 通过用户昵称搜索注册用户
     * @param nickName
     * @return
     */
    List<RegisteredUserInfo> getbyNickName( String nickName);
    /**
    * 通过用户id判断是否可以审核成功
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/18 19:18
    */
    boolean canAuditSuccess( Integer recID);
    /**
    * 是否需求改变审核状态
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/9/5 11:50
    */
    boolean isNeedChangeAuditStatus(RegisteredUserAndCompany registeredUserAndCompany);

}
