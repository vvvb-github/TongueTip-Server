package cn.com.seu.tonguetip.server.service;

import cn.com.seu.tonguetip.server.entity.Comment;
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
public interface ICommentService extends IService<Comment> {
    List<Comment> getComments(Integer dishID);
    boolean addComment(Integer userID, String comment,Integer star,Integer dishID);
}
