package com.example.myapplicationdoctor.model;

import java.io.Serializable;

public class Pagination implements Serializable {
    public int currentPage;

    public Pagination(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
