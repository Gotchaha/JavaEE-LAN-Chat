package com.LANChat.dao;

import com.LANChat.entity.NhManageUser;

import java.util.List;

public interface IUserDao {
    List<NhManageUser> findUserByCondition(NhManageUser user);

    int findCountByCondition(NhManageUser user);

    NhManageUser findByID(int id);

    void update(NhManageUser user);

    void insert(NhManageUser user);
}
