package com.txr.spbdatamybatis.controller.condition;

/**
 * Created by xinrui.tian on 2019/3/23.
 */
public class BondQueryCondition {

    private String searchString;
    private String startDate;
    private String endDate;
    private String orderBy = "issue_start_date";
    private String sort = "asc";


    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = "%" + searchString + "%";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
