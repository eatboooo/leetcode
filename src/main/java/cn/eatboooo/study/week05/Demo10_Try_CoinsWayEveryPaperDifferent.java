/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week05
 * @className cn.eatboooo.study.week05.Demo10_Try_CoinsWayEveryPaperDifferent
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week05;

/**
 * Demo10_Try_CoinsWayEveryPaperDifferent
 * @description
 * @author weiZhiLin
 * @date 2021/8/4 15:28
 * @version 1.0
 *
 * 用数组里的钱 组成 aim
 */
public class Demo10_Try_CoinsWayEveryPaperDifferent {
    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int i, int aim) {
        if (i == arr.length) {
            // no paper
            return aim == 0 ? 1 : 0;
        }
        // 用我
        int way = process(arr, i + 1, aim - arr[i]);
        // 不用我
        way += process(arr, i + 1, aim);
        return way;
    }

    // dp
    public static int dp(int[] arr, int aim) {
        int indexLength = arr.length + 1;
        int restLength = aim + 1;
        int[][] dp = new int[indexLength][restLength];
        dp[arr.length][0] = 1;
        for (int index = indexLength-2; index >= 0; index--) {
            for (int rest = 0; rest < restLength; rest++) {
                if (rest - arr[index] < 0) {
                    dp[index][rest] = dp[index + 1][rest];
                    continue;
                }
                dp[index][rest] = dp[index + 1][rest - arr[index]] + dp[index + 1][rest];
            }
        }
        return dp[0][restLength - 1];
    }


    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
