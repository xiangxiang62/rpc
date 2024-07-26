package com.xiang.xiangrpc.config;

import com.xiang.xiangrpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 25 11 00
 **/
@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "xiang-rpc";


    /**
     * 版本号
     */
    private String version = "1.0";


    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";


    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;


    /**
     * 是否开启 mock
     */
    private boolean mock = false;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

}
