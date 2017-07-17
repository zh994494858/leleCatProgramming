package cc.lelecat.entity.school.lesson;

import cc.lelecat.entity.account.worker.Power;

import java.util.*;
import javax.persistence.*;

/**
 * Created by 张欢 on 2016/11/10.
 * <p>
 * modify by 王冀琛 on 2016/11/17
 * modify content: 关联上power表，衍生出第三张表powerCourse
 */
@Entity
@Table(name = "course")
public class Course {

	private Integer courseId;
	private String courseName;
	private String courseIntroduce;
	private String imgUrl;
	private Boolean enableAtLab;//是否在实验室使用
	private Boolean openOrNot;//是否使用
	private Set<Power> powers = new HashSet<Power>(0);
	private Set<Chapter> chapters = new HashSet<>();
	private String clPlugin;

	@Id
	@Column(name = "courseId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseIntroduce() {
		return courseIntroduce;
	}

	public void setCourseIntroduce(String courseIntroduce) {
		this.courseIntroduce = courseIntroduce;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Boolean getEnableAtLab() {
		return enableAtLab == null ? false : enableAtLab;
	}

	public void setEnableAtLab(Boolean enableAtLab) {
		this.enableAtLab = enableAtLab;
	}

	public Boolean getOpenOrNot() {
		return openOrNot == null ? false : openOrNot;
	}

	public void setOpenOrNot(Boolean openOrNot) {
		this.openOrNot = openOrNot;
	}

	/*设置一对对的映射关系 课程表-权限表*/
	@OneToMany(targetEntity = Power.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	public Set<Power> getPowers() {
		return powers;
	}

	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}

	@OneToMany(targetEntity = Chapter.class, cascade = CascadeType.ALL)
	@JoinTable(name = "course2chapter",
		joinColumns = @JoinColumn(name = "course_id"
			, referencedColumnName = "courseId"),
		inverseJoinColumns = @JoinColumn(name = "chapter_id"
			, referencedColumnName = "chapterId")
	)
	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	public String getClPlugin() {
		return clPlugin;
	}

	public void setClPlugin(String clPlugin) {
		this.clPlugin = clPlugin;
	}
}
