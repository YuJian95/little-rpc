package cn.yujian95.rpc.codec;

import org.junit.Assert;
import org.junit.Test;

public class JSONEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();

        TestBean testBean = new TestBean();
        testBean.setName("yujian");
        testBean.setAge(20);

        byte[] bytes = encoder.encode(testBean);

        Assert.assertNotNull(bytes);
    }
}