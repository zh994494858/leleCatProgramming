package cc.lelecat.account.worker.service;

import cc.lelecat.account.worker.dao.RoleDao;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.account.worker.Power;
import cc.lelecat.entity.account.worker.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ACat on 2016/11/27.
 * ACat i lele.
 */

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
	@Resource
	private PowerService powerService;
	@Resource
	private AccountService accountService;

    @Override
    public Role get(Integer roleId) {
        return this.roleDao.get(roleId);
    }

    @Override
    public List<Role> findAll(Role role) {
        List<Role> roles = this.roleDao.findAll();

        for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
            Role item = iterator.next();

            // 如果item是role本身或者也不是role的子角色
            if (role.equals(item) || !isParent(role, item)) {
                iterator.remove();
            }

        }

        return roles;
    }

	@Override
	public List<Role> filterAll(Role role) {
		List<Role> roles = this.roleDao.filterAll();

		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
			Role item = iterator.next();

			// 如果item是role本身或者也不是role的子角色
			if (role.equals(item) || !isParent(role, item)) {
				iterator.remove();
			}

		}

		return roles;
	}

	@Override
    public boolean checkName(String name) {
        return this.roleDao.findByName(name) == null;
    }


    @Override
    public boolean add(Role role, Role parent) {
        role.setParent(parent);
        return this.roleDao.save(role);
    }

    @Override
    public String[] delete(Integer roleId) {
		String[] result;

		// 删除子角色
		List<Role> list = this.roleDao.findByParentId(roleId);
		for (Role role : list) this.roleDao.delete(role.getId());

        if (this.roleDao.delete(roleId)) {
			result = new String[]{"success", "删除成功"};
		} else {
			result = new String[]{"failure", "删除失败, 未知错误, 请重试."};
		}

		return result;
    }

    @Override
    public boolean update(Role role) {
        return this.roleDao.update(role);
    }

    @Override
    public boolean containRole(Account account, Role role) {
        return role.getParent().equals(account.getRole()) || role.equals(account.getRole());
    }

    @Override
    public String[] setRoleDisabled(Integer roleId) {
        Role role = this.roleDao.get(roleId);
		String[] result;

        if (this.setRoleDisabled(role))
        	result = new String[]{"success", "禁用成功"};
		else
			result = new String[]{"failure", "禁用失败, 发生未知错误, 请重试"};

		return result;
    }

    private boolean setRoleDisabled(Role role) {
		role.setDisabled(true);

		List<Role> list = this.roleDao.findByParentId(role.getId());
		for (Role r : list) {
			if (!this.setRoleDisabled(r)) return false;
			for (Account account : role.getAccounts()) {
				this.accountService.setAccountDisabled(account.getId());
			}
		}

		return this.roleDao.update(role);
	}

    @Override
    public String[] setRoleEnabled(Integer roleId) {
        Role role = this.roleDao.get(roleId);
        role.setDisabled(false);

		String[] result;
		if (this.roleDao.update(role))
			result = new String[]{"success", "启用成功"};
		else
			result = new String[]{"failure", "启用失败, 发生未知错误, 请重试"};

		return result;
    }

	@Override
	public String[] update(Integer roleId, String name, String description, Set<Integer> powerIds, Account parent) {
		String[] result;

		Role role = this.roleDao.get(roleId);

		Set<Power> powers = new HashSet<>();

		// 添加其他管理权限
		if (powerIds != null) {
			for (Integer id : powerIds) {
				Power power = this.powerService.get(id);
				powers.add(power);
			}
		}

		// 验证操作合法性
		if (name == null || name.equals("")) {
			// 角色名不能为空
			result = new String[]{"failure", "编辑失败, 角色名不能为空"};
		} else if (!name.equals(role.getName()) && !checkName(name)) {
			// 角色名不可用
			result = new String[]{"failure", "编辑失败, 指定角色名不可用, 请重试"};
		} else if (!this.powerService.containPowers(parent.getRole(), powers)) {
			// 越权赋予权限
			result = new String[]{"failure", "编辑失败, 赋予非法权限, 请重试"};
		} else {

			role.setName(name);
			role.setDescription(description);
			role.setPowers(powers);
			role.setParent(parent.getRole());
			role.setDisabled(false);

			if (this.roleDao.update(role))
				result = new String[]{"success", "编辑成功"};
			else
				result = new String[]{"failure", "编辑失败, 发生未知错误, 请重试"};

		}

		System.out.println(result[1]);
		return result;
	}

	@Override
	public String[] add(String name, String description, Set<Integer> powerIds, Account parent) {

		String[] result;

		Set<Power> powers = new HashSet<>();

		// 添加其他管理权限
		if (powerIds != null) {
			for (Integer id : powerIds) {
				Power power = this.powerService.get(id);
				powers.add(power);
			}
		}

		// 验证操作合法性
		if (name == null || name.equals("")) {
			// 角色名不能为空
			result = new String[]{"failure", "添加失败, 角色名不能为空"};
		} else if (!checkName(name)) {
			// 角色名不可用
			result = new String[]{"failure", "添加失败, 指定角色名不可用, 请重试"};
		} else if (!this.powerService.containPowers(parent.getRole(), powers)) {
			// 越权赋予权限
			result = new String[]{"failure", "添加失败, 赋予非法权限, 请重试"};
		} else {

			Role role = new Role();
			role.setName(name);
			role.setDescription(description);
			role.setPowers(powers);
			role.setParent(parent.getRole());
			role.setDisabled(false);
			System.out.println(role.getPowers());

			if (this.roleDao.save(role)) {
				result = new String[]{"success", "添加成功"};
			}
			else
				result = new String[]{"failure", "添加失败, 发生未知错误, 请重试"};

		}

		return result;
	}

	private boolean isParent(Role parent, Role child) {
        Role tmp = child.getParent();
        while (tmp != null && !tmp.equals(parent)) {
            tmp = tmp.getParent();
        }

        return tmp != null;
    }
}
