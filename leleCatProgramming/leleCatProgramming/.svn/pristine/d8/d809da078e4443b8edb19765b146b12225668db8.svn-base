package cc.lelecat.account.normal.service;


import cc.lelecat.entity.account.customer.NormalAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：NormalAccountService
 * 功能：实现客户账户服务层
 * 作者：苗怀雨
 * 日期：2016-11-15 15:47:57
 */
@Service
public interface NormalAccountService {


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
    boolean add(NormalAccount account);


    /**
     * 删除普通账户
     * @param accountId
     * @return
     */
    boolean delete(Integer accountId);

    /**
     * 通过id获取工作账户
     * @param accountId
     * @return
     */
    NormalAccount get(Integer accountId);

    /**
     * 更新工作账户
     * @param account
     */
    boolean update(NormalAccount account);

    /**
     * 查寻所有工作账户
     * 启用的账户在前, 禁用的账户在后,
     * 按角色排列
     *
     * @return
     */
    List<NormalAccount> findAll();

    /**
     * 根据参数添加客户账户
     *
     * @param username
     * @param password
     * @param name
     * @return
     */
    String[] add(String username,String password,String name);

    /**
     * 根据参数注册客户账户
     *
     * @param username
     * @param password
     * @return
     */
    String[] register(String username,String password,String passwordConfirmed);

    Map<String,String> register(Map<String,String> usernameAndpassword);

    /**
     * 检测用户名密码是否正确
     *
     * 苗怀雨,
     * 2016-12-6 09:20:03
     *
     * @param username
     * @param password
     */
    NormalAccount login(String username, String password);
}
