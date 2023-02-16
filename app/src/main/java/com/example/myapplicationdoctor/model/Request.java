package com.example.myapplicationdoctor.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    public String sort;
    public int pageSize;
    public int page;
    public String feedback;
    public String key;
    public ArrayList<Double> location;
    public String q;

    public Request(String sort, int pageSize, int page, String feedback, String key, ArrayList<Double> location, String q) {
        this.sort = sort;
        this.pageSize = pageSize;
        this.page = page;
        this.feedback = feedback;
        this.key = key;
        this.location = location;
        this.q = q;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<Double> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Double> location) {
        this.location = location;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
