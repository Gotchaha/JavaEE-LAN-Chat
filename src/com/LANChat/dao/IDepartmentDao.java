package com.LANChat.dao;

import com.LANChat.entity.NhManageDepartment;

import java.util.List;

public interface IDepartmentDao {
    List<NhManageDepartment> findAllDepart();
}
