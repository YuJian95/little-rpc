package cn.yujian95.rpc.transport;

import cn.yujian95.rpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
public class HTTPTransportClient implements TransportClient {

    private String url;

    /**
     * 创建连接
     *
     * @param peer 表示网络传输的一个端点
     */
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    /**
     * 发送数据，并且等待响应
     *
     * @param inputStream 输入流
     * @return 输出流
     */
    @Override
    public InputStream write(InputStream inputStream) {

        HttpURLConnection httpConn = null;

        try {
            httpConn = (HttpURLConnection) new URL(url).openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");

            httpConn.connect();
            IOUtils.copy(inputStream, httpConn.getOutputStream());

            int resultCode = httpConn.getResponseCode();

            if (resultCode == HttpURLConnection.HTTP_OK) {
                return httpConn.getInputStream();
            } else {
                return httpConn.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
    }

    /**
     * 关闭连接
     */
    @Override
    public void close() {
        // 暂时不用关闭
    }
}
