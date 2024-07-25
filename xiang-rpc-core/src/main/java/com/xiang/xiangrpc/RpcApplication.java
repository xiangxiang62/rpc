package com.xiang.xiangrpc;

import com.xiang.xiangrpc.config.RpcConfig;
import com.xiang.xiangrpc.constant.RpcConstant;
import com.xiang.xiangrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 25 11 42
 **/
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    public static void init (RpcConfig newRpcConfig){
        rpcConfig = newRpcConfig;
        log.info("rpc init,config = {}",newRpcConfig.toString());
    }


    public static void init(){
        RpcConfig newRpcConfig;
        try{
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        }catch (Exception e){
            // 配置类加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);

    }


    public static RpcConfig getRpcConfig(){
        if (rpcConfig == null){
            synchronized (RpcApplication.class){
                if (rpcConfig == null){
                    init();
                }
            }
        }
        return rpcConfig;
    }

}
