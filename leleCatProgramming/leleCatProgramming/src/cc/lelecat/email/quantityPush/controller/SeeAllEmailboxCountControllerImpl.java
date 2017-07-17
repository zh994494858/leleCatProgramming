package cc.lelecat.email.quantityPush.controller;

import cc.lelecat.email.quantityPush.service.SeeAllEmailboxCountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 王冀琛
 * on 2016/12/8.
 * 查看所有邮箱中邮件的数量
 */

@Controller
@RequestMapping("admin/email/emailbox")
public class SeeAllEmailboxCountControllerImpl {

	@Resource
	private SeeAllEmailboxCountServiceImpl seeAllEmailboxCountService;

	@ResponseBody
	@RequestMapping(value = "EmailCount",method = RequestMethod.POST)
	public List<Integer> seeAllEmailBoxCount(HttpSession session){

		//获取到用户的ID
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		List<Integer> emailCounts = new LinkedList<Integer> ();

		Integer inboxEmailCount = this.seeAllEmailboxCountService.selectAllInboxEmailCount (accountId);

		Integer outboxEmailCount = this.seeAllEmailboxCountService.selectAllOutboxEmailCount (accountId);

		Integer draftsEmailCount = this.seeAllEmailboxCountService.selectAllDraftsEmailCount (accountId);

		Integer dustbinEmailCount = this.seeAllEmailboxCountService.selectAllDustbinEmailCount (accountId);

		emailCounts.add (inboxEmailCount);
		emailCounts.add (outboxEmailCount);
		emailCounts.add (draftsEmailCount);
		emailCounts.add (dustbinEmailCount);

		return emailCounts;
	}

}
