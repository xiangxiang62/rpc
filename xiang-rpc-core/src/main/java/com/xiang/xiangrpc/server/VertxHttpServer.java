package com.xiang.xiangrpc.server;

import com.xiang.xiangrpc.service.HttpServer;
import com.xiang.xiangrpc.service.HttpServerHandler;
import io.vertx.core.Vertx;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 21 56
 **/
public class VertxHttpServer implements HttpServer {

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.out.println("Failed to start server: " + result.cause());
            }
        });
    }

}
