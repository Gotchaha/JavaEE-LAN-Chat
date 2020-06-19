package com.LANChat.dao;

import com.LANChat.util.ConnUtil;
import org.apache.commons.dbutils.QueryRunner;

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
        List<NhManageUser> list = new ArrayList<>();
        Integer from = (user.getPage()-1)*user.getLimit();
        Integer to = user.getLimit();
        List<Object> param = new ArrayList<>();
        String sql = "SELECT u.*,d.name departName from nh_manage_user u left join nh_manage_department d " +
                "on d.id=u.department_id where 1=1";
        if(user!=null && user.getUsername()!=null &&!user.getUsername().equals("")){
            sql+=" and u.user_name like concat('%',?,'%') ";
            param.add(user.getUsername());
        }
        if(user!=null && user.getRealname()!=null && !user.getRealname().equals("")){
            sql+=" and u.real_name like concat('%',?,'%') ";
            param.add(user.getRealname());
        }
        sql+="  limit ?,? ";
        param.add(from);
        param.add(to);
        Object[] paramO = param.toArray();
        try {
            list = qr.query(sql,new BeanListHandler<>(NhManageUser.class),paramO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int findCountByCondition(NhManageUser user) {
        int i = 0;
        String sql = "select count(0) from nh_manage_user";
        List<Object> param = new ArrayList<>();
        if(user!=null && user.getUsername()!=null &&!user.getUsername().equals("")){
            sql+=" and user_name like concat('%',?,'%') ";
            param.add(user.getUsername());
        }
        if(user!=null && user.getRealname()!=null && !user.getRealname().equals("")){
            sql+=" and real_name like concat('%',?,'%') ";
            param.add(user.getRealname());
        }
        try {
            i = Integer.parseInt(qr.query(sql,new ScalarHandler<>(),param.toArray()).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public NhManageUser findByID(int id) {
        NhManageUser user = new NhManageUser();
        String sql = "select * from nh_manage_user where id=? ";
        try {
            user = qr.query(sql,new BeanHandler<>(NhManageUser.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(NhManageUser user) {
        String sql = "";
    }

    @Override
    public void insert(NhManageUser user) {
        String sql = "INSERT INTO nh_manage_user (user_name, password, real_name, department_id, position, " +
                "gender, phone, email, delete_flag, address, work_phone, memo, update_time) VALUES ( ?, ?, ?, ?, ?, ?,?, ?,?, ?, ?, ?,now())";
        Object[] param = {user.getUsername(),user.getPassword(),user.getRealname(),user.getDepartmentId(),user
                .getPosition(),user.getGender(),user.getPhone(),user.getEmail(),user.getDeleteFlag(),user.getAddress(),user.getWorkPhone(),user.getMemo()};
        try {
            qr.update(sql,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NhManageUser findUserByNameAndPass(String username, String password) {
        String sql = "select * from nh_manage_user where user_name=? and password =?";
        NhManageUser user = null;
        try {
            user = qr.query(sql,new BeanHandler<>(NhManageUser.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}