package cn.com.seu.tonguetip.server.service;

import cn.com.seu.tonguetip.server.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-24
 */
public interface IUserService extends IService<User> {
    void acceptCheck(Integer userID);
    void rejectCheck(Integer userID);
    User getUser(Integer userID);
    User getUser(String userPhone, String userPassword, Integer userType);
    User setUser(Integer userType, String userName, String userPhone, String userPassword);
    boolean setUser_2(Integer userID,String userName,String userPhone,String userPassword,String picPath);
    Integer getUserPro(Integer userID);
    void setUserPro1(Integer usesrID);
    boolean find(String userPhone,String password,Integer type);
}
