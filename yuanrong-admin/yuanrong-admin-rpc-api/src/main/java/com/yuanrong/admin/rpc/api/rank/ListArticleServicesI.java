package com.yuanrong.admin.rpc.api.rank;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.rank.ListArticle;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.util.BaseModel;

import java.util.List;
import java.util.Map;

public interface ListArticleServicesI  extends BaseServicesI<ListArticle> {
        /**
         * 查询文章榜单
         * @param map
         * @return
         */
    PageInfo<Map<String, Object>> selectAllListArticle(BaseModel baseModel,Map<String, Object> map);

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
