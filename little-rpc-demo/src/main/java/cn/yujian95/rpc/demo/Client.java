package cn.yujian95.rpc.demo;

import cn.yujian95.rpc.client.RpcClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yujian yujian95_cn@163.com
 */
@Slf4j
public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(1, 2);
        int r2 = service.minus(10, 8);

        log.info("r1: {}", r1);
        log.info("r2: {}", r2);
    }
}
