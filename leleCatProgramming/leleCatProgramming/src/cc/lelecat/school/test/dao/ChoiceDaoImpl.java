package cc.lelecat.school.test.dao;

import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.test.Choice;
import cc.lelecat.entity.school.test.TrueFalse;
import cc.lelecat.framework.BaseDao;
import cc.lelecat.framework.Escape;
import cc.lelecat.framework.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kishow on 2016-11-24.
 * 选择题对应的dao层
 */
@Repository
public class ChoiceDaoImpl extends BaseDao<Choice, Integer> {
    private Choice escape(Choice choice) {
        choice.setItemContent(Escape.encode(choice.getItemContent()));
        choice.setOptionA(Escape.encode(choice.getOptionA()));
        choice.setOptionB(Escape.encode(choice.getOptionB()));
        choice.setOptionC(Escape.encode(choice.getOptionC()));
        return choice;
    }

    @Override
    public boolean save(Choice entity) {
        return super.save(escape(entity));
    }

    @Override
    public boolean update(Choice entity) {
        return super.update(escape(entity));
    }

    @Resource
    private SessionFactory sessionFactory;
    public void saveChoice(Choice p){
        try {
            this.save(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询属于此章节的所有课程信息
    public List<Choice> findChoiceByChapter(Integer chapterId){
        List list2=null;
        Session session=this.sessionFactory.openSession();
        List list = session.createQuery("from Chapter c where c.chapterId = "+chapterId)
                .list();
        if(list.size()!=0){
            list2=session.createQuery("from Choice c where c.chapter=?").setParameter(0,list.get(0))
                    .list();
    }
        return list2;
    }
    public Choice getChoice(int choiceId){
        try {
            Choice p=this.get(choiceId);
            return p;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void updateChoice(Choice p){
        try {
            this.update(p);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //通过章节ID查询章节对象
    public Chapter findChapterByChapter(Integer chapterId){
        List list2=null;
        Session session=this.sessionFactory.openSession();
        List list = session.createQuery("from Chapter c where c.chapterId = "+chapterId)
                .list();
        if(list.size()!=0){
            return (Chapter)list.get(0);
        }
        else{
            return null;
        }
    }
    public void deleteChoice(int choiceId){
        try {
            this.delete(choiceId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
