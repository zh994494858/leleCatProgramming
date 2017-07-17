package cc.lelecat.school.test.controller;

import cc.lelecat.account.worker.service.PowerService;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.entity.school.test.Choice;
import cc.lelecat.entity.school.test.TrueFalse;
import cc.lelecat.school.lesson.chapter.service.ChapterServiceImpl;
import cc.lelecat.school.test.service.ChoiceServiceImpl;
import cc.lelecat.school.test.service.TrueFalseServiceImpl;
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
 * Created by kishow on 2016-11-30.
 */
@Controller
@RequestMapping("admin")
public class TestController {
	@Resource
	private ChoiceServiceImpl choiceServiceImpl;
	@Resource
	private TrueFalseServiceImpl trueFalseServiceImpl;
	@Resource
	private ChapterServiceImpl chapterService;
	@Resource
	private PowerService powerService;

	private Item tagChoice;
	private Item tagJudge;
	private Item tagReturn;
	private Map<String, Object> tagsMap;

	private String NO_POWER_PAGE = "redirect:/admin/course/listCourse";

	public TestController() {
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
		crumb = new Item(chapter.getChapterName() + " 测试题", null);
		crumbs.add(crumb);

		return crumbs;
	}

	@RequestMapping("choice/list")
	public String listChoice(
		@RequestParam(name = "chapterId") Integer chapterId,
		HttpServletRequest request,
		HttpSession session
	) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Chapter chapter = this.chapterService.getChapter(chapterId);

		// 标签
		tagChoice.setUrl("/admin/choice/list?chapterId=" + chapterId);
		tagJudge.setUrl("/admin/judge/list?chapterId=" + chapterId);
		tagReturn.setUrl("/admin/course/list-chapters?courseId=" + course.getCourseId());
		request.setAttribute("active_tag", tagChoice);
		request.setAttribute("left_tags", tagsMap.get("left_tags"));
		request.setAttribute("right_tags", tagsMap.get("right_tags"));

		request.setAttribute("crumbs", getCrumbs(course, chapter));

		// 页头
		request.setAttribute("title", "管理章节 " + chapter.getChapterName() + " 的测试题");
		request.setAttribute("title-description", "在这里您可以添加该章节的测试题.");
		request.setAttribute("description", "管理章节 " + chapter.getChapterName() + " 的单选测试题.");

		List list = this.choiceServiceImpl.findChoiceByChapter(chapterId);
		request.setAttribute("page", list);
		request.setAttribute("chapterId", chapterId);
		return "/admin/test/listChoiceTest";
	}

	@RequestMapping(value = "choice/delete")
	public String deleteChoice(@RequestParam("choiceId") Integer choiceId, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Integer chapterId = this.choiceServiceImpl.getChoice(choiceId).getChapter().getChapterId();
		this.choiceServiceImpl.dropChoice(choiceId);
		return "redirect:/admin/choice/list?chapterId=" + chapterId;
	}

	@RequestMapping(value = "choice/disable")
	public String disableChoice(@RequestParam("choiceId") Integer choiceId, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Integer chapterId = this.choiceServiceImpl.getChoice(choiceId).getChapter().getChapterId();
		this.choiceServiceImpl.disable(choiceId);
		return "redirect:/admin/choice/list?chapterId=" + chapterId;
	}

	@RequestMapping(value = "choice/enable")
	public String enableChoice(@RequestParam("choiceId") Integer choiceId, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Integer chapterId = this.choiceServiceImpl.getChoice(choiceId).getChapter().getChapterId();
		this.choiceServiceImpl.enable(choiceId);
		return "redirect:/admin/choice/list?chapterId=" + chapterId;
	}

	@RequestMapping(value = "choice/edit")
	public String editChoice(Choice p, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Integer chapterId = this.choiceServiceImpl.getChoice(p.getChoiceId()).getChapter().getChapterId();
		this.choiceServiceImpl.editChoice(p);
		return "redirect:/admin/choice/list?chapterId=" + chapterId;

	}

	@RequestMapping("choice/add")
	public String addChoice(@RequestParam("chapterId") Integer chapterId, Choice choice, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		this.choiceServiceImpl.addChoice(choice, chapterId);
		return "redirect:/admin/choice/list?chapterId=" + chapterId;
	}


	@RequestMapping("judge/list")
	public String listJudge(
		@RequestParam(name = "chapterId") Integer chapterId,
		HttpServletRequest request,
		HttpSession session
	) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Chapter chapter = this.chapterService.getChapter(chapterId);

		// 标签
		tagChoice.setUrl("/admin/choice/list?chapterId=" + chapterId);
		tagJudge.setUrl("/admin/judge/list?chapterId=" + chapterId);
		tagReturn.setUrl("/admin/course/list-chapters?courseId=" + course.getCourseId());
		request.setAttribute("active_tag", tagJudge);
		request.setAttribute("left_tags", tagsMap.get("left_tags"));
		request.setAttribute("right_tags", tagsMap.get("right_tags"));

		request.setAttribute("crumbs", getCrumbs(course, chapter));

		// 页头
		request.setAttribute("title", "管理章节 " + chapter.getChapterName() + " 的测试题");
		request.setAttribute("title-description", "在这里您可以管理该章节的测试题.");
		request.setAttribute("description", "管理章节 " + chapter.getChapterName() + " 的判断测试题.");

		List list = this.trueFalseServiceImpl.findTrueFalseByChapter(chapterId);
		request.setAttribute("page", list);
		request.setAttribute("chapterId", chapterId);
		return "/admin/test/listJudgeTest";
	}

	@RequestMapping(value = "judge/delete")
	public String deleteJudge(@RequestParam("trueFalseId") Integer trueFalseId, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Integer chapterId= this.trueFalseServiceImpl.getTrueFalse(trueFalseId).getChapter().getChapterId();
		this.trueFalseServiceImpl.dropTrueFalse(trueFalseId);
		return "redirect:/admin/judge/list?chapterId=" + chapterId;
	}

	@RequestMapping(value = "judge/disable")
	public String disableJudge(@RequestParam("trueFalseId")Integer trueFalseId, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Integer chapterId= this.trueFalseServiceImpl.getTrueFalse(trueFalseId).getChapter().getChapterId();
		this.trueFalseServiceImpl.disable(trueFalseId);
		return "redirect:/admin/judge/list?chapterId=" + chapterId;
	}

	@RequestMapping(value = "judge/enable")
	public String enableJudge(@RequestParam("trueFalseId")Integer trueFalseId, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Integer chapterId= this.trueFalseServiceImpl.getTrueFalse(trueFalseId).getChapter().getChapterId();
		this.trueFalseServiceImpl.enable(trueFalseId);
		return "redirect:/admin/judge/list?chapterId=" + chapterId;
	}

	@RequestMapping(value = "judge/edit")
	public String editJudge(TrueFalse p, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Integer chapterId= this.trueFalseServiceImpl.getTrueFalse(p.getTrueFalseId()).getChapter().getChapterId();
		this.trueFalseServiceImpl.editTrueFalse(p);

		return "redirect:/admin/judge/list?chapterId=" + chapterId;

	}

	@RequestMapping("judge/add")
	public String addJudge(@RequestParam("chapterId") Integer chapterId, TrueFalse trueFalse, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理测试题权限
		if (!this.powerService.hasTestManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		this.trueFalseServiceImpl.addTrueFalse(trueFalse,chapterId);

		return "redirect:/admin/judge/list?chapterId=" + chapterId;
	}

}
