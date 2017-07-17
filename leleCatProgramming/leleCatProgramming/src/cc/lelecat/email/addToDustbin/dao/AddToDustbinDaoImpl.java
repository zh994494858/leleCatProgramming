package cc.lelecat.email.addToDustbin.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * Created by 王冀琛
 * on 2016/12/1.
 * 添加到垃圾箱的Dao
 */
@Repository
public class AddToDustbinDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public void updateDeleteState(BigInteger[]params,Integer accountId){

		//将页面传过来的emailId 通过数组传递过来 ，再循环拼接到更新的SQL语句中
		String sql = "UPDATE lelecat.emailbox " +
				"SET emailbox.deleteState=1 " +
				"WHERE emailbox.accountId = "+accountId+
				" and emailbox.emailId IN(";
				for(int i = 0;i < params.length;i++){
					BigInteger param = params[i];
					if (params.length-1 != i){
						sql+=param+",";
					}else {
						sql += param;
					}
				}
				sql +=")";

		this.sessionFactory.getCurrentSession ().createSQLQuery (sql).executeUpdate ();

	}

}
