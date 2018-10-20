package beanutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

    /**

     * Scans object "from" for all getters. If object "to"

     * contains correspondent setter, it will invoke it

     * to set property value for "to" which equals to the property

     * of "from".

     * <p/>

     * The type in setter should be compatible to the value returned

     * by getter (if not, no invocation performed).

     * Compatible means that parameter type in setter should

     * be the same or be superclass of the return type of the getter.

     * <p/>

     * The method takes care only about public methods.

     *

     * @param to Object which properties will be set.

     * @param from Object which properties will be used to get values.

     */


    public static void assign(Object to, Object from) {
        if (to == null || from == null) {
            return;
        }

        List<Method> gettersFrom = extractMethods(from, new GetterFilter());
        if (gettersFrom.size() == 0){
            return;
        }

        List<Method> settersTo = extractMethods(to, new SetterFilter());

        if (settersTo.size() == 0) {
            return;
        }

        for (Method get: gettersFrom) {
            for (Method set: settersTo){
                if (areCompatible(get, set)){
                    try {
                        set.invoke(to, get.invoke(from));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private static boolean areCompatible(Method get, Method set) {
        return get.getName().substring(3).equals(set.getName().substring(3))
                && get.getReturnType().equals(set.getParameterTypes()[0]);
    }

    private static List<Method> extractMethods(Object object, MethodFilter filter) {
        Method[] methods = object.getClass().getMethods();
        List<Method> result = new ArrayList<>();
        if (methods.length != 0) {
            for(Method s: methods) {
                if(filter.filter((s))){
                    result.add(s);
                }
            }
        }
        return result;
    }

}