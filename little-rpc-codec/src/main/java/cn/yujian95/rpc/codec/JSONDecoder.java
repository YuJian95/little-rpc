package cn.yujian95.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * JSON 序列化工具
 *
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
public class JSONDecoder implements Decoder {
    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @param clazz 类
     * @return 反序列化得到的对象
     */
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
