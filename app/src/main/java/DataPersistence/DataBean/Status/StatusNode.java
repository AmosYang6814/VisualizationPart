package DataPersistence.DataBean.Status;

/**
 * Created by Administrator on 2020/2/1.
 */

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 状态节点
 */
public class StatusNode {

    /**
     * 该节点对应的组件的简单模块id的值
     */
    int componentId;


    /**
     * 组件中所有发生改变的属性的id
     */
    LinkedList<String> attributeIds=new LinkedList<>();

    /**
     * 所有属性id,相应的新的值
     */
    HashMap<String,Object> newValue=new HashMap<>();


    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    /**
     * 放置新的屬性和新的屬性的值
     * @param attributeName
     * @param value
     */
    public void addChangeAttribute(String attributeName,Object value){
        attributeIds.add(attributeName);
        newValue.put(attributeName,value);
    }

    /**
     * 獲取新的值
     * @param attributeName
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getAttributeValue(String attributeName,Class<T> tClass){
       return (T)newValue.get(attributeName);
    }


    /**
     * 删除属性的新的值
     * @param attributeName
     * @return
     */
    public boolean deleteAttributeValue(String attributeName){
        try {
            attributeIds.remove(attributeName);
            newValue.remove(attributeName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    public void test(){
    }

}
