package cc.lelecat.entity.email;

import cc.lelecat.entity.account.worker.Account;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 王冀琛
 * on 2016/11/26.
 * 邮件的实体Bean
 */
@Entity
@Table(name = "Email")
public class Email {
	private Long emailId;	//邮件Id
	private String emailTheme;	//邮件主题
	private String emailContent;	//邮件内容
	private Account sender;	//发件人
	private Account receiver;	//收件人
	private Date time;	//时间（可能是收件、发件、存为草稿）的时间
	//private EmailBox emailBox;

	/*设置邮件的主键*/
	@Id
	@Column(name = "emailId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	public String getEmailTheme() {
		return emailTheme;
	}

	public void setEmailTheme(String emailTheme) {
		this.emailTheme = emailTheme;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	//设置单向一对一关联，每个邮件只有一个发件人，查邮件时迅速找到发件人，数据库表中字段为sender_userInfoId
	@OneToOne(targetEntity = Account.class)
	@JoinColumn(name = "senderId",referencedColumnName = "id")
	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	//设置单向一对一关联，每个邮件只有一个收件人，查邮件时迅速找到收件人,数据库表中字段为receiver_userInfoId
	@OneToOne(targetEntity = Account.class)
	@JoinColumn(name = "receiverId",referencedColumnName = "id")
	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

//	@OneToOne(targetEntity = EmailBox.class,mappedBy = "email")
//	public EmailBox getEmailBox() {
//		return emailBox;
//	}
//
//	public void setEmailBox(EmailBox emailBox) {
//		this.emailBox = emailBox;
//	}
}
