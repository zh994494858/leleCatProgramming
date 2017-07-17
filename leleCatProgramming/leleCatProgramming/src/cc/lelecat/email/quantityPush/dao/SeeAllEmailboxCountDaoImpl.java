package cc.lelecat.email.quantityPush.dao;

import cc.lelecat.entity.email.Email;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 王冀琛
 * on 2016/12/8.
 * 查看所有邮箱中邮件的数量
 */

@Repository
public class SeeAllEmailboxCountDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public Integer inboxEmailCount(Integer accountId){

		//查找到该用户所有收件箱中的邮件
		String sql = "select *" +
				" from lelecat.email " +
				"where email.emailId in(" +
				"select emailbox.emailId " +
				"from lelecat.emailbox " +
				"where emailbox.accountId= "+accountId+
				" and emailbox.deleteState=0 " +
				"and emailbox.natureState in('已读','未读'))";
		List<Email> inboxEmails = (List<Email>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql).addEntity("email",Email.class).list ();

		return inboxEmails.size ();
	}

	//查询到该用户所有发件箱中邮件的数量
	public Integer outboxEmailCount(Integer accountId){

		String sql = "select * " +
				"from lelecat.emailbox " +
				"where emailbox.accountId= "+accountId+
				" and emailbox.deleteState = 0 " +
				"and emailbox.natureState = '已发'";
		List<Email> inboxCount = (List<Email>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql).list ();

		return inboxCount.size ();
	}

	//查询到该用户所有草稿箱中邮件的数量
	public Integer draftsEmailCount(Integer accountId){

		String sql = "select * " +
				"from lelecat.emailbox " +
				"where emailbox.accountId="+accountId+
				" and emailbox.deleteState = 0 " +
				"and emailbox.natureState='草稿'";
		List<Email> draftsCount = (List<Email>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql).list ();
		return draftsCount.size ();
	}

	//查询到该用户所有垃圾箱中邮件的数量
	public Integer dustbinEmailCount(Integer accountId){

		String sql = "select * " +
				"from lelecat.email " +
				"where email.emailId in(" +
				"select emailbox.emailId " +
				"from lelecat.emailbox " +
				"where emailbox.accountId = "+accountId+
				" and emailbox.deleteState = 1 )";
		List<Email> dustbinCount = (List<Email>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql).list ();

		return dustbinCount.size ();
	}

}
