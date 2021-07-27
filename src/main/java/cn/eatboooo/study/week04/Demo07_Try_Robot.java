/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week04
 * @className cn.eatboooo.study.week04.Demo07_Try_Robot
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week04;

/**
 * Demo07_Try_Robot
 * @description
 * @author weiZhiLin
 * @date 2021/7/27 16:45
 * @version 1.0
 *
 * 机器人走来走去
 * 范围是 1~N
 * 起点是 start
 * 目标是 aim
 * 必须走 K 步
 * 返回有几种方法可以抵达
 */
public class Demo07_Try_Robot {
    public static int ways1(int N, int start, int aim, int K) {
        // 判断是否越界
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(N, start, aim, K);
    }

    private static int process1(int n, int start, int aim, int rest) {
        if (rest == 0) {
            return start == aim ? 1 : 0;
        }
        // 边界判断
        // 走到了最右侧
        if (start == n) {
            return process1(n, start - 1, aim, rest - 1);
        }
        // 走到了最左侧
        if (start == 1) {
            return process1(n, start + 1, aim, rest - 1);
        }
        return process1(n, start - 1, aim, rest - 1) + process1(n, start + 1, aim, rest - 1);
    }

    // 重复计算太多，搞个缓存
    public static int ways2(int N, int start, int aim, int K) {
        // 判断是否越界
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        // 缓存数组
        int[][] dp = new int[N + 1][K + 1];
        // 赋初始值，默认 -1 代表没算过
        // 这里是小于等于，非常关键，否则最后返回必是 0 ，因为边缘两行都是 0
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(N, start, aim, K, dp);
    }

    private static int process2(int n, int start, int aim, int rest, int[][] dp) {
        // 先看缓存
        if (dp[start][rest] != -1) {
            return dp[start][rest];
        }
        // 缓存没算过
        int ans = 0;
        if (rest == 0) {
            ans = start == aim ? 1 : 0;
        } else if (start == n) { // 只能往左
            ans = process2(n, start - 1, aim, rest - 1, dp);
        } else if (start == 1) { // 只能往右
            ans = process2(n, start+ 1, aim, rest - 1, dp);
        } else { // 左右都可
            ans = process2(n, start - 1, aim, rest - 1, dp) + process2(n, start+ 1, aim, rest - 1, dp);
        }
        dp[start][rest] = ans;
        return ans;
    }

    // 把暴力递归改为动态规划
    public static int ways3(int N, int start, int aim, int K) {
        // 判断是否越界
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        // 动态规划数组
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            dp[N][rest] = dp[N - 1][rest - 1];

            // cur = 1 , cur = N 是特殊情况，上面已经处理了
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
        }
        return dp[start][K];
    }
    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }
}
