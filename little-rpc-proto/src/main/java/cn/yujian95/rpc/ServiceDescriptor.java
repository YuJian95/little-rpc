package cn.yujian95.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示服务
 *
 * @author yujian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    /**
     * 类
     */
    private String clazz;
    /**
     * 方法
     */
    private String method;
    /**
     * 返回类型
     */
    private String returnType;
    /**
     * 参数类型
     */
    private String[] parameterTypes;
}
