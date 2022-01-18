package cn.yujian95.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
