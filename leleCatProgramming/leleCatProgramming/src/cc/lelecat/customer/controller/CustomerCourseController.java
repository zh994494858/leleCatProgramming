package cc.lelecat.customer.controller;

import cc.lelecat.customer.service.LearnProgressServiceImpl;
import cc.lelecat.entity.account.customer.NormalAccount;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.entity.school.test.Choice;
import cc.lelecat.entity.school.test.TrueFalse;
import cc.lelecat.framework.Escape;
import cc.lelecat.framework.Page;
import cc.lelecat.school.lesson.chapter.service.ChapterServiceImpl;
import cc.lelecat.school.lesson.course.service.CourseServiceImpl;
import cc.lelecat.school.test.service.ChoiceServiceImpl;
import cc.lelecat.school.test.service.TrueFalseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mhy on 2016/12/6.
 */

@Controller
@RequestMapping("course")
public class CustomerCourseController {

    @Resource
    private CourseServiceImpl courseService;
    @Resource
    private ChoiceServiceImpl choiceServiceImpl;
    @Resource
    private ChapterServiceImpl chapterService;
    @Resource
    private TrueFalseServiceImpl trueFalseServiceImpl;
    @Resource
    private LearnProgressServiceImpl learnProgressService;

    /*controller:用户点击登录，验证用户名和密码*/
    @ResponseBody
    @RequestMapping(value = "get-courses",method = RequestMethod.POST)
    public Map<String, Object> getCourses() {

        List<Course> courses = this.courseService.findAll();
        courses = courses.stream().
            filter(Course::getOpenOrNot).
            collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();

        result.put("title", "学堂");

        List<Map> items = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", course.getCourseId());
            item.put("title", Escape.decode(course.getCourseName()));
            item.put("icon", course.getImgUrl());
            item.put("info", Escape.decode(course.getCourseIntroduce()));
            item.put("dist", "ChooseSection");
            items.add(item);
        }
        result.put("items", items);

        return result;
    }

    @ResponseBody
    @RequestMapping("get-sections")
    public Map<String, Object> getSections(@RequestBody Integer courseId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();

        NormalAccount account = (NormalAccount) session.getAttribute("customer");
        Course course = this.courseService.getCourse(courseId);

        // 查找学习到的章节
        Chapter nextChapter = this.learnProgressService.getNextChapter(account, course);

        result.put("title", course.getCourseName());

        // 章节列表
        List<Map> items = new ArrayList<>();
        // 按index排序章节
        List<Chapter> chapters = course.getChapters().stream().sorted((c1, c2) -> {
            int idx1 = c1.getChapterIndex() == null ? 0 : c1.getChapterIndex();
            int idx2 = c2.getChapterIndex() == null ? 0 : c2.getChapterIndex();

            return idx1 - idx2;
        }).collect(Collectors.toList());
        for (Chapter chapter : chapters) {
            // 下一个可学习的章节
            if (nextChapter != null && chapter.getChapterId().equals(nextChapter.getChapterId())) userInfo.put("nextItem", items.size());

            Map<String, Object> item = new HashMap<>();
            item.put("id", chapter.getChapterId());
            item.put("title", Escape.decode(chapter.getChapterName()));
            item.put("img", chapter.getChapterImg());
            item.put("info", Escape.decode(chapter.getChapterIntroduce()));
            items.add(item);
        }
        result.put("items", items);

        userInfo.putIfAbsent("nextItem", 0);
        result.put("userInfo", userInfo);


        // 将所有章节和下一章节添加到session
        session.setAttribute("chapters", chapters);
        session.setAttribute("nextChapter", nextChapter);

        return result;
    }

    @ResponseBody
    @RequestMapping("test")
    public Map<String, Object> test(@RequestBody Integer chapterId) {

        Map<String, Object> result = new HashMap<>();

        Chapter chapter = this.chapterService.getChapter(chapterId);
        // 过滤掉没有禁用的测试题
        List<Choice> choices = this.choiceServiceImpl.findChoiceByChapter(chapterId).stream().
            filter(c -> !c.getDisabled()).collect(Collectors.toList());
        List<TrueFalse> trueFalses = this.trueFalseServiceImpl.findTrueFalseByChapter(chapterId).stream().
            filter(c -> !c.getDisabled()).collect(Collectors.toList());

        int numChoice = chapter.getChoiceNumber();
        int numJudge = chapter.getTrueFalseNumber();

        Random random = new Random();
        Set<Choice> choiceList = new HashSet<>();
        Set<TrueFalse> trueFalseList = new HashSet<>();

        // 随机查找指定数量的测试题
        while (choiceList.size() < choices.size() && choiceList.size() < numChoice)
            choiceList.add(choices.get(random.nextInt(choices.size())));
        while (trueFalseList.size() < trueFalses.size() && trueFalseList.size() < numJudge)
            trueFalseList.add(trueFalses.get(random.nextInt(trueFalses.size())));

        List<Object> questions = new ArrayList<>();
        for (Choice choice : choiceList) {
            Map<String, Object> question = new HashMap<>();
            question.put("type", "single_choice");
            question.put("question", Escape.decode(choice.getItemContent()));
            Object[] answers = new Object[3];
            answers[0] = Escape.decode(choice.getOptionA());
            answers[1] = Escape.decode(choice.getOptionB());
            answers[2] = Escape.decode(choice.getOptionC());
            question.put("choices", answers);
            question.put("answer", choice.getAnswer());
            questions.add(question);
        }
        for (TrueFalse trueFalse : trueFalseList) {
            Map<String, Object> question = new HashMap<>();
            question.put("type", "judgement");
            question.put("question", Escape.decode(trueFalse.getItemContent()));
            question.put("answer", trueFalse.getAnswer());
            questions.add(question);
        }
        result.put("questions", questions);

        Map<String, Object> standard = new HashMap<>();
        standard.put("pass-grade", chapter.getPassStandard());
        result.put("pass-standard", standard);

        return result;
    }

    @RequestMapping("play")
    @ResponseBody
    public Map<String, Object> play(@RequestBody Integer chapterId) {
        Chapter chapter = this.chapterService.getChapter(chapterId);
        Map<String, Object> result = new HashMap<>();

        result.put("pluginId", chapter.getIdentification());

        return result;
    }

    @RequestMapping("test/pass")
    @ResponseBody
    public void testPass(@RequestBody Integer chapterId, HttpSession session) {

        Chapter nextChapter = (Chapter) session.getAttribute("nextChapter");
        if (!nextChapter.getChapterId().equals(chapterId)) return;

        NormalAccount account = (NormalAccount) session.getAttribute("customer");
        List<Chapter> chapters = (List<Chapter>) session.getAttribute("chapters");

        System.out.println("chapters.size: " + chapters.size());

        for (int i = 0; i < chapters.size(); i++) {
			if (chapters.get(i).getChapterId().equals(chapterId)) {

				if (i < chapters.size() - 1) {
					Chapter chapter = chapters.get(i+1);
					this.learnProgressService.update(account, chapter.getCourse(), chapter);
				}
				break;
			}
		}

    }
}
