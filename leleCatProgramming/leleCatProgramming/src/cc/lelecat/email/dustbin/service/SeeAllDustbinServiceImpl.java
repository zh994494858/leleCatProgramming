package cc.lelecat.email.dustbin.service;

import cc.lelecat.email.dustbin.dao.SeeAllDustbinDaoImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 查看所有的垃圾邮件
 */

@Service
@Transactional(readOnly = true)
public class SeeAllDustbinServiceImpl {

	@Resource
	private SeeAllDustbinDaoImpl seeAllDustbinDao;

	public Map<Email,String> findAllDustbinEmail(Integer accountId){
		return this.seeAllDustbinDao.findAllDustbinByAccountId (accountId);
	}

}
