package cc.lelecat.account.worker.service;

import cc.lelecat.account.worker.dao.PowerDao;
import cc.lelecat.entity.account.worker.OperationType;
import cc.lelecat.entity.account.worker.Power;
import cc.lelecat.entity.account.worker.Role;
import cc.lelecat.entity.school.lesson.Course;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by ACat on 2016/11/27.
 * ACat i lele.
 */

@Service
public class PowerServiceImpl implements PowerService {
    final int ADMIN_MANAGE_POWER = 1;
    final int CUSTOMER_ACCOUNT_MANAGE_POWER = 2;
    final int ROLE_MANAGE_POWER = 3;
    final int WORKER_ACCOUNT_MANAGE_POWER = 4;
    final int PUSH_INFO_POWER = 5;
    final int ADD_COURSE_POWER = 6;

    @Resource
    private PowerDao powerDao;


    @Override
    public boolean hasWorkerAccountManagePower(Role role) {

        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER || power.getId() == WORKER_ACCOUNT_MANAGE_POWER) return true;
        }
        return false;
    }

    @Override
    public boolean hasRoleManagePower(Role role) {

        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER || power.getId() == ROLE_MANAGE_POWER) return true;
        }
        return false;
    }

    @Override
    public Power get(Integer powerId) {
        return this.powerDao.get(powerId);
    }

    @Override
    public boolean containPowers(Role role, Set<Power> powers) {

        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER) return true;
        }

        for (Power power : powers) {
            if (!role.getPowers().contains(power)) return false;
        }
        return true;
    }

    @Override
    public boolean hasPushInfoManagePower(Role role) {
        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER || power.getId() == PUSH_INFO_POWER) return true;
        }
        return false;
    }

    @Override
    public boolean hasCustomerAccountManagePower(Role role) {
        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER || power.getId() == CUSTOMER_ACCOUNT_MANAGE_POWER) return true;
        }
        return false;
    }

    @Override
    public boolean hasCourseManagePower(Role role, Course course) {
        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER)
                return true;
            if (power.getCourse() != null
                && power.getCourse().getCourseId().equals(course.getCourseId())
                && power.getType() == OperationType.COURSE_MANAGE)
                return true;
        }
        return false;
    }

    @Override
    public List<Power> getOtherManagePowers(Role role) {
        List<Power> powers = new ArrayList<>();

        if (role.getPowers().contains(this.get(ADMIN_MANAGE_POWER))) {
            // 如果角色拥有最高管理权限
            powers.add(this.get(CUSTOMER_ACCOUNT_MANAGE_POWER));
            powers.add(this.get(ROLE_MANAGE_POWER));
            powers.add(this.get(WORKER_ACCOUNT_MANAGE_POWER));
            powers.add(this.get(PUSH_INFO_POWER));
            powers.add(this.get(ADD_COURSE_POWER));
        } else {
            for (Power power : role.getPowers()) {
                switch (power.getId()) {
                    case CUSTOMER_ACCOUNT_MANAGE_POWER:
                    case ROLE_MANAGE_POWER:
                    case WORKER_ACCOUNT_MANAGE_POWER:
                    case PUSH_INFO_POWER:
                    case ADD_COURSE_POWER:
                        powers.add(power);
                }
            }
        }

        return powers;
    }

    @Override
    public Map<Course, List<Power>> getCourseManagePowerMap(Role role) {
        Set<Power> powers;
        Map<Course, List<Power>> result = new LinkedHashMap<>();

        if (role.getPowers().contains(this.get(ADMIN_MANAGE_POWER))) {
            // 如果角色拥有最高管理权限
            powers = this.powerDao.findAll();

        } else {
            powers = role.getPowers();
        }

        // 去除其中的最高管理权限和其他权限
        for (Iterator<Power> iterator = powers.iterator(); iterator.hasNext();) {
            switch (iterator.next().getId()) {
                case ADMIN_MANAGE_POWER:
                case CUSTOMER_ACCOUNT_MANAGE_POWER:
                case ROLE_MANAGE_POWER:
                case WORKER_ACCOUNT_MANAGE_POWER:
                case PUSH_INFO_POWER:
                case ADD_COURSE_POWER:
                    iterator.remove();
            }
        }

        // 将所有power按课程分类放入map
        Course key;
        List<Power> value;
        for (Power power : powers) {
            if ((key = power.getCourse()) != null) {
                if (result.containsKey(key)) {
                    result.get(key).add(power);
                } else {
                    value = new ArrayList<>();
                    value.add(power);
                    result.put(key, value);
                }
            }
        }


        return result;
    }

    @Override
    public boolean hasTestManagePower(Role role, Course course) {
        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER)
                return true;
            if (power.getCourse().getCourseId().equals(course.getCourseId())
                && power.getType() == OperationType.TEST_MANAGE)
                return true;
        }
        return false;
    }

    @Override
    public boolean hasChapterManagePower(Role role, Course course) {
        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER)
                return true;
            if (power.getCourse().getCourseId().equals(course.getCourseId())
                && power.getType() == OperationType.CHAPTER_MANAGE)
                return true;
        }
        return false;
    }

    @Override
    public boolean hasAddCoursePower(Role role) {
        for (Power power : role.getPowers()) {
            if (power.getId() == ADMIN_MANAGE_POWER || power.getId() == ADD_COURSE_POWER)
                return true;
        }
        return false;
    }


}
