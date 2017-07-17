package cc.lelecat.customer.service;

import cc.lelecat.customer.dao.LabDaoImpl;
import cc.lelecat.entity.lab.PL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mhy on 2016/12/6.
 */
@Service
@Transactional(readOnly=false)
public class LabServiceImpl {
    @Resource
    private LabDaoImpl labDao;

    public List<PL> findAll() {
        return this.labDao.findAll();
    }
}
