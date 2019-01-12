package com.yuanrong.admin.dao.base;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 内容形式的dao
 * Created MDA
 */
@Repository
public interface ContentFormDaoI extends BaseDaoI<ContentForm> {
    /**
     * 获取内容形式列表
     * @return
     */
    List<ContentForm> list(@Param("statusValue") Integer statusValue);

    /**
     * 根据名称判断是否存在
     * @param str
     * @return
     */
    ContentForm getByName(@Param("str") String str);

    /**
     * 前台商城——根据作者数量获取内容形式字典
     * @return
     */
    List<Map<String,Object>> getAuthorContentForm();
}
