package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        //new BeanPropertyRowMapper：将数据库查询结果转换为java对象
        List<User> query = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
//        List<User> query = jdbcTemplate.query(
//                "select u.*,r.roleName from sys_user u\n" +
//                        "join sys_user_role ur\n" +
//                        "on u.id=ur.userId\n" +
//                        "join sys_role r\n" +
//                        "on ur.roleId=r.id",
//                new BeanPropertyRowMapper<User>(User.class));
        return query;
    }

    //添加用户（sys_user）(id是自动生成的)
    public Long save(final User user) {
        //1、创建PreparedStatementCreator
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //使用原始jdbc完成有个PreparedStatement的组建
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());
                return preparedStatement;
            }
        };
        //2、创建KeyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        //3、使用底层方式执行SQL语句并返回自动生成的id
        jdbcTemplate.update(creator,keyHolder);

        //4、获得生成的主键
        Long userId = keyHolder.getKey().longValue();


        //下面的方法得不到自动生成的userId
//        jdbcTemplate.update("insert into sys_user values(?,?,?,?,?)",null,user.getUsername(),user.getEmail(),user.getPassword(),user.getPhoneNum());

        return userId;//返回当前插入数据的用户id
    }

    //给关系表添加数据（和上面一起用）
    public void saveUserRoleRel(Long userId, Long[] roleIds) {
        for (Long roleId:roleIds) {
            jdbcTemplate.update("insert into sys_user_role values(?,?)",userId,roleId);
        }
    }
}
