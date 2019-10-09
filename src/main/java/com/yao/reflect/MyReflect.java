package com.yao.reflect;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Calm on 2018/11/21
 */
public class MyReflect {

    public String className = null;
    public Class personClass = null;

    /**
     *
     * 反射Person类
     * @throws Exception
     */
    @Before
    public void init() throws Exception{
        className = "com.yao.reflect.Person";
        personClass = Class.forName(className);
    }

    /**
     * 获取某个class文件对象
     * @throws Exception
     */
    @Test
    public void getClassName() throws Exception{
        System.out.println(personClass);
    }

    /**
     * 获取某个class文件对象的另一种方式
     * @throws Exception
     */
    @Test
    public void getClassName2() throws Exception{
        System.out.println(Person.class);
    }

    /**
     * 创建一个class文件表示的实例对象,底层会调用空参数的构造方法
     * @throws Exception
     */
    @Test
    public void getNewInstance() throws Exception{
        System.out.println(personClass.newInstance());
    }

    /**
     * 获取非私有的构造函数
     * @throws Exception
     */
    @Test
    public void getPublicConstructor() throws Exception{
        Constructor constructor = personClass.getConstructor(Long.class, String.class);
        Person person = (Person) constructor.newInstance(100L, "zhangsan");
        System.out.println(person.getId());
        System.out.println(person.getName());
    }

    /**
     * 获取私有的构造函数
     * @throws Exception
     */
    @Test
    public void getPrivateConstructor() throws Exception{
        Constructor con = personClass.getDeclaredConstructor(String.class);
        con.setAccessible(true);//强制取消Java的权限检测
        Person person2 = (Person) con.newInstance("123");
        System.out.println("**" + person2.getName());
    }

    /**
     * 访问非私有的成员变量
     * @throws Exception
     */
    @Test
    public void getNotPrivateField() throws Exception{
        Constructor constructor = personClass.getConstructor(Long.class, String.class);
        Object obj = constructor.newInstance(100L, "234");
        Field field = personClass.getField("name");
        field.set(obj,"555");
        System.out.println(field.get(obj));
    }

    /**
     * 访问私有的成员变量
     * @throws Exception
     */
    @Test
    public void getPrivateField() throws Exception{
        Constructor constructor = personClass.getConstructor(Long.class);
        Object obj = constructor.newInstance(100L);
        Field field2 = personClass.getDeclaredField("id");
        field2.setAccessible(true);//强制取消Java的权限检测
        field2.set(obj,10000L);
        System.out.println(field2.get(obj));
    }

    /**
     * 获取非私有的成员函数
     * @throws Exception
     */
    @Test
    public void getNotPrivateMethod() throws Exception{
        System.out.println(personClass.getMethod("toString"));

        Object obj = personClass.newInstance();//获取空参的构造函数
        Method toStringMethod = personClass.getMethod("toString");
        Object object = toStringMethod.invoke(obj);
        System.out.println(object);
    }

    /**
     * 获取私有的成员函数
     * @throws Exception
     */
    @Test
    public void getPrivateMethod() throws Exception{
        Object obj = personClass.newInstance();//获取空参的构造函数
        Method method = personClass.getDeclaredMethod("getSometing");
        method.setAccessible(true);
        Object value = method.invoke(obj);
        System.out.println(value);
    }

    @Test
    public void otherMethod() throws Exception{
        //当前加载这个class文件的那个类加载器对象
        System.out.println(personClass.getClassLoader());
        //获取某个类实现的所有接口
        Class[] interfaces = personClass.getInterfaces();
        for (Class class1 :
                interfaces) {
            System.out.println(class1);
        }
        //反射当前这个类的直接父类
        System.out.println(personClass.getGenericSuperclass());

        /**
         * getResourceAsStream这个方法可以获取到一个输入流,这个输入流会关联到name所表示的那个文件上
         */
        //path
        //不以'/'开头时默认是从此类所在的包下去资源,以'/'开头则是从ClassPath根下获取.
        //其只是通过path构造一个绝对路径,最终还是由ClassLoader获取资源.
        System.out.println(personClass.getResourceAsStream("/log4j.properties"));
        System.out.println(personClass.getResourceAsStream("log4j.properties"));

        //判断当前的Class对象表示是否是数组
        System.out.println(personClass.isArray());
        System.out.println(new String[3].getClass().isArray());

        //判断当前的Class对象表示是否是枚举类
        System.out.println(personClass.isEnum());
        System.out.println(Class.forName("com.yao.reflect.City").isEnum());

        //判断当前的Class对象表示是否是接口
        System.out.println(personClass.isInterface());
        System.out.println(Class.forName("com.yao.reflect.TestInterface").isInterface());

    }

    @Test
    public void testDemo() throws Exception{
        System.out.println(personClass.getResourceAsStream("/log4j.properties"));
        System.out.println(personClass.getResourceAsStream("log4j.properties"));
    }


}
