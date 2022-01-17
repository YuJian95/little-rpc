package cn.yujian95.rpc;

import lombok.Data;

/**
 * 表示 RPC 的请求
 *
 * @author yujian
 */
@Data
public class Request {
    /**
     * 表示服务
     */
    private ServiceDescriptor service;
    /**
     * 请求参数
     */
    private Object[] parameters;
}
