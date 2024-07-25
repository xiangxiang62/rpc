package com.xiang.xiangrpc.serializer;

import java.io.IOException;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 22 10
 **/
public interface Serializer {

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> byte[] serializer(T object) throws IOException;


    /**
     * 反序列化
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserializer(byte[] bytes, Class<T> type) throws IOException;


}
