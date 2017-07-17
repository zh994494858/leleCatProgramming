package cc.lelecat.email.inbox.service;

import cc.lelecat.email.inbox.dao.SeeAllUnreadEmailDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 王冀琛
 * on 2016/12/6.
 * 查找所有在收件箱的未读的邮件的数量
 */
@Service
@Transactional(readOnly = true)
public class SeeAllUnreadEmailServiceImpl {

	@Resource
	private SeeAllUnreadEmailDaoImpl seeAllUnreadEmailDao;

	public Integer allUnreadEmailsCount(Integer accountId){

		return this.seeAllUnreadEmailDao.findAllUnreadEmailFromInbox (accountId);
	}

}
