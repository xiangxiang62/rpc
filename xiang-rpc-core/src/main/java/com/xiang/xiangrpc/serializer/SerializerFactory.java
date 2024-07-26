package com.xiang.xiangrpc.serializer;

import com.xiang.xiangrpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 26 16 18
 **/
public class SerializerFactory {

    /**
     * 序列化映射（用于实现单例）
     */
//    private static final Map<String,Serializer> KEY_SERIALIZER_MAP = new HashMap<String,Serializer>(){{
//        put(SerializerKeys.JDK,new JdkSerializer());
//        put(SerializerKeys.HESSIAN,new HessianSerializer());
//        put(SerializerKeys.JSON,new JsonSerializer());
//        put(SerializerKeys.KRYO,new KryoSerializer());
//    }};

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key){
       return SpiLoader.getInstance(Serializer.class,key);
    }

}
