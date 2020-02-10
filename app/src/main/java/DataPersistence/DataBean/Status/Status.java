package DataPersistence.DataBean.Status;

/**
 * Created by Administrator on 2020/2/1.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 状态记录
 */
public class Status {

    int id; //自注册的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 状态节点的集合
     */
    LinkedList<StatusNode> nodes=new LinkedList<>();


    /**
     * 返回模块的状态
     * @param componentId
     * @return
     */
    public StatusNode getComponentStatus(int componentId){
       return nodes.get(componentId);
    }

    /**
     * 添加组件状态
     * @param componentId
     * @param changeAttribute
     * @return
     */
    public boolean addComponentSatus(int componentId, HashMap<String,Object> changeAttribute){
        try {
            StatusNode statusNode=new StatusNode();
            statusNode.setComponentId(componentId);
            for(Map.Entry<String,Object> entry:changeAttribute.entrySet())
                statusNode.addChangeAttribute(entry.getKey(),entry.getValue());

            nodes.add(statusNode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
