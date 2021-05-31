package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    //查询所有用户的信息及其相应的角色信息
    public List<User> list() {
        //1、直接多表联查
//        List<User> userList = userDao.findAll();
        //2、两张表分开查
        List<User> userList = userDao.findAll();
        for (User user:userList) {
            //获得user的id
            Long id = user.getId();
            //将id作为参数查询当前user的角色
            List<Role> roles = roleDao.findRoleByUserId(id);

            //将角色封装到user中
            user.setRoles(roles);
        }


        return userList;
    }

    //添加用户（sys_user）
    public void save(User user, Long[] roleIds) {
        //第一步 向sys_user表中存储数据
        Long userId = userDao.save(user);

        //第二步 向sys_user_role关系表中存储多条数据
        userDao.saveUserRoleRel(userId,roleIds);
    }
}
