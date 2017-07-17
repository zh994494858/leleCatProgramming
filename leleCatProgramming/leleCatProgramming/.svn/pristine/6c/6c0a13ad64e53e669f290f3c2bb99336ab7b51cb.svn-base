package cc.lelecat.email.writeEmail.controller;

import cc.lelecat.email.writeEmail.service.EmailDraftsToOutboxServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 王冀琛
 * on 2016/12/8.
 * 将草稿箱的发送出去
 */

@Controller
@RequestMapping("admin/email/fromDraftsToOutbox")
public class EmailDraftsToOutboxControllerImpl {

	@Resource
	private EmailDraftsToOutboxServiceImpl emailDraftsToOutboxService;

	@RequestMapping("moveAndInsert")
	public String OutboxFromDrafts(@RequestParam("message")Long emailId,
								   @RequestParam("receiverId")Integer receiverId,
								   HttpSession session){

		//获取到用户Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.emailDraftsToOutboxService.moveToOutboxFromDrafts (accountId,emailId,receiverId);

		return "redirect:/admin/email/drafts/list";

	}

}
