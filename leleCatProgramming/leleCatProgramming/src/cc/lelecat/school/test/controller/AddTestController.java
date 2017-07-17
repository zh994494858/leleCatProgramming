package cc.lelecat.school.test.controller;

import cc.lelecat.account.worker.service.PowerService;
import cc.lelecat.entity.account.customer.LearnProgress;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.account.worker.Power;
import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.school.lesson.chapter.service.ChapterServiceImpl;
import cc.lelecat.tag.menu.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ACat on 2016/12/8.
 * ACat i lele.
 */
@Controller
@RequestMapping("admin")
public class AddTestController {

	@Resource
	private ChapterServiceImpl chapterService;
	@Resource
	private PowerService powerService;

	private Item tagChoice;
	private Item tagJudge;
	private Item tagReturn;
	private Map<String, Object> tagsMap;

	private String NO_POWER_PAGE = "redirect:/admin/course/listCourse";

	public AddTestController() {
		tagChoice = new Item("单选题", null, "fa-plus");
		tagJudge = new Item("判断题", null, "fa-plus");
		tagReturn = new Item("返回", null, "fa-plus");
		tagReturn.setCmd("tag-success");

		tagsMap = new HashMap<>();
		List<Item> tags = new ArrayList<>();
		tags.add(tagChoice);
		tags.add(tagJudge);
		tagsMap.put("left_tags", tags);
		tags = new ArrayList<>();
		tags.add(tagReturn);
		tagsMap.put("right_tags", tags);
	}

	// 面包屑
	private List<Item> getCrumbs(Course course, Chapter chapter) {
		List<Item> crumbs = new ArrayList<>();
		Item crumb;
		crumb = new Item("主页", "#");
		crumbs.add(crumb);
		crumb = new Item("选择课程", "/admin/course/list");
		crumbs.add(crumb);
		crumb = new Item(course.getCourseName(), "/admin/course/list-chapters?courseId=" + course.getCourseId());
		crumbs.add(crumb);
		crumb = new Item(chapter.getChapterName() + " 测试题", "/admin/choice/list?chapterId=" + chapter.getIdentification());
		crumbs.add(crumb);
		crumb = new Item("添加测试题");
		crumbs.add(crumb);

		return crumbs;
	}

	@RequestMapping("choice/add-test-page")
	public String addChoicePage(@RequestParam("chapterId") Integer chapterId, HttpServletRequest request, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Chapter chapter = this.chapterService.getChapter(chapterId);

		tagChoice.setUrl("/admin/choice/add-test-page?chapterId=" + chapterId);
		tagJudge.setUrl("/admin/judge/add-test-page?chapterId=" + chapterId);
		tagReturn.setUrl("/admin/choice/list?chapterId=" + course.getCourseId());

		request.setAttribute("active_tag", tagChoice);
		request.setAttribute("left_tags", tagsMap.get("left_tags"));
		request.setAttribute("right_tags", tagsMap.get("right_tags"));

		request.setAttribute("crumbs", getCrumbs(course, chapter));

		// 页头
		request.setAttribute("title", "添加章节 " + chapter.getChapterName() + " 的测试题");
		request.setAttribute("title-description", "在这里您可以添加该章节的测试题.");
		request.setAttribute("description", "添加 " + chapter.getChapterName() + " 的单选测试题.");

		request.setAttribute("chapterId", chapterId);

		return "/admin/test/addChoice";
	}

	@RequestMapping("judge/add-test-page")
	public String addJudgePage(@RequestParam("chapterId") Integer chapterId, HttpServletRequest request, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Chapter chapter = this.chapterService.getChapter(chapterId);

		tagChoice.setUrl("/admin/choice/add-test-page?chapterId=" + chapterId);
		tagJudge.setUrl("/admin/judge/add-test-page?chapterId=" + chapterId);
		tagReturn.setUrl("/admin/judge/list?chapterId=" + course.getCourseId());

		request.setAttribute("active_tag", tagJudge);
		request.setAttribute("left_tags", tagsMap.get("left_tags"));
		request.setAttribute("right_tags", tagsMap.get("right_tags"));

		request.setAttribute("crumbs", getCrumbs(course, chapter));

		// 页头
		request.setAttribute("title", "添加章节 " + chapter.getChapterName() + " 的测试题");
		request.setAttribute("title-description", "在这里您可以添加该章节的测试题.");
		request.setAttribute("description", "添加 " + chapter.getChapterName() + " 的判断测试题.");

		request.setAttribute("chapterId", chapterId);

		return "/admin/test/addJudge";
	}
}
