package com.yuanrong.admin.rpc.api.base;
import com.yuanrong.admin.bean.base.IPLable;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;

/**
 * IP标签的services接口
 * Created MDA
 */
public interface IPLableServicesI extends BaseServicesI<IPLable> {
    /**
     * 通过parentID获取IPLabe
     * @param parentId
     * @return
     */
    List<IPLable> getIPLableByParentId(String parentId);
}
