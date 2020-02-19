package GlobalTools.DataBean;

/**
 * Created by Administrator on 2020/2/4.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 屏幕信息包装类，用于包装该屏幕上所有显示的组件的信息
 */

public class Screen {

    /*该屏幕的状态id*/
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*-----------------------------------------------------------------------------------------------------------*/

  private LinkedList<StatusNode> components=new LinkedList<>();

    /**
     * 添加组件
     * @param componentid
     * @return
     */

   public boolean addComponent(int componentid){
       try {
           StatusNode statusNode=new StatusNode();
           statusNode.setComponentId(componentid);
           components.add(statusNode);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
   }

    /**
     * 删除组件
     * @param componentid
     * @return
     */
   public boolean deleteComponent(int componentid){
       try {
           StatusNode statusNode=null;
           for(StatusNode s:components){
               if(s.getComponentId()==componentid)statusNode=s;
           }
           components.remove(statusNode);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
   }

    /**
     * 返回模块的状态
     * @param componentId
     * @return
     */
    public StatusNode getComponentStatus(int componentId){
        return components.get(componentId);
    }

    /**
     * 添加组件状态
     * @param componentId
     * @param changeAttribute
     * @return
     */
    public boolean addComponentAndSatus(int componentId, HashMap<String,Object> changeAttribute){
        try {
            StatusNode statusNode=new StatusNode();
            statusNode.setComponentId(componentId);
            for(Map.Entry<String,Object> entry:changeAttribute.entrySet())
                statusNode.addChangeAttribute(entry.getKey(),entry.getValue());

            components.add(statusNode);
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
    public boolean modifyAndSetComponentStatus(int componentId,HashMap<String,Object> changeAttribute ){
        try {

            StatusNode statusNode=null;
            for(StatusNode statusNo:components){
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
            for(StatusNode statusNo:components){
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
     * 设置
     * @param attributes
     * @return
     */
    public boolean setAttbutes(LinkedList<StatusNode> attributes){
        try {
            this.components=attributes;
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
        return (LinkedList<StatusNode>)components.clone();
    }

    /*----------------------------------------------------------------------------------------------------------*/





}
