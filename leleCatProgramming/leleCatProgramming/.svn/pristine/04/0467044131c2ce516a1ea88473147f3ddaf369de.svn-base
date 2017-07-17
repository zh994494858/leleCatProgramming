package cc.lelecat.entity.email;

import cc.lelecat.entity.account.worker.Account;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by 王冀琛
 * on 2016/11/26.
 * 邮箱表的实体Bean
 */

@Entity
@Table(name = "EmailBox")
public class EmailBox {
	private long emailBoxId;		//邮箱自增Id
	private Email email;			//邮件
	private State natureState;		//邮件所对应状态1：已读&未读（收件箱）、已发送（发件箱）、草稿（草稿箱）
	private boolean deleteState;	//邮件对应的状态2：删除过（1存到垃圾箱中）、未删除过（0不在垃圾箱中）
	private Account account;		//邮箱用户

	//设置邮箱主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getEmailBoxId() {
		return emailBoxId;
	}

	public void setEmailBoxId(long emailBoxId) {
		this.emailBoxId = emailBoxId;
	}

	@OneToOne(targetEntity = Account.class)
	@JoinColumn(name = "accountId",referencedColumnName = "id")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	//设置邮箱和邮件的单项一对一关联关系
	@OneToOne(targetEntity = Email.class)
	@JoinColumn(name = "emailId",referencedColumnName = "emailId")
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	//设置枚举类型的注解配置方式：该枚举包括：{已读&未读（收件箱）、已发送（发件箱）、草稿（草稿箱）四种状态}
	@Enumerated(value = EnumType.STRING)
	@Column(name = "natureState")
	public State getNatureState() {
		return natureState;
	}

	public void setNatureState(State natureState) {
		this.natureState = natureState;
	}

	public boolean isDeleteState() {
		return deleteState;
	}

	public void setDeleteState(boolean deleteState) {
		this.deleteState = deleteState;
	}
}
