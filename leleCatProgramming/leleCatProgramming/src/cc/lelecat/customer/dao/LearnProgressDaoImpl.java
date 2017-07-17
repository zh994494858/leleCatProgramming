package cc.lelecat.customer.dao;

import cc.lelecat.entity.account.customer.LearnProgress;
import cc.lelecat.entity.account.customer.NormalAccount;
import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.framework.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 王冀琛
 * on 2016/12/6.
 * 根据课程的:courseId 和用户的:normalAccountId
 * 找出该用户所点击课程的所有章节，以及刚解锁完毕的章节
 */

@Repository
public class LearnProgressDaoImpl extends BaseDao<LearnProgress, Integer> {

//	@Resource
//	private SessionFactory sessionFactory;
//
//	public List<Chapter> findCorrespondingChaptersByCourseId(Integer courseId){
//
//		//查询到该课程对应的所有章节
//		String sql1 = "select * " +
//				"from lelecat.chapter where chapter.courseId = "+courseId+
//				" ORDER BY chapterId ASC";
//		List<Chapter> chapters = (List<Chapter>) this.sessionFactory.getCurrentSession ().createSQLQuery (sql1).addEntity ("chapter",Chapter.class).list ();
//
//		return chapters;
//	}
//
//	public String findCourseNameByCourseId(Integer courseId){
//
//		//查询得到该课程的名字
//		String sql2 = "select course.courseName from lelecat.course where course.courseId = "+courseId;
//		String courseName = this.sessionFactory.getCurrentSession ().createSQLQuery (sql2).list ().get (0).toString ();
//
//		return courseName;
//	}
//
//	public Integer findUnlockingChapterId(Integer courseId,Integer normalAccountId){
//
//		//查询得到该用户该门课程所解锁到的章节Id
//		String sql3 = "select learnprogress.chapterId " +
//				"from learnprogress " +
//				"where learnprogress.courseId="+courseId+
//				" and learnprogress.normalAccountId = "+normalAccountId;
//		Integer chapterIds = (Integer) this.sessionFactory.getCurrentSession ().createSQLQuery (sql3).list ().get (0);
//
//		return chapterIds;
//	}

	public LearnProgress get(NormalAccount account, Course course) {
		try {
			return super.findOne(
				"from LearnProgress lp where lp.normalAccount=? and lp.course=?", new Object[]{account, course});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteByCourse(Course course) {
		try {
			List<LearnProgress> list = super.findByProperty("from LearnProgress lp where lp.course=?", new Object[]{course});
			for (LearnProgress learnProgress : list) {
				super.delete(learnProgress.getId());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


	}

}