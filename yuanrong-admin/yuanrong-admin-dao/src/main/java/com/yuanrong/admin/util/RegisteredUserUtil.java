package com.yuanrong.admin.util;

import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.seach.RegisterUserSearch;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import com.yuanrong.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

public class RegisteredUserUtil {
    public static boolean judgePersonInfo(RegisteredUserAndCompany registeredUserAndCompany) {
        if (StringUtils.isNotBlank(registeredUserAndCompany.getIDCardBackImageUrl()) && StringUtils.isNotBlank(registeredUserAndCompany.getIDCardFaceImageUrl()) && StringUtils.isNotBlank(registeredUserAndCompany.getIDCardNumber())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean judgeCompanyInfo(RegisteredUserAndCompany registeredUserAndCompany) {
        if (StringUtils.isNotBlank(registeredUserAndCompany.getCompanyID()) && StringUtils.isNotBlank(registeredUserAndCompany.getCompanyImageUrl())) {
            return true;
        } else {
            return false;
        }
    }

    public static void setCheckStatusByType(RegisteredUserAndCompany registeredUserAndCompany) {
        //如果是买家则赋值买家类型为个人，当前角色为买家
        //如果用户类型是个人，买家类型赋个人
        if (registeredUserAndCompany.getUserType() == EnumUserType.个人.getIndex()) {
            if (RegisteredUserUtil.judgePersonInfo(registeredUserAndCompany)) {
                registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                registeredUserAndCompany.setSellerAskAudit(new Timestamp(new Date().getTime()));
                registeredUserAndCompany.setBuyerAskAudit(new Timestamp(new Date().getTime()));
            } else {
                registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
                registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
            }
        } else {
            if (RegisteredUserUtil.judgeCompanyInfo(registeredUserAndCompany)) {
                registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                registeredUserAndCompany.setSellerAskAudit(new Timestamp(new Date().getTime()));
                registeredUserAndCompany.setBuyerAskAudit(new Timestamp(new Date().getTime()));
            } else {
                registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
                registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
            }
        }

    }

    public static void setCheckStatusByBSType(RegisteredUserAndCompany registeredUserAndCompany) {
        if (registeredUserAndCompany.getBuyerUserType() != null) {
            //如果是个人
            if (registeredUserAndCompany.getBuyerUserType().equals(EnumUserSellerAndBuyerUserType.个人.getIndex())) {
                if (RegisteredUserUtil.judgePersonInfo(registeredUserAndCompany)) {
                    registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                    registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                    registeredUserAndCompany.setSellerAskAudit(new Timestamp(new Date().getTime()));
                    registeredUserAndCompany.setBuyerAskAudit(new Timestamp(new Date().getTime()));
                } else {
                    registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
                    registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
                }
            } else {
                if (RegisteredUserUtil.judgeCompanyInfo(registeredUserAndCompany)) {
                    registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                    registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                    registeredUserAndCompany.setSellerAskAudit(new Timestamp(new Date().getTime()));
                    registeredUserAndCompany.setBuyerAskAudit(new Timestamp(new Date().getTime()));

                } else {
                    registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
                    registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
                }
            }
        }

        if (registeredUserAndCompany.getSellerUserType() != null) {
            if (registeredUserAndCompany.getSellerUserType().equals(EnumUserSellerAndBuyerUserType.个人.getIndex())) {
                if (RegisteredUserUtil.judgePersonInfo(registeredUserAndCompany)) {
                    registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                    registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                    registeredUserAndCompany.setSellerAskAudit(new Timestamp(new Date().getTime()));
                    registeredUserAndCompany.setBuyerAskAudit(new Timestamp(new Date().getTime()));

                } else {
                    registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
                    registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());

                }
            } else {
                if (RegisteredUserUtil.judgeCompanyInfo(registeredUserAndCompany)) {
                    registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                    registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
                    registeredUserAndCompany.setSellerAskAudit(new Timestamp(new Date().getTime()));
                    registeredUserAndCompany.setBuyerAskAudit(new Timestamp(new Date().getTime()));

                } else {
                    registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());
                    registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.注册成功.getIndex());

                }
            }
        }


    }
    /**
    * 用户管理列表/审核列表 参数处理
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/9/4 15:29
    */
    public static void paramDeal(RegisterUserSearch registerUserSearch) throws NumberFormatException{
        if (StringUtil.isNoneBlank(registerUserSearch.getMobile())) {
            registerUserSearch.setMobiles(registerUserSearch.getMobile().split(" "));
        }
        if (StringUtil.isNoneBlank(registerUserSearch.getName())) {
            registerUserSearch.setNames(registerUserSearch.getName().split(" "));
        }
        if (StringUtil.isNoneBlank(registerUserSearch.getNickName())) {
            registerUserSearch.setNickNames(registerUserSearch.getNickName().split(" "));
        }
        //销售id拆分
        Integer[] saleIDs = null;
        //媒介id拆分
        Integer[] mediaIDs = null;
        //用户状态
        Integer[] uStatusSS = null;
        //用户id
        Integer[] uIDSS = null;
        //销售id拆分
        if (StringUtil.isNoneBlank(registerUserSearch.getSaleID())) {
            String[] sIDs = registerUserSearch.getSaleID().split(",");
            saleIDs = new Integer[sIDs.length];
            for (int i = 0; i < sIDs.length; i++) {
                saleIDs[i] = Integer.parseInt(sIDs[i]);
            }
            registerUserSearch.setSaleIDs(saleIDs);
        }
        if (StringUtil.isNoneBlank(registerUserSearch.getMediaID())) {
            //媒介id拆分
            String[] mIDs = registerUserSearch.getMediaID().split(",");
            mediaIDs = new Integer[mIDs.length];
            for (int i = 0; i < mIDs.length; i++) {
                mediaIDs[i] = Integer.parseInt(mIDs[i]);
            }
            registerUserSearch.setMediaIDs(mediaIDs);
        }
        if (StringUtil.isNoneBlank(registerUserSearch.getStatusValue())) {
            //用户状态拆分
            String[] uStatus = registerUserSearch.getStatusValue().split(",");
            uStatusSS = new Integer[uStatus.length];
            for (int i = 0; i < uStatus.length; i++) {
                uStatusSS[i] = Integer.parseInt(uStatus[i]);
            }
            registerUserSearch.setStatusValues(uStatusSS);
        }
        if (StringUtil.isNoneBlank(registerUserSearch.getUserID())) {
            //用户id更新
            String[] uIDS = registerUserSearch.getUserID().split(" ");
            uIDSS = new Integer[uIDS.length];
            for (int i = 0; i < uIDS.length; i++) {
                uIDSS[i] = Integer.parseInt(uIDS[i]);
            }
            registerUserSearch.setUserIDs(uIDSS);
        }
    }


}
