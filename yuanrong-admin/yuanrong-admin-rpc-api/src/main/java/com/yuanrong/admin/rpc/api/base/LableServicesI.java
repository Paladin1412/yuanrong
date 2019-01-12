package com.yuanrong.admin.rpc.api.base;
import com.yuanrong.admin.bean.base.Lable;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;
import java.util.Map;

/**
 * 标签的services接口
 * Created MDA
 */
public interface LableServicesI extends BaseServicesI<Lable> {
    /**
     * 获取标签
     * @return
     */
    List<Lable> list(String typeId);

    /**
     * 根据作者数量获取创作风格字典
     * @return
     */
    List<Map<String,Object>> getAuthorContentStyle();
}
