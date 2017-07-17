package cc.lelecat.email.inbox.controller;

import cc.lelecat.email.inbox.service.SeeAllInboxServiceImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/11/29.
 * 查看所有的收件箱邮件 返回所有收件箱邮件 以及收件箱邮件的数量
 */
@Controller
@RequestMapping("/admin/email/inbox")
public class SeeAllInboxController {

	@Resource
	private SeeAllInboxServiceImpl seeAllInboxService;

	@RequestMapping("list")
	public String listInbox(HttpServletRequest request, HttpSession session){

		//获取到用户Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		Map<Email,String> emails = new HashMap<Email,String> (0);

		emails = this.seeAllInboxService.findAllInboxEmailAndNatureState (accountId);

		Integer inboxCount = emails.keySet ().size ();

		session.setAttribute ("inboxCount",inboxCount);
		request.setAttribute ("emails",emails);

		return "/admin/mail/inbox";
	}

}
