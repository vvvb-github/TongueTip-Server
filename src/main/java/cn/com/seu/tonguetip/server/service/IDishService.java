package cn.com.seu.tonguetip.server.service;

import cn.com.seu.tonguetip.server.entity.Dish;
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
public interface IDishService extends IService<Dish> {
    List<Dish> getDishInfo(Integer hostID);
    Dish getDetail(Integer dishID);
    boolean addDish(Integer hostID, String dishName, Double price, String introduction, String picPathList);
    boolean editDish(Integer dishID, String imgList, String dishName, Double price, String tagList);
    Integer gethostID(Integer dishID);
    List<Dish> getHotDish();
    Dish searchHostID(Integer dishID);
    void addStar(Integer dishID,Integer star);
    void deleteDish(Integer dishID);
}
