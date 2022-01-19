package cn.yujian95.rpc.client;

import cn.yujian95.rpc.Peer;
import cn.yujian95.rpc.common.utils.ReflectionUtils;
import cn.yujian95.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yujian yujian95_cn@163.com
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    private static final Random RAND = new Random();

    /**
     * 已经连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    /**
     * 初始化 selector
     *
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现类
     */
    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);

        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server: {}", peer);
        }
    }

    /**
     * 选择一个transport与server做交互
     *
     * @return 网络client
     */
    @Override
    public synchronized TransportClient select() {
        int i = RAND.nextInt(clients.size());
        return clients.get(i);
    }

    /**
     * 释放用完的client
     *
     * @param client 释放客户端
     */
    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    /**
     * 关闭连接
     */
    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }

        clients.clear();
    }


}
