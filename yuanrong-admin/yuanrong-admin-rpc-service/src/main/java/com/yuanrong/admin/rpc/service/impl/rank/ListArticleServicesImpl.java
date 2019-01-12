package com.yuanrong.admin.rpc.service.impl.rank;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.rank.ListArticle;
import com.yuanrong.admin.rpc.api.base.LableServicesI;
import com.yuanrong.admin.rpc.api.rank.ListArticleServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ListArticleServicesImpl extends BaseServicesAbstract<ListArticle> implements ListArticleServicesI {
    @Override
    public ListArticle getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(ListArticle object) {

    }

    @Override
    public List<ListArticle> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    @Override
    public PageInfo<Map<String, Object>> selectAllListArticle(BaseModel baseModel,Map<String, Object> map) {
//        PageHelper.startPage(baseModel.getCp(),baseModel.getRows(),"TotalIndex desc");
        List<Map<String,Object>> rntList = listArticleDaoI.selectAllListArticle(map);
        return new PageInfo<Map<String, Object>>(rntList);
    }

    @Override
    public List<String> selectRangeTimeListArticle(Integer statusValue) {
        return listArticleDaoI.selectRangeTimeListArticle( statusValue);
    }

    @Override
    public void updateListArticle(ListArticle listArticle) {
        listArticleDaoI.updateListArticle(listArticle);
    }
}
