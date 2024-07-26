package com.xiang.xiangrpc.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiang.xiangrpc.model.RpcRequest;
import com.xiang.xiangrpc.model.RpcResponse;

import java.io.IOException;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 26 13 01
 **/
public class JsonSerializer implements Serializer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serializer(T object) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(object);
    }

    @Override
    public <T> T deserializer(byte[] bytes, Class<T> type) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes, type);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest) obj,type);
        }

        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse) obj,type);
        }


        return obj;
    }

    /**
     * 由于 Object 的原始对象会被擦除，导致反序列化时会被 LinkedHashMap 无法转换为原始对象，因此这里做了特出处理
     *
     * @param rpcRequest
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> type) throws IOException {
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();

        // 循环处理每个参数的类型
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> clazz = parameterTypes[i];
            // 如果类型不同，则重新处理一下类型
            if (!clazz.isAssignableFrom(args[i].getClass())){
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                OBJECT_MAPPER.readValue(argBytes,clazz);
            }
        }
        return type.cast(rpcRequest);
    }

    /**
     * 由于 Object 的原始对象会被擦除，导致反序列化时会被 LinkedHashMap 无法转换为原始对象，因此这里做了特出处理
     *
     * @param rpcResponse
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    private <T> T handleResponse(RpcResponse rpcResponse,Class<T> type) throws IOException{
        // 处理响应数据
        byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes,rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }


}
