package cc.lelecat.school.test.service;

import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.test.Choice;
import cc.lelecat.school.test.dao.ChoiceDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kishow on 2016-11-24.
 */
@Service
@Transactional(readOnly=false)
public class ChoiceServiceImpl {
    @Resource
   private ChoiceDaoImpl choiceDaoImpl;

    public void addChoice(Choice p, Integer chapterId){
        p.setChapter(this.choiceDaoImpl.findChapterByChapter(chapterId));
        p.setDisabled(false);
        this.choiceDaoImpl.saveChoice(p);
    }

    @Transactional(readOnly=true)
    public Choice getChoice(int choiceId){
        return this.choiceDaoImpl.getChoice(choiceId);
    }

    public void editChoice(Choice p){
        Choice pdb=this.choiceDaoImpl.getChoice(p.getChoiceId());
        pdb.setItemContent(p.getItemContent());
        pdb.setAnswer(p.getAnswer());
        pdb.setOptionA(p.getOptionA());
        pdb.setOptionB(p.getOptionB());
        pdb.setOptionC(p.getOptionC());

        this.choiceDaoImpl.updateChoice(pdb);
    }

    //自己编写的通过id查询chapter对象
    public Chapter findChapterByChapter(Integer chapterId){
        return this.choiceDaoImpl.findChapterByChapter(chapterId);
    }

    //自己编写的通过chapterId查询出courseId
    @Transactional(readOnly = true)
    public  Integer findCourseIdByChapterId(Integer chapterId){
        return this.choiceDaoImpl.findChapterByChapter(chapterId).getCourse().getCourseId();

    }

    //自己编写的查询对映的chapterId的对象
    @Transactional(readOnly = true)
    public List<Choice> findChoiceByChapter(int chapterId){
        return this.choiceDaoImpl.findChoiceByChapter(chapterId);
    }
    public void dropChoice(int choiceId){
        this.choiceDaoImpl.deleteChoice(choiceId);
    }
    //修改是否禁用
    public void disable(Integer choiceId){
        Choice pdb=this.choiceDaoImpl.getChoice(choiceId);
        pdb.setDisabled(true);
        this.choiceDaoImpl.updateChoice(pdb);
    }

    public void enable(Integer choiceId) {
        Choice pdb=this.choiceDaoImpl.getChoice(choiceId);
        pdb.setDisabled(false);
        this.choiceDaoImpl.updateChoice(pdb);
    }
}
