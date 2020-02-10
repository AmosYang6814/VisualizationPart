package UI.DataBean;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2020/2/4.
 */

public class UiComponent<T> {

    public T getComponentObj() {
        return componentObj;
    }

    public void setComponentObj(T componentObj) {
        this.componentObj = componentObj;
    }

    /**
     * 组件的引用
     */
    T componentObj;

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 组件的属性引用
     */
    HashMap<String,Object> modifyAttributes=new HashMap<>();

    /**
     * 添加新的设置
     * @param attributeName
     * @param values
     * @return
     */
    public boolean AddNewSetting(String attributeName,Object[] values){
        try {
            modifyAttributes.put(attributeName,values);
            return setAttribute(attributeName,values);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除属性
     * @param attributeName
     * @return
     */
    public boolean deleteSetting(String attributeName){
        modifyAttributes.remove(attributeName);
        return setAttributeToDefault(attributeName);
    }

    /**
     * 设置属性的方法，属性的个数不允许超过三个
     * @param attributeName
     * @param values
     * @return
     */
    public boolean setAttribute(String attributeName,Object[] values){
        Class componentClass=componentObj.getClass();
        Method method=null;
        try {
            switch (values.length){
                case 0:
                    method=componentClass.getMethod(attributeName);
                    method.invoke(null);
                    break;
                case 1:
                    method=componentClass.getMethod(attributeName,values[0].getClass());
                    method.invoke(values[0]);
                    break;
                case 2:
                    method=componentClass.getMethod(attributeName,values[0].getClass(),values[1].getClass());
                    method.invoke(values[0],values[1]);
                    break;
                case 3:
                    method=componentClass.getMethod(attributeName,values[0].getClass(),values[1].getClass(),values[2].getClass());
                    method.invoke(values[0],values[1],values[2]);
                    break;
                default: return false;
            }

            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置属性方法到默认值，，，，，，方法正确性待检测
     * @param attributeName
     * @return
     */
    public boolean setAttributeToDefault(String attributeName){
        try {
            Class componentClass=componentObj.getClass();
            Method[] methods=componentClass.getMethods();
            for(int i=0;i<methods.length;i++){
                if(methods[i].getName().equals(attributeName))methods[i].invoke(methods[i].getDefaultValue());
            }
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }


}
