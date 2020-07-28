package cn.com.seu.tonguetip.server.service.impl;

import cn.com.seu.tonguetip.server.entity.Host;
import cn.com.seu.tonguetip.server.mapper.HostMapper;
import cn.com.seu.tonguetip.server.service.IDishOrderService;
import cn.com.seu.tonguetip.server.service.IHostService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IDishOrderService dishOrderService;

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
    public List<Host> getRecommentHost(Integer userID)
    {
        List<Integer> hostidlist = dishOrderService.getRecommendHostID(userID);
        List<Host> hostList = new ArrayList<>();
        for (Integer i:hostidlist)
        {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("HostID",i);
            hostList.add(getOne(wrapper));
        }
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

    @Override
    public void newHost(Integer userID) {
        Host host = new Host();
        host.setUserID(userID);
        host.setHostName("无店名");
        host.setIntroduction("暂无简介");
        host.setLocation("地点未知");
        host.setPhoneNumber("暂无联系方式");
        host.setStar(0.0);
        host.setPicturePath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595617094864&di=c14184908466a235d5215facc406a051&imgtype=0&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D2952589939%2C4283534615%26fm%3D214%26gp%3D0.jpg");
        save(host);
    }

    @Override
    public Host hostbyid(Integer hostID) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("HostID",hostID);
        Host host = getOne(wrapper);
        return host;
    }

    @Override
    public void setStar(Integer hostID, double star) {
        QueryWrapper wrapper = new QueryWrapper();
        Host host = new Host();
        host.setStar(star);
        wrapper.eq("HostID",hostID);
        update(host,wrapper);
    }

    @Override
    public Integer getUserID(Integer hostID) {
        QueryWrapper wrapper  = new QueryWrapper();
        wrapper.eq("HostID",hostID);
        return getOne(wrapper).getUserID();
    }
}
