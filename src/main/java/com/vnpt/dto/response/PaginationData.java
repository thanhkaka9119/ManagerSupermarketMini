package com.vnpt.dto.response;

import java.util.List;
import java.util.Map;

public class PaginationData {
    private List<Map<String,Object>> content;
    private int totalCount;

    public PaginationData(){}

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Map<String, Object>> getContent() {
        return content;
    }

    public void setContent(List<Map<String, Object>> content) {
        this.content = content;
    }
}
