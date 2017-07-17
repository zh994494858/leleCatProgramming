package cc.lelecat.school.lesson.course.dao;

import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.framework.Escape;
import org.springframework.stereotype.Repository;
import cc.lelecat.framework.Page;
import cc.lelecat.framework.BaseDao;
/*查询方法需要知道根据什么查，目前是按名字查改的*/

/**
 * Created by 张欢 on 2016/11/15.
 */
@Repository
public class CourseDaoImpl extends BaseDao<Course, Integer>{

    private Course escape(Course course) {
        course.setCourseName(Escape.encode(course.getCourseName()));
        course.setCourseIntroduce(Escape.encode(course.getCourseIntroduce()));

        return course;
    }

    @Override
    public boolean save(Course entity) {
        return super.save(escape(entity));
    }

    @Override
    public boolean update(Course entity) {
        return super.update(escape(entity));
    }

    public void saveCourse(Course p){
        try {
            this.save(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Page<Course> findCourse(int pageNum, int pageSize,Object[] params){
        String hql;
        if(params!=null && params.length>0){
            hql="from Course p where p.courseName like ?";
            params[0]="%"+params[0]+"%";

        }else{
            hql="from Course";
        }
        try {
            Page<Course> page=new Page<Course>();
            page.setCurrentPageNum(pageNum);
            page.setPageSize(pageSize);
            page=this.findByPage(pageNum, pageSize, hql, params);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public void updateCourse(Course p){
        try {
            this.update(p);
            System.out.println("dao更新类");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteCourse(int courseId){
        try {
            this.delete(courseId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
