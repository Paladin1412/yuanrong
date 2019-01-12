package com.yuanrong.admin.rpc.service.impl.demand;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.demand.DemandProduction;
import com.yuanrong.admin.rpc.api.demand.DemandProductionServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tangz
 * @date 2018/9/7 19:56
 */

@Service
public class DemandProductionImpl extends BaseServicesAbstract<DemandProduction> implements DemandProductionServicesI {
    @Override
    public DemandProduction getById(Integer id) {
        return demandProductionDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(DemandProduction object) {

    }

    @Override
    public List<DemandProduction> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }
}
