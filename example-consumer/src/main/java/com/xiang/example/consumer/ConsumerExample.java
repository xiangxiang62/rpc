package com.xiang.example.consumer;

import com.xiang.example.common.model.User;
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
        User user = new User();
        user.setName("xiangxiang");
        User user1 = userService.getUser(user);
        System.out.println(user1.getName());
//        System.out.println(userService.getNumber());
    }

}
