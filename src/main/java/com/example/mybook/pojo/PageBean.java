package com.example.mybook.pojo;

import java.util.List;

public class PageBean<T> {
    private Integer currentPage;
    private Integer recordCount;
    private Integer pageCount;
    private List<T> list;


    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", list=" + list +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
