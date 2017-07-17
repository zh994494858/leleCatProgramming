package cc.lelecat.account.worker.service;

import cc.lelecat.account.worker.dao.AccountDao;
import cc.lelecat.account.worker.dao.RoleDao;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.account.worker.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * 类名：AccountServiceImpl
 * 功能：实现工作账户服务层
 * 作者：苗怀雨
 * 日期：2016-11-15 15:47:57
 *
 * Modified by ACat,
 * 2016-11-26 14:57:27,
 * ACat i lele.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Resource
    private RoleService roleService;

    public Account login(String username, String password) {
        return this.accountDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean checkUsernameAvailable(String username) {
        return this.accountDao.findByUsername(username) == null;
    }

    @Override
    public boolean add(Account account, Account parent) {
        account.setParent(parent);
        account.setImgUrl("/admin/assets/images/user/arcate/arcate.jpg");
        return accountDao.save(account);
    }

    @Override
    public boolean checkPassowrd(Account account, String password) {
        if (password != null)
            return this.accountDao.findByUsernameAndPassword(account.getUsername(), password) != null;
        else return false;
    }

    @Override
    public String[] delete(Integer accountId) {
        String[] result;

        // 删除子账户
        List<Account> list = this.accountDao.findByParentId(accountId);
        for (Account account : list)
            this.accountDao.delete(account.getId());

        if (this.accountDao.delete(accountId)) result = new String[]{"success", "删除成功"};
        else result = new String[]{"failure", "删除失败, 未知错误, 请重试."};

        return result;
    }

    @Override
    public Account get(Integer accountId) {
        return this.accountDao.get(accountId);
    }

    @Override
    public boolean update(Account account) {
        return this.accountDao.update(account);
    }

    @Override
    public List<Account> findAll(Account account) {
        List<Account> result = this.accountDao.findAll();

        for(Iterator<Account> iterator = result.iterator(); iterator.hasNext();) {
            Account item = iterator.next();

            // 如果item是account本身或不是account的子账户, 则移除
            if (item.equals(account) || !isParent(account, item)) {
                iterator.remove();
            }

        }

        return result;
    }

    @Override
    public String[] add(String username, String password, String name, Integer roleId, Account optAccount) {
        Role role = this.roleService.get(roleId);

        String[] result;

            /*检查用户名和赋予的角色是否符合要求*/
        if (username == null || username.equals("")) {
            result = new String[]{"failure", "添加失败, 账户名不能为空, 请重试"};
        } else if (!this.checkUsernameAvailable(username)) {
            result = new String[]{"failure", "添加失败, 指定账户名不可用, 请重试"};
        } else if (!this.roleService.containRole(optAccount, role)) {
            result = new String[]{"failure", "添加失败, 赋予非法角色, 请重试"};
        } else {
            Account acc = new Account();
            acc.setUsername(username);
            acc.setPassword(password);
            acc.setName(name);
            acc.setRole(role);
            acc.setParent(optAccount);

            if (this.accountDao.save(acc))
                result = new String[]{"success", "添加成功"};
            else
                result = new String[]{"failure", "添加失败, 发生未知错误, 请重试"};

        }

        return result;
    }

    @Override
    public String[] update(Integer accountId, String username, String password, String name, Integer roleId, Account optAccount) {

        String[] result;

        Account acc = this.accountDao.get(accountId);
        Role role = this.roleService.get(roleId);

        /*检查用户名和赋予的角色是否符合要求*/
        if (!this.checkUsernameAvailable(username)) {
            result = new String[]{"failure", "编辑失败, 指定用户名不可用, 请重试"};
        } else if (!this.roleService.containRole(optAccount, role)) {
            result = new String[]{"failure", "编辑失败, 赋予非法角色, 请重试"};
        } else {
            acc.setUsername(username);
            acc.setPassword(password);
            acc.setName(name);
            acc.setRole(role);

            if (this.accountDao.update(acc))
                result = new String[]{"success", "编辑成功"};
            else
                result = new String[]{"failure", "编辑失败, 发生未知错误, 请重试"};
        }

        return result;
    }

    @Override
    public String[] setAccountDisabled(Integer accountId) {
        String[] result;
        Account account = this.accountDao.get(accountId);

        if (this.setAccountDisabled(account))
            result = new String[]{"success", "禁用成功"};
        else
            result = new String[]{"failure", "禁用失败, 发生未知错误, 请重试"};

        return result;
    }

    private boolean setAccountDisabled(Account account) {
        account.setDisabled(true);

        List<Account> list = this.accountDao.findByParentId(account.getId());
        for (Account acc : list) {
            if (!this.setAccountDisabled(acc)) return false;
        }

        return this.accountDao.update(account);
    }

    @Override
    public String[] setAccountEnabled(Integer accountId) {
        String[] result;

        Account account = this.accountDao.get(accountId);
        account.setDisabled(false);

        if (this.accountDao.update(account))
            result = new String[]{"success", "启用成功"};
        else
            result = new String[]{"failure", "启用失败, 发生未知错误, 请重试"};

        return result;
    }

    // 判断两工作账户是否为继承关系
    private boolean isParent(Account parent, Account child) {
        Account tmp = child.getParent();
        while (tmp != null && !tmp.equals(parent)) {
            tmp = tmp.getParent();
        }

        return tmp != null;
    }
}
