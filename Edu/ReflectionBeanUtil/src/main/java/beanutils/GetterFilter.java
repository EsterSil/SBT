package beanutils;

import java.lang.reflect.Method;

public class GetterFilter implements MethodFilter {
    @Override
    public boolean filter(Method method) {
        return method.getName().startsWith("get")
                && !method.getReturnType().equals(void.class)
                && method.getParameterTypes().length == 0;
    }
}
