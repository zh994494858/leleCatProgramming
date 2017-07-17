package cc.lelecat.email.dustbin.controller;

import cc.lelecat.email.dustbin.service.MarkAsReadServiceImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by 王冀琛
 *on 2016/12/2.
 * 将垃圾箱和收件箱中的邮件标为已读
 */

@Controller
@RequestMapping("/admin/email/mark")
public class MarkAsReadControllerImpl {

	@Resource
	private MarkAsReadServiceImpl markAsReadService;

	@ResponseBody
	@RequestMapping(value = "read",method = RequestMethod.POST)
	public void markAsReaded(@RequestBody Map<String,String>emails,
										  HttpSession session){
		//获取到用户的Id
		Integer accountId = Integer.valueOf (Integer.parseInt ((session.getAttribute ("accountId")).toString ()));

		this.markAsReadService.updateTheReadState (accountId,emails);

		return;
	}
}
