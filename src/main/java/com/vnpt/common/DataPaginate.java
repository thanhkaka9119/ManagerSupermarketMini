package com.vnpt.common;

import java.util.List;

public class DataPaginate<V> {
    private List<V> content;
    private int pageNumber;
    private long totalCount;

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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
