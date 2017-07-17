package cc.lelecat.customer.dao;

import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.framework.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
/*查询方法需要知道根据什么查，目前是按名字查改的*/

/**
 * Created by 张欢 on 2016/11/15.
 */
@Repository
public class NewCourseDaoImpl extends BaseDao<Course, Integer>{


    public Course getCourse(int courseId){
        try {
            Course p=this.get(courseId);
            return p;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> findAll() {
        try {
            // 启用的账户在先, 禁用的账户在后
            return super.findByProperty("from Course course order by course.openOrNot", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Course> filterCourses() {
        try {
            // 启用的账户在先, 禁用的账户在后
            return super.findByProperty("from Course course where course.openOrNot=1", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
