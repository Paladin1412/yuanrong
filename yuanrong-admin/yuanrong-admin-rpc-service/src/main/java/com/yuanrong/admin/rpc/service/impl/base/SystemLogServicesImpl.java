package com.yuanrong.admin.rpc.service.impl.base;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.SystemLog;
import com.yuanrong.admin.rpc.api.base.SystemLogServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 系统日志的services实现类
 * Created MDA
 */
@Service
public class SystemLogServicesImpl extends BaseServicesAbstract<SystemLog> implements SystemLogServicesI {
    @Override
    public SystemLog getById(Integer id) {
        return systemLogDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        systemLogDaoI.deleteById(id);
    }
    @Override
    public void save(SystemLog object) {
        systemLogDaoI.save(object);
    }
    @Override
    public List<SystemLog> getAll() {
        return systemLogDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "systemLogId desc");
        List<SystemLog> result = systemLogDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public List<SystemLog> getByClassPathAndId(String classPath, String id) {
        return systemLogDaoI.getByClassPathAndId(classPath , id);
    }

    @Override
    public JSONArray getJSONArrayByClassPathAndId(String classPath, String id) {
        List<SystemLog> systemLogs = this.getByClassPathAndId(classPath , id);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(systemLogs) > 0){
            for(SystemLog systemLog : systemLogs){
                JSONObject ele = new JSONObject();
                ele.put("id" , systemLog.getSystemLogId());
                ele.put("msg" , systemLog.getMsg());
                ele.put("time" , DateUtil.format(systemLog.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
                ele.put("operator" , systemLog.getOperator());
                result.add(ele);
            }
        }
        return result;
    }

    @Override
    public void log(String classPath, int id, String msg, String operator) {
        SystemLog systemLog = new SystemLog();
        systemLog.setClassPath(classPath);
        systemLog.setId(id);
        systemLog.setMsg(msg);
        systemLog.setOperator(operator);
        systemLogDaoI.save(systemLog);
    }
}
