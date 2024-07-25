package com.xiang.example.provider;

import com.xiang.example.common.service.UserService;
import com.xiang.xiangrpc.RpcApplication;
import com.xiang.xiangrpc.registry.LocalRegistry;
import com.xiang.xiangrpc.server.VertxHttpServer;
import com.xiang.xiangrpc.service.HttpServer;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 20 30
 **/
public class EasyProviderExample {

    public static void main(String[] args) {
        // rpc 初始化
        RpcApplication.init();
        // 注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        // 提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }

}
