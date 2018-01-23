package ro.msg.learning.shop.myodata.reflect;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class MyODataReflectMapper {

    private MyODataReflectMapper() {

    }

    public static Map<String, Object> reflectMap(Object object, Class clazz) {
        Map<String, Object> data = new HashMap<>();
        List<Method> accessMethods = Arrays.stream(clazz.getDeclaredMethods()).filter(method -> (method.getName().length() > 3 && method.getName().substring(0, 3).equals("get")) || (method.getName().length() > 2 && method.getName().substring(0, 2).equals("is"))).collect(Collectors.toList());
        Set<String> fieldsToExclude = Arrays.stream(clazz.getDeclaredFields()).filter(
            field -> Arrays.stream(field.getAnnotations()).anyMatch(
                annotation -> annotation.annotationType() == OneToOne.class
                    || annotation.annotationType() == OneToMany.class
                    || annotation.annotationType() == ManyToOne.class
                    || annotation.annotationType() == ManyToMany.class
                    || annotation.annotationType() == Transient.class
            )
        ).map(field -> field.getName().toLowerCase()).collect(Collectors.toSet());

        accessMethods = accessMethods.stream().filter(
            method -> !fieldsToExclude.contains(
                (method.getName().charAt(0) == 'g') ?
                    method.getName().toLowerCase().substring(3) :
                    method.getName().toLowerCase().substring(2))
        ).collect(Collectors.toList());

        for (Method method : accessMethods) {
            try {
                data.put(
                    (method.getName().charAt(0) == 'g') ?
                        method.getName().toLowerCase().substring(3) :
                        method.getName().toLowerCase().substring(2)
                    , method.invoke(object));
            } catch (IllegalAccessException | InvocationTargetException e) {
                Logger.getGlobal().log(Level.FINEST, "Exception in reflectMap:" + e, e.getCause());
            }
        }

        return data;
    }

    public static Map<String, Object> reflectMapWithAnnotation(Object object, Class clazz) {
        Map<String, Object> data = new HashMap<>();
        List<Method> accessMethods = Arrays.stream(clazz.getDeclaredMethods()).filter(method -> (method.getName().length() > 3 && method.getName().substring(0, 3).equals("get")) || (method.getName().length() > 2 && method.getName().substring(0, 2).equals("is"))).collect(Collectors.toList());
        Set<String> fieldsToExclude = new HashSet<>(Arrays.asList(((MyODataMapAnnotation) clazz.getAnnotation(MyODataMapAnnotation.class)).exclude()));
        Set<String> fieldsToMap = new HashSet<>(Arrays.asList(((MyODataMapAnnotation) clazz.getAnnotation(MyODataMapAnnotation.class)).of()));

        if (!fieldsToExclude.isEmpty()) {
            accessMethods = accessMethods.stream().filter(
                method -> !fieldsToExclude.contains((method.getName().charAt(0) == 'g') ?
                    method.getName().toLowerCase().substring(3) :
                    method.getName().toLowerCase().substring(2))
            ).collect(Collectors.toList());

        } else if (!fieldsToMap.isEmpty()) {
            accessMethods = accessMethods.stream().filter(
                method -> fieldsToMap.contains((method.getName().charAt(0) == 'g') ?
                    method.getName().toLowerCase().substring(3) :
                    method.getName().toLowerCase().substring(2))
            ).collect(Collectors.toList());
        }
        try {
            for (Method method : accessMethods) {

                data.put(
                    (method.getName().charAt(0) == 'g') ?
                        method.getName().toLowerCase().substring(3) :
                        method.getName().toLowerCase().substring(2)
                    , method.invoke(object));

            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Logger.getGlobal().log(Level.FINEST, "Exception in reflectMapWithAnnotation:" + e, e.getCause());
        }
        return data;
    }
}
