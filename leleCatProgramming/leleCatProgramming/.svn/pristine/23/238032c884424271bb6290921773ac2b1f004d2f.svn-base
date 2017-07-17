package cc.lelecat.account.worker.service;

import cc.lelecat.entity.account.worker.Account;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名：AccountService
 * 功能：实现工作账户服务层
 * 作者：苗怀雨
 * 日期：2016-11-15 15:47:57
 *
 * Modified by ACat,
 * 2016-11-26 14:57:03,
 * ACat i lele.
 */
@Service
public interface AccountService {

    /**
     * 检测用户名密码是否正确
     *
     * Created by ACat,
     * 2016-11-25 18:19:32,
     * ACat i lele.
     *
     * @param username
     * @param password
     */
    Account login(String username, String password);

    /**
     * 检查用户名是否可用
     * @param username
     * @return
     */
    boolean checkUsernameAvailable(String username);

    /**
     * 持久化工作账户
     *
     * @param account
     */
    boolean add(Account account, Account parent);

    /**
     * 检查账户和密码是否匹配
     * @param account
     * @param password
     * @return
     */
    boolean checkPassowrd(Account account, String password);

    /**
     * 删除工作账户
     * @param accountId
     * @return
     */
    String[] delete(Integer accountId);

    /**
     * 通过id获取工作账户
     * @param accountId
     * @return
     */
    Account get(Integer accountId);

    /**
     * 更新工作账户
     * @param account
     */
    boolean update(Account account);

    /**
     * 查寻所有工作账户
     * 启用的账户在前, 禁用的账户在后,
     * 按角色排列
     *
     * @return
     */
    List<Account> findAll(Account account);

    /**
     * 添加工作账户
     *
     * @param username
     * @param password
     * @param name
     * @param roleId
     * @param account
     * @return
     */
	String[] add(String username, String password, String name, Integer roleId, Account account);


    /**
     * 更新account
     *
     * @param accountId
     * @param username
     * @param password
     * @param name
     * @param roleId
     * @param optAccount
     * @return
     */
    String[] update(Integer accountId, String username, String password, String name, Integer roleId, Account optAccount);

    /**
     * 根据id禁用工作账户
     *
     * @param accountId
     * @return
     */
    String[] setAccountDisabled(Integer accountId);

    /**
     * 根据id禁用工作账户
     * @param accountId
     * @return
     */
    String[] setAccountEnabled(Integer accountId);
}
