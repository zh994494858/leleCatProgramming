package cc.lelecat.email.dustbin.controller;

import cc.lelecat.email.dustbin.service.DeleteEmailServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 王冀琛
 * on 2016/12/2.
 * 将邮件彻底删除 并且跳回垃圾箱列表
 */

@Controller
@RequestMapping("/admin/email/dustbin/delete")
public class DeleteEmailControllerImpl {

	@Resource
	private DeleteEmailServiceImpl deleteEmailService;

	@RequestMapping(value = "downright",method = RequestMethod.POST)
	public String deleteCheckedEmails(@RequestParam(value = "message",required = false)String message,
									  HttpSession session){
		//获取到用户的Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.deleteEmailService.deleteEmail (accountId,message);

		return "redirect:/admin/email/dustbin/list";
	}

}
