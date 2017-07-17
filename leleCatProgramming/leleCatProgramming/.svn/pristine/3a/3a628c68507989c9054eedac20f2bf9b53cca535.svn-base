package cc.lelecat.email.drafts.controller;

import cc.lelecat.email.drafts.service.SeeAllDraftsServiceImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 查看所有的草稿 返回草稿箱邮件的数量，和所有的草稿
 */

@Controller
@RequestMapping("/admin/email/drafts")
public class SeeAllDraftsControllerImpl {

	@Resource
	private SeeAllDraftsServiceImpl seeAllDraftsService;

	@RequestMapping("list")
	public String listDrafts(HttpServletRequest request,
							 HttpSession session,
							 Model model){
		//获取到用户的Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		Map<Email,String> emails= new LinkedHashMap<>(0);

		emails = this.seeAllDraftsService.seeAllDraftsEmail (accountId);

		Integer draftsCount = emails.size ();

		request.setAttribute ("emails",emails);
		session.setAttribute ("draftsCount",draftsCount);

		return "/admin/mail/drafts";

	}

}
