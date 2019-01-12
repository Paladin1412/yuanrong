package com.yuanrong.admin.dao.account;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.account.IP;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.seach.IPListParamSeach;
import com.yuanrong.admin.util.BaseModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IPDaoI extends BaseDaoI<IP>{

    int deleteByPrimaryKey(Integer recID);

    void updateByIdSelective(IP record);

    int updateByPrimaryKey(IP record);

    /**
     * IP添加
     * @param object
     */
    void insertSelective(IP object);

    /**
     * IP详情
     * @param recID
     * @return
     */
    List<Map<String, Object>> findAccountByIPId(@Param("recID") String recID);

    /**
     * 根据ID查IP
     * @param recID
     * @return.
     */
    IP selectById(Integer recID);

    /**
     * 通过IPID获取用户信息，IP信息
     * @param recID
     * @return
     */
    List<Map<String,Object>> findUserByIP(@Param("recID") Integer recID);

    /**
     * IP列表
     * @param data
     */
    List<Map> list(@Param("data") IPListParamSeach data);

    /**
     * 判断IP是否存在同一个用户下
     * @param ipName
     * @param userId
     * @return
     */
    List<IP> getByIPName(@Param("ipName") String ipName,@Param("userId") Integer userId);

    List<Map> listResultMap(@Param("data") Object data);
}