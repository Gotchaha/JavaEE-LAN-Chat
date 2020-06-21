package com.LANChat.entity;

import java.util.List;

public class GridData<T> {
    private int count;
    private String msg;
    private int status;
    private List<T> data;
    public GridData(){}
    public GridData(int count, String msg, int status, List<T> data) {
        this.count = count;
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
