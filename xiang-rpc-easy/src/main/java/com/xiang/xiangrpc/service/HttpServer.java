package com.xiang.xiangrpc.service;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 20 45
 **/
public interface HttpServer {

    /**
     * HTTP 服务器接口
     *
     * @param port
     */
    void doStart(int port);

}
