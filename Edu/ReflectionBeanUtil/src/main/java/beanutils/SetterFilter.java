package beanutils;

import java.lang.reflect.Method;

public class SetterFilter implements MethodFilter {
    @Override
    public boolean filter(Method method) {
        return  method.getName().startsWith("set") && method.getParameterTypes().length == 1;
    }
}
