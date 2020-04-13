package UI.UICenterCtrol;


import UI.ComponentIndex.AbstractDataManager;
import UI.EvenHanding.EvenManager;

/**
 * 全局管理器
 */
public class UIGlobalManager {
    static EvenManager evenManager=new EvenManager();
    static AbstractDataManager dataManager=new AbstractDataManager();
    static UIGlobalManager uiGlobalManager=new UIGlobalManager();

    public static EvenManager getEvenManager() {
        return evenManager;
    }

    public static AbstractDataManager getDataManager() {
        return dataManager;
    }

    public static UIGlobalManager getInstance() {
        return uiGlobalManager;
    }

}
