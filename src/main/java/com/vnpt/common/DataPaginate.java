package com.vnpt.common;

import java.util.List;

public class DataPaginate<V> {
    private List<V> content;
    private int pageNumber;
    private int totalPages;

    public DataPaginate() {
    }

    public List<V> getContent() {
        return content;
    }

    public void setContent(List<V> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
