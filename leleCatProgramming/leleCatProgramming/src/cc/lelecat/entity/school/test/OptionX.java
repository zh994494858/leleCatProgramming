package cc.lelecat.entity.school.test;

import javax.persistence.*;

/**
 * Created by kishow on 2016-11-10.
 * 选择对应的实体类
 * 备注：将Choice中的正确答案属性列移入，改变成为对应的正确答案状态位
 */
@Entity
@Table(name="optionX")
// option为数据库中特殊字符, 不可用option作为表名
public class OptionX {
    private Integer optionXId;//自增id
    private String isRightkey;//正确答案状态位
    private String optionXContent;//选项内容
    private Integer orderOptionX;//选项顺序
    private Choice choice;//设置与选择题表多对一关系

    //配置主键
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getOptionXId() {
        return optionXId;
    }
    public void setOptionXId(Integer optionXId) {
        this.optionXId = optionXId;
    }

    public String getIsRightkey() {
        return isRightkey;
    }
    public void setIsRightkey(String isRightkey) {
        this.isRightkey = isRightkey;
    }

    public String getOptionXContent() {
        return optionXContent;
    }
    public void setOptionXContent(String optionXContent) {
        this.optionXContent = optionXContent;
    }

    public Integer getOrderOptionX() {
        return orderOptionX;
    }
    public void setOrderOptionX(Integer orderOptionX) {
        this.orderOptionX = orderOptionX;
    }

    // 定义该 OptionX 实体关联的 Choice 属性映射外键列
    @ManyToOne(targetEntity = Choice.class)
    @JoinColumn(name="choiceId")
    public Choice getChoice() {
        return choice;
    }
    public void setChoice(Choice choice) {
        this.choice = choice;
    }

}
