package cc.lelecat.entity.school.test;

import javax.persistence.*;
import cc.lelecat.entity.lab.PL;
import cc.lelecat.entity.school.lesson.Chapter;

/**
 * Created by kishow on 2016-11-10.
 * 关于编程题实体类的编写
 */
@Entity
@Table(name = "programming")
public class Programming {
    private Integer programmingId;
    private String itemContent;
    private String inputTime;
    private Integer degree;
    private Integer loginId;

    private Chapter chapter;//设置与章节表的多对一关系
    private  PL pl;//设置与编程语言表的多对一的关系

    //配置主键
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getProgrammingId() {
        return programmingId;
    }
    public void setProgrammingId(Integer programmingId) {
        this.programmingId = programmingId;
    }

    public String getItemContent() {
        return itemContent;
    }
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public String getInputTime() {
        return inputTime;
    }
    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public Integer getDegree() {
        return degree;
    }
    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getLoginId() {
        return loginId;
    }
    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    // 定义该 Programming 实体关联的 Chapter 属性映射外键列
    @ManyToOne(targetEntity = Chapter.class)
    @JoinColumn(name= "chapterId")
    public Chapter getChapter() {
        return chapter;
    }
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    // 定义该 Programming 实体关联的 PL属性映射外键列
    @ManyToOne(targetEntity = PL.class)
    @JoinColumn(name= "PLId")
    public PL getPl() {
        return pl;
    }
    public void setPl(PL pl) {
        this.pl = pl;
    }
}
