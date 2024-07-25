package com.xiang.example.consumer;

import cn.hutool.json.JSONUtil;
import com.xiang.xiangrpc.config.RpcConfig;
import com.xiang.xiangrpc.constant.RpcConstant;
import com.xiang.xiangrpc.utils.ConfigUtils;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 25 11 58
 **/
public class ConsumerExample {


    public static void main(String[] args) {

        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpc);

    }

}
