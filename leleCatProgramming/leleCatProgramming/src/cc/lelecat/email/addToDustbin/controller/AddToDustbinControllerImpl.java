package cc.lelecat.email.addToDustbin.controller;

import cc.lelecat.email.addToDustbin.service.AddToDustbinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodType;
import java.math.BigInteger;

/**
 * Created by 王冀琛
 * on 2016/12/1.
 * 添加到垃圾箱的 controller 跳转到相应的页面
 */

@Controller
@RequestMapping(value = "/admin/email/moveToDustbin")
public class AddToDustbinControllerImpl {

	@Resource
	private AddToDustbinServiceImpl addToDustbinService;

	@RequestMapping(value = "InboxMove",method = RequestMethod.POST)
	public String changeToDustbin(@RequestParam(value = "message",required = false)String message,
								  HttpSession session){
		//获取到用户的Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.addToDustbinService.moveToDustbin (message,accountId);

		return "redirect:/admin/email/inbox/list";
	}

	@RequestMapping(value = "Outbox",method = RequestMethod.POST)
	public String OutboxChengeToDusbin(@RequestParam(value = "message",required = false)String message,
									   HttpSession session){
		//获取到用户的Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.addToDustbinService.moveToDustbin (message,accountId);

		return "redirect:/admin/email/outbox/list";
	}

	@RequestMapping(value = "Drafts",method = RequestMethod.POST)
	public String  draftsChangeToDustbin(@RequestParam(value = "message",required = false)String message,
										 HttpSession session){
		//获取到用户的Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.addToDustbinService.moveToDustbin (message,accountId);

		return "redirect:/admin/email/drafts/list";
	}

	@RequestMapping(value = "Dustbin",method = RequestMethod.POST)
	public String  sustbinChangeToDustbin(@RequestParam(value = "message",required = false)String message,
										  HttpSession session){
		//获取到用户的Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.addToDustbinService.moveToDustbin (message,accountId);

		return "redirect:/admin/email/dustbin/list";
	}

}