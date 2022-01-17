package cn.yujian95.rpc.codec;

/**
 * 反序列化 接口
 *
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
public interface Decoder {
    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @param clazz 类
     * @param <T>   泛型
     * @return 反序列化得到的对象
     */
    <T> T decode(byte[] bytes, Class<T> clazz);
}
