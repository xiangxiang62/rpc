package com.xiang.example.consumer;

import com.xiang.example.common.service.UserService;
import com.xiang.xiangrpc.config.RpcConfig;
import com.xiang.xiangrpc.proxy.ServiceProxyFactory;
import com.xiang.xiangrpc.utils.ConfigUtils;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 25 11 58
 **/
public class ConsumerExample {


    public static void main(String[] args) {

        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpc);

        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        System.out.println(userService.getNumber());
    }

}
