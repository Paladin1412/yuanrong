package com.yuanrong.admin.dao.rank;

import com.yuanrong.admin.bean.rank.ListArticle;
import com.yuanrong.admin.dao.BaseDaoI;

import java.util.List;
import java.util.Map;

public interface ListArticleDaoI extends BaseDaoI<ListArticle> {
    /**
     * 查询文章榜单
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAllListArticle(Map<String,Object> map);

    /**
     * 查询ListArticle 时间段
     * @return
     */
    List<String> selectRangeTimeListArticle(Integer statusValue);

    /**
     * 更新 文章榜单
     * @param listArticle
     */
    void updateListArticle(ListArticle listArticle);

}
