package cn.com.seu.tonguetip.server.service.impl;

import cn.com.seu.tonguetip.server.entity.UserCheck;
import cn.com.seu.tonguetip.server.mapper.UserCheckMapper;
import cn.com.seu.tonguetip.server.service.IUserCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-24
 */
@Service
public class UserCheckServiceImpl extends ServiceImpl<UserCheckMapper, UserCheck> implements IUserCheckService {
    @Override
    public List<UserCheck> getCheckList() {
        return list();
    }

    @Override
    public Integer getUserIDByCheckID(Integer checkID) {
        UserCheck check = getById(checkID);
        return check.getUserID();
    }

    @Override
    public void deleteCheckByID(Integer checkID) {
        removeById(checkID);
    }

    @Override
    public void setUserCheck(Integer userID, String picPath) {
        UserCheck userCheck=new UserCheck();
        userCheck.setUserID(userID);
        userCheck.setPictruePath(picPath);
        save(userCheck);
    }
}
