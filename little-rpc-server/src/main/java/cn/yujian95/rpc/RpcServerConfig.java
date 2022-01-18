package cn.yujian95.rpc;

import cn.yujian95.rpc.codec.Decoder;
import cn.yujian95.rpc.codec.Encoder;
import cn.yujian95.rpc.codec.JSONDecoder;
import cn.yujian95.rpc.codec.JSONEncoder;
import cn.yujian95.rpc.transport.HTTPTransportServer;
import cn.yujian95.rpc.transport.TransportServer;
import lombok.Data;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/18
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportServerClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
