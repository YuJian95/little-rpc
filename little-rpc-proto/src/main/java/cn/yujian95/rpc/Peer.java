package cn.yujian95.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示网络传输的一个端点
 *
 * @author yujian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    /**
     * ip地址或域名
     */
    private String host;
    /**
     * 端口
     */
    private int port;
}
