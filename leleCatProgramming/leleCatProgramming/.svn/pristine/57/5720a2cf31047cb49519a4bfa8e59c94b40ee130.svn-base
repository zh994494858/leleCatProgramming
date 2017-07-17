package cc.lelecat.school.test.service;

import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.test.TrueFalse;
import cc.lelecat.framework.Page;
import cc.lelecat.school.test.dao.TrueFalseDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kishow on 2016-11-21.
 */
@Service
@Transactional(readOnly=false)
public class TrueFalseServiceImpl {
    @Resource
    private TrueFalseDaoImpl trueFalseDaoImpl;

    public void addTrueFalse(TrueFalse p, Integer chapterId){
        p.setChapter(this.trueFalseDaoImpl.findChapterByChapter(chapterId));
        p.setDisabled(false);
        this.trueFalseDaoImpl.saveTrueFalse(p);
    }
    //自己编写的通过id查询chapter对象
    public Chapter findChapterByChapter(Integer chapterId){
        return this.trueFalseDaoImpl.findChapterByChapter(chapterId);
    }

    //自己编写的通过chapterId查询出courseId
    @Transactional(readOnly = true)
    public  Integer findCourseIdByChapterId(Integer chapterId){
        return this.trueFalseDaoImpl.findChapterByChapter(chapterId).getCourse().getCourseId();

    }


    @Transactional(readOnly=true)
    public Page<TrueFalse> listTrueFalse(int pageNum, int pageSize, Object[] params){
        return this.trueFalseDaoImpl.findTrueFalse(pageNum, pageSize, params);
    }
    //自己编写的查询对映的chapterId的对象
    @Transactional(readOnly = true)
    public List<TrueFalse> findTrueFalseByChapter(int chapterId){
        return this.trueFalseDaoImpl.findTrueFalseByChapter(chapterId);
    }
    @Transactional(readOnly=true)
    public TrueFalse getTrueFalse(int trueFalseId){
        return this.trueFalseDaoImpl.getTrueFalse(trueFalseId);
    }

    public void disable(Integer trueFalseId){
        TrueFalse pdb=this.trueFalseDaoImpl.getTrueFalse(trueFalseId);
        pdb.setDisabled(true);
        this.trueFalseDaoImpl.updateTrueFalse(pdb);
    }
    public void enable(Integer trueFalseId){
        TrueFalse pdb=this.trueFalseDaoImpl.getTrueFalse(trueFalseId);
        pdb.setDisabled(false);
        this.trueFalseDaoImpl.updateTrueFalse(pdb);
    }
    public void editTrueFalse(TrueFalse p){
        TrueFalse pdb=this.trueFalseDaoImpl.getTrueFalse(p.getTrueFalseId());
        pdb.setAnswer(p.getAnswer());
        pdb.setItemContent(p.getItemContent());

        this.trueFalseDaoImpl.updateTrueFalse(pdb);
    }

    public void dropTrueFalse(int trueFalseId){
        this.trueFalseDaoImpl.deleteTrueFalse(trueFalseId);
    }
}
