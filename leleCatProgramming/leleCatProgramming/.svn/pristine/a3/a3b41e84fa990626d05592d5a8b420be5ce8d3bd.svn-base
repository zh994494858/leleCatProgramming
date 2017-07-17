package cc.lelecat.email.addToDustbin.service;

import cc.lelecat.email.addToDustbin.dao.AddToDustbinDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * Created by 王冀琛
 * on 2016/12/1.
 *
 * 添加到垃圾箱的service
 */

@Service
@Transactional(readOnly = false)
public class AddToDustbinServiceImpl {

	@Resource
	private AddToDustbinDaoImpl addToDustbinDao;

	public void moveToDustbin(String params,Integer accountId){

		if (params == null){
			return;
		}else {

			String str[] = params.split (",");

			BigInteger[] emailIds = new BigInteger[str.length];

			for (int i = 0; i < str.length; i++) {
				emailIds[i] = BigInteger.valueOf (Integer.parseInt (str[i]));
			}
			this.addToDustbinDao.updateDeleteState (emailIds,accountId);
		}
	}

}
