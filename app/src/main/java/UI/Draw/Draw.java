package UI.Draw;

/**
 * Created by Administrator on 2020/2/4.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 绘制接口
 */
public interface Draw {

    /**
     * 获取屏幕的长宽
     * @return
     */
    public Size getScreenSize();

    /**
     * 简单组件的绘制
     * @param simpleComponent
     * @return
     */
    public boolean drawInscreen(List<Object> simpleComponent);

    /**
     * 复杂组件的绘制
     * @param complexComponent
     * @return
     */
    public boolean drawComplexScreen(List<ArrayList<Object>> complexComponent);

    /**
     * 设置属性值
     * @param object
     * @param attribute
     * @param value
     * @return
     */
    public boolean SetAttribute(Object object,String attribute,Object value);

    /**
     * 刷新
     * @return
     */
    public boolean refresh();
}
