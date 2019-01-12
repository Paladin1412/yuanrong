package com.yuanrong.admin.dao.base;

import com.yuanrong.admin.bean.base.SmsRecord;
import com.yuanrong.admin.dao.BaseDaoI;
import org.springframework.stereotype.Repository;

/**
 * 记录调用短信的Dao
 */
@Repository
public interface SmsRecordDaoI extends BaseDaoI<SmsRecord>{
    /**
     * 调用短信记录
     * @param smsRecord
     * @return
     */
    int saveSmsRecord(SmsRecord smsRecord);
}
