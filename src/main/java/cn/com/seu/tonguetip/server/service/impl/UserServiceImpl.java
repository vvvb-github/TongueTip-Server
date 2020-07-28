package cn.com.seu.tonguetip.server.service.impl;

import cn.com.seu.tonguetip.server.entity.User;
import cn.com.seu.tonguetip.server.mapper.UserMapper;
import cn.com.seu.tonguetip.server.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void acceptCheck(Integer userID) {
        User user = getById(userID);
        user.setType(2);
        updateById(user);
    }

    @Override
    public void rejectCheck(Integer userID) {
        removeById(userID);
    }

    @Override
    public User getUser(Integer userID) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("UserID",userID);
        User user=getOne(wrapper);
        return user;
    }

    @Override
    public User getUser(String userPhone, String userPassword, Integer userType) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("PhoneNumber",userPhone);
        wrapper.eq("Password",userPassword);
        wrapper.eq("Type",userType);
        User user = getOne(wrapper);
        return user;
    }

    @Override
    public User setUser(Integer userType, String userName, String userPhone, String userPassword) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("PhoneNumber",userPhone);
        wrapper.eq("Type",userType);
        if(count(wrapper)==0) {
            User user = new User();
            user.setType(userType);
            user.setUserName(userName);
            user.setPhoneNumber(userPhone);
            user.setPassword(userPassword);
            user.setPriority(0);
            user.setIconPath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595505371318&di=f731e87615a4e59de562130f034df187&imgtype=0&src=http%3A%2F%2Fbbsfiles.vivo.com.cn%2Fvivobbs%2Fattachment%2Fforum%2F201801%2F11%2F085513hqsqqmdd5kfq66qs.jpg");
            save(user);
            return user;
        }
        else {
            User user = new User();
            user.setPhoneNumber("奶萌兔");
            return user;
        }
    }

    @Override
    public boolean setUser_2(Integer userID,Integer id,String arg) {
        User user=new User();
        if (id == 1)
            user.setUserName(arg);
        if (id == 2)
        {
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("PhoneNumber",arg);
            if(count(wrapper1)>0)
                return false;
            user.setPhoneNumber(arg);
        }
        if (id == 3)
            user.setPassword(arg);
        if (id == 4)
            user.setIconPath(arg);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserID",userID);
        update(user,wrapper);
        return true;
    }

    @Override
    public Integer getUserPro(Integer userID) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserID",userID);
        Integer pro = getOne(wrapper).getPriority();
        if (pro == 1)
        {
            User user = new User();
            user.setPriority(0);
            update(user,wrapper);
        }
        return pro;
    }

    @Override
    public void setUserPro1(Integer userID) {
        User user =new User();
        user.setPriority(1);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserID",userID);
        update(user,wrapper);
    }

    @Override
    public boolean find(String userPhone, String password,Integer type) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("PhoneNumber",userPhone);
        wrapper.eq("Type",type);
        if (count(wrapper) == 0)
            return false;
        else
        {
            User user = new User();
            user.setPassword(password);
            update(user,wrapper);
            return true;
        }
    }
}
