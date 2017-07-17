package cc.lelecat.account.worker.dao;

import cc.lelecat.entity.account.worker.Power;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by ACat on 2016/11/27.
 * ACat i lele.
 */

@Repository
public interface PowerDao {

    /**
     * 通过id查找权限
     * @param powerId
     * @return
     */
    Power get(Integer powerId);

	/**
	 * 查找所有权限
	 *
	 * @return
	 */
	Set<Power> findAll();

	/**
	 * 添加权限
	 * @param power
	 */
	boolean save(Power power);
}
