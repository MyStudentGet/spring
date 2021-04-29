package itheima.dao.impl;

import com.itheima.dao.Tbl_UserDao;
import org.springframework.jdbc.core.JdbcTemplate;

//数据处理层
public class Tbl_UserDaoImpl implements Tbl_UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //转钱的方法
    public void out(String outMan,double money){
        jdbcTemplate.update("update tbl_User set balance = balance-? where username = ?",money,outMan);
    }
    //收钱的方法
    public void in(String inMan,double money){
        jdbcTemplate.update("update tbl_User set balance = balance+? where username = ?",money,inMan);
    }
}
