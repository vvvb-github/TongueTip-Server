package cn.com.seu.tonguetip.server.service;

import cn.com.seu.tonguetip.server.entity.UserCheck;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-24
 */
public interface IUserCheckService extends IService<UserCheck> {
    List<UserCheck> getCheckList();
    Integer getUserIDByCheckID(Integer checkID);
    void deleteCheckByID(Integer checkID);
    void setUserCheck(Integer userID,String picPath);
}
