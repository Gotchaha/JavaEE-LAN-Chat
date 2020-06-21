package com.LANChat.service;

import com.LANChat.entity.NhManageUser;

import java.util.List;

public interface IUserService {

    List<NhManageUser> findUserByCondition(NhManageUser user);

    int findCountByCondition(NhManageUser user);

    NhManageUser findByID(int parseInt);

    void update(NhManageUser user);

    void insert(NhManageUser user);

    boolean login(String username, String password);
}
