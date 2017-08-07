package com.example;/**
 * Created by Administrator on 2017/3/21 0021.
 */

import com.alibaba.fastjson.JSON;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;

/**
 * @author wendongshan
 * @create 2017-03-21 下午 2:15
 **/
public class TestFile {
    static List<TreeBean> list=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //初始化集合
        @Language("RegExp") String regexp = "[0-9]+";

        addList();
        TreeBean superTree = new TreeBean();
        for (TreeBean treeBean:list
             ) {
            if(treeBean.getCode()==1){
                superTree.setCode(treeBean.getCode());
                superTree.setParentNode(treeBean.getParentNode());
                superTree.setNote(treeBean.getNote());
                continue;
            }
        }
        //实现递归查询子集
        digui(superTree.getCode(),superTree);
        System.out.println(JSON.toJSONString(superTree));
    }

public  static void digui(int code,TreeBean superTree){
    List<TreeBean> subList=new ArrayList<>();
    for (TreeBean treeBean:list
         ) {
            if(code==treeBean.getParentNode()){
                TreeBean data=new TreeBean();
                data.setCode(treeBean.getCode());
                data.setParentNode(treeBean.getParentNode());
                data.setNote(treeBean.getNote());
                subList.add(data);
                superTree.setSubTree(subList);
                digui(data.getCode(),data);
            }
    }

}


    public  static  void addList(){
        TreeBean treeBean=new TreeBean();
        treeBean.setCode(1);
        treeBean.setNote("父级");
        treeBean.setParentNode(0);
        list.add(treeBean);

        TreeBean treeBean1=new TreeBean();
        treeBean1.setCode(2);
        treeBean1.setNote("2级");
        treeBean1.setParentNode(1);
        list.add(treeBean1);

        TreeBean treeBean2=new TreeBean();
        treeBean2.setCode(3);
        treeBean2.setNote("3级");
        treeBean2.setParentNode(2);
        list.add(treeBean2);

        TreeBean treeBean22=new TreeBean();
        treeBean22.setCode(22);
        treeBean22.setNote("2级");
        treeBean22.setParentNode(2);
        list.add(treeBean22);

        TreeBean treeBean223=new TreeBean();
        treeBean223.setCode(222);
        treeBean223.setNote("2级");
        treeBean223.setParentNode(22);
        list.add(treeBean223);

        TreeBean treeBean33=new TreeBean();
        treeBean33.setCode(33);
        treeBean33.setNote("2级");
        treeBean33.setParentNode(3);
        list.add(treeBean33);


    }
}
