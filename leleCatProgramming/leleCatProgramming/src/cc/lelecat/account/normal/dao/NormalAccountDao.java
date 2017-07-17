package cc.lelecat.account.normal.dao;

import cc.lelecat.entity.account.customer.NormalAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类名：NormalAccountDao
 * 功能：实现客户账户Dao层
 * 作者：苗怀雨
 * 日期：2016-11-15 15:47:57
 */
@Repository
public interface NormalAccountDao {
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
    NormalAccount findByUsernameAndPassword(String username, String password);

    /**
     * 通过 Username 查找普通账户
     * @param username
     * @return
     */
    NormalAccount findByUsername(String username);

    /**aa
     * 添加普通账户
     * @param account
     * @return
     */
    boolean save(NormalAccount account);

    /**
     * 删除普通账户
     * @param accountId
     * @return
     */
    boolean delete(Integer accountId);

    /**
     * 获取普通账户
     *
     * @param accountId
     * @return
     */
    NormalAccount get(Integer accountId);

    /**
     * 更新普通账户
     *
     * @param account
     * @return
     */
    boolean update(NormalAccount account);

    /**
     * 查找所有普通账户
     * 启用的账户在前, 禁用的账户在后,
     *
     *
     * @return
     */
    List<NormalAccount> findAll();
}
