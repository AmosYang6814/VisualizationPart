package DataPersistence.DataBean.Component;

/**
 * Created by Administrator on 2020/2/1.
 */

import java.util.LinkedList;

/**
 * 复杂模块
 */
public class ComplexComponent implements Component {

    /**
     * 返回组件类型
     * @return
     */
    @Override
    public int getType() {
        return COMPLEX_COMPONENT_TYPE;
    }

    /**
     * 复杂模块的id,自动增长属性，不赋值
     */
    int id;

    /**
     * 复杂模块中的简单模块表
     */
    LinkedList<SimpleComponent> simpleComponents=new LinkedList<>();

    public int getId() {
        return id;
    }

    public void increaseId() {
    }

    public LinkedList<SimpleComponent> getSimpleComponents() {
        return simpleComponents;
    }


    public void setSimpleComponents(LinkedList<SimpleComponent> simpleComponents) {
        this.simpleComponents = simpleComponents;
    }

    /**
     * 添加简单模块
     * @param simpleComponent
     */
    public void addSimpleComponent(SimpleComponent simpleComponent){
        simpleComponents.add(simpleComponent);
    }

    /**
     * 删除简单模块
     * @param simpleComponentId
     */
    public void deleteSimpleComponent(int simpleComponentId){
        for(SimpleComponent simpleComponent:simpleComponents){
            if(simpleComponent.getId()==simpleComponentId)simpleComponents.remove(simpleComponent);
        }
    }

    /**
     * 通过模块的id,获取简单模块
     * @param simpleComponentId
     * @return
     */
    public SimpleComponent getSimpleComponentById(int simpleComponentId){
        for(SimpleComponent simpleComponent:simpleComponents){
            if(simpleComponent.getId()==simpleComponentId)return simpleComponent;
        }

        return null;
    }
}
