package cc.lelecat.school.lesson.chapter.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import cc.lelecat.account.worker.service.AccountService;
import cc.lelecat.account.worker.service.PowerService;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.school.lesson.course.service.CourseServiceImpl;
import cc.lelecat.tag.menu.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.school.lesson.chapter.service.ChapterServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*对应的哪个jsp页面*/

/**
 * Class:ChapterController
 * Created by 王子恒 on 2016/11/24.
 * 功能：实现chapter（章节）的增删改查
 */
@Controller
@RequestMapping("admin/chapter")
public class ChapterController {
	@Resource
    private ChapterServiceImpl chapterServiceImpl;
	@Resource
	private PowerService powerService;
	@Resource
	private AccountService accountService;

	private String title = "管理课程";
	private String description = "在这里您可以管理课程或者删除该课程.";

	private String NO_POWER_PAGE = "redirect:/admin/course/listCourse";

	@RequestMapping("add-page")
	public String addPage(HttpServletRequest request, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		// 标签
		Item tagAdd;
		Item tagReturn;
		Map<String, Object> tagsMap = new HashMap<>();

		tagAdd = new Item("添加章节", null, "fa-plus");
		tagReturn = new Item("返回", "/admin/course/list-chapters?courseId=" + course.getCourseId(), "fa-plus");
		tagReturn.setCmd("tag-success");

		List<Item> tags = new ArrayList<>();
		tags.add(tagAdd);
		tagsMap.put("left_tags", tags);
		tags = new ArrayList<>();
		tags.add(tagReturn);
		tagsMap.put("right_tags", tags);

		request.setAttribute("active_tag", tagAdd);
		request.setAttribute("left_tags", tagsMap.get("left_tags"));
		request.setAttribute("right_tags", tagsMap.get("right_tags"));


		// 面包屑
		List<Item> crumbs = new ArrayList<>();
		Item crumb;

		crumb = new Item("主页", "#");
		crumbs.add(crumb);
		crumb = new Item("选择课程", "/admin/course/list");
		crumbs.add(crumb);
		crumb = new Item(course.getCourseName(), "/admin/course/list-chapters?courseId=" + course.getCourseId());
		crumbs.add(crumb);
		crumb = new Item("添加章节", null);
		crumbs.add(crumb);

		request.setAttribute("crumbs", crumbs);

		// 页头
		request.setAttribute("title", title + ": " + course.getCourseName());
		request.setAttribute("title-description", description);
		request.setAttribute("description", "在课程" + course.getCourseName() + "添加一个新的章节.");

		request.setAttribute("courseId", course.getCourseId());

		return "/admin/chapter/addChapter";
	}


	@RequestMapping("pass-standard")
	public String passStandardPage(
		@RequestParam("chapterId") Integer chapterId,
		HttpServletRequest request,
		HttpSession session
	) {

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Chapter chapter = this.chapterServiceImpl.getChapter(chapterId);

		Item tagAdd;
		Item tagReturn;
		// 标签卡属性Map
		Map<String, Object> tagsMap = new HashMap<>();
		// 面包屑
		List<Item> crumbs;

		// 标签
		tagAdd = new Item("通过标准", null, "fa-plus");
		tagReturn = new Item("返回",
			"/admin/course/list-chapters?courseId=" + chapter.getCourse().getCourseId(), "fa-plus");
		tagReturn.setCmd("tag-success");

		List<Item> tags = new ArrayList<>();
		tags.add(tagAdd);
		tagsMap.put("left_tags", tags);
		tags = new ArrayList<>();
		tags.add(tagReturn);
		tagsMap.put("right_tags", tags);

		// 面包屑
		crumbs = new ArrayList<>();
		Item crumb;

		crumb = new Item("主页", null);
		crumbs.add(crumb);
		crumb = new Item("选择课程", "/admin/course/list");
		crumbs.add(crumb);
		crumb = new Item(chapter.getCourse().getCourseName(),
			"/admin/course/list-chapters?courseId=" + chapter.getCourse().getCourseId());
		crumbs.add(crumb);
		crumb = new Item("通过标准", null);
		crumbs.add(crumb);

		// 设置面包屑
		request.setAttribute("crumbs", crumbs);

		// 设置标签
		tagReturn.setUrl("/admin/course/list-chapters?courseId=" + chapter.getCourse().getCourseId());

		request.setAttribute("active_tag", tagAdd);
		request.setAttribute("left_tags", tagsMap.get("left_tags"));
		request.setAttribute("right_tags", tagsMap.get("right_tags"));

		request.setAttribute("title", title + ": " + chapter.getCourse().getCourseName());
		request.setAttribute("title-description", description);
		request.setAttribute("description", "设置章节" + chapter.getChapterName() + "的测验题通过标准.");

		request.setAttribute("courseId", chapter.getCourse().getImgUrl());

		request.setAttribute("chapter", chapter);

		return "/admin/chapter/pass";
	}

