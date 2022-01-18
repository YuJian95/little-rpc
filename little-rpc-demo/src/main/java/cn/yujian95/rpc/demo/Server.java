package cn.yujian95.rpc.demo;

import cn.yujian95.rpc.RpcServer;

/**
 * @author yujian yujian95_cn@163.com
 */
public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer();

        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
