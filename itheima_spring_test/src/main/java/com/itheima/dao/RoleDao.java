package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleDao {

    List<Role> finAll();

    void save(Role role);

    List<Role> findRoleByUserId(Long id);
}
