package rpc;

import lombok.Data;

/**
 * 表示RPC的请求
 *
 * @author yujian
 */
@Data
public class Request {
    /**
     * 表示服务
     */
    private ServiceDescriptor service;
    private Object[] parameters;
}
