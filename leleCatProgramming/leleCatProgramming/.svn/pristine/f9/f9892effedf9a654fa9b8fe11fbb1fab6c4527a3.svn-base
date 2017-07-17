package cc.lelecat.email.outbox.controller;

import cc.lelecat.email.outbox.service.SeeAllOutboxServiceImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 查看所有的发件箱邮件 返回发件箱所有的邮件 ，以及发件箱邮件的数量
 */

@Controller
@RequestMapping("/admin/email/outbox")
public class SeeAllOutboxControllerImpl {

	@Resource
	private SeeAllOutboxServiceImpl seeAllOutboxService;

	@RequestMapping("list")
	public String listOutbox(HttpServletRequest request,
							 HttpSession session,
							 Model model){
		//获取到用户Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		Map<Email,String> emails = new HashMap<Email,String>();

		emails = this.seeAllOutboxService.findAllOutboxEmail (accountId);

		Integer outboxCount = emails.keySet ().size ();

		session.setAttribute ("outboxCount",outboxCount);
		request.setAttribute ("emails",emails);

		return "/admin/mail/outbox";

	}

}
