package com.LANChat.entity;

public class NhManageDepartment {

    private long id;
    private String name;
    private String memo;
    private long delete_flag;
    private String update_time;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public long getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(long delete_flag) {
        this.delete_flag = delete_flag;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
