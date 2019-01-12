package com.yuanrong.admin.rpc.service.impl.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDictInfoType;
import com.yuanrong.admin.bean.account.IP;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.rpc.api.account.IPServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.IPListParamSeach;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IPServicesImpl
 * @Author Jss
 * @Date 2018/4/27
 */
public class IPServicesImpl extends BaseServicesAbstract<IP> implements IPServicesI {
    @Override
    public IP getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    /**
     * IP新增
     * @param object
     */
    @Override
    public void save(IP object) {

    }

    @Override
    public List<IP> getAll() {
        return null;
    }

    /**
     * 列表展示IP
     * @param data
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() ,baseModel.getRows() , "ip.createdTime desc");
        return new PageInfo<Map>(ipDaoI.listResultMap(data));
    }

    /**
     * IP添加
     * @param data
     */
    @Override
    public Integer insertSelective(IP data) {
        ipDaoI.insertSelective(data);
        return data.getRecID();
    }

    /**
     * IP详情
     * @param recID
     * @return
     */
    @Override
    public List<Map<String, Object>> findAccountByIPId(String recID) {
        return ipDaoI.findAccountByIPId(recID);
    }

    /**
     * 根据ID查IP
     * @param recID
     * @return
     */
    @Override
    public IP selectById(Integer recID) {
        return ipDaoI.selectById(recID);
    }


    /**
     * 修改IP/移除IP对应的账号
     * @param ip
     * @param accountId
     */
    @Override
    public void updateIPAccount(IP ip, String accountId) {
        if(!StringUtil.isBlank(accountId)){
            List<PlatformIPAccount> list = new ArrayList<PlatformIPAccount>();
            String[] split = accountId.split(",");
            Integer recID =null;
            for (String sp : split) {
                PlatformIPAccount platformIPAccount = new PlatformIPAccount();
                platformIPAccount.setId(Integer.parseInt(sp));
                platformIPAccount.setiPID(recID);
                list.add(platformIPAccount);
            }
            //修改账号信息
            platformIPAccountDaoI.updateAccount(list);
        }
        //修改IP信息
        ipDaoI.updateByIdSelective(ip);
    }

    /**
     * 通过IPID获取用户信息，IP信息
     * @param recID
     */
    @Override
    public List<Map<String, Object>> findUserByIP(Integer recID) {
        return ipDaoI.findUserByIP(recID);
    }

    /**
     * 批量插入IP（通过Excel文件）
     * @param result
     */
    @Override
    public String saveImportIP(JSONArray result) {
        String userIds = "";
        if(result.size() > 0){
            for(int i = 0;i<result.size();i++){
                JSONObject jb = result.getJSONObject(i);
                if(registeredUserInfoDAO.getById(jb.getInteger("userID")) == null){
                    System.out.println(jb.getInteger("userID")+"==========================用户不存在");
                    if("".equals(userIds)){
                        userIds += jb.getInteger("userID");
                    }else{
                        userIds += "," + jb.getInteger("userID");
                    }
                    continue;
                }
                IP ip = new IP();
                ip.setRegisteredUserInfoID(jb.getInteger("userID"));
                ip.setiPName(jb.getString("iPName"));
                DictInfo dictInfo = dictInfoDaoI.getDictInfoByTypeAndName(EnumDictInfoType.圆融分类.getIndex(),jb.getString("iPCategory"));
                if(dictInfo == null){
                    ip.setCategoryID(null);
                }else{
                    ip.setCategoryID(dictInfo.getId());
                }
                ip.setiPFans(jb.getInteger("iPFans"));
                ip.setiPIntroduction(jb.getString("iPIntroduction"));
                ip.setiPWholeNetworkPrice(jb.getBigDecimal("iPWholeNetworkPrice"));
                ip.setInvalidTime(jb.getString("iPInvalidTime").equals("") ? null : jb.getString("iPInvalidTime"));
                ipDaoI.insertSelective(ip);
            }
        }
        return userIds;
    }

    /**
     * 判断IP是否存在同一个用户下
     * @param ipName
     * @param userId
     * @return
     */
    @Override
    public List<IP> getByIPName(String ipName, Integer userId) {
        return ipDaoI.getByIPName(ipName, userId);
    }
}
