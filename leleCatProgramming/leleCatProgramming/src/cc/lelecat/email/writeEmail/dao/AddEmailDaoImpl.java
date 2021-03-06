package cc.lelecat.email.writeEmail.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 添加邮件
 */

@Repository
public class AddEmailDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public Integer getReceiverId(String accountName){
		//根据jsp页面传过来的 收件人姓名：accountName 找到收件人的Id:receiverId
		String sql1 = "select lelecat.worker_account.id " +
				"from lelecat.worker_account " +
				"where worker_account.name = '" + accountName + "'";
		Integer receiverId = -1;
		if(!this.sessionFactory.getCurrentSession ().createSQLQuery (sql1).list ().isEmpty ()) {
			receiverId = (Integer) this.sessionFactory.getCurrentSession ().createSQLQuery (sql1).list ().get (0);
		}

		return receiverId;
	}

	//将邮件插入到邮件表中，返回新插入的邮件的Id:emailId
	public BigInteger getJustInsertedEmailId(String accountName,String time,String emailTheme,String emailContent,Integer accountId){

		//根据jsp页面传过来的 收件人姓名：accountName 找到收件人的Id:receiverId
		Integer receiverId = this.getReceiverId (accountName);
		//如果查不到 返回-1
		if(receiverId.intValue ()<0) {
			return BigInteger.valueOf (-1);
		}

			//将该 email 插入到email表中去
		String sql2 = "insert into email(emailContent,emailTheme,time,receiverId,senderId) " +
				"values('" + emailContent + "','" + emailTheme + "','" + time + "'," + receiverId + "," + accountId + ")";
		Integer successState1 = this.sessionFactory.getCurrentSession ().createSQLQuery (sql2).executeUpdate ();

		//找到刚刚插入到数据库中的那封email的id
		String sql3 = "select emailId " +
				"from email " +
				"where emailTheme = '" + emailTheme + "' " +
				"and emailContent = '" + emailContent + "' " +
				"and time = '" + time + "' " +
				"and receiverId = " + receiverId + " " +
				"and senderId = " + accountId;
		BigInteger emailId = (BigInteger) this.sessionFactory.getCurrentSession ().createSQLQuery (sql3).list ().get (0);

		return emailId;
	}

	//根据 getJustInsertedEmailId 返回的刚刚插入的 emailId 将该邮件与邮箱（草稿箱）关联
	public void insertEmailToDrafts(String accountName,String time,String emailTheme,String emailContent,Integer accountId){

		BigInteger emailId = this.getJustInsertedEmailId (accountName, time, emailTheme, emailContent, accountId);
		//如果emailId等于-1，返回
		if(emailId.intValue ()<0) {
			return;
		}

		String sql = "insert into emailbox(deleteState,natureState,accountId,emailId) " +
				"values(0,'草稿'," + accountId + "," + emailId + ")";
		Integer successState2 = this.sessionFactory.getCurrentSession ().createSQLQuery (sql).executeUpdate ();
	}

	//根据 getJustInsertedEmailId 返回的刚刚插入的 emailId 将该邮件与邮箱（发件箱）关联，并且将该邮件与收件人的收件箱相关联
	public void insertEmailToOutbox(String accountName,String time,String emailTheme,String emailContent,Integer accountId){

		BigInteger emailId = this.getJustInsertedEmailId (accountName,time,emailTheme,emailContent,accountId);
		//如果emailId等于-1，返回
		if(emailId.intValue ()<0) {
			return;
		}

		String sql = "insert into emailbox(deleteState,natureState,accountId,emailId) " +
				"values(0,'已发',"+accountId+","+emailId+")";
		Integer successState1 = this.sessionFactory.getCurrentSession ().createSQLQuery (sql).executeUpdate ();

		//收件人的邮箱也应该多一条记录
		Integer receiverId = this.getReceiverId (accountName);

		//将该邮件插入到收件人所在邮箱的收件箱中
		String sql2 = "insert into emailbox(deleteState,natureState,accountId,emailId)"+
				"values(0,'未读',"+receiverId+","+emailId+")";
		Integer successState2 = this.sessionFactory.getCurrentSession ().createSQLQuery (sql2).executeUpdate ();
	}

	//删除原先草稿箱中的内容
	public void deleteOriginalById(Integer accountId,BigInteger emailId){
		String sql = "delete from emailbox where emailbox.emailId="+emailId+" and emailbox.accountId="+accountId;
		Integer success = this.sessionFactory.getCurrentSession ().createSQLQuery (sql).executeUpdate ();
		return;
	}

}
