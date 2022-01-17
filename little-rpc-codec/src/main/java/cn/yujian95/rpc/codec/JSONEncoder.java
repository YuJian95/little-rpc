package cn.yujian95.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
public class JSONEncoder implements Encoder {
    /**
     * 序列化对象
     *
     * @param object 对象
     * @return 序列化得到的字节数组
     */
    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }
}
