package com.lucas;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author dangkeyi
 * @description 自定义类加载器
 * @since 2020.01.13
 */
public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class clazz = new MyClassLoader().findClass("Hello");
            Method method = clazz.getMethod("hello");
            method.invoke(clazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取class对象
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            InputStream in = new FileInputStream("/Users/lucas/IdeaProjects/GeekUniversity/JAVA-01/Week_01/Hello.xlass");
            byte[] data = getByteArray(in);
            return defineClass(name, data, 0, data.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字节流
     *
     * @param in
     * @return
     * @throws IOException
     */
    private byte[] getByteArray(InputStream in) throws IOException {
        byte[] buffer = new byte[1024 * 4];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(255 - n);
        }
        return out.toByteArray();
    }
}
