package cc.lelecat.email.drafts.service;

import cc.lelecat.email.drafts.dao.SeeAllDraftsDaoImpl;
import cc.lelecat.entity.email.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王冀琛
 * on 2016/11/30.
 * 查看所有草稿的 service
 */

@Service
@Transactional(readOnly = true)
public class SeeAllDraftsServiceImpl {

	@Resource
	private SeeAllDraftsDaoImpl seeAllDraftsDao;

	public Map<Email,String> seeAllDraftsEmail(Integer accountId){
		Map<Email,String> emails = new LinkedHashMap<Email,String>(0);
		List<Email> oldEmails = this.seeAllDraftsDao.findAllDraftsByAccountId (accountId);

		for(int i = 0; i < oldEmails.size(); i++){
			emails.put(oldEmails.get(i),null);
		}
		return emails;
	}

}
