package DataPersistence.DataBean.Component;

/**
 * Created by Administrator on 2020/2/1.
 */


import java.util.LinkedList;

/**
 * 简单模块的存储实体类
 */
public class SimpleComponent implements Component {

    /**
     * id of the component,is auto increase
     */
    int Id;

    /**
     * the name of the component,
     */
    String componentName;

    /**
     * point of the component,describle the location of the component in the screen
     */
    int point_x;
    int point_y;

    /**
     * 模块的支持的动作列表，
     * @return
     */
    LinkedList<Action> actions=new LinkedList<>();

    /**
     * 模块支持的属性描述列表
     */
    LinkedList<attribute> attributes=new LinkedList<>();



    public int getId() {
        return Id;
    }

    /**
     * set id by auto increase
     */
    public void increaseId(){
    }

    /**
     * 返回组件模块类型
     * @return
     */
    @Override
    public int getType() {
        return SIMPLE_COMPONENT_TYPE;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getPoint_x() {
        return point_x;
    }

    public void setPoint_x(int point_x) {
        this.point_x = point_x;
    }

    public int getPoint_y() {
        return point_y;
    }

    public void setPoint_y(int point_y) {
        this.point_y = point_y;
    }


}
