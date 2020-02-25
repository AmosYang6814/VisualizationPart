package GlobalTools.DataBean.Action;


import java.util.LinkedList;

import GlobalTools.DataBean.StatusNode;

public class ChangeLink extends Action {
    LinkedList<StatusNode> changes;
    /**
     * 改变的构造器
     * @param action
     */
    ChangeLink(Action action){
        super(action);
        changes=new LinkedList<>();
        super.classType=ACTIONTYPE_MEAN_CHANGE;
    }

    public void addStatusNote(StatusNode statusNode){
        changes.add(statusNode);
    }

    public void deleteComponent(int componentId){
        StatusNode statusNode=null;
        for(StatusNode s:changes){
            if(s.getComponentId()==componentId)statusNode=s;
        }
        this.changes.remove(statusNode);
    }
}
