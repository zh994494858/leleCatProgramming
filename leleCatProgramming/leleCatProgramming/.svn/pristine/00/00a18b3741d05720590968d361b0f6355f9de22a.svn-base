package cc.lelecat.email.quantityPush.service;

import cc.lelecat.email.quantityPush.dao.SeeAllEmailboxCountDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 王冀琛
 * on 2016/12/8.
 * 查看所有邮箱中邮件的数量
 */

@Service
@Transactional(readOnly = true)
public class SeeAllEmailboxCountServiceImpl {

	@Resource
	private SeeAllEmailboxCountDaoImpl seeAllEmailboxCountDao;

	public Integer selectAllInboxEmailCount(Integer accountId){
		return this.seeAllEmailboxCountDao.inboxEmailCount (accountId);
	}

	public Integer selectAllOutboxEmailCount(Integer accountId){
		return this.seeAllEmailboxCountDao.outboxEmailCount (accountId);
	}

	public Integer selectAllDustbinEmailCount(Integer accountId){
		return this.seeAllEmailboxCountDao.dustbinEmailCount (accountId);
	}

	public Integer selectAllDraftsEmailCount(Integer accountId){
		return this.seeAllEmailboxCountDao.draftsEmailCount (accountId);
	}

}
