package GlobalTools;

import DataPersistence.CenterControl.IdManager;

/**
 * Created by Administrator on 2020/2/19.
 */

/*全局管理器，提供多种管理器的调用*/
public class GlobalManager {


    /**
     * 注册屏幕的id
     * @return
     */
    public static int registerScreen(){
        return IdManager.getIdManager().registerStatusInOtherLayer();
    }


}
