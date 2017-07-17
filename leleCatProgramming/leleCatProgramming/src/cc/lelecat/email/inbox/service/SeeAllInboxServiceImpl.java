package cc.lelecat.email.inbox.service;

import cc.lelecat.email.inbox.dao.SeeAllInboxDaoImpl;
import cc.lelecat.entity.email.Email;
import cc.lelecat.framework.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by 王冀琛
 * on 2016/11/29.
 * 查看所有的收件箱邮件，以及在各个邮箱中邮件的数量
 */

@Service
@Transactional(readOnly = true)
public class SeeAllInboxServiceImpl {

	@Resource
	private SeeAllInboxDaoImpl seeAllInboxDao;

	public Map<Email,String> findAllInboxEmailAndNatureState(Integer accountId){

		Map<Email,String> emailsAndNatureState = new LinkedHashMap<Email,String> (0);
		List<Email> emails = new LinkedList<Email> ();
		List<String> natureStates = new LinkedList<String> ();

		emails = this.seeAllInboxDao.findAllInboxEmailByAccountId (accountId);
		natureStates = this.seeAllInboxDao.getAllInboxEmailsNatureState (accountId);

		if (emails.size () == natureStates.size ()){
			for (int i = 0;i<emails.size ();i++){
				emailsAndNatureState.put (emails.get (i),natureStates.get (i));
			}
		}
		return emailsAndNatureState;
	}

}
