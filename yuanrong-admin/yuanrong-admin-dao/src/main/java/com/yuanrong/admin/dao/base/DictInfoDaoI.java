package com.yuanrong.admin.dao.base;

import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/28.
 */
@Repository
public interface DictInfoDaoI extends BaseDaoI<DictInfo> {
    /**
     * 通过类型查找数据字典信息
     * @param type
     * @return
     */
     List<DictInfo> getDictInfoByType (@Param("type") int type);
    /**
    * 通过类型id集合查找数据字典信息
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/16 11:43
    */
     List<DictInfo> getDictInfoByTypeS (@Param("types") Integer[] types);

    /**
     * 根据类型和名字，查找字典信息
     * @param type
     * @param name
     * @return
     */
     DictInfo  getDictInfoByTypeAndName (@Param("type")int type , @Param("name")String name);

    /**
     * 用过名字获取字典
     * @param name
     * @return
     */
     DictInfo getByName( @Param("name")String name);
    /**
     * 根据字典类型和ID查找是否存在
     * @param type
     * @param id
     * @return
     */
    public DictInfo getDictInfoByTypeAndID(@Param("type") int type ,@Param("id") int id);

    /**
     * 前台商城——根据作者数量获取内容分类字典
     * @param type
     * @return
     */
    List<Map<String,Object>> getAuthorContentType(@Param("type") Integer type);
}
