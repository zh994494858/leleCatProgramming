package cc.lelecat.email.mailDetail.dao;

import cc.lelecat.entity.email.Email;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by 王冀琛
 * on 2016/12/3.
 * 查看邮件内容
 */

@Repository
public class SeeDetailDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public Email findEmialDetailByEmailId(Long emailId){

		String hql = "from Email e" +
				"where e.emailId = "+emailId;

		Email email = this.sessionFactory.getCurrentSession ().get (Email.class,emailId);

		return email;
	}

}
