package cn.eatboooo.study.week07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 滑动窗口对动态规划的优化
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/10 10:16
 * <p>
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 */
public class Demo01_Window_TryMinCoins {
    public static int minCoins3(int[] arr, int aim) {
        Map map = toMap(arr);
        int[] ints = Arrays.stream(arr).distinct().toArray();
        return minCoinsDp(map, ints, 0, aim);
    }

    private static int minCoinsDp(Map<Integer, Integer> map, int[] ints, int i, int aim) {
        if (aim == 0) {
            // 返回的是使用了多少张，而不是有多少种方法
            // 张数在之前已经加过了
            return 0;
        }
        if (aim < 0 || i == ints.length) {
            return Integer.MAX_VALUE;
        }
        int anInt = ints[i];
        // 一开始最大值，在遍历中包含了 without
        int p1 = Integer.MAX_VALUE;
        for (int k = 0; k <= map.get(anInt) && aim - k * anInt >= 0; k++) {
            int i1 = minCoinsDp(map, ints, i + 1, aim - k * anInt);
            // 如果把最大值放进去，再加上张数，会变成负数
            if (i1 != Integer.MAX_VALUE) {
                // 记得 +k，也就是加上张数
                p1 = Math.min(p1, i1 + k);
            }
        }
        return p1;
    }

    public static Map toMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        return map;
    }

    // 动态规划-版本1
    public static int dp1(int[] arr, int aim) {
        Map<Integer, Integer> map = toMap(arr);
        int[] ints = Arrays.stream(arr).distinct().toArray();
        // ⚠️注意边界，第一遍排查了很久
        int length = ints.length + 1;
        int[][] dp = new int[length][aim + 1];
        // 第一遍遗漏掉可以优化的点
        for (int j = 1; j <= aim; j++) {
            dp[length - 1][j] = Integer.MAX_VALUE;
        }

        for (int i = length - 2; i >= 0; i--) {
            for (int a = 0; a < aim + 1; a++) {
                int anInt = ints[i];
                // 一开始最大值，在遍历中包含了 without
                // 第一遍遗漏掉可以优化的点
                dp[i][a] = dp[i + 1][a];
                for (int k = 1; k <= map.get(anInt) && a - k * anInt >= 0; k++) {
                    // 如果把最大值放进去，再加上张数，会变成负数
                    if (dp[i + 1][a - k * anInt] != Integer.MAX_VALUE) {
                        // 记得 +k，也就是加上张数
                        dp[i][a] = Math.min(dp[i][a], dp[i + 1][a - k * anInt] + k);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    // 动态规划-版本2
    public static int dp2(int[] arr, int aim) {
        Map<Integer, Integer> map = toMap(arr);
        int[] ints = Arrays.stream(arr).distinct().toArray();
        // ⚠️注意边界，第一遍排查了很久
        int length = ints.length + 1;
        int[][] dp = new int[length][aim + 1];
        // 第一遍遗漏掉可以优化的点
        for (int j = 1; j <= aim; j++) {
            dp[length - 1][j] = Integer.MAX_VALUE;
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int a = 0; a < Math.min(aim + 1, ints[i]); a++) {
                int anInt = ints[i];
                // 一开始最大值，在遍历中包含了 without
                // 第一遍遗漏掉可以优化的点
                dp[i][a] = dp[i + 1][a];

                // 滑动窗口优化
                LinkedList<Integer> list = new LinkedList<>();
                list.add(a);
                for (int rest = a + anInt; rest <= aim; rest += anInt) {
                    // 补偿
                    // int offset = (rest - list.peekLast()) / anInt;
                    // 加入双向队列
                    while (!list.isEmpty() && (dp[i + 1][list.peekLast()] == Integer.MAX_VALUE ||
                            dp[i + 1][list.peekLast()] + ((-list.peekLast() + rest) / anInt) >= dp[i + 1][rest])) {
                        list.pollLast();
                    }
                    list.addLast(rest);

                    // 弹出窗口外的,所以张数要+1,注意是 rest
                    int overdue = rest - (map.get(anInt) + 1) * anInt;
                    if (overdue == list.peekFirst()) {
                        list.pollFirst();
                    }
                    // peekFirst，细心
                    dp[i][rest] = dp[i + 1][list.peekFirst()] + ((-list.peekFirst() + rest) / anInt);
                }
            }
        }
        return dp[0][aim];
    }
}
