package cc.lelecat.email.writeEmail.controller;

import cc.lelecat.email.writeEmail.service.AddEmailServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 添加邮件
 */

@Controller
@RequestMapping("admin/email/writeEmail")
public class AddEmailControllerImpl {

	@Resource
	private AddEmailServiceImpl addEmailService;

	@RequestMapping("addToDrafts")
	public String addToDrafts(@RequestParam(value = "emailTheme",required = false)String emailTheme,
							  @RequestParam(value = "emailContent",required = false)String emailContent,
							  @RequestParam(value = "receiver",required = false)String accountName,
							  @RequestParam(value = "emailId",required = false)String emailId,
							  HttpSession session){

		//获取到用户Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.addEmailService.addEmailToDrafts (emailId,accountName,emailTheme,emailContent,accountId);

		return "/admin/mail/writeLatter";
	}

	@RequestMapping("addToOutbox")
	public String addToOutbox(@RequestParam(value = "emailTheme",required = false)String emailTheme,
							  @RequestParam(value = "emailContent",required = false)String emailContent,
							  @RequestParam(value = "receiver",required = false)String accountName,
							  @RequestParam(value = "emailId",required = false)String emailId,
							  HttpSession session){

		//获取到用户Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.addEmailService.addEmailToOutbox (emailId,accountName,emailTheme,emailContent,accountId);

		return "/admin/mail/writeLatter";
	}

}
