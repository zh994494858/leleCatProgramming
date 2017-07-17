
package cc.lelecat.email.mailDetail.controller;

import cc.lelecat.email.mailDetail.service.SeeDetailServiceImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 王冀琛
 * on 2016/12/3.
 * 查看邮件内容
 */

@Controller
@RequestMapping("/admin/email/seeDetail")
public class SeeDetailControllerImpl {

	@Resource
	private SeeDetailServiceImpl seeDetailService;

	@RequestMapping(value = "detail",method = RequestMethod.GET)
	public String seeEmailDetail(@RequestParam("emailId")Long emailId,
								 HttpServletRequest request){

		Email email =this.seeDetailService.seeEmailDetail (emailId);
		String detailType = (String)request.getParameter("detailType");

		request.setAttribute ("detailType",detailType);
		request.setAttribute ("email",email);

		return "/admin/mail/mailDetail";
	}

	@RequestMapping(value = "draftsDetail",method = RequestMethod.GET)
	public String seeDraftsDetail(@RequestParam("emailId") Long emailId,HttpServletRequest request){
		Email email = this.seeDetailService.seeEmailDetail (emailId);

		request.setAttribute ("email",email);

		return "/admin/mail/draftsEmailDetail";

	}

}
