package proxy.dynamic;

public class Advice {
    public void before(){
        System.out.println("对核心业务方法执行前的增强......");
    }

    public void after(){
        System.out.println("后置增强......");

    }
}
