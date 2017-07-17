package cc.lelecat.account.normal.dao;

import cc.lelecat.entity.account.customer.NormalAccount;
import cc.lelecat.framework.BaseDao;
import cc.lelecat.framework.Escape;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：NormalAccountDaoImpl
 * 功能：实现客户账户Dao层
 * 作者：苗怀雨
 * 日期：2016-11-15 15:47:57
 */
@Repository
public class NormalAccountDaoImpl extends BaseDao<NormalAccount, Integer> implements NormalAccountDao {

    private NormalAccount escape(NormalAccount account) {
        account.setEmail(Escape.encode(account.getEmail()));
        account.setLastLoginTime(Escape.encode((account.getLastLoginTime())));
        account.setName(Escape.encode(account.getName()));
        account.setTelephone(Escape.encode(account.getTelephone()));

        return account;
    }

    @Override
    public NormalAccount findByUsernameAndPassword(String username, String password) {
        try{
            return super.findOne("from NormalAccount nc where nc.username=? and nc.password=?",
                    new Object[]{username,password});
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NormalAccount findByUsername(String username) {
        try{
            return super.findOne("from NormalAccount nc where nc.username=?", new Object[]{username});
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(NormalAccount account) {
        return super.save(escape(account));
    }

    @Override
    public boolean delete(Integer accountId) {
        return super.delete(accountId);
    }

    @Override
    public NormalAccount get(Integer accountId) {
        try {
            return super.findOne("from NormalAccount nc where nc.normalAccountId=?", new Object[]{accountId});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(NormalAccount account) {
        return super.update(escape(account));
    }

    @Override
    public List<NormalAccount> findAll() {
        try {
            // 启用的账户在先, 禁用的账户在后
            return super.findByProperty("from NormalAccount nc order by nc.disabled", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
