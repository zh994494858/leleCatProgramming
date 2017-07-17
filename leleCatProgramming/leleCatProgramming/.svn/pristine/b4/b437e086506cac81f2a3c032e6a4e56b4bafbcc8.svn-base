package cc.lelecat.account.worker.dao;

import cc.lelecat.entity.account.worker.Power;
import cc.lelecat.framework.BaseDao;
import cc.lelecat.framework.Escape;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ACat on 2016/11/27.
 * ACat i lele.
 */

@Repository
public class PowerDaoImpl extends BaseDao<Power, Integer> implements PowerDao {

    private Power escape(Power power) {
        power.setName(Escape.encode(power.getName()));
        return power;
    }

    @Override
    public boolean save(Power entity) {
        return super.save(escape(entity));
    }

    @Override
    public boolean update(Power entity) {
        return super.update(escape(entity));
    }

    @Override
    public Power get(Integer powerId) {
        try {
            return super.get(powerId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Power> findAll() {
        Set<Power> result = new HashSet<>();
        try {
            result.addAll(super.findByProperty("from Power", new Object[]{}));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
}
