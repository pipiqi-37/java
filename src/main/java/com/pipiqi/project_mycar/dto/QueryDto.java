package com.pipiqi.project_mycar.dto;

import javax.xml.soap.SAAJResult;

public class QueryDto {
    /*
    * http://localhost:8080/sys/menu/list?order=asc&limit=10&offset=0
    * 需要的字段 order   limit offset id  sort
    * */
    private String order;
    private int limit;
    private int offset;
    private String sort;  //排序字段
    private String search; //搜索

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
