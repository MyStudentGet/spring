package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.Tbl_User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 页面跳转：
 * /quick：  返回值类型：String            只有视图                页面结果：Success!
 * /quick2： 返回值类型：ModelAndView      模板视图                页面结果：Success!itcast
 * /quick3： 返回值类型：ModelAndView      模板视图对象由参数创建     页面结果：Success!itheima
 * /quick4： 返回值类型：String            模板与视图分离           页面结果：Success!模板和视图分开
 * /quick5： 返回值类型：String            req对象由参数创建        页面结果：Success!请求对象          不常用
 *
 * 回写数据：
 * /quick6： 返回值类型：void              Web方式回写数据          页面结果：hello itcast
 * /quick7： 返回值类型：String            直接返回回写数据          页面结果：hello itheima
 * /quick8： 返回值类型：String            使用json转换工具回写数据   页面结果：json格式字符串
 * /quick9： 返回值类型：对象类型            xml中配置处理器映射器     页面结果：json格式字符串
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //映射关系用注解代替
    //请求方式必须是get才能访问
    //属性params={"ss"}:表示请求参数必须有ss,params={"ss!100"}表示ss的值不能是100
    @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"})
    public String save(){
        System.out.println("Controller save running....");

        //return "redirect:/success.jsp";重定向页面（如果不加是默认转发（forward）（地址不改变））
        return "success";
    }

    //返回ModelAndView方式
    @RequestMapping(value = "/quick2")
    public ModelAndView save2(){
        /*
            Model：模型  作用封装数据
            View： 视图  作用展示数据

        */
        ModelAndView modelAndView = new ModelAndView();

        // 设置视图名（jsp名）
        modelAndView.setViewName("success");

        //设置模型数据（jsp中用el表达式显示（值可以是集合、对象都可以））
        modelAndView.addObject("username","itcast");
        return modelAndView;
    }

    //返回ModelAndView参数方式(参数对象（modelAndView）由Spring容器提供)
    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView){

        modelAndView.setViewName("success");
        modelAndView.addObject("username","itheima");

        return modelAndView;
    }

    //返回模板（Model）和视图（jsp）分开方式
    @RequestMapping(value = "/quick4")
    public String save4(Model model){

        model.addAttribute("username","模板和视图分开");

        return "success";
    }

    //返回request和视图（jsp）分开方式
    @RequestMapping(value = "/quick5")
    public String save5(HttpServletRequest request){

        //未封装的Model，作用和Model相同
        request.setAttribute("username","请求对象");

        return "success";
    }

    //回写数据（普通Web方式）
    @RequestMapping(value = "/quick6")
    public void save6(HttpServletResponse response) throws IOException {

        //回写数据（前端发ajax访问）
        response.getWriter().print("hello itcast");
    }

    //注解回写数据方式
    @RequestMapping(value = "/quick7")
    @ResponseBody  //告诉SpringMVC这是页面回写不是跳转视图
    public String save7(HttpServletResponse response) throws IOException {

        return "hello itheima";
    }

    //使用json转换工具（返回user对象）
    @RequestMapping(value = "/quick8")
    @ResponseBody  //告诉SpringMVC这是页面回写不是跳转视图
    public String save8() throws IOException {
        Tbl_User tbl_user =new Tbl_User();
        tbl_user.setUsername("lisi");
        tbl_user.setPassword("123456");

        //使用json转换工具(需要导三个包jackson-core、jackson-databind、jackson-annotations)
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(tbl_user);

        return json;
    }

    //使用json转换工具（返回user对象）
    @RequestMapping(value = "/quick9")
    @ResponseBody  //告诉SpringMVC这是页面回写不是跳转视图
    //期望SpringMVC自动将User转换成json格式的字符串
    public Tbl_User save9() throws IOException {
        Tbl_User tbl_user =new Tbl_User();
        tbl_user.setUsername("lisi2");
        tbl_user.setPassword("123456");

        //在Spring-mvc.xml中配置处理器映射器

        return tbl_user;
    }
}
