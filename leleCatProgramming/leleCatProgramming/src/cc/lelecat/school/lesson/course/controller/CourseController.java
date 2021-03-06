package cc.lelecat.school.lesson.course.controller;

import cc.lelecat.account.worker.service.AccountService;
import cc.lelecat.account.worker.service.PowerService;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.school.lesson.chapter.service.ChapterServiceImpl;
import cc.lelecat.school.lesson.course.service.CourseServiceImpl;
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
import java.util.stream.Collectors;
/*对应的哪个jsp页面*/

/**
 * Created by 张欢 on 2016/11/15.
 */
@Controller
@RequestMapping("admin/course")
public class CourseController {
	@Resource
	private CourseServiceImpl courseServiceImpl;
	@Resource
	private AccountService accountService;
	@Resource
	private ChapterServiceImpl chapterServiceImpl;
	@Resource
	private PowerService powerService;

	// 管理页标签
	private Item tagChoose;
	// 添加页标签
	private Item tagAdd;
	private Item tagDelete;
	private Item tagEdit;
	private Item tagRetrun;
	private Item tagManage;
	// 标签卡属性Map
	private Map<String, Object> tagsMap = new HashMap<>();
	// 面包屑
	private List<Item> crumbs;

	private String title = "管理课程";
	private String description = "管理当前的课程.";

	private String NO_POWER_PAGE = "redirect:/admin/course/listCourse";

	CourseController() {

		// 选择页标签
		tagChoose = new Item("选择课程", "/admin/course/list", "fa-hand-o-up");
		tagAdd = new Item("添加新课程", "/admin/course/add-page", "fa-plus");
		tagAdd.setCmd("tag-success");

		// 管理页标签
		tagManage = new Item("管理章节", "/admin/course/list-chapters", "fa-hand-o-up");
		tagEdit = new Item("编辑课程", "/admin/course/toEdit", "fa-hand-o-up");
		tagRetrun = new Item("返回选择课程", "/admin/course/list", "fa-hand-o-up");
		tagRetrun.setCmd("tag-success");
		tagDelete = new Item("删除课程", "/admin/course/delete-page", "fa-plus");
		tagDelete.setCmd("tag-danger");

		// 选择页面标签
		List<Item> tags = new ArrayList<>();
		tags.add(tagChoose);
		tagsMap.put("choose_left_tags", tags);
		tags = new ArrayList<>();
		tags.add(tagAdd);
		tagsMap.put("choose_right_tags", tags);

		// 管理页面标签
		tags = new ArrayList<>();
		tags.add(tagManage);
		tags.add(tagEdit);
		tagsMap.put("manage_left_tags", tags);
		tags = new ArrayList<>();
		tags.add(tagRetrun);
		tags.add(tagDelete);
		tagsMap.put("manage_right_tags", tags);

	}

	private List<Item> getCrumbs(Course course) {
		// 面包屑
		crumbs = new ArrayList<>();
		Item crumb;

		crumb = new Item("主页", "#");
		crumbs.add(crumb);

		crumb = new Item("选择课程", "/admin/course/list");
		crumbs.add(crumb);

		crumb = new Item(course.getCourseName());
		crumbs.add(crumb);

		return crumbs;
	}

	private List<Item> getCrumbs() {
		// 面包屑
		crumbs = new ArrayList<>();
		Item crumb;

		crumb = new Item("主页", "#");
		crumbs.add(crumb);

		crumb = new Item("选择课程");
		crumbs.add(crumb);

		return crumbs;
	}

