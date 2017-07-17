package cc.lelecat.customer.service;

import cc.lelecat.customer.dao.LearnProgressDaoImpl;
import cc.lelecat.entity.account.customer.LearnProgress;
import cc.lelecat.entity.account.customer.NormalAccount;
import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.lesson.Course;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by 王冀琛
 * on 2016/12/6.
 * 根据课程的Id 和用户的Id找出该用户所点击课程的所有章节，以及刚解锁完毕的章节
 */

@Service
public class LearnProgressServiceImpl {

	@Resource
	private LearnProgressDaoImpl learnProgressDao;

//	public Map<String,Object> findAllLeanProgressInThisCourse(Map<String,String> courseAndAccountMSG){
//
//		Map<String,Object> allMsg = new HashMap<String,Object> (0);
//
//		//分别取到传递过来的courseId 和 normalAccountId
//		Integer courseId = 0;
//		Integer normalAccountId = 0;
//		Iterator iterator = courseAndAccountMSG.entrySet ().iterator ();
//		while (iterator.hasNext ()){
//			Map.Entry entry = (Map.Entry)iterator.next ();
//			String key = entry.getKey ().toString ();
//			String value = entry.getValue ().toString ();
//
//			if (key.equals ("courseId")){
//				courseId = Integer.valueOf (Integer.parseInt (value));
//				continue;
//			}
//			if (key.equals ("normalAccountId")){
//				normalAccountId = Integer.valueOf (Integer.parseInt (value));
//				continue;
//			}
//		}
//
//		//通过查询得到响应的 章节、课程名称、解锁且刚解锁的章节Id
//		String courseName = this.learnProgressDao.findCourseNameByCourseId (courseId);
//		List<Chapter> chapters = this.learnProgressDao.findCorrespondingChaptersByCourseId (courseId);
//		Integer chapterId = this.learnProgressDao.findUnlockingChapterId (courseId,normalAccountId);
//
//		//将各个章节中的内容提取出来，每一个封装成一个map类型
//
//		List<Map<String,String>>chapterMsgs = new ArrayList<Map<String, String>> (0);
//		for (Chapter chapter:chapters){
//			Map<String,String>chapterMsg = new HashMap<String,String> (0);
//			chapterMsg.put ("chapterName",chapter.getChapterName ());
//			chapterMsg.put ("chapterImg",chapter.getChapterImg ());
//			chapterMsg.put ("chapterIntroduce",chapter.getChapterIntroduce ());
//			chapterMsg.put ("ChapterId",chapter.getChapterId ().toString ());
//			chapterMsgs.add (chapterMsg);
//		}
//
//		//将查询所得到的信息封装成一个Map
//		allMsg.put ("courseName",courseName);    //map中第一个EntrySet为该课程的名称 courseName
//		allMsg.put ("chapterMsgs",chapterMsgs);		 //map中第二个EntrySet为该课程所有的章节 chapters
//		allMsg.put ("chapterId",chapterId);		 //map中第三个EntrySet为解锁且刚解锁的 chapterId
//
//		//用于测试 打印要传递给jsp页面的参数的键和值
//		/*String key = null;
//		Integer value = null;
//		Iterator it = allMsg.entrySet ().iterator ();
//		while (it.hasNext ()){
//			Map.Entry entry = (Map.Entry)it.next ();
//			key = entry.getKey ().toString ();
//			System.out.println ("键为："+key+",值为："+entry.getValue ());
//		}*/
//
//		return allMsg;
//	}

	/**
	 * 通过account和course查找对应的下一节该学习的课程
	 *
	 * @param account
	 * @param course
	 * @return
	 */
	public Chapter getNextChapter(NormalAccount account, Course course) {
		LearnProgress lp = this.learnProgressDao.get(account, course);
		if (lp == null) {
			lp = new LearnProgress();
			lp.setNormalAccount(account);
			lp.setCourse(course);
			Chapter chapter = course.getChapters().stream().filter(c -> c.getChapterIndex() == 0).collect(Collectors.toList()).get(0);
			lp.setChapter(chapter);
			this.learnProgressDao.save(lp);
		}

		return lp.getChapter();
	}

	/**
	 * 添加新的学习记录
	 *
	 * @param account
	 * @param course
	 * @param chapter
	 */
	public void update(NormalAccount account, Course course, Chapter chapter) {
		LearnProgress lp = this.learnProgressDao.get(account, course);
		lp.setChapter(chapter);
		this.learnProgressDao.update(lp);
	}
}