package DataPersistence.DataBean.Component;

/**
 * Created by Administrator on 2020/2/1.
 */

public interface Component {
    static final int COMPLEX_COMPONENT_TYPE=01;
    static final int SIMPLE_COMPONENT_TYPE=02;

    int getId();

    void increaseId();

    /**
     * 获取组件类型
     * @return
     */
    int getType();
}
