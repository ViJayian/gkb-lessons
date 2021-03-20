package org.geektimes.test;

import org.geektimes.configuration.microprofile.config.JavaConfig;

import java.util.Map;
import java.util.Properties;

/**
 * converter test {@link JavaConfig}
 * 自测使用，作业测试 {@link TestListener}
 *
 * @author ViJay
 * @date 2021/3/14 20:05
 */
@Deprecated
public class ConverterTest {


    /**
     * String 类型转换为目标 Class类型
     *
     * @param propertyValue
     * @param aClass
     * @param <T>
     * @return
     */
    public static <T> T convert(String propertyValue, Class<T> aClass) {
        T targetValue = null;
        if (aClass == Boolean.class || aClass == boolean.class) {
        }
        return targetValue;
    }

    /*public static void main(String[] args) {
        Class classt1 = Boolean.class;
        Class classt2 = boolean.class;
        System.out.println(classt1.getName());
        System.out.println(classt1.getSimpleName());
        System.out.println(classt1.getTypeName());
        System.out.println(classt2.getName());
        System.out.println(classt2.getSimpleName());
        System.out.println(classt2.getTypeName());
        System.out.println(classt1 == classt2);

       *//* Type[] genericInterfaces = B.class.getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
        Class actualTypeArgument = (Class) actualTypeArguments[0];
        System.out.println(actualTypeArgument.getSimpleName());
*//*

        Properties systemProperties = System.getProperties();
        Map<String, String> getenv = System.getenv();
        JavaConfig javaConfig = new JavaConfig();
        String value = javaConfig.getValue("java.runtime.name", String.class);
        System.out.println(value);

        int port = javaConfig.getValue("server.port", Integer.class);
        String name = javaConfig.getValue("application.name", String.class);
        System.out.println(String.format("application name = [%s] , port = [%d]", name, port));
    }*/
}

