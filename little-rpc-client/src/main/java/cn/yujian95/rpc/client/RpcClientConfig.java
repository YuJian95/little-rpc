package cn.yujian95.rpc.client;

import cn.yujian95.rpc.Peer;
import cn.yujian95.rpc.codec.Decoder;
import cn.yujian95.rpc.codec.Encoder;
import cn.yujian95.rpc.codec.JSONDecoder;
import cn.yujian95.rpc.codec.JSONEncoder;
import cn.yujian95.rpc.transport.HTTPTransportClient;
import cn.yujian95.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author yujian yujian95_cn@163.com
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
