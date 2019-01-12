package com.yuanrong.admin.rpc.service.impl.usermanagement;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserExtCompany;
import com.yuanrong.admin.exception.ErrorMessageData;
import com.yuanrong.admin.exception.ParmException;
import com.yuanrong.admin.seach.BatchUpdateSaleOrMedia;
import com.yuanrong.admin.seach.RegisterUserSearch;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.rpc.api.usermanagement.RegisteredUserInfoServiceI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.LogicUtil;
import com.yuanrong.common.util.MD5Util;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author ShiLinghuai
 * @return
 * @exception
 * @date 2018/4/27 12:08
 */
public class RegisteredUserInfoServiceIImpl extends BaseServicesAbstract<RegisteredUserInfo> implements RegisteredUserInfoServiceI {


    @Override
    public RegisteredUserInfo getById(Integer id) {
        return registeredUserInfoDAO.getById(id);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(RegisteredUserInfo registeredUserInfo) {

        // this.registeredUserInfoDAO.save(object);
    }

    /**
     * 通过查询手机号得到用户信息
     *
     * @param mobile
     * @return
     */
    @Override
    public List<RegisteredUserInfo> getByMobile(String mobile) {
        return this.registeredUserInfoDAO.getByMobile(mobile);
    }

    @Override
    /**
     * 保存用户
     * @author ShiLinghuai
     * @param registeredUserAndCompany
     * @return void
     * @exception
     * @date 2018/5/7 11:57
     */
    public void saveUser(RegisteredUserAndCompany registeredUserAndCompany) {
        if (registeredUserAndCompany.getUserType().equals(EnumUserType.个人.getIndex())) {
            this.registeredUserInfoDAO.saveRegisterUser(registeredUserAndCompany);
        } else {
            this.registeredUserInfoDAO.saveConpany(registeredUserAndCompany);
            registeredUserAndCompany.setCompanyExtID(registeredUserAndCompany.getCompanyExtID());
            this.registeredUserInfoDAO.saveRegisterUser(registeredUserAndCompany);
        }
        //快速需求id不为空，添加完用户更新快速需求的注册用户id.
        if(registeredUserAndCompany.getDemandFastID()!=null){
            DemandFast demandFast = new DemandFast();
            demandFast.setDemandFastId(registeredUserAndCompany.getDemandFastID());
            demandFast.setRegisteredUserInfoId(registeredUserAndCompany.getRecID());
            demandFast.setMobile(registeredUserAndCompany.getUserMobile());
            demandFastDaoI.updateStatus(demandFast);

        }
        if(registeredUserAndCompany.getDemandID()!=null){
            Demand demand = new Demand();
            demand.setDemandId(registeredUserAndCompany.getDemandID());
            demand.setRegisteredUserInfoId(registeredUserAndCompany.getRecID());
            demandDaoI.updateDemand(demand);

        }
    }



    /**
     * 获取客户公司职位
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:58
     */
    @Override
    public List<DictInfo> gainCompanyPositionList() {
        List<DictInfo> list = this.dictInfoDaoI.getDictInfoByType(1);
        return list;
    }

    /**
     * 通过用户ID获取用户信息包括媒介经理(IP添加页面)
     *
     * @param recID
     * @return
     */
    @Override
    public List<Map<String, Object>> getUserById(Integer recID) {
        return registeredUserInfoDAO.getUserById(recID);
    }

    /**
     * 按条件查询客户信息
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:58
     */
    @Override
    public PageInfo<RegisteredUserInfo> listByCondition(RegisterUserSearch registerUserSearch, BaseModel baseModel) {

        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(),baseModel.getFields());

        //List<Map<String, Object>> list = this.registeredUserInfoDAO.listByCondition(registerUserSearch);
        return new PageInfo<RegisteredUserInfo>(registeredUserInfoDAO.listByCondition(registerUserSearch));
    }

    @Override
    public List<RegisteredUserInfo> listByConditionNoPage(RegisterUserSearch registerUserSearch) {
        List<RegisteredUserInfo> list = this.registeredUserInfoDAO.listByCondition(registerUserSearch);
        return list;
    }

    /**
     * 批量为用户更改销售或媒介
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/8 9:40
     */
    @Override
    public void batchUpdateSaleOrMediaForUser(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        if (batchUpdateSaleOrMedia.getSaleID() != null) {
            this.registeredUserInfoDAO.batchUpdateSaleForUser(batchUpdateSaleOrMedia);
        }
        if (batchUpdateSaleOrMedia.getMediaID() != null) {
            this.registeredUserInfoDAO.batchUpdateMediaForUser(batchUpdateSaleOrMedia);
        }
    }

    /**
     * 批量重置密码
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/8 10:14
     */
    @Override
    public void batchResetPassword(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        if (batchUpdateSaleOrMedia.getIds().length > 0) {
            this.registeredUserInfoDAO.batchResetPassword(batchUpdateSaleOrMedia);
        }
    }

