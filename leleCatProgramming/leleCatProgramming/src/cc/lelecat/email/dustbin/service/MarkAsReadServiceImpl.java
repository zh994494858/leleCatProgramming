package cc.lelecat.email.dustbin.service;

import cc.lelecat.email.dustbin.dao.MarkAsReadDaoImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/12/2.
 * 标为已读邮件的service层
 */

@Service
@Transactional(readOnly = false)
public class MarkAsReadServiceImpl {

	@Resource
	private MarkAsReadDaoImpl markAsReadDao;

	public void updateTheReadState(Integer accountId, Map<String,String> emails){
		if (emails.size () <= 0){
			return;
		}else{

			String emailId = null;

			for (Map.Entry<String,String> entry:emails.entrySet ()){
				emailId = entry.getValue ();
				if (emailId != null){
					break;
				}
			}

			String str[] = emailId.split (",");
			BigInteger[]emailIds = new BigInteger[str.length];
			for (int i = 0;i<str.length;i++){
				emailIds[i] = BigInteger.valueOf (Integer.parseInt (str[i]));
			}

			this.markAsReadDao.updateByEmailIds (accountId,emailIds);

			return;
		}
	}

}
