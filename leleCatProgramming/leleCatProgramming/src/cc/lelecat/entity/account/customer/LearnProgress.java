package cc.lelecat.entity.account.customer;

import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.lesson.Course;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 王冀琛
 * on 2016/12/6.
 * 记录该用户学习情况，该用户对应课程解锁到的章节(用户和课程作为联合主键)
 */
@Entity
@Table(name = "LearnProgress")
public class LearnProgress implements Serializable{

	private Integer id;
	private NormalAccount normalAccount;
	private Course course;
	private Chapter chapter;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//定义关联的NormalAccount实体，列名为normalAccountId，参照normalAccountId，定义为一个主键
	@OneToOne(targetEntity = NormalAccount.class)
	@JoinColumn(name = "normalAccountId",referencedColumnName = "normalAccountId")
	public NormalAccount getNormalAccount() {
		return normalAccount;
	}
	public void setNormalAccount(NormalAccount normalAccount) {
		this.normalAccount = normalAccount;
	}

	//定义关联的Course实体，列名为courseId，参照courseId，定义为一个主键
	@OneToOne(targetEntity = Course.class)
	@JoinColumn(name = "courseId",referencedColumnName = "courseId")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	//设置学习进度和章节的一对一的单向关联关系
	@OneToOne(targetEntity = Chapter.class)
	@JoinColumn(name = "chapterId",referencedColumnName = "chapterId")
	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public int hashCode() {
		return (this.normalAccount == null? 0:this.normalAccount.hashCode ())*31*31+
				(this.course == null ? 0 : this.course.hashCode ())*31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj != null && obj.getClass ()==LearnProgress.class){
			LearnProgress target = (LearnProgress)obj;
			return this.normalAccount.equals (target.getNormalAccount ())
					&&this.course.equals (target.getCourse ());
		}
		return false;
	}
}
