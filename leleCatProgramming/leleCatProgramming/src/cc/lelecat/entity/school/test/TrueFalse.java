package cc.lelecat.entity.school.test;

import cc.lelecat.entity.school.lesson.Chapter;
import javax.persistence.*;


/**
 * Created by kishow on 2016-11-10.
 * 判断题对应的实体类
 */
@Entity
@Table(name = "trueFalse")
public class TrueFalse {

    private Integer trueFalseId;//主键id
    private Boolean answer;//答案*
    private String itemContent;//题干*
    private Boolean disabled;//禁用标识
    private Chapter chapter;//设置与章节表的多对一关系

    //配置主键
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getTrueFalseId() {
        return trueFalseId;
    }
    public void setTrueFalseId(Integer trueFalseId) {
        this.trueFalseId = trueFalseId;
    }

    public Boolean getAnswer() {
        return answer;
    }
    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public String getItemContent() {
        return itemContent;
    }
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public Boolean getDisabled() {
        return disabled == null ? false : disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    // 定义该 TrueFalse 实体关联的 Chapter 属性映射外键列
    @ManyToOne(targetEntity = Chapter.class)
    @JoinColumn(name= "chapterId")
    public Chapter getChapter() {
        return chapter;
    }
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }





}
