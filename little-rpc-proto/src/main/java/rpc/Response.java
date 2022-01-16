package rpc;

import lombok.Data;

/**
 * 表示RPC的返回
 *
 * @author yujian
 */
@Data
public class Response {
    private int code = 0;
    private String message = "ok";
    private Object data;
}
