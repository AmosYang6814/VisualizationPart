package UI.Translate;

/**
 * Created by Administrator on 2020/2/4.
 */

import java.util.LinkedList;

import DataPersistence.DataBean.RelationShip.Relation;

/**
 * 获取所有的连接信息
 */
public interface RelationInfo {

    /**
     * 获取所有的连接信息
     * @return
     */
    public LinkedList<Relation> getAllRelation();
}
