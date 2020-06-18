package com.LANChat.dao;

import com.LANChat.entity.NhManageUser;
import com.LANChat.util.ConnUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao{

    QueryRunner qr = new QueryRunner(ConnUtil.getDataSource());
    @Override
    public List<NhManageUser> findUserByCondition(NhManageUser user) {
        Integer from = (user.getPage()-1)*user.getLimit();
        Integer to = user.getLimit();
        String sql="SELECT * from nh_manage_user LIMIT ?,?";

        return null;
    }

    @Override
    public int findCountByCondition(NhManageUser user) {
        return 0;
    }

    @Override
    public NhManageUser findByID(int id) {
        return null;
    }

    @Override
    public void update(NhManageUser user) {

    }

    @Override
    public void insert(NhManageUser user) {

    }

    @Override
    public NhManageUser findUserByNameAndPass(String username, String password) {
        return null;
    }


}
