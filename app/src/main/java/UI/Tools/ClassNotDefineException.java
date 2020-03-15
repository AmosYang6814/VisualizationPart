package UI.Tools;

public class ClassNotDefineException extends Exception {
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println("类型超出定义");
    }
}
