package com.xiang.xiangrpc.service;

import com.xiang.xiangrpc.model.RpcRequest;
import com.xiang.xiangrpc.model.RpcResponse;
import com.xiang.xiangrpc.registry.LocalRegistry;
import com.xiang.xiangrpc.serializer.JdkSerializer;
import com.xiang.xiangrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 22 25
 **/
public class HttpServerHandler implements Handler<HttpServerRequest> {

    /**
     * 1. 反序列化请求为对象，并从请求对象中获取参数；
     * 2. 根据服务名称从本地注册器中获取到对应的服务实现类；
     * 3. 通过反射机制调用方法，得到返回结果；
     * 4. 对返回结果进行封装和序列化，并写入到响应中。
     *
     * @param request
     */

    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        final Serializer serializer = new JdkSerializer();

        request.bodyHandler(body -> {
            byte[] bodyBytes = body.getBytes();
            RpcRequest rpcRequest = null;

            try {
                rpcRequest = serializer.deserializer(bodyBytes, RpcRequest.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            // 如果请求为 null，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request,rpcResponse,serializer);
                return;
            }

            // 获取想要调用的服务实现类，通过反射调用
            Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
            Method method = null;
            try {
                method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                // 封装返回的结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("OK");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            // 响应
            doResponse(request,rpcResponse,serializer);
        });
    }

    /**
     * 响应
     *
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response().putHeader("content-type", "application/json");

        try {
            byte[] serialized = serializer.serializer(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }

    }


}
