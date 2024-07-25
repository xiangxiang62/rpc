package com.xiang.xiangrpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 25 00 05
 **/
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> serviceClass){
        return(T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }

}
