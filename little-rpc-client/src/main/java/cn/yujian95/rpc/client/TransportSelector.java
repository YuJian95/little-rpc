package cn.yujian95.rpc.client;

import cn.yujian95.rpc.Peer;
import cn.yujian95.rpc.transport.TransportClient;

import java.util.List;

/**
 * @author yujian yujian95_cn@163.com
 */
public interface TransportSelector {
    /**
     * 初始化 selector
     *
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现类
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport与server做交互
     *
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     *
     * @param client 释放客户端
     */
    void release(TransportClient client);

    /**
     * 关闭连接
     */
    void close();
}
