package cc.lelecat.account.normal.service;

import cc.lelecat.account.normal.dao.NormalAccountDao;
import cc.lelecat.entity.account.customer.NormalAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类名：NormalAccountServiceImpl
 * 功能：实现客户账户服务层
 * 作者：苗怀雨
 * 日期：2016-11-15 15:47:57
 */
@Service
@Transactional(readOnly=false)
public class NormalAccountServiceImpl implements NormalAccountService {
    @Resource
    private NormalAccountDao normalAccountDao;

    @Override
    public boolean checkUsernameAvailable(String username) {
        return this.normalAccountDao.findByUsername(username) == null;
    }

    @Override
    public boolean add(NormalAccount account) {
        return normalAccountDao.save(account);
    }

    public String[] add(String username,String password,String name) {
        String[] result;
        NormalAccount normalAccount = new NormalAccount();

        if(!checkUsernameAvailable(username)){
            result = new String[]{"failure", "添加失败, 指定账户名已存在, 请重试"};
        } else{
            normalAccount.setUsername(username);
            normalAccount.setPassword(password);
            normalAccount.setName(name);

            if(this.add(normalAccount))
                result = new String[]{"success", "添加成功"};
            else
                result = new String[]{"failure", "未知错误"};
        }

        System.out.println(result[1]);
        return result;
    }

    @Override
    public String[] register(String username, String password,String passwordConfirmed) {
        String[] result;
        NormalAccount normalAccount = new NormalAccount();

        if(!checkUsernameAvailable(username)){
            result = new String[]{"failure", "注册失败, 指定账户名已存在, 请重试"};
        } else if(!password.equals(passwordConfirmed)){
            result = new String[]{"failure","两次密码输入不一致，请重新输入"};
        } else{
            normalAccount.setUsername(username);
            normalAccount.setPassword(password);

            if(this.add(normalAccount))
                result = new String[]{"success", "注册成功"};
            else
                result = new String[]{"failure", "未知错误"};
        }

        return result;
    }

    @Override
    public Map<String,String> register(Map<String, String> usernameAndPassword) {
        Map<String,String> registerReturnMessage= new HashMap<String,String> (0);

        //将controller中的用户名和密码分解开，放到username和password中
        String username = null;
        String password = null;
        Iterator iterator = usernameAndPassword.entrySet ().iterator ();
        while (iterator.hasNext ()){
            Map.Entry entry = (Map.Entry)iterator.next ();
            String key = entry.getKey ().toString ();
            String value = entry.getValue ().toString ();

            if (key.equals ("username")){
                username = value;
                continue;
            }
            if (key.equals ("password")){
                password = value;
                continue;
            }
        }
        NormalAccount normalAccount = new NormalAccount();
        normalAccount.setUsername (username);
        normalAccount.setPassword (password);
        normalAccount.setDisabled(false);

        //先查找到所有已经存在的账户
        List<NormalAccount> normalAccounts = this.normalAccountDao.findAll ();

        //错误提示消息
        String wrongCode = null;

        //看该注册的用户名是否和已经存在的用户名冲突，如果冲突则错误提示消息有内容，否则错误提示消息为空
        for (int i = 0;i<normalAccounts.size ();i++){
            if (normalAccounts.get (i).getUsername ().equals (normalAccount.getUsername ())){
                wrongCode = "1";
                break;
            }
        }

        //如果错误提示消息为空，则插入到数据库
        if (wrongCode ==null){
            Boolean result = this.normalAccountDao.save (normalAccount);
            registerReturnMessage.put ("result",result.toString ());
        }else{
            registerReturnMessage.put ("result","false");
            registerReturnMessage.put ("wrongCode",wrongCode);
        }
        return registerReturnMessage;
    }


    @Override
    public NormalAccount login(String username, String password) {
        NormalAccount normalAccount = null;
        if((normalAccount = normalAccountDao.findByUsernameAndPassword(username,password) )!= null){
            if(normalAccount.getDisabled() == true)
                return null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            normalAccount.setLastLoginTime(formatter.format(new Date()));

            int count = normalAccount.getLoginCount() == null ? 0 : normalAccount.getLoginCount();
            normalAccount.setLoginCount(++count);
        }
        return normalAccount;
    }

    @Override
    public boolean delete(Integer accountId) {
        return this.normalAccountDao.delete(accountId);
    }

    @Override
    public NormalAccount get(Integer accountId) {
        return this.normalAccountDao.get(accountId);
    }

    @Override
    public boolean update(NormalAccount account) {
        return this.normalAccountDao.update(account);
    }

    @Override
    public List<NormalAccount> findAll() {
        return this.normalAccountDao.findAll();
    }
}
