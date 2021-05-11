package com.net.nxx.service.impl;

import com.alibaba.fastjson.JSON;
import com.net.nxx.dao.NxxIdleItemDao;
import com.net.nxx.dao.NxxUserDao;
import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.model.NxxUser;
import com.net.nxx.service.IdleItemService;
import com.net.nxx.vo.PageVo;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @Author: XiaYu
 * @Date 2021/5/8 23:48
 */

@Service
public class IdleItemServiceImpl implements IdleItemService {
    @Resource
    private NxxIdleItemDao idleItemDao;

    @Resource
    private NxxUserDao userDao;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 发布闲置
     *
     * @param idleItemModel
     * @return
     */
    @Override
    public boolean addIdleItem(NxxIdleItem idleItemModel) {
        System.out.println(idleItemModel);
        boolean b = idleItemDao.insert(idleItemModel) == 1;
        /*
        //idleItemDao.
        //创建请求
        IndexRequest ESitem = new IndexRequest("item");
        //填充规则
        ESitem.id(idleItemModel.getId().toString());  //文档编号
        //将对象放入请求中
        ESitem.source(JSON.toJSONString(idleItemModel), XContentType.JSON);
        //客户端发送请求，接收响应结果
        try {
            IndexResponse index = restHighLevelClient.index(ESitem, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        return b;
    }

    /**
     * 查询闲置信息，同时查出发布者的信息
     *
     * @param id
     * @return
     */
    @Override
    public NxxIdleItem getIdleItem(Long id) {
        NxxIdleItem idleItemModel = idleItemDao.selectByPrimaryKey(id);
        if (idleItemModel != null) {
            idleItemModel.setUser(userDao.selectByPrimaryKey(idleItemModel.getUserId()));
        }
        return idleItemModel;
    }

    /**
     * 查询用户发布的所有闲置
     * user_id建索引
     *
     * @param userId
     * @return
     */
    @Override
    public List<NxxIdleItem> getAllIdelItem(Long userId) {
        return idleItemDao.getAllIdleItem(userId);
    }

    /**
     * 搜索，分页
     * 同时查出闲置发布者的信息
     *
     * @param findValue
     * @param page
     * @param nums
     * @return
     */
    @Override
    public PageVo<NxxIdleItem> findIdleItem(String findValue, int page, int nums) {
        findValue = findValue.trim();
        LinkedList<Long> IdList = new LinkedList<>();
        //创建请求对象
        SearchRequest java_index = new SearchRequest("item");
        //构造搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (findValue.length() == 0) {
            //使用工具类构造搜索信息
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        } else {
            MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(findValue, "idle_name", "idle_details");
            //MatchQueryBuilder query = QueryBuilders.matchQuery("idle_details", findValue);
            //QueryBuilders.
            searchSourceBuilder.query(query);
        }

        java_index.source(searchSourceBuilder);
        //发送请求
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(java_index, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(JSON.toJSONString(search.getHits().getHits()));  //Hits对象就包含查询的各种信息
        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //System.out.println(sourceAsMap);
            //System.out.println((String) (sourceAsMap.get("id")));
            long id = (Integer) sourceAsMap.get("id");
            System.out.println("id" + id);
            IdList.add(id);
        }
        //List<NxxIdleItem> list = idleItemDao.findIdleItem(findValue, (page - 1) * nums, nums);
        List<NxxIdleItem> list = new LinkedList<>();
        if (IdList.size() > 0) {
            list = idleItemDao.findIdleByList(IdList);
        }
        List<NxxIdleItem> reallist = new LinkedList<>();

        for (NxxIdleItem i : list) {
            if (i.getIdleStatus() == ((byte) 1)) {
//                DeleteRequest delete = new DeleteRequest("item", String.valueOf(i.getId()));
//                try {
//                    restHighLevelClient.delete(delete, RequestOptions.DEFAULT);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                reallist.add(i);
            }
        }


        System.out.println("查询成功？" + reallist.size());
        list = reallist;
        if (list.size() > 0) {
            List<Long> idList = new ArrayList<>();
            for (NxxIdleItem i : list) {
                idList.add(i.getUserId());
            }
            List<NxxUser> userList = userDao.findUserByList(idList);
            Map<Long, NxxUser> map = new HashMap<>();
            for (NxxUser user : userList) {
                map.put(user.getId(), user);
            }
            for (NxxIdleItem i : list) {
                i.setUser(map.get(i.getUserId()));
            }
        }
        //int count = idleItemDao.countIdleItem(findValue);
        int count = IdList.size();
        return new PageVo<>(list, count);
    }

    /**
     * 分类查询，分页
     * 同时查出闲置发布者的信息，代码结构与上面的类似，可封装优化，或改为join查询
     *
     * @param idleLabel
     * @param page
     * @param nums
     * @return
     */
    @Override
    public PageVo<NxxIdleItem> findIdleItemByLable(int idleLabel, int page, int nums) {
        List<NxxIdleItem> list = idleItemDao.findIdleItemByLable(idleLabel, (page - 1) * nums, nums);
        if (list.size() > 0) {
            List<Long> idList = new ArrayList<>();
            for (NxxIdleItem i : list) {
                idList.add(i.getUserId());
            }
            List<NxxUser> userList = userDao.findUserByList(idList);
            Map<Long, NxxUser> map = new HashMap<>();
            for (NxxUser user : userList) {
                map.put(user.getId(), user);
            }
            for (NxxIdleItem i : list) {
                i.setUser(map.get(i.getUserId()));
            }
        }
        int count = idleItemDao.countIdleItemByLable(idleLabel);
        return new PageVo<>(list, count);
    }

    /**
     * 更新闲置信息
     *
     * @param idleItemModel
     * @return
     */
    @Override
    public boolean updateIdleItem(NxxIdleItem idleItemModel) {
        return idleItemDao.updateByPrimaryKeySelective(idleItemModel) == 1;
    }

    @Override
    public PageVo<NxxIdleItem> adminGetIdleList(int status, int page, int nums) {
        List<NxxIdleItem> list = idleItemDao.getIdleItemByStatus(status, (page - 1) * nums, nums);
        if (list.size() > 0) {
            List<Long> idList = new ArrayList<>();
            for (NxxIdleItem i : list) {
                idList.add(i.getUserId());
            }
            List<NxxUser> userList = userDao.findUserByList(idList);
            Map<Long, NxxUser> map = new HashMap<>();
            for (NxxUser user : userList) {
                map.put(user.getId(), user);
            }
            for (NxxIdleItem i : list) {
                i.setUser(map.get(i.getUserId()));
            }
        }
        int count = idleItemDao.countIdleItemByStatus(status);
        return new PageVo<>(list, count);
    }
}
