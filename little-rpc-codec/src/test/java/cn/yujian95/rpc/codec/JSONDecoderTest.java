package cn.yujian95.rpc.codec;

import org.junit.Assert;
import org.junit.Test;

public class JSONDecoderTest {

    @Test
    public void testDecode() {
        Encoder encoder = new JSONEncoder();

        TestBean testBean = new TestBean();
        testBean.setName("yujian");
        testBean.setAge(20);

        byte[] bytes = encoder.encode(testBean);
        Decoder decoder = new JSONDecoder();

        TestBean testBean2 = decoder.decode(bytes, TestBean.class);
        Assert.assertEquals(testBean2.getAge(), testBean.getAge());
        Assert.assertEquals(testBean2.getName(), testBean.getName());
    }
}