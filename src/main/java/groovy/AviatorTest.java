package groovy;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

public class AviatorTest {

    public static void main(String[] args) {

        Map<String,Object> env = new HashMap<>();
        env.put("value","-1");

        Object result =  AviatorEvaluator.execute("3>long(value)&&long(value)<5",env);
        System.out.println(result);

        result =  AviatorEvaluator.execute("3>1&&2<5");
        System.out.println(result);

        System.out.println(Boolean.parseBoolean(result.toString()));


        env.put("value",12);
        env.put("value1",12);
        env.put("v3",12);
        Object result1 =  AviatorEvaluator.execute("v3==value");
        env.put("value","Unemployed");
        env.put("par","Unemployed,Student,Retire");
        env.put("rp_apply_time",20);
        env.put("minValue",0);
        env.put("maxValue",40);
        env.put("rp_bank_card_verification","SUCCESS");
        env.put("rule_minValue",18);
        env.put("rule_maxValue","50");
        env.put("rule_value","1");
        Object result2 =  AviatorEvaluator.execute("string.indexOf('SUCCESS',rp_bank_card_verification)+1>=long(rule_value)",env);

        System.out.println(result2);










    }
}
