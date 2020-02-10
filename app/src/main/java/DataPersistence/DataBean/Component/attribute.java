package DataPersistence.DataBean.Component;

/**
 * Created by Administrator on 2020/2/1.
 */

public class attribute<T> {

    /**
     * 数据类型名字
     */
    String name;

    /**
     * 数据类型泛型
     */
    T value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    T defValue;

    public T getDefValue() {
        return defValue;
    }

    public void setDefValue(T defValue) {
        this.defValue = defValue;
    }
}
