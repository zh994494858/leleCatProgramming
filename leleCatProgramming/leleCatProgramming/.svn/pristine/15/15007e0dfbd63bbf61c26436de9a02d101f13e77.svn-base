package cc.lelecat.email.drafts.dao;

import cc.lelecat.entity.email.Email;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 查看所有的草稿
 */

@Repository
public class SeeAllDraftsDaoImpl {

	@Resource
	private SessionFactory sessionFactory;


	//查询到该用户的所有是草稿状态的邮件Id
	public List<Email> findAllDraftsByAccountId(Integer accountId){

		String sql = "select *" +
				" from lelecat.email " +
				"where email.emailId in(" +
				"select emailbox.emailId " +
				"from lelecat.emailbox " +
				"where emailbox.accountId= "+accountId+
				" and emailbox.deleteState=0 " +
				"and emailbox.natureState ='草稿')";

		List<Email>emails = (List<Email>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql).addEntity("email",Email.class).list ();

		return emails;
	}

}
