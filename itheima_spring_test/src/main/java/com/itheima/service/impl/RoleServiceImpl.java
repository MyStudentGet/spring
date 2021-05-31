package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    public List<Role> list() {
        List<Role> roleList = roleDao.finAll();
        return roleList;
    }

    public void save(Role role) {
        roleDao.save(role);
    }
}
