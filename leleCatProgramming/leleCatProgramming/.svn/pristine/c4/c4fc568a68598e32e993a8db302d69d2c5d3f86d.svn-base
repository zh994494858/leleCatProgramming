package cc.lelecat.email.outbox.service;

import cc.lelecat.email.outbox.dao.SeeAllOutboxDaoImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 查看所有的发件箱邮件
 */
@Service
@Transactional(readOnly = true)
public class SeeAllOutboxServiceImpl {

	@Resource
	private SeeAllOutboxDaoImpl seeAllOutboxDao;

	public Map<Email,String> findAllOutboxEmail(Integer accountId){
		Map<Email,String> emails = new LinkedHashMap<Email,String>(0);
		List<Email> oldEmails = seeAllOutboxDao.findAllOutboxByAccountId(accountId);

		for (int i = 0;i<oldEmails.size ();i++){
			emails.put (oldEmails.get (i),null);
		}

		return emails;
	}

}
