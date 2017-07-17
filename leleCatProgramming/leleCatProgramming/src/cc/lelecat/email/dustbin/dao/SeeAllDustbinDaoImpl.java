package cc.lelecat.email.dustbin.dao;

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
 * on 2016/11/30.
 * 查看所有的垃圾邮件
 */

@Repository
public class SeeAllDustbinDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public Map<Email,String> findAllDustbinByAccountId(Integer accountId){

		String sql = "select * " +
				"from lelecat.email " +
				"where email.emailId in(" +
				"select emailbox.emailId " +
				"from lelecat.emailbox " +
				"where emailbox.accountId = "+accountId+
				" and emailbox.deleteState = 1 )";

		List<Email> tempEmails = (List<Email>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql).addEntity ("email",Email.class).list ();

		//查找到该用户所有垃圾箱中的邮件Id
		String sql1 = "select emailbox.emailId " +
				"from lelecat.emailbox " +
				"where emailbox.accountId = "+accountId+
				" and emailbox.deleteState = 1";
		List<BigInteger>emailIds = this.sessionFactory.getCurrentSession ().createSQLQuery (sql1).list ();

		Map<Email,String> emails = new HashMap<Email,String> (0);
		//如果垃圾箱中的邮件数量为0 则不执行以下操作
		if (emailIds.size () != 0){
			//查找每一个Id对应的邮件的状态（已读or未读）
			String sql2 ="select emailbox.natureState from emailbox where emailbox.emailId in (";
			for(int i = 0;i < emailIds.size ();i++){
				BigInteger param = emailIds.get (i);
				if (emailIds.size ()-1 != i){
					sql2+=param+",";
				}else {
					sql2 += param;
				}
			}
			sql2 +=")";
			List<String> natureStates = this.sessionFactory.getCurrentSession ().createSQLQuery (sql2).list ();

			//将邮件和它所在邮箱的状态定义为一个Map<Email,String>类型的 emails
			for (int i = 0; i < tempEmails.size ();i++){
				emails.put (tempEmails.get (i),natureStates.get (i));
			}
		}

		return emails;
	}
}
