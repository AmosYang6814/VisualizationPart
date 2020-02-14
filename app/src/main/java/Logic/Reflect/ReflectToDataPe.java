package Logic.Reflect;

/**
 * Created by Administrator on 2020/2/14.
 */

import java.util.List;

import DataPersistence.DataBean.Component.Component;

/**
 * 实现的功能：
 *   1.传递的组件转化成
 */
public interface ReflectToDataPe {
    /**
     * 获取全部的组件
     * @return
     */
    List<Component> getAllComponent();

    /**
     * 根据ID获取组件
     * @return
     */
    Component getComponentById();

    /**
     * 存储组件
     * @param component
     * @return
     */
    boolean saveComponent(Component component);

    /**
     * 删除组件
     * @param component
     * @return
     */
    boolean deleteComponent(Component component);
}
