package test;

import model.User;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: Xiaofei
 * @DATE: 2021/4/8 21:51
 */
public class RefletTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException {
        User user = new User();
        user.getClass();
        System.out.println(user.getClass());
        Class clazz = User.class;
        System.out.println(clazz);
        Class.forName("model.User");
        System.out.println(Class.forName("model.User"));
        System.out.println(clazz.getDeclaredField("name"));
        System.out.println(clazz.getField("grade"));
        Method getName = clazz.getMethod("getName");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(user, "aaa");
        System.out.println(user);
        System.out.println(getName.getReturnType());;
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
