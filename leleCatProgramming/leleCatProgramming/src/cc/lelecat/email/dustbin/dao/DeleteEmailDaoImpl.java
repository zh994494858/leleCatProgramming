package cc.lelecat.email.dustbin.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * Created by 王冀琛
 * on 2016/12/2.
 *
 * 将邮件彻底删除
 */

@Repository
public class DeleteEmailDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public void deleteByEmailIds(Integer accountId, BigInteger []emailIds){

		//此处的accountId先写死 暂定为1 方便测试
		String sql = "delete from lelecat.emailbox " +
				"where emailbox.accountId = "+accountId+
				" AND emailbox.emailId IN(";
		for(int i = 0;i < emailIds.length;i++){
			BigInteger param = emailIds[i];
			if (emailIds.length-1 != i){
				sql+=param+",";
			}else {
				sql += param;
			}
		}
		sql +=")";

		this.sessionFactory.getCurrentSession ().createSQLQuery (sql).executeUpdate ();
	}

}
