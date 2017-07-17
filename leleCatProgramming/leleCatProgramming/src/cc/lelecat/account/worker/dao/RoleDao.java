package cc.lelecat.account.worker.dao;

import cc.lelecat.entity.account.worker.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ACat on 2016/11/27.
 * ACat i lele.
 */

@Repository
public interface RoleDao {

    /**
     * 通过id查找角色
     *
     * @param roleId
     * @return
     */
    Role get(Integer roleId);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 查询所有启用的角色
     *
     * @return
     */
    List<Role> filterAll();

    /**
     * 通过name查找角色
     *
     * @param name
     * @return
     */
    Role findByName(String name);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    boolean save(Role role);

    /**
     * 通过id删除角色
     *
     * @param roleId
     * @return
     */
    boolean delete(Integer roleId);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    boolean update(Role role);

    /**
     * 通过父角色ID查找子角色
     * @param id
     * @return
     */
	List<Role> findByParentId(Integer id);
}
