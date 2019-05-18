package com.txr.spbdatamybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/4/8.
 */
@Controller
public class LoginController {


    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {
        if ("admin".equals(username) && "123456".equals(password)) {
            //登录成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser", username);
            return "redirect:/main";
        } else {
            map.put("msg", "账号或密码错误");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        //return "redirect:/main";
        //return "main";
        //return "redirect:/login";  //被当做 GET login 请求 报错：HttpRequestMethodNotSupportedException: Request method 'GET' not supported]
        //return "redirect:/login.html";
        return "login";
    }
}
