package cc.lelecat.customer.service;

import cc.lelecat.customer.dao.NewCourseDaoImpl;
import cc.lelecat.entity.school.lesson.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by 张欢 on 2016/11/15.
 */
@Service
@Transactional()
public class NewCourseService {
    @Resource
    private NewCourseDaoImpl newCourseDaoImpl;

    @Transactional(readOnly=true)

    public List<Course> findAll() {
        List<Course> result = this.newCourseDaoImpl.findAll();
        return result;
    }
    public List<Course> filterCourses() {
        List<Course> result = this.newCourseDaoImpl.filterCourses();
        return result;
    }
}
