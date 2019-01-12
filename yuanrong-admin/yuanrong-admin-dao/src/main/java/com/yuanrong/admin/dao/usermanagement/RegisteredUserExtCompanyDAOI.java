package com.yuanrong.admin.dao.usermanagement;

import com.yuanrong.admin.bean.usermanagement.RegisteredUserExtCompany;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegisteredUserExtCompanyDAOI extends BaseDaoI<RegisteredUserExtCompany> {
    /**
    * 更新公司
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/9 10:11
    */
    void updateById(RegisteredUserExtCompany registeredUserExtCompany);

}
