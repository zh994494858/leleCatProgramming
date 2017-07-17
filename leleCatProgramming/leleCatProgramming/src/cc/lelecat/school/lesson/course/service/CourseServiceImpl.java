package cc.lelecat.school.lesson.course.service;

import cc.lelecat.account.worker.dao.PowerDao;
import cc.lelecat.customer.dao.LearnProgressDaoImpl;
import cc.lelecat.entity.account.customer.LearnProgress;
import cc.lelecat.entity.account.worker.OperationType;
import cc.lelecat.entity.account.worker.Power;
import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.framework.FileUpload;
import cc.lelecat.framework.Page;
import cc.lelecat.school.lesson.course.dao.CourseDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 张欢 on 2016/11/15.
 */
@Service
@Transactional(readOnly=false)
public class CourseServiceImpl {
    @Resource
    private CourseDaoImpl courseDaoImpl;
    @Resource
    private LearnProgressDaoImpl learnProgressDao;

    public void addCourse(Course course, HttpServletRequest request) throws Exception {
        course.setOpenOrNot(true);

        Power power = new Power();
        power.setType(OperationType.COURSE_MANAGE);
        power.setName(course.getCourseName() + " 课程管理权限");
        course.getPowers().add(power);

        power = new Power();
        power.setType(OperationType.CHAPTER_MANAGE);
        power.setName(course.getCourseName() + " 章节管理权限");
        course.getPowers().add(power);

        power = new Power();
        power.setType(OperationType.TEST_MANAGE);
        power.setName(course.getCourseName() + " 测试管理权限");
        course.getPowers().add(power);

        int lastCourseId = this.findLastCourseId();     //获得所有课程中最大的课程ID
        this.courseDaoImpl.saveCourse(course);;
        lastCourseId += 1;

        //课程类已经创建好，获取的ID已经正确，设置存放课程图片的路径并创建图片


        //开设课程并且设置课程的图片
        Course newCourse = this.getCourse(lastCourseId);

        course.setImgUrl("/res/raw-assets/resources/School/HTML/img.jpg");

        course.setOpenOrNot(true);

        this.courseDaoImpl.updateCourse(newCourse);

    }

    @Transactional(readOnly=true)
    public Page<Course> listCourse(int pageNum,int pageSize,Object[] params){
        return this.courseDaoImpl.findCourse(pageNum, pageSize, params);
    }

    @Transactional(readOnly=true)
    public Course getCourse(int courseId){
        return this.courseDaoImpl.getCourse(courseId);
    }

    public void editCourse(Course p,HttpServletRequest request){
        Course pdb=this.courseDaoImpl.getCourse(p.getCourseId());

        pdb.setCourseName(p.getCourseName());
        pdb.setCourseIntroduce(p.getCourseIntroduce());
        pdb.setOpenOrNot(p.getOpenOrNot());
        pdb.setEnableAtLab(p.getEnableAtLab());
        pdb.setClPlugin(p.getClPlugin());


        pdb.setImgUrl("/res/raw-assets/resources/School/HTML/img.jpg");

        this.courseDaoImpl.updateCourse(pdb);
    }

    public void dropCourse(int courseId,HttpServletRequest request){

        Course course = this.courseDaoImpl.getCourse(courseId);
        this.learnProgressDao.deleteByCourse(course);
        this.courseDaoImpl.deleteCourse(courseId);


        //删除图片资源
//        String folderPath = request.getSession().getServletContext().getRealPath("res/raw-assets/resources/School") + "\\" + courseId;
//        delFolder(folderPath);
    }

    public List<Course> findAll() {
        try {
            return this.courseDaoImpl.findByProperty("from Course", new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    /*
     *
     */
    public int findLastCourseId() throws Exception {
            List<Course> courses =  this.courseDaoImpl.findByProperty("from Course", new Object[]{});
            return  courses.get(courses.size() - 1).getCourseId();
    }
    /**
     *  删除文件夹
     *  @return  boolean
     */
    public  void  delFolder(String  folderPath)  {
        try  {
            delAllFile(folderPath);  //删除完里面所有内容
            String filePath  =  folderPath;
            File myFilePath  =  new File(filePath);
            myFilePath.delete();  //删除空文件夹
        }
        catch  (Exception  e)  {
            e.printStackTrace();
        }

    }
    /**
     *  删除文件夹里面的所有文件
     *  @param  path  String  文件夹路径  如  c:/fqf
     */
    public  void  delAllFile(String path)  {
        File file  =  new File(path);
        if  (!file.exists())  {
            return;
        }
        if  (!file.isDirectory())  {
            return;
        }
        String[]  tempList  =  file.list();
        File  temp  =  null;
        for  (int  i  =  0;  i  <  tempList.length;  i++)  {
            if  (path.endsWith(File.separator))  {
                temp  =  new  File(path  +  tempList[i]);
            }
            else  {
                temp  =  new  File(path  +  File.separator  +  tempList[i]);
            }
            if  (temp.isFile())  {
                temp.delete();
            }
            if  (temp.isDirectory())  {
                delAllFile(path+"/"+  tempList[i]);//先删除文件夹里面的文件
                delFolder(path+"/"+  tempList[i]);//再删除空文件夹
            }
        }
    }

}