package thread;

import com.alibaba.fastjson.JSON;
import concurrent.CountDownLatchTest;

import java.util.Map;
import java.util.concurrent.Callable;

public class TestCallable implements Callable {

    private int a;
    private Map<String,Integer> map;

    public TestCallable(int i){
        a=i;
    }

    public TestCallable(int i, Map<String,Integer> map){
        a=i;
        this.map = map;
    }
    @Override
    public Object call() throws Exception {

        System.out.println(a);
        return "c";
    }
}
