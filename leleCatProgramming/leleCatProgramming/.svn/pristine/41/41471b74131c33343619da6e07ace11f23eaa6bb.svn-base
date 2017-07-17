package cc.lelecat.entity.school.test;


import cc.lelecat.entity.school.lesson.Chapter;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kishow on 2016-11-10.
 * 关于测试选择题表的实体类编写
 */
@Entity
@Table(name = "choice")
public class Choice {
	private Integer choiceId;//自增主键id
	private String itemContent;//题干*
	private String optionA;
	private String optionB;
	private String optionC;//3个选项*
	private Integer answer;//答案*
	private Boolean disabled;//禁用标识

	private Chapter chapter;//设置与章节表的多对一关系

	// private Set<OptionX> optionXs=new HashSet<OptionX>(0);//准备删除

	//对主键进行设置
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(Integer choiceId) {
		this.choiceId = choiceId;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public Integer getAnswer() {
		return answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	public Boolean getDisabled() {
		return disabled == null ? false : disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	// 定义该 Choice 实体关联的 Chapter 属性映射外键列
	@ManyToOne(targetEntity = Chapter.class)
	@JoinColumn(name = "chapterId")
	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}


   /* //设置选择题与选项的单向一对多
	@OneToMany(targetEntity = OptionX.class)
    @JoinColumn(name = "choiceId")
    public Set<OptionX> getOptionXs() {
        return optionXs;
    }
    public void setOptionXs(Set<OptionX> optionXs) {
        this.optionXs = optionXs;
    } 准备删除*/
}


