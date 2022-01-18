package cn.yujian95.rpc.demo;

import cn.yujian95.rpc.client.RpcClient;

/**
 * @author yujian yujian95_cn@163.com
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(1, 2);
        int r2 = service.minus(10, 8);

        System.out.println(r1);
        System.out.println(r2);
    }
}
