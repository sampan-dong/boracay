package com.hex.bigdata.udsp.dsl.model;

import java.util.List;

/**
 * Created by JunjieM on 2019-1-8.
 */
public class DslRequest {

    private String name; // 名称
    private List<Column> columns; // 返回字段
    private List<Component> where; // 过滤
    private List<String> groupBy; // 分组
    private List<Order> orderBy; // 排序
    private Limit limit; // 限制

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Component> getWhere() {
        return where;
    }

    public void setWhere(List<Component> where) {
        this.where = where;
    }

    public List<String> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<String> groupBy) {
        this.groupBy = groupBy;
    }

    public List<Order> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List<Order> orderBy) {
        this.orderBy = orderBy;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }
}