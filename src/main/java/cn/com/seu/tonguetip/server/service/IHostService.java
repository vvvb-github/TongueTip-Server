package cn.com.seu.tonguetip.server.service;

import cn.com.seu.tonguetip.server.entity.Host;
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
public interface IHostService extends IService<Host> {
    Host getHost(Integer dishID);
    Host hostbyuser(Integer userID);
    List<Host> getAllHost();
    List<Host> getRecommentHost(Integer userID);
    List<Host> getRankList();
    void editHost(Integer hostID,String hostName,String phone,String location,String introduction,String picPath);
    String getLocation(Integer hostID);
    void newHost(Integer userID);
    Host hostbyid(Integer hostID);
    void setStar(Integer hostID,double star);
    Integer getUserID(Integer hostID);
}
