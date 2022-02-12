package cn.eatboooo.study.week09;

import java.util.TreeMap;

/**
 * 因为规则限制，所以采取分开治理、再合并
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:27
 * <p>
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]，背包容量为w，牛牛想知道在总体积不超过背包容量的情况下,
 * 一共有多少种零食放法，体积为0也算一种放法
 * 1 <= n <= 30, 1 <= w <= 2 * 10^9，v[I] (0 <= v[i] <= 10^9）
 */
public class Demo03_FindRule_NiuFood {
    // 2 param: 零食 index，剩余空间 rest
    // return : 有多少种方法
    // 从左往右
    public static int m1(int[] v, int w) {
        int[][] dp = new int[v.length + 1][w + 1];
        for (int index = 0; index < v.length + 1; index++) {
            dp[v.length][index] = 1;
        }
        for (int index = v.length; index >= 0; index--) {
            int[] ints = dp[index];
            for (int rest = 0; rest < ints.length; rest++) {
                // use
                int p1 = 0;
                if (rest - v[index] >= 0) {
                    p1 = dp[index + 1][rest - v[index]];
                }
                // unused
                int p2 = dp[index + 1][rest];
                // 这里是有多少种方法，而不是最多有多少个零食
                // dp[index][rest] = Math.max(p1, p2);
                dp[index][rest] = p1 + p2;
            }
        }
        return dp[0][w];
    }


    // 从 start 开始，到 end 结束，已经使用了 sum，最多使用使用 w 空间，
    // 返回有几种拿零食的方法
    // 这种方式可以采用分治骚操作，
    // 数组分成左右两侧，有三种拿货情况
    // 1。只拿左边 2。只拿右边 3。左右都拿
    // 1、2 可以得到，
    // 3：拿完左侧剩余空间 x、拿完左侧方法数量 y，右侧 map.get(w - x) * y 即为一个答案
    //          遍历左侧 map，把答案加起来得到解决办法
    public static long func(int[] v, int start, int end, long sum, long w, TreeMap<Long, Long> map) {
        if (sum > w) {
            return 0;
        }
        if (start > end) {
            if (sum == 0) {
                // 什么货都没拿
                return 0;
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1L);
            }
            return 1;
        }
        // with
        long p1 = func(v, start + 1, end, sum + v[start], w, map);
        // without
        long p2 = func(v, start + 1, end, sum, w, map);

        return p1 + p2;
    }
}
