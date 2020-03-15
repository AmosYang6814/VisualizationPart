package UI.ComponentIndex;

import java.util.LinkedList;

import GlobalTools.DataBean.UiComponent;
import GlobalTools.DataBean.Visibility;

/**
 * 抽象层数据管理器，为具体实现层提供数据的交互
 */
public class AbstractDataManager {
    static AbstractDataManager abstractDataManager;


    /**
     * 返回唯一实例
     * @return
     */
    public static AbstractDataManager getAbstractDataManager(){
        if(abstractDataManager==null)abstractDataManager=new AbstractDataManager();
        return abstractDataManager;
    }

    /**
     * 实现层的测试方法，在之后需要重写该方法
     * @return
     */
    public LinkedList<UiComponent> getAllcomponent(){
        UiComponent uiComponent=new UiComponent(UiComponent.ComponentType.simple,"android.widget.EditText");
        uiComponent.setId(101);
        uiComponent.setVisiblity(Visibility.visible);
        uiComponent.setNearName("文本输入框");

        UiComponent uiComponent1=new UiComponent(UiComponent.ComponentType.simple,"android.widget.Button");
        uiComponent1.setId(102);
        uiComponent1.setVisiblity(Visibility.visible);
        uiComponent1.setNearName("按钮");

        LinkedList<UiComponent> linkedList=new LinkedList<>();
        linkedList.add(uiComponent);
        linkedList.add(uiComponent1);
        return linkedList;
    }
}
