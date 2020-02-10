package UI.Translate;

/**
 * Created by Administrator on 2020/2/4.
 */

import java.util.LinkedList;

import DataPersistence.DataBean.Status.Status;

/**
 * 获取所有的状态信息
 */
public interface StatusInfo {

    /**
     * 获取所有的状态信息
     * @return
     */
    public LinkedList<Status> getAllStatus();

}
