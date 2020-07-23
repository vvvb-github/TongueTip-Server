package cn.com.seu.tonguetip.admin.service.impl;

import cn.com.seu.tonguetip.admin.entity.User;
import cn.com.seu.tonguetip.admin.mapper.UserMapper;
import cn.com.seu.tonguetip.admin.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-22
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
}
