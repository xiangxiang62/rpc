package com.xiang.xiangrpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 25 12 48
 **/
@Slf4j
public class MockServiceProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> methodReturnType = method.getReturnType();
        String name = method.getName();
        log.info("mock invoke {}", name);
        return getDefaultObject(methodReturnType);
    }


    private Object getDefaultObject(Class<?> type){
        // 基本类型
        if (type.isPrimitive()){
            if (type == boolean.class){
                return false;
            }else if (type == short.class){
                return (short)0;
            }else if (type == int.class){
                return 0;
            }else if (type == long.class){
                return 0L;
            }
        }
        // 对象类型
        return null;
    }

}
