package cn.yujian95.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求
 *
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
public interface RequestHandler {
    /**
     * @param receive    接收
     * @param toResponse 返回
     */
    void onRequest(InputStream receive, OutputStream toResponse);
}
