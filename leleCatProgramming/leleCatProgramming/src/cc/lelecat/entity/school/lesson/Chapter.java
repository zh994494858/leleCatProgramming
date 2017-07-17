package cc.lelecat.entity.school.lesson;

import cc.lelecat.entity.school.test.Choice;
import cc.lelecat.entity.school.test.TrueFalse;

import java.util.*;
import javax.persistence.*;

/**
 * Created by 张欢 on 2016/11/10.
 */
/*更新第2次*/
@Entity
@Table(name="chapter")
public class Chapter {

    private Integer chapterId;
    private String chapterName;
    private String chapterIntroduce;
    private String identification;
    private Set<Choice> choices= new HashSet<Choice>(0);
    private Set<TrueFalse> trueFalses= new HashSet<TrueFalse>(0);
    private Boolean block; //章节状态（禁用与解锁）
    private Course course;
    private String chapterImg;    //每章对应的章节图片
    private Integer choiceNumber;     //选择题个数
    private Integer trueFalseNumber;  //判断题个数
    private Integer passStandard;     //选择题通过标准
    private Integer chapterIndex;


    @Id @Column(name="chapterId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getChapterId() {
        return chapterId;
    }
    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterIntroduce() {
        return chapterIntroduce;
    }
    public void setChapterIntroduce(String chapterIntroduce) {
        this.chapterIntroduce = chapterIntroduce;
    }

    @OneToMany(targetEntity=Choice.class , mappedBy="chapter", cascade = CascadeType.ALL)
    public Set<Choice> getChoices() {
        return choices;
    }
    public void setChoices(Set<Choice> choices) {
        this.choices = choices;
    }

    public String getIdentification() {
        return identification;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @OneToMany(targetEntity=TrueFalse.class , mappedBy="chapter", cascade = CascadeType.ALL)
    public Set<TrueFalse> getTrueFalses() {
        return trueFalses;
    }
    public void setTrueFalses(Set<TrueFalse> trueFalses) {
        this.trueFalses = trueFalses;
    }

    public Boolean getBlock() {
        return block;
    }
    public void setBlock(Boolean block) {
        this.block = block;
    }

    @ManyToOne(targetEntity = Course.class)
    @JoinTable(name = "course2chapter",
        joinColumns = @JoinColumn(name = "chapter_id"
            , referencedColumnName = "chapterId"),
        inverseJoinColumns = @JoinColumn(name = "course_id"
            , referencedColumnName = "courseId")
    )
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getChoiceNumber() {
        return choiceNumber == null ? 0 : choiceNumber;
    }
    public void setChoiceNumber(Integer choiceNumber) {
        this.choiceNumber = choiceNumber;
    }

    public Integer getTrueFalseNumber() {
        return trueFalseNumber == null ? 0 : trueFalseNumber;
    }
    public void setTrueFalseNumber(Integer trueFalseNumber) {
        this.trueFalseNumber = trueFalseNumber;
    }

    public Integer getPassStandard() {
        return passStandard == null ? 0 : passStandard;
    }

    public void setPassStandard(Integer passStandard) {
        this.passStandard = passStandard;
    }

    public String getChapterImg() {
        return chapterImg;
    }

    public void setChapterImg(String chapterImg) {
        this.chapterImg = chapterImg;
    }

    public Integer getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(Integer chapterIndex) {
        this.chapterIndex = chapterIndex;
    }
}
