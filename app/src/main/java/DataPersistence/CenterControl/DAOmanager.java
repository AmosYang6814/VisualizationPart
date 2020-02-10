package DataPersistence.CenterControl;

import DataPersistence.AbstractStorage.PreserveId;
import DataPersistence.AbstractStorage.PreserveComponent;
import DataPersistence.AbstractStorage.PreserveRelation;
import DataPersistence.AbstractStorage.PreserveStatus;

/**
 * Created by Administrator on 2020/2/1.
 */


/**
 * 存储类的管理器，程序中对于存储类只持有唯一实例
 */
public class DAOmanager {
    static DAOmanager daOmanager=new DAOmanager();

    public static DAOmanager getDaOmanager() {
        return daOmanager;
    }



    PreserveId preserveIdDAO;
    PreserveComponent componentDAO;
    PreserveStatus statusDAO;
    PreserveRelation relationDAO;

    public static void setDaOmanager(DAOmanager daOmanager) {
        DAOmanager.daOmanager = daOmanager;
    }

    public PreserveId getPreserveIdDAO() {
        return preserveIdDAO;
    }

    public void setPreserveIdDAO(PreserveId preserveIdDAO) {
        this.preserveIdDAO = preserveIdDAO;
    }

    public PreserveComponent getComponentDAO() {
        return componentDAO;
    }

    public void setComponentDAO(PreserveComponent componentDAO) {
        this.componentDAO = componentDAO;
    }

    public PreserveStatus getStatusDAO() {
        return statusDAO;
    }

    public void setStatusDAO(PreserveStatus statusDAO) {
        this.statusDAO = statusDAO;
    }

    public PreserveRelation getRelationDAO() {
        return relationDAO;
    }

    public void setRelationDAO(PreserveRelation relationDAO) {
        this.relationDAO = relationDAO;
    }
}
