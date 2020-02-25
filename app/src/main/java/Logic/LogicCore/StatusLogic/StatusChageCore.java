package Logic.LogicCore.StatusLogic;

import java.util.LinkedList;
import java.util.List;

import DataPersistence.CenterControl.DAOmanager;
import DataPersistence.DataBean.Status.Status;
import DataPersistence.UpperService.ReadManager;
import DataPersistence.UpperService.WriteManager;
import GlobalTools.DataBean.Screen;

/**
 * Created by Administrator on 2020/2/19.
 */

/**
 * 对于接口层的实现
 */
public class StatusChageCore implements StatusToDataPre {
    @Override
    public LinkedList<Screen> getAllStatus() throws Exception {
        Screen screenTemp;
        LinkedList<Status> statuses= (LinkedList<Status>) ReadManager.getAllStatus();
        LinkedList<Screen> result=new LinkedList<>();
        for(Status status:statuses){
            screenTemp=new Screen();
            screenTemp.setId(status.getId());
            screenTemp.setAttbutes(status.getAllStatusNode());
            result.add(screenTemp);
        }
        return result;

    }

    /**
     * 根据id返回
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Screen getStatusById(int id)  {
        Screen screen=new Screen();
        screen.setId(id);

        Status status= ReadManager.getStatusById(id);
        screen.setAttbutes(status.getAllStatusNode());
        return screen;
    }

    /**
     * 存储状态
     * @param screen
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveStatus(Screen screen) throws Exception {
        Status status=new Status();
        status.setId(screen.getId());
        if(!status.setAttbutes(screen.getAllStatusNode())) return false;
        if(!WriteManager.getWritManager().submitStatus(status))return false;
        return true;
    }


    /**
     * 删除状态
     * @param screen
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteStatus(Screen screen) throws Exception {
        return WriteManager.getWritManager().deleteinfo(Status.STATUS,screen.getId());
    }
}
