package com.net.nxx;

import com.alibaba.fastjson.JSON;
import com.net.nxx.model.NxxIdleItem;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {
    /*
    @Field(type=FieldType.Text, analyzer="ik_max_word")     表示该字段是一个文本，并作最大程度拆分，默认建立索引
    @Field(type=FieldType.Text,index=false)             表示该字段是一个文本，不建立索引
    @Field(type=FieldType.Date)                                表示该字段是一个文本，日期类型，默认不建立索引
    @Field(type=FieldType.Long)                               表示该字段是一个长整型，默认建立索引
    @Field(type=FieldType.Keyword)                         表示该字段内容是一个文本并作为一个整体不可分，默认建立索引
    @Field(type=FieldType.Float)                               表示该字段内容是一个浮点类型并作为一个整体不可分，默认建立索引
    date 、float、long都是不能够被拆分的
     */
    @Autowired
    RestHighLevelClient restHighLevelClient;

    //测试添加文档
    @Test
    public void addDocument() throws IOException {
        //创建对象
        NxxIdleItem item = new NxxIdleItem(30L);

        //创建请求
        IndexRequest ESitem = new IndexRequest("item");
        //填充规则
        ESitem.id("30");  //文档编号
        //将对象放入请求中
        ESitem.source(JSON.toJSONString(item), XContentType.JSON);
        //客户端发送请求，接收响应结果
        IndexResponse index = restHighLevelClient.index(ESitem, RequestOptions.DEFAULT);
        //打印响应结果
        System.out.println(index.toString());  //查看返回的具体json信息
        System.out.println(index.status());  //查看操作的状态
    }

    //测试获取文档信息
    @Test
    public void getDocument() throws IOException {
        GetRequest java_index = new GetRequest("item", "30");
        GetResponse getResponse = restHighLevelClient.get(java_index, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());  //打印文档的内容
        System.out.println(getResponse);  //getResponse对象就包含ES的所有查询信息
    }

    //测试修改文档信息
    @Test
    public void updateDocument() throws IOException {
        UpdateRequest java_index = new UpdateRequest("item", "30");
        NxxIdleItem item = new NxxIdleItem(30L);
        item.setUserId(100L);
        java_index.doc(JSON.toJSONString(item), XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(java_index, RequestOptions.DEFAULT);
        System.out.println(update.status());  //查看更新状态
    }

    //删除文档信息
    @Test
    public void deleteDocument() throws IOException {
        DeleteRequest java_index = new DeleteRequest("item", "30");
        DeleteResponse delete = restHighLevelClient.delete(java_index, RequestOptions.DEFAULT);
        System.out.println(delete.status());  //查看删除状态
    }

    //测试批量添加文档下信息
    @Test
    public void bulkDocument() throws IOException {
        //创建批量操作对象
        BulkRequest bulkRequest = new BulkRequest();
        ArrayList<NxxIdleItem> list = new ArrayList<>();
        list.add(new NxxIdleItem(12L));
        list.add(new NxxIdleItem(12L));
        list.add(new NxxIdleItem(12L));
        list.add(new NxxIdleItem(12L));
        list.add(new NxxIdleItem(12L));
        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("item").id("" + (i + 10))
                            .source(JSON.toJSONString(list.get(i)), XContentType.JSON));
        }
        //发送请求
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());  //查看状态，是否失败，返回false代表成功
    }

    //测试查询文档信息
    @Test
    public void search() throws IOException {
        //创建请求对象
        SearchRequest java_index = new SearchRequest("item");
        //构造搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //使用工具类构造搜索信息
        MatchQueryBuilder query = QueryBuilders.matchQuery("user_id", "1");
        searchSourceBuilder.query(query);
        java_index.source(searchSourceBuilder);
        //发送请求
        SearchResponse search = restHighLevelClient.search(java_index, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(search.getHits()));  //Hits对象就包含查询的各种信息
    }
}
