package com.yuanrong.admin.rpc.api.rank;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.rank.ListIPCreativity;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.util.BaseModel;

import java.util.List;
import java.util.Map;

public interface ListIPCreativityServicesI extends BaseServicesI<ListIPCreativity> {


    /**
     * IP创作者榜单
     * @param map
     * @return
     */
    PageInfo<Map<String, Object>>  selectAllListIPCreativity(BaseModel baseModel, Map<String,Object> map);

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
