package com.yuanrong.admin.rpc.api.base;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;
import java.util.Map;

/**
 * 内容形式的services接口
 * Created MDA
 */
public interface ContentFormServicesI extends BaseServicesI<ContentForm> {
    /**
     * 内容形式—后台
     * @return
     */
    List<ContentForm> list();

    /**
     * 内容形式—前台
     * @param statusValue
     * @return
     */
    List<ContentForm> list(Integer statusValue);

    /**
     * 通过名称获取内容形式
     * @param contentFormName
     * @return
     */
    ContentForm getByName(String contentFormName);

    /**
     * 前台商城——根据作者数量获取内容形式字典
     * @return
     */
    List<Map<String,Object>> getAuthorContentForm();
}