	/**
	 * 课程中章节列表页面
	 *
	 * @param courseId
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "list-chapters")
	public String listChapters(
		@RequestParam(name = "courseId") int courseId,
		HttpServletRequest request,
		HttpSession session
	) {

		Account account = (Account) session.getAttribute("account");
		Course course = this.courseServiceImpl.getCourse(courseId);

		// 判断是否有管理课程权限
		if (!this.powerService.hasCourseManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		// 判断是否有测试题管理权限
		if (this.powerService.hasTestManagePower(account.getRole(), course))
			request.setAttribute("test-power", true);
		// 判断是否有章节管理权限
		if (this.powerService.hasChapterManagePower(account.getRole(), course))
			request.setAttribute("chapter-power", true);

		List list = this.chapterServiceImpl.listChapter(courseId);

		// 设置面包屑
		request.setAttribute("crumbs", this.getCrumbs(course));

		// 设置标签
		tagManage.setUrl("/admin/course/list-chapters?courseId=" + courseId);
		tagEdit.setUrl("/admin/course/toEdit?courseId=" + courseId);
		tagDelete.setUrl("/admin/course/delete-page?courseId=" + courseId);

		request.setAttribute("active_tag", tagManage);
		request.setAttribute("left_tags", tagsMap.get("manage_left_tags"));
		request.setAttribute("right_tags", tagsMap.get("manage_right_tags"));

		request.setAttribute("title", title + ": " + course.getCourseName());
		request.setAttribute("title-description", description);
		request.setAttribute("description", "管理" + course.getCourseName() + "中的章节.");

		request.setAttribute("page", list);
		request.setAttribute("courseId", courseId);
		request.setAttribute("courseName", course.getCourseName());

		session.setAttribute("course", course);

		return "/admin/course/listChapter";
	}

	/**
	 * 课程删除页面
	 * @param courseId
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("delete-page")
	public String deletePage(@RequestParam("courseId") Integer courseId, HttpServletRequest request, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		Course course = this.courseServiceImpl.getCourse(courseId);

		// 判断是否有管理课程权限
		if (!this.powerService.hasCourseManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		// 设置面包屑
		request.setAttribute("crumbs", this.getCrumbs(course));

		// 设置标签
		tagManage.setUrl("/admin/course/list-chapters?courseId=" + courseId);
		tagEdit.setUrl("/admin/course/toEdit?courseId=" + courseId);
		tagDelete.setUrl("/admin/course/delete-page?courseId=" + courseId);

		request.setAttribute("active_tag", tagDelete);
		request.setAttribute("left_tags", tagsMap.get("manage_left_tags"));
		request.setAttribute("right_tags", tagsMap.get("manage_right_tags"));

		request.setAttribute("title", title + course.getCourseName());
		request.setAttribute("title-description", description);
		request.setAttribute("description", "删除课程: " + course.getCourseName() + ".");

		request.setAttribute("courseName", course.getCourseName());

		return "/admin/course/deleteCourse";
	}

	/**
	 * 添加课程动作
	 * @param course
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add")
	public String add(Course course, HttpSession session,HttpServletRequest request) throws Exception {

		Account account = (Account) session.getAttribute("account");
		// 判断是否有添加课程权限
		if (!this.powerService.hasAddCoursePower(account.getRole())) return NO_POWER_PAGE;
		this.courseServiceImpl.addCourse(course,request);

		return "redirect:list";
	}

	/**
	 * 添加课程页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("add-page")
	public String addPage(HttpServletRequest request, HttpSession session) {

		Account account = (Account) session.getAttribute("account");
		// 判断是否有添加课程权限
		if (!this.powerService.hasAddCoursePower(account.getRole())) return NO_POWER_PAGE;

		// 设置面包屑
		request.setAttribute("crumbs", this.getCrumbs());

		// 设置标签
		request.setAttribute("active_tag", tagAdd);
		request.setAttribute("left_tags", tagsMap.get("choose_left_tags"));
		request.setAttribute("right_tags", tagsMap.get("choose_right_tags"));

		request.setAttribute("title", title);
		request.setAttribute("title-description", description);
		request.setAttribute("description", "添加一个新的课程.");

		return "/admin/course/addCourse";
	}

	/**
	 * 编辑页面
	 * @param courseId
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(
		@RequestParam("courseId") int courseId,
		HttpServletRequest request,
		HttpSession session
	) {

		Account account = (Account) session.getAttribute("account");
		Course course = this.courseServiceImpl.getCourse(courseId);
		// 判断是否有管理课程权限
		if (!this.powerService.hasCourseManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		// 设置面包屑
		request.setAttribute("crumbs", this.getCrumbs(course));

		// 设置标签
		tagManage.setUrl("/admin/course/list-chapters?courseId=" + courseId);
		tagEdit.setUrl("/admin/course/toEdit?courseId=" + courseId);
		tagDelete.setUrl("/admin/course/delete-page?courseId=" + courseId);

		request.setAttribute("active_tag", tagEdit);
		request.setAttribute("left_tags", tagsMap.get("manage_left_tags"));
		request.setAttribute("right_tags", tagsMap.get("manage_right_tags"));

		request.setAttribute("title", title + course.getCourseName());
		request.setAttribute("title-description", description);
		request.setAttribute("description", "编辑课程: " + course.getCourseName() + ".");

		request.setAttribute("course", course);

		return "/admin/course/editCourse";
	}

	/**
	 * 编辑课程动作
	 * @param c
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit")
	public String edit(Course c, HttpSession session,HttpServletRequest request) {

		Account account = (Account) session.getAttribute("account");
		Course course = this.courseServiceImpl.getCourse(c.getCourseId());
		// 判断是否有管理课程权限
		if (!this.powerService.hasCourseManagePower(account.getRole(), course)) return NO_POWER_PAGE;
		this.courseServiceImpl.editCourse(c,request);

		return "redirect:list-chapters?courseId=" + c.getCourseId();
	}

	/**
	 * 删除课程动作
	 * @param courseId
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delete(
		@RequestParam("courseId") Integer courseId,
		@RequestParam("password") String password,
		HttpSession session,
		HttpServletRequest request
	) {

		Account account = (Account) session.getAttribute("account");
		Course course = this.courseServiceImpl.getCourse(courseId);

		// 判断是否有管理课程权限
		if (!this.powerService.hasCourseManagePower(account.getRole(), course)) return NO_POWER_PAGE;

		if (this.accountService.checkPassowrd(account, password)) {
			this.courseServiceImpl.dropCourse(courseId,request);
		} else {
			System.out.println("删除失败");
		}
		return "redirect:list";
	}

	/**
	 * 课程列表页
	 *
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpSession session) {

		Account account = (Account) session.getAttribute("account");

		List<Course> courses = this.courseServiceImpl.findAll();

		// 过滤没有权限查看的课程条目
		courses = courses.stream().
			filter(course -> powerService.hasCourseManagePower(account.getRole(), course)).
			collect(Collectors.toList());
		request.setAttribute("courses", courses);


		// 设置面包屑
		request.setAttribute("crumbs", this.getCrumbs());

		// 设置标签
		request.setAttribute("active_tag", tagChoose);
		request.setAttribute("left_tags", tagsMap.get("choose_left_tags"));
		request.setAttribute("right_tags", tagsMap.get("choose_right_tags"));

		request.setAttribute("title", "选择课程");
		request.setAttribute("title-description", "在这里您可以选择一个课程进行更改或者增加一个新的课程.");
		request.setAttribute("description", "选择一个您要管理的课程.");

		return "/admin/course/listCourse";

	}

}