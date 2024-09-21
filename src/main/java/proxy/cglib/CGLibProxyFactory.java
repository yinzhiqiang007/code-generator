package proxy.cglib;

import cn.hutool.aop.proxy.CglibProxyFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxyFactory implements MethodInterceptor {


    //维护目标对象
    private Object target;

    public CGLibProxyFactory(Object target) {
        this.target = target;
    }


    public Object getObject() {
        //工具类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(target.getClass());
        //设置回调
        enhancer.setCallback(this);
        //创建代理类
        Object object = enhancer.create();
        return object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        //执行目标对象的目标方法
        method.invoke(target,objects);

        System.out.println("after");

        return null;
    }


    public static void main(String[] args) {

        Refund refund = new Refund();
        Refund proxyObject = (Refund) new CGLibProxyFactory(refund).getObject();
        proxyObject.refund();
    }

}
