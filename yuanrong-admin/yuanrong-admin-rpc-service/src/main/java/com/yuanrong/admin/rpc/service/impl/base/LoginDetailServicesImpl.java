package com.yuanrong.admin.rpc.service.impl.base;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.LoginDetail;
import com.yuanrong.admin.rpc.api.base.LoginDetailServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 登录日志的services实现类
 * Created MDA
 */
@Service
public class LoginDetailServicesImpl extends BaseServicesAbstract<LoginDetail> implements LoginDetailServicesI {
    @Override
    public LoginDetail getById(Integer id) {
        return loginDetailDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        loginDetailDaoI.deleteById(id);
    }
    @Override
    public void save(LoginDetail object) {
        loginDetailDaoI.save(object);
    }
    @Override
    public List<LoginDetail> getAll() {
        return loginDetailDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "loginDetailId desc");
        List<LoginDetail> result = loginDetailDaoI.list(data);
        return new PageInfo(result);
    }
}
