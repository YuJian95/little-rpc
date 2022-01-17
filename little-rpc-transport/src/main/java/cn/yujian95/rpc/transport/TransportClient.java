package cn.yujian95.rpc.transport;

import cn.yujian95.rpc.Peer;

import java.io.InputStream;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
public interface TransportClient {

    /**
     * 创建连接
     *
     * @param peer 表示网络传输的一个端点
     */
    void connect(Peer peer);

    /**
     * 发送数据，并且等待响应
     *
     * @param inputStream 输入流
     * @return 结果输入流
     */
    InputStream write(InputStream inputStream);

    /**
     * 关闭连接
     */
    void close();
}
