package GlobalTools.DataBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Logger.LogManager;

/**
 * Created by Administrator on 2020/2/4.
 */


/**
 * UIComponent中采用的是装饰模式，其中使用结构体ComponentType区别
 * @param
 */

public class UiComponent {

    /**
     * -----------------------------------------------------------------------------------------
     * 可见性
     */

    Visibility visible;
    public Visibility getVisiblity() {
        return visible;
    }

    public void setVisiblity(Visibility visible) {
        this.visible = visible;
    }



    /**
     * 组件名字
     */
    public String nearName;
    public String getNearName() {
        return nearName;
    }

    /**
     * ---------------------------------------------------------------------------------------------------------
     *
     * 组件的类型的定义
     */
    public enum ComponentType{
        complex,simple;
    }

    /**
     * 组件类型
     */
    ComponentType componentType=ComponentType.simple;
    public UiComponent(ComponentType componentType,String componentName) {
        this.nearName=componentName;
        setComponentType(componentType);
    }

    public ComponentType getComponentType() {
        return componentType;
    }
    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
        if(componentType.equals(ComponentType.complex))simpleComponent=new HashMap<>();
    }
    /**
     * ---------------------------------------------------------------------------------------------------------------
     *
     * 包含的简单组件
     */

    public HashMap<Integer,UiComponent> simpleComponent;
    /**
     * 添加简单组件
     * @param component
     * @return
     */
    public boolean addSimpleComponent(UiComponent component){
        try {
            simpleComponent.put(component.getId(),component);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LogManager.print("未指定组件类型：复杂组件？");
            return false;
        }
    }

    /**
     * 获取简单组件
     * @param id
     * @return
     */
    public UiComponent getUiComponent( int id){
        return simpleComponent.get(id);
    }
    public LinkedList<UiComponent> getSimpleUiComponent(){
        LinkedList<UiComponent> list=new LinkedList<>();
        for(Map.Entry<Integer,UiComponent> entry:simpleComponent.entrySet())list.add(entry.getValue());
        return list;
    }

    /**
     * -------------------------------------------------------------------------------------------------------
     *
     * 组件中内容的引用
     */
    Object componentObj;
    /**
     * 获取组件
     * @return
     */
    public <T> T getComponentObj(Class<T> tClass) {
        return (T)componentObj;
    }

    public void setComponentObj(Object componentObj) {
        this.componentObj = componentObj;
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     *
     * id的获取和确认
     */
    int id=-1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    /**
     * --------------------------------------------------------------------------------------------------------
     * 组件的属性引用
     */
    HashMap<String,Object> modifyAttributes=new HashMap<>();

    /**
     * 添加新的设置
     * @param attributeName
     * @param values
     * @return
     */
    public boolean AddNewSetting(String attributeName,Object... values){
        try {
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
    public boolean setAttribute(String attributeName,Object... values){
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

            for(int i=0;i<values.length;i++){
                modifyAttributes.put(attributeName+"_*_"+i,values[i]);
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

    /**
     * 获取全部的属性值
     */
    public HashMap<String,Object> getAllAttribute(){
        return (HashMap<String, Object>) modifyAttributes.clone();
    }

}
