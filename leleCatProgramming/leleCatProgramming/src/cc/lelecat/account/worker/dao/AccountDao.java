package cc.lelecat.account.worker.dao;

import cc.lelecat.entity.account.worker.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类名：AccountDao
 * 功能：实现工作账户Dao层
 * 作者：苗怀雨
 * 日期：2016-11-15 15:47:57
 *
 * Modified by ACat,
 * 2016-11-26 14:56:33,
 * ACat i lele.
 */
@Repository
public interface AccountDao {
    /**
     * 通过 Username 和 Password 查找工作账户
     *
     * Created by ACat,
     * 2016-11-25 18:32:36,
     * ACat i lele.
     *
     * @param username
     * @param password
     * @return
     */
    Account findByUsernameAndPassword(String username, String password);

    /**
     * 通过 Username 查找工作账户
     * @param username
     * @return
     */
    Account findByUsername(String username);

    /**
     * 添加工作账户
     * @param account
     * @return
     */
    boolean save(Account account);

    /**
     * 删除工作账户
     * @param accountId
     * @return
     */
    boolean delete(Integer accountId);

    /**
     * 获取工作账户
     *
     * @param accountId
     * @return
     */
    Account get(Integer accountId);

    /**
     * 更新工作账户
     *
     * @param account
     * @return
     */
    boolean update(Account account);

    /**
     * 查找所有工作账户
     * 启用的账户在前, 禁用的账户在后,
     * 按角色排列
     *
     * @return
     */
    List<Account> findAll();

    /**
     * 通过父账户ID查找子账户
     * @param accountId
     * @return
     */
	List<Account> findByParentId(Integer accountId);
}
