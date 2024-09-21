package groovy;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBigInt;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class AviatorCompareTest extends AbstractFunction {
    @Override
    public String getName() {
        return "string.compareTo";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String p1 = FunctionUtils.getStringValue(arg1, env);
        String p2 = FunctionUtils.getStringValue(arg2, env);
        Integer i = new Integer(p1.compareTo(p2));

        if(i>=0){
            return AviatorBoolean.valueOf(true);
        }else{
            return AviatorBoolean.valueOf(false);
        }
    }
}
