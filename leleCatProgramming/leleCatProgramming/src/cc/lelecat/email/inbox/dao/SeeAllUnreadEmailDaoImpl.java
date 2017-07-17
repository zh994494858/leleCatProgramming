package cc.lelecat.email.inbox.dao;

import cc.lelecat.entity.email.Email;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 王冀琛
 * on 2016/12/6.
 * 查找收件箱里所有未读邮件，要在首页的显示并提醒
 */

@Repository
public class SeeAllUnreadEmailDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public Integer findAllUnreadEmailFromInbox(Integer accountId){

		String sql = "select *" +
				" from lelecat.email " +
				"where email.emailId in(" +
				"select emailbox.emailId " +
				"from lelecat.emailbox " +
				"where emailbox.accountId= "+accountId+
				" and emailbox.deleteState=0 " +
				"and emailbox.natureState='未读')";

		List<Email> allUnreadEmailsInInbox = this.sessionFactory.getCurrentSession ().createSQLQuery (sql).list ();

		return allUnreadEmailsInInbox.size ();

	}

}
