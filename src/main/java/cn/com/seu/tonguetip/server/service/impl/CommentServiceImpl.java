package cn.com.seu.tonguetip.server.service.impl;

import cn.com.seu.tonguetip.server.entity.Comment;
import cn.com.seu.tonguetip.server.mapper.CommentMapper;
import cn.com.seu.tonguetip.server.service.ICommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Override
    public List<Comment> getComments(Integer dishID) {
        QueryWrapper<Comment> wrapper=new QueryWrapper<>();
        wrapper.eq("DishID",dishID);
        List<Comment> comments=list(wrapper);
        return comments;
    }

    @Override
    public boolean addComment(Integer userID, String content, Integer star, Integer dishID) {
        Comment comment=new Comment();
        comment.setUserID(userID);
        comment.setContent(content);
        comment.setStar(Double.valueOf(star));
        comment.setDishID(dishID);
        LocalDateTime time = LocalDateTime.now();
        comment.setTime(time);
        return save(comment);
    }
}
