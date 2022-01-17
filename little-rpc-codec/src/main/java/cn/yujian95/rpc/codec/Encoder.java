package cn.yujian95.rpc.codec;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
public interface Encoder {
    /**
     * 序列化对象
     *
     * @param object 对象
     * @return 序列化得到的字节数组
     */
    byte[] encode(Object object);
}
