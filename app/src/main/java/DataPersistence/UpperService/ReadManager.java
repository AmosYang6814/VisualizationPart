package DataPersistence.UpperService;

/**
 * Created by Administrator on 2020/2/15.
 */

import java.util.LinkedList;
import java.util.List;

import DataPersistence.CenterControl.CacheManager;
import DataPersistence.DataBean.Component.Component;

/**
 * 允许异步读
 */
public class ReadManager {

    /**
     * 获取所有的组件
     * @return
     */
    public static List<Component> getAllComponent(){
       return CacheManager.getCacheManager().getAllComponent();
    }

    /**
     * 通过ID获取组件
     * @return
     */
    public static Component getComponentById(int id){
        return CacheManager.getCacheManager().getComponentById(id);
    }

    public static ReadManager GetReadManagerInstance(){
        return new ReadManager();
    }
}
