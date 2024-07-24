package com.xiang.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.xiang.example.common.model.User;
import com.xiang.example.common.service.UserService;
import com.xiang.xiangrpc.model.RpcRequest;
import com.xiang.xiangrpc.model.RpcResponse;
import com.xiang.xiangrpc.serializer.JdkSerializer;
import com.xiang.xiangrpc.serializer.Serializer;

import java.io.IOException;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 23 39
 **/
public class UserServiceProxy implements UserService {


    @Override
    public User getUser(User user) {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();

        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();

        try {
            byte[] bodyBytes = serializer.serializer(rpcRequest);
            byte[] result;
            HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute();
            result = httpResponse.bodyBytes();
            RpcResponse rpcResponse = serializer.deserializer(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
