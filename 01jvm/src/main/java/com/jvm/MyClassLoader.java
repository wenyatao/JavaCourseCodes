package com.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        // 相关参数
        final String className = "Hello";
        final String methodName = "hello";
        // 创建类加载器
        ClassLoader classLoader = new MyClassLoader();
        // 加载相应的类
        Class<?> clazz = classLoader.loadClass(className);
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName() + "." + m.getName());
        }
        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用实例方法
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("/Users/wenyt/javaCourseCodes/01jvm/src/main/resources/Hello.xlass");
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassBytes(File file) throws Exception {
        //读取字节
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel fileChannel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        int bytesRead = fileChannel.read(buffer);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        WritableByteChannel writableByteChannel = Channels.newChannel(outputStream);

        while (bytesRead != -1) {
            buffer.flip();//模式切换
            writableByteChannel.write(buffer);
            buffer.clear();//清空position
            bytesRead = fileChannel.read(buffer);
        }
        inputStream.close();
        return decode(outputStream.toByteArray());
    }

    // 解码
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

}
