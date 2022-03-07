package cn.eatboooo.study.week04;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/30 14:46
 * <p>
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 * <p>
 * 背包问题，从左往右的尝试模型
 */
public class Demo07_Try_Goods {
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, bag, 0);
    }

    // 无非两种，带我和不带我
    private static int process(int[] w, int[] v, int bag, int i) {
        if (bag < 0) {
            return Integer.MIN_VALUE;
        }
        if (i == w.length) {
            // 已经没有物品了！
            return 0;
        }
        // 带我
        int p1 = process(w, v, bag - w[i], i + 1);
        p1 += v[i];
        // 不带
        int p2 = process(w, v, bag, i + 1);
        return Math.max(p1, p2);
    }

    // 自己改的第一版，问题很多，尤其是边界条件
    public static int dp1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int[][] dp = new int[bag + 1][w.length + 1];
        for (int index = w.length-1; index >= 0; index--) {
            for (int b = 0; b <= bag; b++) {
                int p2 = 0;
                if (b - w[index] >= 0) {
                    p2 = dp[b - w[index]][index + 1] + v[index];
                }
                dp[b][index] = Math.max(p2, dp[b][index + 1]);
            }
        }
        print(dp);
        return dp[bag][0];
    }

    private static void print(int[][] dp) {

        for (int i = 0; i < dp[0].length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[j][i] + "\t");
            }
            System.out.println();
        }
    }

    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    // test
    public static void main(String[] args) {
        int[] weights = {10,1,2,3,2,5,3};
        // int[] weights = {3, 2, 4, 7, 3, 1, 7};
        // int[] values = {5, 6, 3, 19, 12, 4, 2};
        int[] values = {10,4,5,5,6,1,3};
        int bag = 7;
        // System.out.println(maxValue(weights, values, bag));
        System.out.println(dp1(weights, values, bag));
    }
}
