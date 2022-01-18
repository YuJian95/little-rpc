package cn.yujian95.rpc;

import cn.yujian95.rpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理 rpc 暴露的服务
 *
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/18
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> serviceMap;

    public ServiceManager() {
        this.serviceMap = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);

        for (Method method : methods) {
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass, method);

            serviceMap.put(serviceDescriptor, serviceInstance);
            log.info("register service:{} {}", serviceDescriptor.getClazz(), serviceDescriptor.getMethod());
        }
    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor serviceDescriptor = request.getService();
        return serviceMap.get(serviceDescriptor);
    }
}
