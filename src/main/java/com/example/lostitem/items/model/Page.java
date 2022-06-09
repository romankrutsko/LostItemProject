package com.example.lostitem.items.model;

import java.util.List;

public class Page<T> {
    private int pageId;
    private int pageSize;
    private List<T> data;

    public Page(int pageId, int pageSize) {
        this.pageId = pageId;
        this.pageSize = pageSize;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
