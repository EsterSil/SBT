package beanutils;

import java.lang.reflect.Method;

public interface MethodFilter {

    boolean filter(Method method);
}
