package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.MybatisUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlSession sqlSession;

    //查询所有用户信息
    public List<User> findAll() throws IOException {
        //new BeanPropertyRowMapper：将数据库查询结果转换为java对象

        //mybatis方式
//        List<User> query = sqlSession.selectList("userMapper.findAll");

        //jdbc模板方式
        List<User> query = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
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
//            Map<String,Object> map = new HashMap<String, Object>();
//            map.put("userId",userId);
//            map.put("roleId",roleId);
//            sqlSession.insert("userMapper.saveUserRoleRel",map);
        }
    }

    //删除指定id的关系表信息
    public void delUserRoleRel(Long userId) {
        jdbcTemplate.update("delete from sys_user_role where userId=?",userId);
    }

    //删除指定id的用户信息
    public void del(Long userId) {
        jdbcTemplate.update("delete from sys_user where id=?",userId);
    }

    //根据指定username和password查询用户
    public User findByUsernameAndPassword(String username, String password) throws EmptyResultDataAccessException {
        User user = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?", new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }
}