    //增加功能
    @RequestMapping(value = "add")
    public String add(Chapter c, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

        this.chapterServiceImpl.addChapter(c, course.getCourseId());
        return "redirect:/admin/course/list-chapters?courseId=" + course.getCourseId();
    }

    //编辑功能
    @RequestMapping("edit")
    public String edit(Chapter c, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		this.chapterServiceImpl.editChapter(c);

        return "redirect:/admin/course/list-chapters?courseId=" + course.getCourseId();
    }


    //在pass.jsp页面中修改选择，判断题的个数与通过标准
    @RequestMapping(value="editNP")
    public String editNP(Chapter c, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

        this.chapterServiceImpl.editNP(c);
        return "redirect:/admin/course/list-chapters?courseId="+ course.getCourseId();
    }

    //删除功能
    @RequestMapping(value="delete")
    public String delete(
    	@RequestParam("chapterId") Integer chapterId,
		@RequestParam(value = "password", required = false) String password,
		HttpSession session
	){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

        Chapter cha = this.chapterServiceImpl.getChapter(chapterId);

		if (this.accountService.checkPassowrd(account, password)) {
			this.chapterServiceImpl.dropChapter(chapterId);
		} else {
			System.out.println("删除失败");
		}

        return "redirect:/admin/course/list-chapters?courseId="+cha.getCourse().getCourseId();
    }

    // 禁用功能
    @RequestMapping(value = "disable")
    public String disable(@RequestParam("chapterId") Integer chapterId, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

        Chapter chapter = this.chapterServiceImpl.getChapter(chapterId);
		chapter.setBlock(true);

		this.chapterServiceImpl.editChapter(chapter);

        return "redirect:/admin/course/list-chapters?courseId=" + course.getCourseId();
    }

	// 启用功能
	@RequestMapping(value = "enable")
	public String enable(@RequestParam("chapterId") Integer chapterId, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		Chapter chapter = this.chapterServiceImpl.getChapter(chapterId);
		chapter.setBlock(false);

		this.chapterServiceImpl.editChapter(chapter);

		return "redirect:/admin/course/list-chapters?courseId=" + course.getCourseId();
	}

    //上移功能
    @RequestMapping(value = "moveonit")
    public String moveonChapter(@RequestParam("chapterId") Integer chapterId, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

        this.chapterServiceImpl.editMoveon(chapterId);

        return "redirect:/admin/course/list-chapters?courseId=" + course.getCourseId();
    }

    //下移功能
    @RequestMapping(value = "movedownit")
    public String movedownChapter(@RequestParam("chapterId") Integer chapterId, HttpSession session){

		Account account = (Account) session.getAttribute("account");
		Course course = (Course) session.getAttribute("course");

		// 判断是否有管理章节权限
		if (!this.powerService.hasChapterManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		this.chapterServiceImpl.editMovedown(chapterId);

        return "redirect:/admin/course/list-chapters?courseId=" + course.getCourseId();
    }
}


