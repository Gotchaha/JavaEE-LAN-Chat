package com.LANChat.dao;

import com.LANChat.entity.NhManageDepartment;
import com.LANChat.util.ConnUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements IDepartmentDao {
    QueryRunner qr = new QueryRunner(ConnUtil.getDataSource());
    @Override
    public List<NhManageDepartment> findAllDepart() {
        String sql  = "select * from nh_manage_department";
        List<NhManageDepartment> list= new ArrayList<>();
        try {
            list = qr.query(sql,new BeanListHandler<>(NhManageDepartment.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
