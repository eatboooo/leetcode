package cn.eatboooo.study.week10;

/**
 * 动态规划中外部信息简化
 * 刷刷乐
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/20 22:03
 * <p>
 * 有台奇怪的打印机有以下两个特殊要求：
 * 打印机每次只能打印由同一个字符组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串s，你的任务是计算这个打印机打印它需要的最少打印次数。
 * Leetcode题目：https://leetcode.com/problems/strange-printer/
 */
public class Demo03_Try_StrangePrinter {
    public static int strangePrinter1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1);
    }

    // 从 l ～ r 最少转数是多少
    private static int process1(char[] arr, int l, int r) {
        if (l == r) {
            return 1;
        }
        // 每个位置都单独打印，需要都次数即为最大次数
        int ans = r - l + 1;
        for (int i = l + 1; i <= r; i++) {
            // 从 i 位置，分成左右两部分
            int left = process1(arr, l, i - 1);
            int right = process1(arr, i, r);

            // 左部分的第一个如果和右部分的第一个相等，就可以合并起来一起刷
            // 如果可以一起刷，呢就在原来分开的基础上 -1
            int merge = arr[l] == arr[i] ? 1 : 0;
            // ⚠️ 经过测试，发现只要 左部分与右部分的任意一个相等，就可以一起刷
            // 因为最后通过递归，总是要变成一个数和一个数这样子
            // 所以 int merge = arr[l] == arr[r] ? 1 : 0; 也成立
            ans = Math.min(ans, left + right - merge);
        }
        return ans;
    }

    // 动态规划
    public static int strangePrinter2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        for (int l = arr.length - 2; l >= 0; l--) {
            for (int r = l + 1; r < dp.length; r++) {
                int ans = r - l + 1;
                for (int i = l + 1; i <= r; i++) {
                    // 从 i 位置，分成左右两部分
                    int left = dp[l][i - 1];
                    int right = dp[i][r];

                    // 左部分的第一个如果和右部分的第一个相等，就可以合并起来一起刷
                    // 如果可以一起刷，呢就在原来分开的基础上 -1
                    int merge = arr[l] == arr[i] ? 1 : 0;
                    // ⚠️ 经过测试，发现只要 左部分与右部分的任意一个相等，就可以一起刷
                    // 因为最后通过递归，总是要变成一个数和一个数这样子
                    // 所以 int merge = arr[l] == arr[r] ? 1 : 0; 也成立
                    ans = Math.min(ans, left + right - merge);
                }
                dp[l][r] = ans;
            }
        }
        return dp[0][arr.length - 1];
    }


}
