package cc.lelecat.email.writeEmail.service;

import cc.lelecat.email.writeEmail.dao.AddEmailDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 添加邮件
 */

@Service
@Transactional(readOnly = false)
public class AddEmailServiceImpl {

	@Resource
	private AddEmailDaoImpl addEmailDao;

	public void addEmailToDrafts(String emailId,String accountName,String emailTheme,String emailContent,Integer accountId){

		//得到请求页面的时间,定为该邮件的 编辑时间 or 发送时间 or 接收时间
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(currentTime);

		if (emailId.length ()>0){
			BigInteger id = new BigInteger (emailId);
			this.addEmailDao.deleteOriginalById (accountId,id);
		}

		//如果邮件的内容完整，则可以存储到草稿箱
		if (accountId > 0 && accountName.length ()!=0 && emailContent.length () != 0 && emailTheme.length () != 0) {

			//如果数据中有  英文的 ' ，则将其转换为''
			String emailContent1 = emailContent.replace ("'","''");
			String emailTheme1 = emailTheme.replace ("'","''");

			this.addEmailDao.insertEmailToDrafts (accountName, time, emailTheme1, emailContent1, accountId);
		}else{
			return ;
		}

	}

	public void addEmailToOutbox(String emailId,String accountName,String emailTheme,String emailContent,Integer accountId){

		//得到请求页面的时间,定为该邮件的 编辑时间 or 发送时间 or 接收时间
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(currentTime);

		if (emailId.length ()>0){
			BigInteger id = new BigInteger (emailId);
			this.addEmailDao.deleteOriginalById (accountId,id);
		}

		//如果邮件的内容完整，则可以存储到发件箱
		if (accountId > 0 && accountName.length () != 0 && emailContent.length () != 0 && emailTheme.length () != 0) {

			//如果数据中有  英文的 ' ，则将其转换为''
			String emailContent1 = emailContent.replace ("'", "''");
			String emailTheme1 = emailTheme.replace ("'", "''");

			this.addEmailDao.insertEmailToOutbox (accountName, time, emailTheme1, emailContent1, accountId);
		} else {
			return;
		}

	}

}
