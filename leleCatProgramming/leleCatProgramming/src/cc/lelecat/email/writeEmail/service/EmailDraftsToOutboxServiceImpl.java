package cc.lelecat.email.writeEmail.service;

import cc.lelecat.email.writeEmail.dao.EmailDraftsToOutboxDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 王冀琛
 * on 2016/12/8.
 * 将草稿箱的发送出去
 */

@Service
@Transactional(readOnly = false)
public class EmailDraftsToOutboxServiceImpl {

	@Resource
	private EmailDraftsToOutboxDaoImpl emailDraftsToOutboxDao;

	public void moveToOutboxFromDrafts(Integer accountId,Long emailId,Integer receiverId){

		this.emailDraftsToOutboxDao.changeToOutboxFromDraftsByEmailboxId (accountId,emailId,receiverId);
	}

}
