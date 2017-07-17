package cc.lelecat.school.test.dao;

import cc.lelecat.entity.school.lesson.Chapter;
import cc.lelecat.entity.school.test.TrueFalse;
import cc.lelecat.framework.BaseDao;
import cc.lelecat.framework.Escape;
import cc.lelecat.framework.Page;
import javassist.runtime.Inner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kishow on 2016-11-21.
 *判断题对应的dao层
 */
@Repository
public class TrueFalseDaoImpl extends BaseDao<TrueFalse, Integer> {
    @Resource
    private SessionFactory sessionFactory;

    private TrueFalse escape(TrueFalse trueFalse) {
        trueFalse.setItemContent(Escape.encode(trueFalse.getItemContent()));
        return trueFalse;
    }

    @Override
    public boolean save(TrueFalse entity) {
        return super.save(escape(entity));
    }

    @Override
    public boolean update(TrueFalse entity) {
        return super.update(escape(entity));
    }

    public void saveTrueFalse(TrueFalse p){
        try {
            this.save(p);
        } catch (Exception e) {
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

    //查询属于此章节的所有课程信息
    public List<TrueFalse> findTrueFalseByChapter(Integer chapterId){
        List list2=null;
        Session session=this.sessionFactory.openSession();
        List list = session.createQuery("from Chapter c where c.chapterId = "+chapterId)
                .list();
        if(list.size()!=0){
            list2=session.createQuery("from TrueFalse p where p.chapter=?").setParameter(0,list.get(0))
                    .list();
        }
        return list2;
    }

    public Page<TrueFalse> findTrueFalse(int pageNum, int pageSize, Object[] params){
        String hql;
        if(params!=null && params.length>0){
            hql="from TrueFalse p where p.trueFalseName like ";
            params[0]="%"+params[0]+"%";

        }else{
            hql="from TrueFalse";
        }
        try {
            Page<TrueFalse> page=new Page<TrueFalse>();
            page.setCurrentPageNum(pageNum);
            page.setPageSize(pageSize);
            page=this.findByPage(pageNum, pageSize, hql, params);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TrueFalse getTrueFalse(int trueFalseId){
        try {
            TrueFalse p=this.get(trueFalseId);
            return p;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void updateTrueFalse(TrueFalse p){
        try {
            this.update(p);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteTrueFalse(Integer trueFalseId){
        try {
            this.delete(trueFalseId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
