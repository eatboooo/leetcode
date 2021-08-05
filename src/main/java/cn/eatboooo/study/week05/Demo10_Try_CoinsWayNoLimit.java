/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week05
 * @className cn.eatboooo.study.week05.Demo10_Try_CoinsWayNoLimit
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week05;

import cn.eatboooo.leetcode.demo.Base01;

/**
 * Demo10_Try_CoinsWayNoLimit
 * @description
 * @author weiZhiLin
 * @date 2021/8/4 16:48
 * @version 1.0
 *
 * 用数组里的面值 组成 aim
 * 每个面值无数张
 */
public class Demo10_Try_CoinsWayNoLimit {
    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    // my, 这个方法可以走的通，而且转 dp 后相等于空间压缩后的方法，非常 nice
    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            // 没钱了，看看成功没
            return rest == 0 ? 1 : 0;
        }
        if (rest == 0) {
            // 钱凑够了，溜了
            return 1;
        }
        if (rest < 0) {
            // 多了，失败
            return 0;
        }
        int way = 0;
        // 不带我
        way += process(arr, index + 1, rest);
        // 带我，因为后面还有可能带我，所以 index 不加
        way += process(arr, index, rest - arr[index]);
        return way;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int yL = arr.length + 1;
        int xL = aim + 1;
        // [index][rest]
        int[][] dp = new int[yL][xL];
        for (int i = 0; i < yL; i++) {
            dp[i][0] = 1;
        }
        for (int index = yL - 2; index >= 0; index--) {
            for (int rest = 1; rest < xL ; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] < 0 ? 0 : dp[index][rest - arr[index]]);
            }
        }
        return dp[0][aim];
    }

    // todo 空间压缩

    // copy

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
