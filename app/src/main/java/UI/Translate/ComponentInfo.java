package UI.Translate;

/**
 * Created by Administrator on 2020/2/4.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import GlobalDataBean.DataBean.UiComponent;

/**
 * 提供所有的组件信息
 */
public interface ComponentInfo {

    /**
     * 获取所有的简单组件信息
     * @return
     */
    public LinkedList<UiComponent> getAllSimpleComponent();

    /**
     * 获取所有的复杂组件信息
     * @return
     */
    public LinkedList<ArrayList<UiComponent>> getAllComplexCompoent();

    /**
     * 获取复杂组件中的简单组件
     * @param ComponentId
     * @return
     */
    public LinkedList<UiComponent>getSimpleComponentInComplex(int ComponentId);

    /**
     * 根据组件的id获取组件
     * @param ComponentId
     * @return
     */
    public LinkedList<UiComponent>getSimpleComponentById(int ComponentId);
}
