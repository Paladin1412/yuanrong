package com.yuanrong.admin.rpc.api.bug;
import com.yuanrong.admin.bean.bug.BugInfo;
import com.yuanrong.admin.rpc.BaseServicesI;
/**
 * bug信息的services接口
 * Created MDA
 */
public interface BugInfoServicesI extends BaseServicesI<BugInfo> {

    /**
     * 记录Error
     * @param bugInfo
     * @return
     */
    public BugInfo error(BugInfo bugInfo);
}
