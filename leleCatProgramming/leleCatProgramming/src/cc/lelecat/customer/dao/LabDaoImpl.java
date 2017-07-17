package cc.lelecat.customer.dao;

import cc.lelecat.entity.lab.PL;
import cc.lelecat.framework.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhy on 2016/12/6.
 */
@Repository
public class LabDaoImpl extends BaseDao<PL, Integer> {
    public PL getPL(int PLId){
        try {
            PL pl = this.getPL(PLId);
            return pl;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public List<PL> findAll() {
        try {
            // 启用的账户在先, 禁用的账户在后
            return super.findByProperty("from PL", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
