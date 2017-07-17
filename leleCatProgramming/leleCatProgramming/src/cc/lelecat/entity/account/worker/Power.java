package cc.lelecat.entity.account.worker;

import cc.lelecat.entity.school.lesson.Course;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限表
 *
 * Created by 王冀琛 on 2016/11/17.
 * 关于账户管理方面 权限表 的实体类
 *
 * Modified by ACat,
 * 2016-11-25 11:32:10,
 * ACat i lele.
 */

@Entity
@Table(name = "worker_power")
public class Power {
	private Integer id;				// 主键
	private String name;			// 权限名称
	private OperationType type;		// 权限的类型
	private Course course;			// 该权限所对应的课程
	private Set<Role> roles;

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

	@Enumerated(EnumType.STRING)
	public OperationType getType() {
		return type;
	}
	public void setType(OperationType type) {
		this.type = type;
	}

	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name="course_id" , referencedColumnName="courseId")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToMany(targetEntity = Role.class, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(
		name="worker_roles2powers",
		joinColumns={@JoinColumn(name="power_id")},
		inverseJoinColumns={@JoinColumn(name="role_id")})
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Power power = (Power) o;

		if (id != null ? !id.equals(power.id) : power.id != null) return false;
		if (name != null ? !name.equals(power.name) : power.name != null) return false;
		if (type != power.type) return false;

		// course属性
		// 两者引用不同并且有一个是null, 则返回false

		if (course != power.course && (course == null || power.course == null)) return false;
		// 比较两者id是否相同, 不同则返回false
		if (course != null && !course.getCourseId().equals(power.course.getCourseId()))
			return false;

		return true;
	}
	@Override
	public int hashCode() {

		int result = id != null ? id : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (course != null ? course.getCourseId() : 0);
		return result;
	}
}