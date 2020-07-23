package cn.com.seu.tonguetip.admin.service;

import cn.com.seu.tonguetip.admin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-22
 */
public interface IUserService extends IService<User> {
    void acceptCheck(Integer userID);
    void rejectCheck(Integer userID);
}
