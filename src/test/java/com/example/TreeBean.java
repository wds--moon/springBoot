package com.example;/**
 * Created by Administrator on 2017/5/5 0005.
 */

import java.util.List;

/**
 * @author wendongshan
 * @create 2017-05-05 下午 1:51
 **/
public class TreeBean {

    private  int code;
    private  String note;
    private  int parentNode;

    private List<TreeBean> subTree;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getParentNode() {
        return parentNode;
    }

    public void setParentNode(int parentNode) {
        this.parentNode = parentNode;
    }

    public List<TreeBean> getSubTree() {
        return subTree;
    }

    public void setSubTree(List<TreeBean> subTree) {
        this.subTree = subTree;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TreeBean{");
        sb.append("code=").append(code);
        sb.append(", note='").append(note).append('\'');
        sb.append(", parentNode=").append(parentNode);
        sb.append(", subTree=").append(subTree);
        sb.append('}');
        return sb.toString();
    }
}
