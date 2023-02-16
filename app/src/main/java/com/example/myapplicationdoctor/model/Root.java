package com.example.myapplicationdoctor.model;

import java.util.ArrayList;

public class Root {
    public Pagination pagination;
    public Request request;
    public ArrayList<Object> results;

    public Root(Pagination pagination, Request request, ArrayList<Object> results) {
        this.pagination = pagination;
        this.request = request;
        this.results = results;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ArrayList<Object> getResults() {
        return results;
    }

    public void setResults(ArrayList<Object> results) {
        this.results = results;
    }
}
