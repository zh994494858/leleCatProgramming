package cc.lelecat.account.worker.service;

import cc.lelecat.entity.account.worker.Power;
import cc.lelecat.entity.account.worker.Role;
import cc.lelecat.entity.school.lesson.Course;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ACat on 2016/11/26.
 */

@Resource
public interface PowerService {
    /**
     * 判断账户是否有账户管理权限
     * @param role
     * @return
     */
    boolean hasWorkerAccountManagePower(Role role);

    /**
     * 判断账户是否有角色管理权限
     * @param role
     * @return
     */
    boolean hasRoleManagePower(Role role);

    /**
     * 通过id查找权限
     * @param powerId
     * @return
     */
    Power get(Integer powerId);

    /**
     * 判断角色是否拥有指定权限
     *
     * @param role
     * @param powers
     * @return
     */
    boolean containPowers(Role role, Set<Power> powers);

    /**
     * 判断角色是否拥有推送通知权限
     *
     * @param role
     * @return
     */
    boolean hasPushInfoManagePower(Role role);

    /**
     * 判断角色是否拥有用户账户管理权限
     *
     * @param role
     * @return
     */
    boolean hasCustomerAccountManagePower(Role role);

    /**
     * 判断角色是否拥有课程管理权限
     *
     * @param role
     * @return
     */
    boolean hasCourseManagePower(Role role, Course course);

    /**
     * 根据角色获取其包含的其他类型权限
     *
     * @param role
     * @return
     */
    List<Power> getOtherManagePowers(Role role);

    /**
     * 根据角色获取其包含的课程管理权限
     *
     * @param role
     * @return
     */
    Map<Course, List<Power>> getCourseManagePowerMap(Role role);

    /**
     * 是否具有测试题管理权限
     * @param role
     * @param course
     * @return
     */
    boolean hasTestManagePower(Role role, Course course);

    /**
     * 是否具有章节管理权限
     * @param role
     * @param course
     * @return
     */
    boolean hasChapterManagePower(Role role, Course course);

    /**
     * 是否具有添加课程权限
     * @param role
     * @return
     */
    boolean hasAddCoursePower(Role role);

}
