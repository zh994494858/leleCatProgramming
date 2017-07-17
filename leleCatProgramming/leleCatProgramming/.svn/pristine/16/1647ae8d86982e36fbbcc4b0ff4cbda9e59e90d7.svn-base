package cc.lelecat.entity.account.customer;

import javax.persistence.*;

/**
 * 普通用户账户
 *
 * Created by 王冀琛 on 2016/11/17.
 * 关于账户管理方面 用户表 的实体类
 *
 * 未改动
 */

@Entity
@Table(name = "customer_account")
public class NormalAccount {

	private Integer normalAccountId;//用户表的自增ID

	private String username;//登录名
	private String password;//密码
	private String name;//真实名
	private String telephone;//联系方式
	private String email;//邮箱
	private Integer loginStatus;//登录状态
	private String lastLoginTime; //上次登录时间
	private Boolean disabled;//标识该账户是否被封锁
	private Integer loginCount; //登录次数

	//设置主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getNormalAccountId() {
		return normalAccountId;
	}
	public void setNormalAccountId(Integer normalAccountId) {
		this.normalAccountId = normalAccountId;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
}
