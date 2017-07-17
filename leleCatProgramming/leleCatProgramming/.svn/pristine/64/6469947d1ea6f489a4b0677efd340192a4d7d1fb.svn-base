package cc.lelecat.account.worker.service;

import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.account.worker.Role;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by ACat on 2016/11/26.
 */

@Resource
public interface RoleService {
    /**
     * 通过id获得角色
     * @param roleId
     * @return
     */
    Role get(Integer roleId);

    /**
     * 查找所有该角色下的角色(包括自身角色)
     * @param role
     * @return
     */
    List<Role> findAll(Role role);

    /**
     * 查找所有该角色下所有启用的角色(包括自身角色)
     * @param role
     * @return
     */
    List<Role> filterAll(Role role);

    /**
     * 检查角色的name是否可用
     * @param name
     * @return
     */
    boolean checkName(String name);

    /**
     * 使role持久化, 其父role为parent
     * @param role
     * @param parent
     * @return
     */
    boolean add(Role role, Role parent);

    /**
     * 通过id删除role
     * @param roleId
     * @return
     */
    String[] delete(Integer roleId);

    /**
     * 更新role
     *
     * @param role
     * @return
     */
    boolean update(Role role);

    /**
     * 判断账户是否包含角色
     *
     * @param account
     * @param role
     * @return
     */
    boolean containRole(Account account, Role role);

    /**
     * 设置账户为不可用
     *
     * @param roleId
     * @return
     */
    String[] setRoleDisabled(Integer roleId);

    /**
     * 设置账户为可用
     * @param roleId
     * @return
     */
    String[] setRoleEnabled(Integer roleId);

    /**
     * 根据id更新角色
     *
     * @param roleId
     * @param name
     * @param description
     * @param powerIds
     * @param parent
     * @return
     */
    String[] update(Integer roleId, String name, String description, Set<Integer> powerIds, Account parent);

    /**
     * 添加角色
     *
     * @param name
     * @param description
     * @param powerIds
     * @param parent
     * @return
     */
    String[] add(String name, String description, Set<Integer> powerIds, Account parent);

}
