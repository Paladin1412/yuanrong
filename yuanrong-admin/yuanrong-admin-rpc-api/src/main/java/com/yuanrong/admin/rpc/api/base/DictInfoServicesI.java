package com.yuanrong.admin.rpc.api.base;

import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/28.
 */
public interface DictInfoServicesI extends BaseServicesI<DictInfo> {
    /**
     * 通过类型查找数据字典信息
     * @param type
     * @return
     */
    public List<DictInfo> getDictInfoByType (int type);
    /**
     * 通过类型集合查找数据字典信息
     * @param
     * @return
     */
    public List<DictInfo> getDictInfoByTypes (Integer[] dictInfos);

    /**
     * 根据类型和名字，查找字典信息
     * @param type
     * @param name
     * @return
     */
    public DictInfo  getDictInfoByTypeAndName (int type , String name);

    /**
     * 根据字典类型和ID查找是否存在
     * @param type
     * @param id
     * @return
     */
    public DictInfo getDictInfoByTypeAndID(int type ,int id);

    /**
     * 前台商城——根据作者数量获取内容分类字典
     * @param type
     * @return
     */
    List<Map<String,Object>> getAuthorContentType(Integer type);
}
