package com.yuanrong.admin.rpc.service.impl.resources;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.resources.UserImages;
import com.yuanrong.admin.rpc.api.resources.UserImagesServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 用户图片的services实现类
 * Created MDA
 */
@Service
public class UserImagesServicesImpl extends BaseServicesAbstract<UserImages> implements UserImagesServicesI {
    @Override
    public UserImages getById(Integer id) {
        return userImagesDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        userImagesDaoI.deleteById(id);
    }
    @Override
    public void save(UserImages object) {
        userImagesDaoI.save(object);
    }
    @Override
    public List<UserImages> getAll() {
        return userImagesDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "userImagesId desc");
        List<UserImages> result = userImagesDaoI.list(data);
        return new PageInfo(result);
    }
}
