package cn.yujian95.rpc.demo;

/**
 * @author yujian yujian95_cn@163.com
 */
public interface CalcService {

    /**
     * 加法
     *
     * @param a 加数a
     * @param b 加数b
     * @return 俩数之和
     */
    int add(int a, int b);

    /**
     * 减法
     *
     * @param a 被减数a
     * @param b 减数b
     * @return 俩数之差
     */
    int minus(int a, int b);
}
