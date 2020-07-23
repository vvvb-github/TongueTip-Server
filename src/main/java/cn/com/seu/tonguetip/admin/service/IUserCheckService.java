package cn.com.seu.tonguetip.admin.service;

import cn.com.seu.tonguetip.admin.entity.UserCheck;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-22
 */
public interface IUserCheckService extends IService<UserCheck> {
    List<UserCheck> getCheckList();
    Integer getUserIDByCheckID(Integer checkID);
    void deleteCheckByID(Integer checkID);
}
