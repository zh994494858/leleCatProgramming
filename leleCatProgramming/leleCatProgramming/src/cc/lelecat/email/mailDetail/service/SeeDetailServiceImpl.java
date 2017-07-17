package cc.lelecat.email.mailDetail.service;

import cc.lelecat.email.mailDetail.dao.SeeDetailDaoImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * Created by 王冀琛
 *on 2016/12/3.
 * 查看邮件内容
 */

@Service
@Transactional(readOnly = true)
public class SeeDetailServiceImpl {

	@Resource
	private SeeDetailDaoImpl seeDetailDao;

	public Email seeEmailDetail(Long emialId){

		return this.seeDetailDao.findEmialDetailByEmailId (emialId);
	}

}
