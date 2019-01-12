package com.yuanrong.admin.rpc.api.base;

import com.yuanrong.admin.bean.base.SmsRecord;
import com.yuanrong.admin.rpc.BaseServicesI;

public interface SmsRecordServicesI extends BaseServicesI<SmsRecord> {

    /**
     * 调用短信记录
     * @param smsRecord
     * @return
     */
    int saveSmsRecord(SmsRecord smsRecord);

    /**
     * 调用短信接口，并记录调用信息
     */
    boolean insertSendMessage(SmsRecord smsRecord,String content);
    /**
    * 调用短信，并记录信息
    * @author      ShiLinghuai
    * @param
    * @return 是否发送成功成功
    * @exception
    * @date        2018/9/5 16:54
    */
    boolean sendMessageAInsert(String MessageCotent,String mobile);
}
