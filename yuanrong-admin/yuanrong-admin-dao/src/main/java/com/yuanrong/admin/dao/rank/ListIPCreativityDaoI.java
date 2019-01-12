package com.yuanrong.admin.dao.rank;

import com.yuanrong.admin.bean.rank.ListIPCreativity;
import com.yuanrong.admin.dao.BaseDaoI;

import java.util.List;
import java.util.Map;

public interface ListIPCreativityDaoI extends BaseDaoI<ListIPCreativity> {

    /**
     * IP创作者榜单
     * @param map
     * @return
     */
    List<Map<String,Object> > selectAllListIPCreativity(Map<String,Object> map);

    /**
     *  查询 ListIPCreativity 时间段
     * @return
     */
    List<String> selectRangeTimeListIPCreativity(Integer statusValue);

    /**
     * 更新IP创作力榜单
     * @param listIPCreativity
     */
    void updateListIPCreativity(ListIPCreativity listIPCreativity);
}
