package cc.lelecat.entity.lab;

import java.util.*;
import javax.persistence.*;

/**
 * Created by 张欢 on 2016/11/10.
 */
@Entity
@Table(name="unit")
public class Unit {

    private Integer unitId;
    private String sign;
    private Set<PL> PLs
            = new HashSet<>();

    @Id @Column(name="unitId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getUnitId() {
        return unitId;
    }
    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }

    // 定义该Unit实体所有关联的PL实体
    @ManyToMany(targetEntity=PL.class)
    // 映射连接表，指定连接表的表名为PLUnit
    @JoinTable(name="PLUnit",
            // 映射连接表中名为unitId的外键列，
            // 该列参照当前实体对应表的unitId主键列
            joinColumns=@JoinColumn(name="unitId"
                    , referencedColumnName="unitId"),
            // 映射连接表中名为PLId的外键列，
            // 该列参数当前实体的关联实体对应表的PLId主键列
            inverseJoinColumns=@JoinColumn(name="PLId"
                    , referencedColumnName="PLId")
    )
    public Set<PL> getPLs() {
        return PLs;
    }
    public void setPLs(Set<PL> PLs) {
        this.PLs = PLs;
    }
}
