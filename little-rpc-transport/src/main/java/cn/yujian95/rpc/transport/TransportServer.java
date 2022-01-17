package cn.yujian95.rpc.transport;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
public interface TransportServer {

    /**
     * 初始化
     *
     * @param port    端口
     * @param handler 请求处理器
     */
    void init(int port, RequestHandler handler);

    /**
     * 启动、监听
     */
    void start();

    /**
     * 关闭监听
     */
    void stop();
}
