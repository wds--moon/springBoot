package com.example.common.config;

import com.alibaba.fastjson.JSON;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by wendongshan on 2016/11/18.
 * 客户端连接
 */
@Component
public class SolrClientConfig {


    private  final  static  String HOST_URL="http://192.168.2.201:8080/solr/moon";

    public  String search(){
        HttpSolrClient server = new HttpSolrClient(HOST_URL);
        SolrQuery query = new SolrQuery("userName:te*");//查询条件,支持模糊匹配
        //实现分页的查询
       /* query.setStart(0);
        query.setRows(3);*/
       String param="";
        QueryResponse res = null;
        try {
            res = server.query(query);
            SolrDocumentList sdl = res.getResults();
            System.out.println("总数："+sdl.getNumFound());
            param=JSON.toJSONString(sdl);
            /*for(SolrDocument sd : sdl){
                System.out.println("id="+sd.get("id")+";   userName="+sd.get("userName")+";    userAddress="+sd.get("userAddress")+";");
            }*/
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return param;

    }

}
