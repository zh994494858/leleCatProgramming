package cc.lelecat.customer.controller;

import cc.lelecat.account.normal.service.NormalAccountService;
import cc.lelecat.entity.account.customer.NormalAccount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 类名：LoginController
 * 功能：实现前台客户账户登录验证身份系列
 * 作者：苗怀雨
 * 日期：2016-12-6 09:17:54
 */

@Controller
@RequestMapping("login")
public class NormalLoginController {

    @Resource
    private NormalAccountService normalAccountService;

    /*controller:用户点击登录，验证用户名和密码*/
    @ResponseBody
    @RequestMapping(value = "check",method = RequestMethod.POST)
    public Boolean checkUser(
            @RequestBody Map<String,String> loginInfo,
            HttpSession session) {

            String username = loginInfo.get("username");
            String password = loginInfo.get("password");

            NormalAccount normalAccount = this.normalAccountService.login(username,password);
            if(normalAccount != null){
                session.setAttribute("customer", normalAccount);
                return true;    //成功登录页面
            }else{
                 //request.setAttribute("error","账号和密码不匹配");
                return false;   //失败登录页面
            }
    }
}