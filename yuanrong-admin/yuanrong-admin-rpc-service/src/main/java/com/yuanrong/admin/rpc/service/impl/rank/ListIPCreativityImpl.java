package com.yuanrong.admin.rpc.service.impl.rank;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.rank.ListIPCreativity;
import com.yuanrong.admin.rpc.api.rank.ListIPCreativityServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ListIPCreativityImpl extends BaseServicesAbstract<ListIPCreativity> implements ListIPCreativityServicesI{

    @Override
    public ListIPCreativity getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(ListIPCreativity object) {

    }

    @Override
    public List<ListIPCreativity> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }



    @Override
    public PageInfo<Map<String, Object>> selectAllListIPCreativity(BaseModel baseModel, Map<String, Object> map) {
//        PageHelper.startPage(baseModel.getCp(),baseModel.getRows(),"TotalIndex desc");
         List<Map<String,Object>> rntList = listIPCreativityDaoI.selectAllListIPCreativity(map);
        return new PageInfo(rntList);
    }

    @Override
    public List<String> selectRangeTimeListIPCreativity(Integer statusValue) {
        return listIPCreativityDaoI.selectRangeTimeListIPCreativity(statusValue);
    }

    @Override
    public void updateListIPCreativity(ListIPCreativity listIPCreativity) {
        listIPCreativityDaoI.updateListIPCreativity(listIPCreativity);
    }
}
