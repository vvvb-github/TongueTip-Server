package cn.com.seu.tonguetip.server.service.impl;

import cn.com.seu.tonguetip.server.entity.Host;
import cn.com.seu.tonguetip.server.mapper.HostMapper;
import cn.com.seu.tonguetip.server.service.IHostService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class HostServiceImpl extends ServiceImpl<HostMapper, Host> implements IHostService {
    @Override
    public Host getHost(Integer hostID)
    {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("HostID",hostID);
        Host host = getOne(wrapper);
        return host;
    }

    @Override
    public Host hostbyuser(Integer userID)
    {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("UserID",userID);
        Host host = getOne(wrapper);
        return host;
    }

    @Override
    public List<Host> getAllHost()
    {
        QueryWrapper wrapper = new QueryWrapper();
        List<Host> hostList = list(wrapper);
        return hostList;
    }

    @Override
    public List<Host> getRecommentHost()
    {
        QueryWrapper wrapper = new QueryWrapper();
        List<Host> hostList = list(wrapper);
        return hostList;
    }

    @Override
    public List<Host> getRankList()
    {
        QueryWrapper wrapper = new QueryWrapper();
        List<Host> hostList = list(wrapper);
        List<Host> ans = new ArrayList<>();
        for (Integer i = 0;i<10 && i< hostList.size();i++)
        {
            for (Integer j = i + 1; j < hostList.size(); j++)
                if (hostList.get(j).getStar() > hostList.get(i).getStar())
                {
                    Host t = hostList.get(i);
                    hostList.set(i, hostList.get(j));
                    hostList.set(j, t);
                }
            ans.add(hostList.get(i));
        }
        return ans;
    }

    @Override
    public void editHost(Integer hostID,String hostName,String phone,String location,String introduction,String picPath)
    {
        Host host = new Host();
        host.setHostName(hostName);
        host.setPhoneNumber(phone);
        host.setLocation(location);
        host.setIntroduction(introduction);
        if (!(picPath == null || picPath.equals("")))
            host.setPicturePath(picPath);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("HostID",hostID);
        update(host,wrapper);
    }

    @Override
    public String getLocation(Integer hostID)
    {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("HostID",hostID);
        Host host = getOne(wrapper);
        return host.getLocation();
    }
}