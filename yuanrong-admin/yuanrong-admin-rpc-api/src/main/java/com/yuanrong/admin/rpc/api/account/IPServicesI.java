package com.yuanrong.admin.rpc.api.account;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.account.IP;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.IPListParamSeach;
import com.yuanrong.admin.util.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * IP列表
 * @ClassName IPServicesI
 * @Author Jss
 * @Date 2018/4/27
 */
public interface IPServicesI extends BaseServicesI<IP>{
    /**
     * Ip添加
     * @param data
     */
    Integer insertSelective(IP data);
    /**
     * IP详情
     * @param recID
     * @return
     */
    List<Map<String, Object>> findAccountByIPId(String recID);

    /**
     * 根据ID查IP
     * @param recID
     * @return
     */
    IP selectById(Integer recID);

    /**
     * 修改IP/移除IP对应的账号
     * @param ip
     * @param accountId
     */
    void updateIPAccount(IP ip, String accountId);

    /**
     * 通过IPID获取用户信息，IP信息
     * @param recID
     */
    List<Map<String, Object>> findUserByIP(Integer recID);

    /**
     * 批量插入IP（通过Excel文件）
     * @param result
     */
    String saveImportIP(JSONArray result);

    /**
     * 判断IP是否存在同一个用户下
     * @param ipName
     * @param userId
     * @return
     */
    List<IP> getByIPName(String ipName, Integer userId);
}
