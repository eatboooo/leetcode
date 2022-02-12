package cn.eatboooo.study.week09;

/**
 * 卡特兰数
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:28
 *
 * 假设给你N个0，和N个1，你必须用全部数字拼序列，
 * 返回有多少个序列满足任何前缀串，1的数量都不少于0的数量
 */
public class Demo04_Cattelen_OneZero {
    // 经典卡特兰数问题，参考括号问题
    // C（2n,n） = (2n!)/(n!*(2n - n)!) = (n~2n)!/n!
    // 卡特兰数 ： C(2n,n) / n + 1
    public static long ways2(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        long a = 1;
        long b = 1;
        long limit = N << 1;
        for (long i = 1; i <= limit; i++) {
            if (i <= N) {
                a *= i;
            } else {
                b *= i;
            }
        }
        return (b / a) / (N + 1);
    }
}
