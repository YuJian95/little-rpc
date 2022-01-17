package cn.yujian95.rpc;

import lombok.Data;

/**
 * 表示 RPC 的返回
 *
 * @author yujian
 */
@Data
public class Response {
    /**
     * 状态码
     */
    private int code = 0;
    /**
     * 返回信息
     */
    private String message = "ok";
    /**
     * 返回参数
     */
    private Object data;
}
