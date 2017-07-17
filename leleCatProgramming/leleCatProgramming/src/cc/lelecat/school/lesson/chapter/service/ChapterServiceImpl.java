package cc.lelecat.school.lesson.chapter.service;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.school.lesson.chapter.dao.ChapterDaoImpl;

import java.util.Collections;
import java.util.List;

/**
 * Created by 张欢 on 2016/11/15.
 */
@Service
public class ChapterServiceImpl {
	@Resource
	private ChapterDaoImpl chapterDaoImpl;

	public void addChapter(Chapter c, int courseId) {
		c.setChapterImg("/res/raw-assets/resources/School/HTML/img.jpg");
		c.setCourse(this.chapterDaoImpl.findCourseBycourseId(courseId));
		c.setChapterIndex(this.chapterDaoImpl.findChaptersByCourseId(courseId).size());


		this.chapterDaoImpl.saveChapter(c);
	}

	@Transactional(readOnly = true)
	public List<Chapter> listChapter(int courseId) {
		return this.chapterDaoImpl.findChaptersByCourseId(courseId);
	}

	public String findCourseName(int courseId) {
		return this.chapterDaoImpl.findCourseName(courseId);
	}

	@Transactional(readOnly = true)
	public Chapter getChapter(int chapterId) {

		return this.chapterDaoImpl.getChapter(chapterId);
	}

	public void editChapter(Chapter c) {
		Chapter cb = this.chapterDaoImpl.getChapter(c.getChapterId());

		cb.setChapterName(c.getChapterName());
		cb.setChapterIntroduce(c.getChapterIntroduce());
		cb.setChoiceNumber(c.getChoiceNumber());
		cb.setIdentification(c.getIdentification());
		cb.setTrueFalseNumber(c.getTrueFalseNumber());
		cb.setPassStandard(c.getPassStandard());

		this.chapterDaoImpl.updateChapter(cb);
	}

	public void editNP(Chapter c) {
		Chapter cb = this.chapterDaoImpl.getChapter(c.getChapterId());

		cb.setChoiceNumber(c.getChoiceNumber());
		cb.setTrueFalseNumber(c.getTrueFalseNumber());
		cb.setPassStandard(c.getPassStandard());

		this.chapterDaoImpl.updateChapter(cb);
	}

	public void editMoveon(Integer chapterId) {

		Chapter currChapter = this.chapterDaoImpl.getChapter(chapterId);
		this.chapterDaoImpl.update(currChapter);

		// 查询同Course下所有Chapter(按index升序排序)
		List<Chapter> chapters = this.chapterDaoImpl.findChaptersByCourseId(currChapter.getCourse().getCourseId());

		for (Chapter c : chapters) {
			if (c.getChapterId().equals(currChapter.getChapterId())) {
				currChapter = c;
				break;
			}
		}

		int curr = chapters.indexOf(currChapter);

		// 如果当前章节次序为第一位, 不排序
		if (curr <= 0) return;

		Chapter prevChapter = chapters.get(curr - 1);

		// index 交换
		int tmp = prevChapter.getChapterIndex() == null ? 0 : prevChapter.getChapterIndex();
		prevChapter.setChapterIndex(currChapter.getChapterIndex());
		currChapter.setChapterIndex(tmp);

		// 重新建立索引
		this.sortChapters(chapters);
	}

	public void editMovedown(Integer chapterId) {
		Chapter currChapter = this.chapterDaoImpl.getChapter(chapterId);

		// 查询同Course下所有Chapter(按index升序排序)
		List<Chapter> chapters = this.chapterDaoImpl.findChaptersByCourseId(currChapter.getCourse().getCourseId());

		for (Chapter c : chapters) {
			if (c.getChapterId().equals(currChapter.getChapterId())) {
				currChapter = c;
				break;
			}
		}

		int curr = chapters.indexOf(currChapter);

		// 如果当前章节次序为最后一位, 不排序
		if (curr >= chapters.size() - 1) return;

		Chapter nextChapter = chapters.get(curr + 1);

		// index 交换
		int tmp = nextChapter.getChapterIndex() == null ? 0 : nextChapter.getChapterIndex();
		nextChapter.setChapterIndex(currChapter.getChapterIndex());
		currChapter.setChapterIndex(tmp);

		// 重新建立索引
		this.sortChapters(chapters);
	}

	private void sortChapters(List<Chapter> chapters) {
		// 按index升序排列
		Collections.sort(chapters, (c1, c2) -> {
			int idx1 = c1.getChapterIndex() == null ? 0 : c1.getChapterIndex();
			int idx2 = c2.getChapterIndex() == null ? 0 : c2.getChapterIndex();

			return idx1 - idx2;
		});

		for (int i = 0; i < chapters.size(); i++) {
			chapters.get(i).setChapterIndex(i);
			this.chapterDaoImpl.update(chapters.get(i));
		}
	}

	public void dropChapter(int chapterId) {
		try {
			Chapter chapter = this.chapterDaoImpl.get(chapterId);
			int courseId = chapter.getCourse().getCourseId();

			this.chapterDaoImpl.deleteChapter(chapterId);

			this.sortChapters(this.chapterDaoImpl.findChaptersByCourseId(courseId));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
