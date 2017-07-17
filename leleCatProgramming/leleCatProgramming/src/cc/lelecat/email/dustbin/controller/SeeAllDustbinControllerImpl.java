package cc.lelecat.email.dustbin.controller;

import cc.lelecat.email.dustbin.service.SeeAllDustbinServiceImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 查看所有的垃圾邮件 返回所有的垃圾邮件 以及垃圾邮件的数量
 */

@Controller
@RequestMapping("/admin/email/dustbin")
public class SeeAllDustbinControllerImpl {

	@Resource
	private SeeAllDustbinServiceImpl seeAllDustbinService;

	@RequestMapping("list")
	public String listDustbin(HttpServletRequest request,
							  HttpSession session){
		//获取到用户的Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		Map<Email,String> emails = new HashMap<Email,String> (0);

		emails = this.seeAllDustbinService.findAllDustbinEmail (accountId);

		Integer dustbinCount = emails.keySet ().size ();

		session.setAttribute ("dustbinCount",dustbinCount);
		request.setAttribute ("emails",emails);

		return "/admin/mail/dustbin";

	}

}
