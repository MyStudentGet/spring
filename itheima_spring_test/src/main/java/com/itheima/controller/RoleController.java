package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    //获得角色管理列表
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();

        //获得用户列表
        List<Role> roleList = roleService.list();

        //设置模型
        modelAndView.addObject("roleList",roleList);

        //设置视图(返回到哪个资源)
        modelAndView.setViewName("role-list");

        return modelAndView;
    }

    //添加角色
    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);

        return "redirect:/role/list";
    }

}
