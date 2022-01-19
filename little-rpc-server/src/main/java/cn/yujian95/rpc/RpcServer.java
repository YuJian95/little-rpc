package cn.yujian95.rpc;

import cn.yujian95.rpc.codec.Decoder;
import cn.yujian95.rpc.codec.Encoder;
import cn.yujian95.rpc.common.utils.ReflectionUtils;
import cn.yujian95.rpc.transport.RequestHandler;
import cn.yujian95.rpc.transport.TransportServer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/18
 */
@Slf4j
@Getter
public class RpcServer {

    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    /**
     * 重写 void onRequest(InputStream receive, OutputStream toResponse)
     */
    private RequestHandler handler = (InputStream receive, OutputStream toResponse) -> {
        Response response = new Response();

        try {
            byte[] inBytes = IOUtils.readFully(receive, receive.available());

            Request request = decoder.decode(inBytes, Request.class);
            log.info("get request: {}", request);
            ServiceInstance serviceInstance = serviceManager.lookup(request);

            Object result = serviceInvoker.invoker(serviceInstance, request);
            response.setData(result);

        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response.setCode(1);
            response.setMessage("RpcServer got error: " + e.getClass().getName() + " " + e.getMessage());
        } finally {
            try {
                byte[] outBytes = encoder.encode(response);
                toResponse.write(outBytes);
                log.info("response client");
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
            }
        }
    };

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;

        // net
        this.net = ReflectionUtils.newInstance(config.getTransportServerClass());
        this.net.init(config.getPort(), this.handler);

        // codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        // service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    /**
     * 服务注册
     *
     * @param interfaceClass 服务代理类
     * @param bean           服务实现类
     * @param <T>            服务代理类
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }
}
