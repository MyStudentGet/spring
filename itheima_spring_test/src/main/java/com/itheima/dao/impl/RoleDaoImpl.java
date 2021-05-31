package com.itheima.dao.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Role> finAll() {
        List<Role> roleList = jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    //向sys_role（角色）表中插入一条数据
    public void save(Role role) {
        jdbcTemplate.update("insert into sys_role values(?,?,?)",null,role.getRoleName(),role.getRoleDesc());
    }

    //根据userID查询相应的角色
    public List<Role> findRoleByUserId(Long id) {
        List<Role> roles = jdbcTemplate.query("select r.* from sys_user_role ur\n" +
                "join sys_role r\n" +
                "on ur.roleId=r.id\n" +
                "where ur.userId = ?",new BeanPropertyRowMapper<Role>(Role.class),id);

        return roles;
    }
}
