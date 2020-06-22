package com.LANChat.service;

import com.LANChat.dao.IUserDao;
import com.LANChat.dao.UserDao;
import com.LANChat.entity.NhManageUser;

import java.util.List;

public class UserService implements IUserService {
    IUserDao userDao = new UserDao();
    @Override
    public List<NhManageUser> findUserByCondition(NhManageUser user) {
        return userDao.findUserByCondition(user);
    }

    @Override
    public int findCountByCondition(NhManageUser user) {
        return userDao.findCountByCondition(user);
    }

    @Override
    public NhManageUser findByID(int id) {
        return userDao.findByID(id);
    }

    @Override
    public void update(NhManageUser user) {
        userDao.update(user);
    }

    @Override
    public void insert(NhManageUser user) {
        userDao.insert(user);
    }

    @Override
    public boolean login(String username, String password) {
        NhManageUser user = userDao.findUserByNameAndPass(username,password);
        if(user!=null){
            return true;
        }
        return false;
    }

    @Override
    public void del(NhManageUser user) {
        userDao.del(user);
    }
}
