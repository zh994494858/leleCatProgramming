package cc.lelecat.entity.account.worker;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色表
 *
 * Created by 王冀琛 on 2016/11/17.
 * 关于账户管理方面 角色表 的实体类
 *
 * Modified by ACat,
 * 2016-11-25 11:32:10,
 * ACat i lele.
 */

@Entity
@Table(name = "worker_role")
public class Role {
	private Integer id;
	private String name;  // 角色名称
	private String description;  // 角色描述
	private Boolean disabled;  // 是否被禁用
	private Role parent;
	private Set<Power> powers = new HashSet<>();
	private Set<Account> accounts = new HashSet<>();
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	@OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL)
	@JoinTable(name = "worker_accounts2role",
			joinColumns = @JoinColumn(name = "role_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "account_id"
					, referencedColumnName = "id")
	)
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	// 父工作账户
	@OneToOne(targetEntity = Role.class)
	public Role getParent() {
		return parent;
	}
	public void setParent(Role parent) {
		this.parent = parent;
	}

	// 角色对应的权限
	@ManyToMany(targetEntity = Power.class, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(
		name="worker_roles2powers",
		joinColumns={@JoinColumn(name="role_id")},
		inverseJoinColumns={@JoinColumn(name="power_id")})
	public Set<Power> getPowers() {
		return powers;
	}
	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		if (id != null ? !id.equals(role.id) : role.id != null) return false;
		if (name != null ? !name.equals(role.name) : role.name != null) return false;
		if (description != null ? !description.equals(role.description) : role.description != null) return false;
		if (disabled != null ? !disabled.equals(role.disabled) : role.disabled != null) return false;

		// parent属性
		// 两者引用不同并且有一个是null, 则返回false
		if (parent != role.parent && (parent == null || role.parent == null)) return false;
		// 比较两者id是否相同, 不同则返回false
		if (parent != null && !parent.getId().equals(role.parent.getId())) return false;

		// powers属性, 根据集合大小和其元素的id判断集合是否相同
		// 两者引用不同并且有一个是null, 则返回false
		if (powers != role.powers && (powers == null || role.powers == null)) return false;
		if (powers != role.powers) {
			if (powers.size() != role.powers.size()) return false;
			int calc = (int) powers.stream().filter(power -> {
				for (Power p : role.powers) {
					if (p.getId().equals(power.getId())) return true;
				}
				return false;
			}).count();
			if (calc != powers.size()) return false;
		}


		return true;

	}

	@Override
	public int hashCode() {

		int result = id != null ? id : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (disabled != null && disabled ? 1 : 0);
		result = 31 * result + (parent != null ? parent.id : 0);
		result = 15 * result;
		if (powers != null) {
			for (Power power : powers) {
				result += power.getId();
			}
		}
		result = result + (powers != null ? powers.size() : 0);
		return result;
	}

}
