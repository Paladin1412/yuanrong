package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumDemandFastStatus;
import com.yuanrong.admin.Enum.EnumDemandStatus;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;

import java.io.Serializable;

/**
 * 后台—快速需求列表返回结果
 * @ClassName DemandFastListResult
 * @Author Jss
 * @Date 2018/6/30
 */
public class DemandFastListResult extends DemandFast implements Serializable {

    /**
     * 普通需求号
     */
    private String demandNum;
    /**
     * 销售经理
     */
    private String realName;
    /**
     * 是否注册标记
     */
    private Integer flag;

    /**
     * 待处理状态快速需求的个数
     */
    private Integer pendingNum;

    public String getDemandNum() {
        return demandNum;
    }

    public void setDemandNum(String demandNum) {
        this.demandNum = demandNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getPendingNum() {
        return pendingNum;
    }

    public void setPendingNum(Integer pendingNum) {
        this.pendingNum = pendingNum;
    }

    /**
     * 快速需求列表结果封装
     * @param demandFast
     * @return
     */
    public static JSONObject packageDemandFast(DemandFastListResult demandFast) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("demandFastId",demandFast.getDemandFastId());
        jsonObject.put("demandId",demandFast.getDemandId());
        jsonObject.put("demandSn",demandFast.getDemandSn());
        jsonObject.put("demandNum",demandFast.getDemandNum());
        //快速需求拒约原因
        jsonObject.put("refuseReason",demandFast.getRefuseReason());
        jsonObject.put("demandFastStatusIndex",demandFast.getDemandFastStatusIndex());
        jsonObject.put("demandFastStatus",demandFast.getDemandFastStatusIndex() == null ? "" : EnumUtil.valueOf(EnumDemandFastStatus.class,demandFast.getDemandFastStatusIndex()).getName());
        jsonObject.put("demandFastTypeIndex",demandFast.getDemandTypeIndex());
        jsonObject.put("demandFastType",demandFast.getDemandTypeIndex() == null ? "" : EnumUtil.valueOf(EnumDemandType.class,demandFast.getDemandTypeIndex()).getName());
        jsonObject.put("userNickName",demandFast.getNickname());
        jsonObject.put("userPhone",demandFast.getMobile());
        jsonObject.put("flag",demandFast.getFlag() == 0 ? "未注册" : "已注册");
        jsonObject.put("createdTime",demandFast.getCreatedTime() == null ? "" : DateUtil.format(demandFast.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
        jsonObject.put("realName",demandFast.getRealName());
        jsonObject.put("pendingNum",demandFast.getPendingNum());
        return jsonObject;
    }
}
