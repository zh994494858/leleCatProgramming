package cc.lelecat.entity.account.worker;

import javax.persistence.*;

/**
 * 账户登录表
 * <p>
 * Created by 王冀琛 on 2016/11/17.
 * 关于账户管理方面 用户表 的实体类
 * <p>
 * Modified by ACat,
 * 2016-11-25 11:32:10,
 * ACat i lele.
 */

@Entity
@Table(name = "worker_account")
public class Account {
	private Integer id;
	private String username;  // 登录名
	private String name;  // 真实姓名
	private String password;  // 密码
	private String imgUrl;  // 头像
	private Integer loginStatus;  // 登录状态
	private Boolean disabled;  // 是否被禁用
	private Account parent;  // 父工作账户
	private Role role;
	private AccountInfo accountInfo;


	//设置主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Boolean getDisabled() {
		return disabled == null ? false : disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	@OneToOne(targetEntity = Account.class)
	public Account getParent() {
		return parent;
	}
	public void setParent(Account parent) {
		this.parent = parent;
	}

	@ManyToOne(targetEntity = Role.class)
	@JoinTable(name = "worker_accounts2role",
		joinColumns = @JoinColumn(name = "account_id"
			, referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"
			, referencedColumnName = "id")
	)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	@OneToOne(targetEntity = AccountInfo.class, cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Account account = (Account) o;

		if (id != null ?
				account.id != null && !id.equals(account.id) : account.id != null)
			return false;
		if (username != null ?
				account.username != null && !username.equals(account.username) : account.username != null)
			return false;
		if (name != null ?
				account.name != null && !name.equals(account.name) : account.name != null)
			return false;
		if (password != null ?
				account.password != null && !password.equals(account.password) : account.password != null)
			return false;
		if (imgUrl != null ?
				account.imgUrl != null && !imgUrl.equals(account.imgUrl) : account.imgUrl != null)
			return false;
		if (loginStatus != null ?
				account.loginStatus != null && !loginStatus.equals(account.loginStatus) : account.loginStatus != null)
			return false;
		if (disabled != null ?
				account.disabled != null && !disabled.equals(account.disabled) : account.disabled != null)
			return false;
		if (parent != null ?
				account.parent != null && !parent.id.equals(account.parent.id)
				: account.parent != null && account.parent.id != null)
			return false;
		if (role != null ?
				account.role != null && !role.getId().equals(account.role.getId())
				: account.role != null && account.role.getId() != null)
			return false;
		if (accountInfo != null ?
				account.accountInfo != null && !accountInfo.getId().equals(account.accountInfo.getId())
				: account.accountInfo != null && account.accountInfo.getId() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
		result = 31 * result + (loginStatus != null ? loginStatus : 0);
		result = 31 * result + (disabled != null && disabled ? 1 : 0);
		result = 31 * result + (parent != null ? parent.id : 0);
		result = 31 * result + (role != null ? role.getId() : 0);
		result = 31 * result + (accountInfo != null ? accountInfo.getId() : 0);
		return result;
	}
}
