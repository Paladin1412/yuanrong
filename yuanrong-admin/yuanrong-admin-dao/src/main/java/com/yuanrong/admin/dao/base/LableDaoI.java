package com.yuanrong.admin.dao.base;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.bean.base.Lable;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 标签的dao
 * Created MDA
 */
@Repository
public interface LableDaoI extends BaseDaoI<Lable> {
    /**
     * 获取标签
     * @param typeId
     * @return
     */
    List<Lable> list(@Param("typeId") String typeId);

    /**
     * 根据名称判断是否存在
     * @param str
     * @return
     */
    Lable getByName(@Param("str") String str);

    /**
     * 根据作者数量获取创作风格字典
     * @return
     */
    List<Map<String,Object>> getAuthorContentStyle();

    /**
     * 根据名称判断是否存在
     * @param str
     * @return
     */
    Lable getByNameAndType(@Param("str") String str,@Param("type") Integer type);

}
