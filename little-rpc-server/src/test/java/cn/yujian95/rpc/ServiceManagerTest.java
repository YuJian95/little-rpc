package cn.yujian95.rpc;

import cn.yujian95.rpc.common.utils.ReflectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class ServiceManagerTest {

    ServiceManager serviceManager;

    @Before
    public void before() {
        serviceManager = new ServiceManager();

        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);
    }

    @Test
    public void register() {
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestInterface.class);
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class, methods[0]);
        Request request = new Request();
        request.setService(serviceDescriptor);

        ServiceInstance serviceInstance = serviceManager.lookup(request);
        Assert.assertNotNull(serviceInstance);
    }
}