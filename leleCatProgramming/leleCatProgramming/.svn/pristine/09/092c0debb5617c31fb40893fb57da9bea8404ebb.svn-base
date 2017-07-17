package cc.lelecat.email.writeEmail.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by 王冀琛
 * on 2016/12/8.
 * 将草稿箱的发送出去
 */

@Repository
public class EmailDraftsToOutboxDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public void changeToOutboxFromDraftsByEmailboxId(Integer accountId,Long emailId,Integer receiverId){

		String sql = "update emailbox " +
				"set natureState='已发' " +
				"where emailbox.accountId="+accountId+
				" and emailbox.emailId="+emailId;

		this.sessionFactory.getCurrentSession ().createSQLQuery (sql).executeUpdate ();

		String sql1 = "insert into emailbox(deleteState,natureState,accountId,emailId) " +
				"values(0,'未读',"+receiverId+","+emailId+")";

		this.sessionFactory.getCurrentSession ().createSQLQuery (sql1).executeUpdate ();

	}

}
