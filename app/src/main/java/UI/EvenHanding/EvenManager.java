package UI.EvenHanding;

import java.util.HashMap;
import java.util.LinkedList;

import GlobalTools.DataBean.Action.Action;

/**
 * 时间管理器，主要管理事件的点击之后的处理，进行封装成Action和Event对象
 */
public class EvenManager {



    /**
     * 根据屏幕id添加的动作链
     */
    HashMap<Integer, LinkedList<Action>> actionMap=new HashMap<>();

    /**
     * 添加动作
     * @param screenId
     * @param action
     * @return
     */
    public boolean addAction(int screenId, Action action){
        try {
            if(!actionMap.containsKey(screenId)){
                LinkedList<Action> actionLinkedList=new LinkedList<>();
                actionMap.put(screenId,actionLinkedList);
            }
            actionMap.get(screenId).add(action);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}