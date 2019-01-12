package com.yuanrong.admin.server.util;

import com.yuanrong.admin.Enum.EnumUserRoleLicenseStatus;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import org.apache.commons.lang3.StringUtils;

public class RegisteredUserUtil {
    public static boolean judgePersonInfo(RegisteredUserAndCompany registeredUserAndCompany) {
            if (StringUtils.isNotBlank(registeredUserAndCompany.getIDCardBackImageUrl())  &&StringUtils.isNotBlank( registeredUserAndCompany.getIDCardFaceImageUrl())&&StringUtils.isNotBlank( registeredUserAndCompany.getIDCardNumber())) {
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
}
