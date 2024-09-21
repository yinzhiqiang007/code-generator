package proxy.dynamic;

public class Target implements TargetInterface {
    @Override
    public void coreWork() {
        System.out.println("===核心业务方法运行===");
    }
}
