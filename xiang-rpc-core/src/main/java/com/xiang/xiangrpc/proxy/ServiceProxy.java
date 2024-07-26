package com.xiang.xiangrpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.xiang.xiangrpc.RpcApplication;
import com.xiang.xiangrpc.model.RpcRequest;
import com.xiang.xiangrpc.model.RpcResponse;
import com.xiang.xiangrpc.serializer.JdkSerializer;
import com.xiang.xiangrpc.serializer.Serializer;
import com.xiang.xiangrpc.serializer.SerializerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 23 58
 **/
public class ServiceProxy implements InvocationHandler {


    /**
     * 调用代理
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        // 序列化
        byte[] bodyBytes = serializer.serializer(rpcRequest);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                .body(bodyBytes)
                .execute();

        byte[] result = httpResponse.bodyBytes();
        // 反序列化
        RpcResponse rpcResponse = serializer.deserializer(result, RpcResponse.class);
        return rpcResponse.getData();

    }



}
