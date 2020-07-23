package cn.com.seu.tonguetip.admin.service.impl;

import cn.com.seu.tonguetip.admin.entity.UserCheck;
import cn.com.seu.tonguetip.admin.mapper.UserCheckMapper;
import cn.com.seu.tonguetip.admin.service.IUserCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-22
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
}
