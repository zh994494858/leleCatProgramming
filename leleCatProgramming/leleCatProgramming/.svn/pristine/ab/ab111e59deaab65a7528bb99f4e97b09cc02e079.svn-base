package cc.lelecat.email.dustbin.service;

import cc.lelecat.email.dustbin.dao.DeleteEmailDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * Created by 王冀琛
 * on 2016/12/2.
 * 将邮件彻底删除
 */

@Service
@Transactional(readOnly = false)
public class DeleteEmailServiceImpl {

	@Resource
	private DeleteEmailDaoImpl deleteEmailDao;

	public void deleteEmail(Integer accountId,String params){

		if (params == null){
			return;
		}else {

			String str[] = params.split (",");

			BigInteger[] emailIds = new BigInteger[str.length];

			for (int i = 0; i < str.length; i++) {
				emailIds[i] = BigInteger.valueOf (Integer.parseInt (str[i]));
			}
			this.deleteEmailDao.deleteByEmailIds (accountId, emailIds);
		}
	}

}
