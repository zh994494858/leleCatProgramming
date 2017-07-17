package cc.lelecat.customer.controller;

import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.framework.Escape;
import cc.lelecat.school.lesson.course.service.CourseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mhy on 2016/12/6.
 */

@Controller
@RequestMapping("lab")
public class CustomerLabController {

	@Resource
	private CourseServiceImpl courseService;

	/*controller:用户点击实验室，发送编程语言*/
	@ResponseBody
	@RequestMapping(value = "get-labs", method = RequestMethod.POST)
	public Map<String, Object> getLabs() {

		List<Course> courses = this.courseService.findAll();
		courses = courses.stream().
			filter(course -> course.getOpenOrNot() && course.getEnableAtLab()).
			collect(Collectors.toList());

		Map<String, Object> result = new HashMap<>();

		result.put("title", "实验室");

		List<Map> items = new ArrayList<>();
		for (Course course : courses) {
			Map<String, Object> item = new HashMap<>();
			item.put("id", course.getCourseId());
			item.put("title", Escape.decode(course.getCourseName()));
			item.put("icon", course.getImgUrl());
			item.put("info", Escape.decode(course.getCourseIntroduce()));
			item.put("cl-plugin-id", course.getClPlugin());
			item.put("dist", "CodeLab");
			items.add(item);
		}
		result.put("items", items);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "get",method = RequestMethod.POST)
	public Map<String, Object> getLab(@RequestBody Integer courseId) {
		Map<String, Object> result = new HashMap<>();

		Course course = this.courseService.getCourse(courseId);

		result.put("title", Escape.decode(course.getCourseName()));

		return result;
	}

}
