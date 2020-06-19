package com.LANChat.service;

import com.LANChat.dao.DepartmentDao;
import com.LANChat.dao.IDepartmentDao;
import com.LANChat.entity.NhManageDepartment;

import java.util.List;

public class DepartmentService implements IDepartmentService{
    IDepartmentDao departmentDao = new DepartmentDao();
    @Override
    public List<NhManageDepartment> findAllDepart() {
        return departmentDao.findAllDepart();
    }
}
