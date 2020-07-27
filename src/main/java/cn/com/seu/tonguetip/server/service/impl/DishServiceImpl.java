package cn.com.seu.tonguetip.server.service.impl;

import cn.com.seu.tonguetip.server.entity.Dish;
import cn.com.seu.tonguetip.server.mapper.DishMapper;
import cn.com.seu.tonguetip.server.service.IDishService;
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
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

    @Autowired
    private IHostService hostService;

    @Override
    public List<Dish> getDishInfo(Integer hostID) {
        QueryWrapper<Dish> wrapper=new QueryWrapper<>();
        wrapper.eq("HostID",hostID);
        List<Dish> dishes=list(wrapper);
        return dishes;
    }

    @Override
    public Dish getDetail(Integer dishID) {
        QueryWrapper<Dish> wrapper=new QueryWrapper<>();
        wrapper.eq("DishID",dishID);
        Dish dish=getOne(wrapper);
        return dish;
    }

    @Override
    public boolean addDish(Integer hostID, String dishName, Double price, String introduction, String picPathList) {
        Dish dish=new Dish();
        dish.setHostID(hostID);
        dish.setDishName(dishName);
        dish.setPrice(price);
        dish.setIntroduction(introduction);
        dish.setPicturePath(picPathList);
        return save(dish);
    }

    @Override
    public boolean editDish(Integer dishID, String imgList, String dishName, Double price, String tagList) {
        Dish dish=new Dish();
        dish.setDishID(dishID);
        dish.setDishName(dishName);
        dish.setPrice(price);
        dish.setTag(tagList);
        if(imgList!=null&&imgList!="") {
            dish.setPicturePath(imgList);
        }
        QueryWrapper<Dish> wrapper=new QueryWrapper<>();
        wrapper.eq("DishID",dishID);
        return update(dish,wrapper);
    }

    @Override
    public Integer gethostID(Integer dishID)
    {
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq("DishID",dishID);
        Dish dish = getOne(wrapper);
        return dish.getHostID();
    }

    @Override
    public List<Dish> getHotDish() {
        List<Dish> dishList = list();
        List<Dish> ans = new ArrayList<>();
        for (Integer i = 0;i<10 && i< dishList.size();i++)
        {
            for (Integer j = i + 1; j < dishList.size(); j++)
                if (dishList.get(j).getStar() > dishList.get(i).getStar())
                {
                    Dish t = dishList.get(i);
                    dishList.set(i, dishList.get(j));
                    dishList.set(j, t);
                }
            ans.add(dishList.get(i));
        }
        return ans;
    }

    @Override
    public Dish searchHostID(Integer dishID) {
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq("DishID",dishID);
        Dish dish=getOne(wrapper);
        return dish;
    }

    @Override
    public void addStar(Integer dishID, Integer star)
    {
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq("DishID",dishID);
        Dish dish = getOne(wrapper);
        Integer Sales = dish.getSales();
        double oldstar = dish.getStar();
        Dish newdish = new Dish();
        newdish.setStar((Sales*oldstar+star)/(Sales+1));
        newdish.setSales(Sales+1);
        QueryWrapper<Dish> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DishID",dishID);
        update(newdish,wrapper1);
        Integer hostID = gethostID(dishID);
        QueryWrapper<Dish> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("HostID",hostID);
        List<Dish> dishes = list(wrapper2);
        double s = 0;
        double cnt = 0;
        for (Dish i:dishes)
        {
            s+=i.getStar()*i.getSales();
            cnt+=i.getSales();
        }
        hostService.setStar(hostID,s/cnt);
    }
}
