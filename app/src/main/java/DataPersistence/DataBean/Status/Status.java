package DataPersistence.DataBean.Status;

/**
 * Created by Administrator on 2020/2/1.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import GlobalTools.DataBean.StatusNode;

/**
 * 状态记录
 */
public class Status {
    public static final int STATUS=10003;


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

    /**
     * 修改属性值
     * @param componentId
     * @param changeAttribute
     * @return
     */
    public boolean modifyComponentStatus(int componentId,HashMap<String,Object> changeAttribute ){
        try {

            StatusNode statusNode=null;
            for(StatusNode statusNo:nodes){
                if(statusNo.getComponentId()==componentId)statusNode=statusNo;
            }
            for(Map.Entry<String,Object> entry:changeAttribute.entrySet()) {
                if(statusNode.containAttribute(entry.getKey()))statusNode.deleteAttributeValue(entry.getKey());
                statusNode.addChangeAttribute(entry.getKey(), entry.getValue());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除属性值
     * @param componentId
     * @param attributeName
     * @return
     */
    public boolean deleteSttribute(int componentId,String attributeName){
        try {
            StatusNode statusNode=null;
            for(StatusNode statusNo:nodes){
                if(statusNo.getComponentId()==componentId)statusNode=statusNo;
            }
            statusNode.deleteAttributeValue(attributeName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除模块
     * @param componentId
     * @return
     */
    public boolean deleteComponent(int componentId){
        try {
            StatusNode statusNode=null;
            for(StatusNode statusNo:nodes){
                if(statusNo.getComponentId()==componentId)statusNode=statusNo;
            }
            nodes.remove(statusNode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回所有的节点
     * @return
     */
    public LinkedList<StatusNode> getAllStatusNode(){
        return (LinkedList<StatusNode>)nodes.clone();
    }

    /**
     * 设置
     * @param attributes
     * @return
     */
    public boolean setAttbutes(LinkedList<StatusNode> attributes){
        try {
            this.nodes=attributes;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
