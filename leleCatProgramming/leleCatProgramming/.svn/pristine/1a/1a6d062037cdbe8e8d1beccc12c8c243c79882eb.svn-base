package cc.lelecat.email.inbox.controller;

import cc.lelecat.email.inbox.service.SeeAllUnreadEmailServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/12/6.
 * 查询指定用户的所有收件箱中未读邮件的数量
 */
@Controller
@RequestMapping("/admin/email/inboxEmails")
public class SeeAllUnreadEmailControllerImpl {

	@Resource
	private SeeAllUnreadEmailServiceImpl seeAllUnreadEmailService;

	@ResponseBody
	@RequestMapping(value = "count",method = RequestMethod.POST)
	public Integer findUnreadEmailCountFromInbox(@RequestBody Map<String,String> accountId, HttpSession session){

		Integer id = 0;

		for (Map.Entry<String,String>entry:accountId.entrySet ()){
			id = Integer.valueOf (Integer.parseInt (entry.getValue ()));
			if (id != null){
				break;
			}
		}

		session.setAttribute ("accountId",id);

		Integer unreadEmailsCountFromInbox = this.seeAllUnreadEmailService.allUnreadEmailsCount (id);

		return unreadEmailsCountFromInbox;
	}

}
