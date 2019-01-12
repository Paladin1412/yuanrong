package com.yuanrong.admin.rpc.service.impl.base;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.base.SmsRecord;
import com.yuanrong.admin.rpc.api.base.SmsRecordServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.module.shortmessage.SMSSend;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SmsRecordImpl extends BaseServicesAbstract<SmsRecord> implements SmsRecordServicesI {
    @Override
    public SmsRecord getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(SmsRecord object) {

    }

    @Override
    public List<SmsRecord> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    @Override
    public int saveSmsRecord(SmsRecord smsRecord) {

        return smsRecordDaoI.saveSmsRecord(smsRecord);
    }

    @Override
    public boolean insertSendMessage(SmsRecord smsRecord ,String content) {

        String rst =   SMSSend.sendSMS(content,smsRecord.getMobile());

        //判断结果
        if (SMSSend.isSendSuccess(rst)) {
            smsRecord.setStatus(EnumYesOrNo.NORMAL.getIndex());
            smsRecordDaoI.saveSmsRecord(smsRecord);
            return true;
        }else {
            smsRecord.setStatus(EnumYesOrNo.FORBIDDEN.getIndex());
            smsRecordDaoI.saveSmsRecord(smsRecord);
            return false;
        }
    }
    /**
     * 调用短信，并记录信息
     * @author      ShiLinghuai
     * @param
     * @return 是否发送成功成功
     * @exception
     * @date        2018/9/5 16:54
     */
    @Override
    public boolean sendMessageAInsert(String MessageCotent, String mobile) {
        String rst = SMSSend.sendSMS(MessageCotent, mobile);
        SmsRecord smsRecord = new SmsRecord();
        //判断结果
        if (SMSSend.isSendSuccess(rst)) {
            smsRecord.setStatus(EnumYesOrNo.NORMAL.getIndex());
            smsRecord.setMobile(mobile);
            smsRecord.setContent(MessageCotent);
            smsRecordDaoI.saveSmsRecord(smsRecord);
            return true;
        }else {
            smsRecord.setStatus(EnumYesOrNo.NORMAL.getIndex());
            smsRecord.setMobile(mobile);
            smsRecord.setContent(MessageCotent);
            smsRecordDaoI.saveSmsRecord(smsRecord);
            return false;
        }
    }
}
