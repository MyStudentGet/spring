package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //用户管理列表展示
    @RequestMapping("/list")
    public ModelAndView list() throws IOException {
        List<User> userList = userService.list();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");

        return modelAndView;
    }

    //新建用户时的用户角色展示
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){

        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");

        return modelAndView;
    }

    //得到表单传过来的值添加用户
    @RequestMapping("/save")
    public String save(User user,Long[] roleIds){

        userService.save(user,roleIds);
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");

        return "redirect:/user/list";
    }

    //得到表单传过来的值删除用户
    //Restful风格的参数（设计模式）要使用注解@PathVariable接收参数
    @RequestMapping("/del/{userId}")
    public String del(@PathVariable("userId") Long userId){

        userService.del(userId);


        return "redirect:/user/list";
    }

    //得到表单传过来的值删除用户

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){

        //查询指定name和password的user对象
        User user = userService.login(username,password);
        if (user!=null){
            //登录成功  将user存储到session
            session.setAttribute("user",user);
            //重定向到主页
            return "redirect:/index.jsp";
        }

        return "redirect:/login.jsp";
    }
}
