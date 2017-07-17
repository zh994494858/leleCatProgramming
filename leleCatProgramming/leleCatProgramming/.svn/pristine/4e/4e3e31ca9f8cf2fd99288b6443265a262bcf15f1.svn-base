package cc.lelecat.customer.controller;


import cc.lelecat.account.normal.service.NormalAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

/**
 * 类名：RegisterController
 * 功能：实现注册系列
 * 作者：苗怀雨
 * 日期：2016-12-5 20:50:30
 */

@Controller
public class RegisterController {

    @Resource
   private NormalAccountService normalAccountService;

    @ResponseBody
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Map<String, String> registerUser(@RequestBody Map<String,String> usernameAndPassword){

        return this.normalAccountService.register (usernameAndPassword);
    }

    /*controller:用户点击注册，验证用户名和密码*/
    /*@RequestMapping("register")
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("passwordConfirmed") String passwordConfirmed,
            HttpServletRequest request,
            HttpSession session) {

        String[] info = this.normalAccountService.register(username,password,passwordConfirmed);
        request.setAttribute("info", info[1]);

        return "";
    }*/
}