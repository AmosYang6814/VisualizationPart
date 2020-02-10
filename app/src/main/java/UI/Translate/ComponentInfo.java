package UI.Translate;

/**
 * Created by Administrator on 2020/2/4.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 提供所有的组件信息
 */
public interface ComponentInfo {

    /**
     * 获取所有的简单组件信息
     * @return
     */
    public LinkedList<Object> getAllSimpleComponent();

    /**
     * 获取所有的复杂组件信息
     * @return
     */
    public LinkedList<ArrayList<Object>> getAllComplexCompoent();
}
