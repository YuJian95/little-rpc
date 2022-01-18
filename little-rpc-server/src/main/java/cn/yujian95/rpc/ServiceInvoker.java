package cn.yujian95.rpc;

import cn.yujian95.rpc.common.utils.ReflectionUtils;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/18
 */
public class ServiceInvoker {
    public Object invoker(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}
