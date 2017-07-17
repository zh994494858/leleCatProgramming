package cc.lelecat.email.inbox.dao;

import cc.lelecat.entity.email.Email;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/11/29.
 * 查看所有的收件箱邮件
 */

@Repository
public class SeeAllInboxDaoImpl{

	@Resource
	private SessionFactory sessionFactory;

	//查询到所有该用户的邮件id
	public List<Email> findAllInboxEmailByAccountId(Integer accountId){

		//查找到该用户所有收件箱中的邮件
		String sql = "select *" +
			  " from lelecat.email " +
			  "where email.emailId in(" +
				"select emailbox.emailId " +
				"from lelecat.emailbox " +
				"where emailbox.accountId= "+accountId+
				" and emailbox.deleteState=0 " +
				"and emailbox.natureState in('已读','未读')) " +
				"ORDER BY emailId ASC ";
		List<Email> emails = (List<Email>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql).addEntity("email",Email.class).list ();

		return emails;
	}

	public List<String> getAllInboxEmailsNatureState(Integer accountId){

		String sql = "select emailbox.natureState " +
				"from emailbox " +
				"where emailbox.accountId="+accountId+" " +
				"and emailbox.deleteState=0 " +
				"and emailbox.natureState in('已读','未读') " +
				"ORDER BY emailId ASC ";

		List<String> natureStates = this.sessionFactory.getCurrentSession ().createSQLQuery (sql).list ();

		return natureStates;
	}
}