    /**
     * 获取全部注册用户
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/8 12:16
     */
    @Override
    public PageInfo<Map<String, Object>> getAllUser(AdminUser adminUser, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(), "ruu.id desc");
        return new PageInfo<Map<String, Object>>(registeredUserInfoDAO.getAllUserByAdmin(adminUser));

    }

    /**
     * 获取用户详情
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/8 15:03
     */
    @Override
    public JSONObject getUserDetailByID(Integer recID) {
        //获取用户全部信息
        RegisteredUserInfo registeredUserInfo = this.registeredUserInfoDAO.getUserDetailByID(recID);
        if (registeredUserInfo != null) {
            if (registeredUserInfo.getCurrentRole() != null) {
                //判断当前身份
                if (registeredUserInfo.getCurrentRole() == EnumUserCurrentRole.双身份.getIndex()) {
                    //如果买家和卖家类型都是个人返回个人
                    //如果卖家和买家类型都是企业返回企业
                    //其他返回全部
                    if (registeredUserInfo.getBuyerType() == EnumUserSellerAndBuyerUserType.个人.getIndex() &&
                            registeredUserInfo.getSellerType() == EnumUserSellerAndBuyerUserType.个人.getIndex()) {
                        JSONObject ele = new JSONObject();
                        putAllUserValueToPersonalUser(registeredUserInfo, ele);
                        return ele;
                    } else if (registeredUserInfo.getBuyerType() == EnumUserSellerAndBuyerUserType.企业.getIndex() &&
                            registeredUserInfo.getSellerType() == EnumUserSellerAndBuyerUserType.企业.getIndex()) {
                        JSONObject ele = new JSONObject();
                        putAllUserValueToCompanyUser(registeredUserInfo, ele);
                        return ele;
                    } else {
                        JSONObject ele = new JSONObject();
                        putAllUserInfoToJson(registeredUserInfo, ele);
                        return ele;
                    }
                } else if (registeredUserInfo.getCurrentRole() == EnumUserCurrentRole.当前身份卖家.getIndex()) {
                    //如果卖家类型是个人返回个人
                    if (registeredUserInfo.getSellerType() != null) {
                        if (registeredUserInfo.getSellerType() == EnumUserSellerAndBuyerUserType.个人.getIndex()) {
                            JSONObject ele = new JSONObject();
                            putAllUserValueToPersonalUser(registeredUserInfo, ele);
                            return ele;
                        } else {
                            JSONObject ele = new JSONObject();
                            putAllUserValueToCompanyUser(registeredUserInfo, ele);
                            return ele;
                        }
                    }
                } else if (registeredUserInfo.getCurrentRole() == EnumUserCurrentRole.当前身份买家.getIndex()) {
                    //如果买家类型是个人返回个人
                    if (registeredUserInfo.getBuyerType() != null) {
                        if (registeredUserInfo.getBuyerType() == EnumUserSellerAndBuyerUserType.个人.getIndex()) {
                            JSONObject ele = new JSONObject();
                            putAllUserValueToPersonalUser(registeredUserInfo, ele);
                            return ele;
                        } else {
                            JSONObject ele = new JSONObject();
                            putAllUserValueToCompanyUser(registeredUserInfo, ele);
                            return ele;
                        }
                    }
                }
            } else {
                //为null代表只是注册
                JSONObject ele = new JSONObject();
                putAllUserInfoToJson(registeredUserInfo, ele);
                return ele;
            }


        }
        return null;
    }

    /**
     * 个人详情 前台
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/31 20:02
     */
    @Override
    public JSONObject getUserDetailByIDFront(Integer recID, RegisteredUserAndCompany registeredUserAndCompany) {
        //获取用户全部信息
        RegisteredUserInfo registeredUserInfo = this.registeredUserInfoDAO.getUserDetailByID(recID);
        if (registeredUserInfo != null && registeredUserInfo.getCurrentRole() != null) {
            //如果卖家类型是个人返回个人
            return putSellerTypeInfoToJSON(registeredUserInfo);
        } else {
            //如果currentRole是null
            JSONObject ele = new JSONObject();
            ele.put("userMobile", registeredUserInfo.getMobile());
            ele.put("userType", registeredUserInfo.getBuyerType());
            return ele;
        }
    }



    private JSONObject putSellerTypeInfoToJSON(RegisteredUserInfo registeredUserInfo) {
        if (registeredUserInfo.getSellerType() == EnumUserSellerAndBuyerUserType.个人.getIndex()) {
            JSONObject ele = new JSONObject();
            putAllUserValueToPersonalUserFront(registeredUserInfo, ele);
            return ele;
        } else {
            JSONObject ele = new JSONObject();
            putAllUserValueToCompanyUserFront(registeredUserInfo, ele);
            return ele;
        }
    }

    private void putAllUserInfoToJson(RegisteredUserInfo registeredUserInfo, JSONObject ele) {
        ele.put("recID", registeredUserInfo.getRecID());
        ele.put("userMobile", registeredUserInfo.getMobile());
        ele.put("nickName", registeredUserInfo.getNickName());
        ele.put("role", registeredUserInfo.getRole());
        ele.put("currentRole", registeredUserInfo.getCurrentRole());
        ele.put("buyerUserType", registeredUserInfo.getBuyerType());
        ele.put("sellerUserType", registeredUserInfo.getSellerType());
        ele.put("adminUserMediaID", registeredUserInfo.getAdminUserMediaID());
        ele.put("adminUserSalesID", registeredUserInfo.getAdminUserSalesID());
        ele.put("buyerCheckStatus", registeredUserInfo.getBuyerStatusValue());
        ele.put("buyerCheckFailedReason", registeredUserInfo.getBuyerCheckFailedReasonID());

        ele.put("sellerCheckStatus", registeredUserInfo.getSellerStatusValue());
        ele.put("sellerCheckFailedReason", registeredUserInfo.getSellerCheckFailedReasonID());

        ele.put("realName", registeredUserInfo.getRealName());
        ele.put("IDCardNumber", registeredUserInfo.getIDCardNumber());
        ele.put("IDCardFaceImageUrl", registeredUserInfo.getIDCardFaceImageUrl());
        ele.put("IDCardBackImageUrl", registeredUserInfo.getIDCardBackImageUrl());


        ele.put("companyName", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyName());
        ele.put("leaderName", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getLeaderName());
        ele.put("roleID", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getRoleID());
        ele.put("leaderMobile", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getMobile());
        ele.put("companyImageUrl", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyImageUrl());
        ele.put("companyID", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyID());

    }

    private void putAllUserValueToCompanyUser(RegisteredUserInfo registeredUserInfo, JSONObject ele) {
        ele.put("recID", registeredUserInfo.getRecID());
        ele.put("userMobile", registeredUserInfo.getMobile());
        ele.put("nickName", registeredUserInfo.getNickName());
        ele.put("role", registeredUserInfo.getRole());
        ele.put("currentRole", registeredUserInfo.getCurrentRole());
        ele.put("buyerUserType", registeredUserInfo.getBuyerType());
        ele.put("sellerUserType", registeredUserInfo.getSellerType());
        ele.put("adminUserMediaID", registeredUserInfo.getAdminUserMediaID());
        ele.put("adminUserSalesID", registeredUserInfo.getAdminUserSalesID());
        ele.put("buyerCheckStatus", registeredUserInfo.getBuyerStatusValue());
        ele.put("buyerCheckFailedReason", registeredUserInfo.getBuyerCheckFailedReasonID());
        ele.put("sellerCheckStatus", registeredUserInfo.getSellerStatusValue());
        ele.put("sellerCheckFailedReason", registeredUserInfo.getSellerCheckFailedReasonID());
        ele.put("companyName", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyName());
        ele.put("leaderName", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getLeaderName());
        ele.put("roleID", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getRoleID());
        ele.put("leaderMobile", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getMobile());
        ele.put("companyImageUrl", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyImageUrl());
        ele.put("companyID", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyID());


    }

    private void putAllUserValueToPersonalUser(RegisteredUserInfo registeredUserInfo, JSONObject ele) {
        ele.put("recID", registeredUserInfo.getRecID());
        ele.put("userMobile", registeredUserInfo.getMobile());
        ele.put("nickName", registeredUserInfo.getNickName());
        ele.put("role", registeredUserInfo.getRole());
        ele.put("adminUserMediaID", registeredUserInfo.getAdminUserMediaID());
        ele.put("adminUserSalesID", registeredUserInfo.getAdminUserSalesID());
        ele.put("currentRole", registeredUserInfo.getCurrentRole());
        ele.put("buyerUserType", registeredUserInfo.getBuyerType());
        ele.put("sellerUserType", registeredUserInfo.getSellerType());
        ele.put("buyerCheckStatus", registeredUserInfo.getBuyerStatusValue());
        ele.put("buyerCheckFailedReasonID", registeredUserInfo.getBuyerCheckFailedReasonID());
        ele.put("buyerCheckFailedReason", registeredUserInfo.getBuyerCheckFailedReasonID());

        ele.put("sellerCheckStatus", registeredUserInfo.getSellerStatusValue());
        ele.put("sellerCheckFailedReasonID", registeredUserInfo.getSellerCheckFailedReasonID());
        ele.put("sellerCheckFailedReason", registeredUserInfo.getSellerCheckFailedReasonID());

        ele.put("realName", registeredUserInfo.getRealName());
        ele.put("IDCardNumber", registeredUserInfo.getIDCardNumber());
        ele.put("IDCardFaceImageUrl", registeredUserInfo.getIDCardFaceImageUrl());
        ele.put("IDCardBackImageUrl", registeredUserInfo.getIDCardBackImageUrl());




    }

    /**
     * 返回个人信息给前台
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/31 20:03
     */
    private void putAllUserValueToPersonalUserFront(RegisteredUserInfo registeredUserInfo, JSONObject ele) {
        ele.put("recID", registeredUserInfo.getRecID());
        ele.put("userMobile", registeredUserInfo.getMobile());
        ele.put("userType", registeredUserInfo.getSellerType());
        ele.put("status", registeredUserInfo.getSellerStatusValue());
        ele.put("checkFailedReason", registeredUserInfo.getSellerCheckFailedReasonID());
        ele.put("realName", registeredUserInfo.getRealName());
        ele.put("IDCardNumber", registeredUserInfo.getIDCardNumber());
        ele.put("IDCardFaceImageUrl", registeredUserInfo.getIDCardFaceImageUrl());
        ele.put("IDCardBackImageUrl", registeredUserInfo.getIDCardBackImageUrl());

    }

    /**
     * 返回企业信息给前台
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/31 20:06
     */
    private void putAllUserValueToCompanyUserFront(RegisteredUserInfo registeredUserInfo, JSONObject ele) {

        ele.put("recID", registeredUserInfo.getRecID());
        ele.put("userMobile", registeredUserInfo.getMobile());
        ele.put("userType", registeredUserInfo.getSellerType());
        ele.put("status", registeredUserInfo.getSellerStatusValue());
        ele.put("checkFailedReason", registeredUserInfo.getSellerCheckFailedReasonID());
        ele.put("companyName", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyName());
        ele.put("leaderName", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getLeaderName());
        ele.put("roleID", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getRoleID());
        ele.put("leaderMobile", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getMobile());
        ele.put("companyImageUrl", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyImageUrl());
        ele.put("companyID", registeredUserInfo.getRegisteredUserExtCompany() == null ? null : registeredUserInfo.getRegisteredUserExtCompany().getCompanyID());


    }
    /**
     * 更新企业用户前台
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/9 10:20
     */
    @Override
    public void updateCompanyUserFront(RegisteredUserAndCompany registeredUserAndCompany) {
        //获取用户信息
        RegisteredUserInfo registeredUserInfo =
                this.registeredUserInfoDAO.getById(registeredUserAndCompany.getRecID());
        //分别验证当前身份买家个人，当前身份卖家个人，当前双身份个人的合法性
        //如果当前身份是买家个人，那么他的买家类型不为空，卖家类型必为空，否则就异常
        // CheckCurrentRoleLegal(registeredUserAndCompany, registeredUserInfo);
        //获取客户公司
        RegisteredUserExtCompany registeredUserExtCompany = this.registeredUserExtCompanyDAOI.getById(registeredUserInfo.getCompanyExtID());
        //把前台传来的值赋值给查出来的公司，并先更新公司.如果没有公司则需要添加公司
        if (registeredUserExtCompany == null) {
            //RegisteredUserExtCompany registeredUserExtCompanyNew = new RegisteredUserExtCompany();
            //putRegisteredUserAndCompanyValueToRCompany(registeredUserAndCompany,registeredUserExtCompanyNew);
            // registeredUserExtCompanyDAOI.save(registeredUserExtCompany);
            registeredUserInfoDAO.saveConpany(registeredUserAndCompany);
            registeredUserAndCompany.setCompanyExtID(registeredUserAndCompany.getCompanyExtID());
        } else {
            putRegisteredUserAndCompanyValueToRCompany(registeredUserAndCompany, registeredUserExtCompany);
            this.registeredUserExtCompanyDAOI.updateById(registeredUserExtCompany);
        }

        //更新用户信息
        //判断媒介id是否为空
        //把前台只有注册用户的信息放到查到的对象里
        putFrontPersionParamToSelected(registeredUserAndCompany, registeredUserInfo);
        this.registeredUserInfoDAO.updateByID(registeredUserInfo);
    }
    /**
     * 将参数的公司审核信息的一些参数赋值给数据库操作对象
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/6/2 11:52
     */
    private void putRegisteredUserAndCompanyValueToRCompany(RegisteredUserAndCompany registeredUserAndCompany, RegisteredUserExtCompany registeredUserExtCompany) {
        //负责人电话
        if (registeredUserAndCompany.getLeaderMobile() !=null) {
            registeredUserExtCompany.setMobile(registeredUserAndCompany.getLeaderMobile());
        }
        //负责人姓名
        if (registeredUserAndCompany.getLeaderName()!=null) {
            registeredUserExtCompany.setLeaderName(registeredUserAndCompany.getLeaderName());
        }
        //公司名称
        if (registeredUserAndCompany.getCompanyName()!=null) {
            registeredUserExtCompany.setCompanyName(registeredUserAndCompany.getCompanyName());
        }
        //负责人职位
        if (registeredUserAndCompany.getRoleID() != null) {
            registeredUserExtCompany.setRoleID(registeredUserAndCompany.getRoleID());
        }
        //公司执照
        if (registeredUserAndCompany.getCompanyImageUrl() !=null) {
            registeredUserExtCompany.setCompanyImageUrl(registeredUserAndCompany.getCompanyImageUrl());
        }
        //统一社会信用代码
        if (registeredUserAndCompany.getCompanyID() !=null) {
            registeredUserExtCompany.setCompanyID(registeredUserAndCompany.getCompanyID());
        }

    }


    private void CheckCurrentRoleLegal(RegisteredUserAndCompany registeredUserAndCompany, RegisteredUserInfo registeredUserInfo) {
        if (registeredUserAndCompany.getCurrentRole() == EnumUserCurrentRole.当前身份买家.getIndex()) {
            if (registeredUserInfo.getBuyerType() == null || registeredUserInfo.getSellerType() != null) {
                throw new ParmException(new ErrorMessageData(null, "当前身份为买家，异常为库中买家类型为空或者卖家类型不用空"));
            }

        } else if (registeredUserAndCompany.getCurrentRole() == EnumUserCurrentRole.当前身份卖家.getIndex()) {
            //如果当前身份是卖家个人，那么他的卖家类型不为空，买家类型必为空，否则就异常
            if (registeredUserInfo.getSellerType() == null || registeredUserInfo.getBuyerType() != null) {
                throw new ParmException(new ErrorMessageData(null, "当前身份为卖家，库中卖家类型不可为空且买家类型不为空"));
            }
        } else if (registeredUserAndCompany.getCurrentRole() == EnumUserCurrentRole.双身份.getIndex()) {
            //如果当前身份是双身份，那么他的买家类型和卖家类型都为空，否数据异常
            if (registeredUserInfo.getSellerType() == null && registeredUserInfo.getBuyerType() == null) {
                throw new ParmException(new ErrorMessageData(null, "数据库中卖家类型，库中买家类型都为为空"));
            }
        }
    }

    @Override
    public Integer getCountPhone(String phone) {
        return this.registeredUserInfoDAO.getCountPhone(phone);
    }

    @Override
    public List<DictInfo> gainFailedReason() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(EnumDictInfoType.个人用户审核失败原因.getIndex());
        list.add(EnumDictInfoType.企业用户审核失败原因.getIndex());
        // List<DictInfo> result = this.dictInfoDaoI.getDictInfoByTypeS(list);

        return null;
    }

    /**
     * 批量置审核通过
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/16 17:33
     */
    @Override
    public void batchCheckedPass(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) throws ParmException {
        //检查前台传来的id或ids的参数是否全
        List<Integer> ids = batchUpdateSaleOrMedia.getIdss();
        for (Integer id : ids) {
            RegisteredUserInfo registeredUserInfo = registeredUserInfoDAO.getById(id);
            if (registeredUserInfo == null) {
                throw new ParmException(new ErrorMessageData("", "此user：" + id + "系统不存在"));
            }
            //个人用户
            if (registeredUserInfo.getUserType() == 0) {
                //用户id的姓名不能为空
                if (registeredUserInfo.getRealName() == null || registeredUserInfo.getRealName() == "") {
                    throw new ParmException(new ErrorMessageData("", "此user：" + id + "姓名不能为空"));
                }
                //用户id的身份证号不能为空
                if (registeredUserInfo.getIDCardNumber() == null || registeredUserInfo.getIDCardNumber() == "") {
                    throw new ParmException(new ErrorMessageData("", "此user：" + id + "身份证不能为空"));
                }
                //用户id的身份证照片不能为空
                if (registeredUserInfo.getIDCardBackImageUrl() == null || registeredUserInfo.getIDCardBackImageUrl() == ""
                        || registeredUserInfo.getIDCardFaceImageUrl() == null || registeredUserInfo.getIDCardFaceImageUrl() == "") {
                    throw new ParmException(new ErrorMessageData("", "此user：" + id + "身份证照片不能为空"));
                }
                //企业用户
            } else {
                RegisteredUserExtCompany registeredUserExtCompany = registeredUserExtCompanyDAOI.getById(registeredUserInfo.getCompanyExtID());
                //负责人电话
                if (registeredUserExtCompany.getMobile() == null) {
                    throw new ParmException(new ErrorMessageData("", "此user：" + id + "负责人电话不能为空"));
                }
                //负责人姓名
                if (registeredUserExtCompany.getLeaderName() == null) {
                    throw new ParmException(new ErrorMessageData("", "此user：" + id + "负责人姓名不能为空"));
                }
                //公司名称
                if (registeredUserExtCompany.getCompanyName() == null) {
                    throw new ParmException(new ErrorMessageData("", "此user：" + id + "公司名称不能为空"));
                }
                //负责人职位
                if (registeredUserExtCompany.getRoleID() == null) {
                    throw new ParmException(new ErrorMessageData("", "此user：" + id + "负责人职位不能为空"));
                }
                //公司执照
                if (registeredUserExtCompany.getCompanyImageUrl() == null) {
                    throw new ParmException(new ErrorMessageData("", "此user：" + id + "公司执照不能为空"));
                }
            }
        }
        //检查通过则批量置审核通过
        this.registeredUserInfoDAO.batchCheckedPass(batchUpdateSaleOrMedia.getIdss());

    }

    @Override
    public void batchCheckedFaid(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) throws Exception {
        //检查通过则批量置审核失败
        this.registeredUserInfoDAO.batchCheckedFail(batchUpdateSaleOrMedia);
    }

    @Override
    public RegisteredUserInfo login(String username, String password) {
        return registeredUserInfoDAO.login(username, password);
    }

    @Override
    public RegisteredUserInfo judeRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        if (registeredUserInfo.getPassword() != null && registeredUserInfo.getPassword().length() > 1) {

            registeredUserInfo.setPassword(MD5Util.md5(MD5Util.md5(registeredUserInfo.getPassword() + "#yr")));
        }
        return registeredUserInfoDAO.judeRegisteredUserInfo(registeredUserInfo);
    }

    /**
     * 添加注册用户
     *
     * @param
     * @return
     * @throws
     * @author Tangzheng
     * @date 2018/5/22 13:48
     */
    @Override
    public int saveRegisteredUser(Map<String, Object> map) {
        map.put("password", MD5Util.md5(MD5Util.md5(map.get("password").toString() + "#yr")));
        return registeredUserInfoDAO.saveRegisteredUser(map);
    }

    /**
     * 前台个人中心，通过注册用户ID查找下相关信息
     *
     * @param
     * @return
     * @throws
     * @author Tangzheng
     * @date 2018/5/26 11:31
     */
    @Override
    public List<Map<String, Object>> getMyInfoByID(Map<String, Object> map) {
        return registeredUserInfoDAO.getMyInfoByID(map);
    }

    /**
     * 前台个人中心，依据用户ID，更新相关信息
     *
     * @param
     * @return
     * @throws
     * @author Tangzheng
     * @date 2018/5/26 15:31
     */
    @Override
    public int updateMyInfoRegisterUserInfo(Map<String, Object> map) {
        return registeredUserInfoDAO.updateMyInfoRegisterUserInfo(map);
    }

    /**
     * 前台个人中心，依据用户ID，更新企业相关信息
     *
     * @param
     * @return
     * @throws
     * @author Tangzheng
     * @date 2018/5/28 19:51
     */
    @Override
    public int updateMyInfoRegisterUserInfoCompany(Map<String, Object> map) {
        return registeredUserInfoDAO.updateMyInfoRegisterUserInfoCompany(map);
    }

    /**
     * 前台个人中心， 卖家中心，统计注册用户:账号数、创作者数、作品数
     *
     * @param
     * @return
     * @throws
     * @author Tangzheng
     * @date 2018/5/30 12:11
     */
    @Override
    public Map<String, Object> cnt_registeredUserInfo(Map<String, Object> map) {
        return registeredUserInfoDAO.cnt_registeredUserInfo(map);
    }

    @Override
    public void updateByID(RegisteredUserInfo registeredUserInfo) {
        registeredUserInfoDAO.updateByID(registeredUserInfo);
    }

    /**
     * 判断手机号码是否存
     *
     * @param map
     * @return
     */
    @Override
    public int getCountMobile(Map<String, Object> map) {
        return registeredUserInfoDAO.getCountMobile(map);
    }

    /**
     * 保存发布需求的用户信息
     *
     * @param map
     * @return
     */

    @Override
    public int addRegisteredUserInfo(Map<String, Object> map) {
        return registeredUserInfoDAO.addRegisteredUserInfo(map);
    }


    /**
     * 根据用户ID更新手机号
     *
     * @param map
     * @return
     */
    @Override
    public int updateMobileByRecID(Map<String, Object> map) {
        return registeredUserInfoDAO.updateMobileByRecID(map);
    }

    /**
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/6/12 20:09
     */
    private void putFrontToSelected(RegisteredUserAndCompany registeredUserAndCompany, RegisteredUserInfo registeredUserInfo) {
        //把个人的信息放入
        putFrontPersionParamToSelected(registeredUserAndCompany, registeredUserInfo);
        //判断姓名
        if (registeredUserAndCompany.getRealName() != null) {
            registeredUserInfo.setRealName(registeredUserAndCompany.getRealName());
        }
        //判断身份证号
        if (registeredUserAndCompany.getIDCardNumber() != null) {
            registeredUserInfo.setIDCardNumber(registeredUserAndCompany.getIDCardNumber());
        }
        //判断身份证正面
        if (registeredUserAndCompany.getIDCardFaceImageUrl() != null) {
            registeredUserInfo.setIDCardFaceImageUrl(registeredUserAndCompany.getIDCardFaceImageUrl());
        }
        //判断身份证背面
        if (registeredUserAndCompany.getIDCardBackImageUrl()!=null) {
            registeredUserInfo.setIDCardBackImageUrl(registeredUserAndCompany.getIDCardBackImageUrl());
        }

    }

    /**
     * 将用户前台的一些不关身份验证的部分放到要更新的类中
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/6/2 11:49
     */
    private void putFrontPersionParamToSelected(RegisteredUserAndCompany registeredUserAndCompany, RegisteredUserInfo registeredUserInfo) {
        //判断媒介id是否为空
        if (registeredUserAndCompany.getAdminUserMediaID() != null) {
            registeredUserInfo.setAdminUserMediaID(registeredUserAndCompany.getAdminUserMediaID());
        }
        //为查出的对象赋值销售id
        if (registeredUserAndCompany.getAdminUserSalesID() != null) {
            registeredUserInfo.setAdminUserSalesID(registeredUserAndCompany.getAdminUserSalesID());
        }
        //判断手机号是否为空
        if (StringUtils.isNotBlank(registeredUserAndCompany.getUserMobile() )) {
            registeredUserInfo.setMobile(registeredUserAndCompany.getUserMobile());
        }
        //判断用户简称时候用修改
        if (StringUtils.isNotBlank(registeredUserAndCompany.getNickName() )) {
            registeredUserInfo.setNickName(registeredUserAndCompany.getNickName());
        }
        //判断当前角色
        if (registeredUserAndCompany.getCurrentRole() != null) {
            registeredUserInfo.setCurrentRole(registeredUserAndCompany.getCurrentRole());
        }
        //判断买家类型
        if (registeredUserAndCompany.getBuyerUserType() != null) {
            registeredUserInfo.setBuyerType(registeredUserAndCompany.getBuyerUserType());
        }
        //判断卖家类型
        if (registeredUserAndCompany.getSellerUserType() != null) {
            registeredUserInfo.setSellerType(registeredUserAndCompany.getSellerUserType());
        }
        //判断用户状态
        if (registeredUserAndCompany.getStatusValue() != null) {
            registeredUserInfo.setStatusValue(registeredUserAndCompany.getStatusValue());
        }

        //判断公司拓展id是否为空
        if (registeredUserAndCompany.getCompanyExtID() != null) {
            registeredUserInfo.setCompanyExtID(registeredUserAndCompany.getCompanyExtID());
        }
        //判断买家认证状态是否为空
        if (registeredUserAndCompany.getBuyerStatusValue() != null) {
            registeredUserInfo.setBuyerStatusValue(registeredUserAndCompany.getBuyerStatusValue());
        }
        //判断卖家的认证状态是否为空
        if (registeredUserAndCompany.getSellerStatusValue() != null) {
            registeredUserInfo.setSellerStatusValue(registeredUserAndCompany.getSellerStatusValue());
        }
        //判断买家的失败原因是否为空
        if (registeredUserAndCompany.getBuyerCheckFailedReasonID() != null) {
            registeredUserInfo.setBuyerCheckFailedReasonID(registeredUserAndCompany.getBuyerCheckFailedReasonID());
        }
        //判断卖家的失败原因是否空
        if (registeredUserAndCompany.getSellerCheckFailedReasonID() != null) {
            registeredUserInfo.setSellerCheckFailedReasonID(registeredUserAndCompany.getSellerCheckFailedReasonID());
        }
        //卖家审核人
        if (registeredUserAndCompany.getSellerAuditUser() != null) {
            registeredUserInfo.setSellerAuditUser(registeredUserAndCompany.getSellerAuditUser());
        }
        //买家审核人
        if (registeredUserAndCompany.getBuyerAuditUser() != null) {
            registeredUserInfo.setBuyerAuditUser(registeredUserAndCompany.getBuyerAuditUser());
        }
        //申请认证时间
        if(registeredUserAndCompany.getBuyerAskAudit()!=null){
            registeredUserInfo.setBuyerAskAudit(registeredUserAndCompany.getBuyerAskAudit());

        }
        //申请认证时间
        if(registeredUserAndCompany.getSellerAskAudit()!=null){
            registeredUserInfo.setSellerAskAudit(registeredUserAndCompany.getSellerAskAudit());
        }
    }

    /**
     * @param
     * @return
     * @throws
     * @author
     * @date 2018/5/7 11:59
     */
    @Override
    public List<AdminUser> findListByRoleID(Integer roleID) {
        List list = this.adminUserDaoI.findListByRoleID(roleID);
        return this.adminUserDaoI.findListByRoleID(roleID);
    }

    @Override
    public List<RegisteredUserInfo> getAll() {

        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    /**
     * 批量操作
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/6/6 15:21
     */
    @Override
    public void batchCheck(List<Integer> ids, Integer status) {
        registeredUserInfoDAO.batchCheckPerson(ids, EnumUserRoleLicenseStatus.审核成功.getIndex());
        registeredUserInfoDAO.batchCheckCompany(ids, EnumUserRoleLicenseStatus.审核成功.getIndex());
        registeredUserInfoDAO.batchCheckSellerStatus(ids, EnumUserRoleLicenseStatus.审核成功.getIndex());
        registeredUserInfoDAO.batchCheckBuyerStatus(ids, EnumUserRoleLicenseStatus.审核成功.getIndex());
        List<Integer> list = new ArrayList<Integer>();
        for (Integer userID : ids
                ) {
            RegisteredUserInfo registeredUserInfo = registeredUserInfoDAO.getById(userID);
            if (registeredUserInfo != null && registeredUserInfo.getSellerStatusValue() != null && registeredUserInfo.getSellerStatusValue().equals(EnumUserRoleLicenseStatus.审核成功.getIndex())) {
                list.add(userID);
            }

        }
        if (list.size() > 0) {
            platformIPAccountDaoI.updateAccountStatusByUserIDs(list);
        }


    }


    @Override
    public RegisteredUserInfo getUserWithCompanyByID(Integer recID) {
        return registeredUserInfoDAO.getUserDetailByID(recID);
    }

    /**
     * 获取用户简称的数量
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/6/20 17:59
     */
    @Override
    public int getCountNickName(String nickName, Integer recID) {
        return registeredUserInfoDAO.getCountNickName(nickName, recID);
    }

    /**
     * 更新个人用户
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/9 10:20
     */
    @Override
    public void updatePersonUserThirdVersion(RegisteredUserAndCompany registeredUserAndCompany) {
        //获取用户信息
        RegisteredUserInfo registeredUserInfo =
                this.registeredUserInfoDAO.getById(registeredUserAndCompany.getRecID());
        //把前台传来的对象的值赋给查出的对象
        putFrontToSelected(registeredUserAndCompany, registeredUserInfo);
        this.registeredUserInfoDAO.updateByID(registeredUserInfo);
    }

    /**
     * 更新企业用户
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/9 10:20
     */
    @Override
    public void updateCompanyUserThirdVersion(RegisteredUserAndCompany registeredUserAndCompany) {
        //获取用户信息
        RegisteredUserInfo registeredUserInfo =
                this.registeredUserInfoDAO.getById(registeredUserAndCompany.getRecID());
        //获取客户公司
        RegisteredUserExtCompany registeredUserExtCompany = this.registeredUserExtCompanyDAOI.getById(registeredUserInfo.getCompanyExtID());
        if (registeredUserExtCompany != null) {
            putRegisteredUserAndCompanyValueToRCompany(registeredUserAndCompany, registeredUserExtCompany);
            this.registeredUserExtCompanyDAOI.updateById(registeredUserExtCompany);
        } else {
            registeredUserInfoDAO.saveConpany(registeredUserAndCompany);
            registeredUserAndCompany.setCompanyExtID(registeredUserAndCompany.getCompanyExtID());
        }
        //把前台只有注册用户的信息放到查到的对象里
        putFrontPersionParamToSelected(registeredUserAndCompany, registeredUserInfo);
        this.registeredUserInfoDAO.updateByID(registeredUserInfo);
    }

    @Override
    public String checkInfo(RegisteredUserAndCompany registeredUserAndCompany) {
        return null;
    }

    @Override
    public void updateCompanyUser(RegisteredUserAndCompany registeredUserAndCompany, Integer role) {

    }

    @Override
    public void updatePersonUser(RegisteredUserAndCompany registeredUserAndCompany, Integer role) {

    }

    /**
     * 通过用户简称获取用户
     *
     * @param userInfo
     * @return
     */
    @Override
    public List<RegisteredUserInfo> getUserByName(RegisteredUserInfo userInfo) {
        return registeredUserInfoDAO.getUserByName(userInfo);
    }

    @Override
    public List<RegisteredUserInfo> getbyNickName(String nickName) {
        return registeredUserInfoDAO.getbyNickName(nickName);
    }
    @Override
    public boolean canAuditSuccess(Integer recID) {
        RegisteredUserInfo registeredUserInfo = registeredUserInfoDAO.getUserDetailByID(recID);
        if(registeredUserInfo.getSellerType()!=null){
            if(registeredUserInfo.getSellerType().equals(EnumUserSellerAndBuyerUserType.个人.getIndex())){
                Integer count = registeredUserInfoDAO.getCountByIDRID(registeredUserInfo.getIDCardNumber(),null,registeredUserInfo.getRecID());
                if(count>=1){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isNeedChangeAuditStatus(RegisteredUserAndCompany registeredUserAndCompany) {
        RegisteredUserInfo registeredUserInfo = getUserWithCompanyByID(registeredUserAndCompany.getRecID());
        boolean isAllSame = true;
        //如果传来的值的身份审核信息和查出来的身份审核信息不同则走改变审核状态的逻辑，
        //如果传来的值的身份审核信息和查出来的身份审核信息相同，则直接更新的逻辑。或者就没有传身份验证信息
        if (LogicUtil.anyOr((Integer) EnumUserSellerAndBuyerUserType.个人.getIndex() == registeredUserAndCompany.getBuyerUserType(),
                (Integer) EnumUserSellerAndBuyerUserType.个人.getIndex() == registeredUserAndCompany.getSellerUserType())) {
            //判断姓名
            if (registeredUserAndCompany.getRealName() != null) {
                if (!registeredUserAndCompany.getRealName().equals(registeredUserInfo.getRealName())) {
                    isAllSame = false;
                }
            }
            //判断身份证号
            if (registeredUserAndCompany.getIDCardNumber() != null) {
                if (!registeredUserAndCompany.getIDCardNumber().equals(registeredUserInfo.getIDCardNumber())) {
                    isAllSame = false;
                }
            }
            //判断身份证正面
            if (registeredUserAndCompany.getIDCardFaceImageUrl() != null) {
                if (!registeredUserAndCompany.getIDCardFaceImageUrl().equals(registeredUserInfo.getIDCardFaceImageUrl())) {
                    isAllSame = false;
                }
            }
            //判断身份证背面
            if (registeredUserAndCompany.getIDCardBackImageUrl() != null) {
                if (!registeredUserAndCompany.getIDCardBackImageUrl().equals(registeredUserAndCompany.getIDCardBackImageUrl())) {
                    isAllSame = false;
                }
            }

        } else if (LogicUtil.anyOr((Integer) EnumUserSellerAndBuyerUserType.企业.getIndex() == registeredUserAndCompany.getBuyerUserType(),
                (Integer) EnumUserSellerAndBuyerUserType.企业.getIndex() == registeredUserAndCompany.getSellerUserType())) {
            RegisteredUserExtCompany registeredUserExtCompany = registeredUserInfo.getRegisteredUserExtCompany();
            if (registeredUserExtCompany == null) {
                isAllSame = false;
            } else {
                //负责人电话
                if (registeredUserAndCompany.getLeaderMobile() != null) {
                    if (!registeredUserAndCompany.getLeaderMobile().equals(registeredUserExtCompany.getMobile())) {
                        isAllSame = false;
                    }
                }
                //负责人姓名
                if (registeredUserAndCompany.getLeaderName() != null) {
                    if (!registeredUserAndCompany.getLeaderName().equals(registeredUserExtCompany.getLeaderName())) {
                        isAllSame = false;
                    }
                }
                //公司名称
                if (registeredUserAndCompany.getCompanyName() != null) {
                    if (!registeredUserAndCompany.getCompanyName().equals(registeredUserExtCompany.getCompanyName())) {
                        isAllSame = false;
                    }
                }
                //负责人职位
                if (registeredUserAndCompany.getRoleID() != null) {
                    if (!registeredUserAndCompany.getRoleID().equals(registeredUserExtCompany.getRoleID())) {
                        isAllSame = false;
                    }
                }
                //公司执照
                if (registeredUserAndCompany.getCompanyImageUrl() != null) {
                    if (!registeredUserAndCompany.getCompanyImageUrl().equals(registeredUserExtCompany.getCompanyImageUrl())) {
                        isAllSame = false;
                    }
                }
                //统一社会信用代码
                if (registeredUserAndCompany.getCompanyID() != null) {
                    if (!registeredUserAndCompany.getCompanyID().equals(registeredUserExtCompany.getCompanyID())) {
                        isAllSame = false;
                    }
                }
            }
        }
        //如果全部相同则不用变审核状态
        if(isAllSame){
            return false;
        }else {
            return true;
        }
    }
}
