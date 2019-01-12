package com.yuanrong.admin.dao.base;
import com.yuanrong.admin.bean.base.IPLable;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * IP标签的dao
 * Created MDA
 */
@Repository
public interface IPLableDaoI extends BaseDaoI<IPLable> {
    /**
     * 通过parentID获取IPLabe
     * @param parentId
     * @return
     */
    List<IPLable> getIPLableByParentId(@Param("parentId") String parentId);
}
