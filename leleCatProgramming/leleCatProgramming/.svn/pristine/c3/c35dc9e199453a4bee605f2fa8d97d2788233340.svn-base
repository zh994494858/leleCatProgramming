package cc.lelecat.account.worker.dao;

import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.framework.BaseDao;
import cc.lelecat.framework.Escape;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * 类名：AccountDaoImpl
 * 功能：实现工作账户Dao层
 * 作者：苗怀雨
 * 日期：2016-11-15 15:47:57
 *
 * Modified by ACat,
 * 2016-11-26 14:56:09,
 * ACat i lele.
 */
@Repository
public class AccountDaoImpl extends BaseDao<Account, Integer> implements AccountDao {

    private Account escape(Account account) {
        account.setName(Escape.encode(account.getName()));

        return account;
    }

    @Override
    public boolean save(Account entity) {
        return super.save(escape(entity));
    }

    @Override
    public boolean update(Account entity) {
        return super.update(escape(entity));
    }

    public Account findByUsernameAndPassword(String username, String password) {

        try{
            return super.findOne("from Account account where account.username=? and account.password=?",
                    new Object[]{username,password});
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Account findByUsername(String username) {
        try{
            return super.findOne("from Account account where account.username=?", new Object[]{username});
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Integer accountId) {
        return super.delete(accountId);
    }

    @Override
    public Account get(Integer accountId) {
        try {
            return super.findOne("from Account account where account.id=?", new Object[]{accountId});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Account> findAll() {
        try {
            // 启用的账户在先, 禁用的账户在后
            return super.findByProperty("from Account account order by account.disabled, account.role", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Account> findByParentId(Integer accountId) {
        try {
            return super.findByProperty("from Account account, Account parent order by account.parent=parent and parent.id=?", new Object[]{accountId});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
