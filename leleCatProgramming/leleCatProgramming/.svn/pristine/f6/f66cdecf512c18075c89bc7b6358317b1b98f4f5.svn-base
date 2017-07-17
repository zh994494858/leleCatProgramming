package cc.lelecat.email.outbox.dao;

import cc.lelecat.entity.email.Email;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 查看所有的发件箱邮件
 */

@Repository
public class SeeAllOutboxDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public List<Email> findAllOutboxByAccountId(Integer accountId){

		String sql = "select * " +
				"from lelecat.email " +
				"where email.emailId in(" +
				"select emailBox.emailId " +
				"from lelecat.emailbox " +
				"where emailbox.accountId = "+accountId +
				" and emailbox.deleteState = 0 " +
				"and emailbox.natureState ='已发')";

		List<Email>emails = (List<Email>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql).addEntity("email",Email.class).list ();

		return emails;
	}


}
