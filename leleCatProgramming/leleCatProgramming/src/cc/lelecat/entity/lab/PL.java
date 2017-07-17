package cc.lelecat.entity.lab;

import java.util.*;
import javax.persistence.*;

/**
 * Created by 张欢 on 2016/11/10.
 */
@Entity
@Table(name="pl")
public class PL {

    private Integer PLId;
    private String PLName;

    private Set<Unit> units
            = new HashSet<>();

    @Id @Column(name="PLId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getPLId() {
        return PLId;
    }

    public void setPLId(Integer PLId) {
        this.PLId = PLId;
    }

    public String getPLName() {
        return PLName;
    }

    public void setPLName(String PLName) {
        this.PLName = PLName;
    }
    // 定义该PL实体所有关联的Unit实体
    @ManyToMany(targetEntity=Unit.class)
    // 映射连接表，指定连接表的表名为PLUnit
    @JoinTable(name="PLUnit",
            // 映射连接表中名为PLId的外键列，
            // 该列参照当前实体对应表的PLId主键列
            joinColumns=@JoinColumn(name="PLId"
                    , referencedColumnName="PLId"),
            // 映射连接表中名为unitId的外键列，
            // 该列参数当前实体的关联实体对应表的unitId主键列
            inverseJoinColumns=@JoinColumn(name="unitId"
                    , referencedColumnName="unitId")
    )

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
}
