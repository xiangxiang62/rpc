package com.xiang.example.consumer;

import com.xiang.example.common.model.User;
import com.xiang.example.common.service.UserService;
import com.xiang.xiangrpc.proxy.ServiceProxyFactory;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 20 32
 **/
public class EasyConsumerExample {

    public static void main(String[] args) {
//        // todo 需要获取 UserService 的实现类对象
//        UserService userService = null;
//        User user = new User();
//        user.setName("yupi");
//        // 调用
//        User newUser = userService.getUser(user);
//        if (newUser != null){
//            System.out.println(newUser.getName());
//        }else{
//            System.out.println("user == null");
//        }
//        UserService userService = new UserServiceProxy();
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("xiangmouren");
        User user1 = userService.getUser(user);
        System.out.println(user1.getName());

    }

}
