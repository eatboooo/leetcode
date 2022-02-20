package cn.eatboooo.study.week10;

/**
 * 动态规划中外部信息简化
 * 开心消消乐
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/20 15:30
 * <p>
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色，你将经过若干轮操作去去掉盒子
 * 直到所有的盒子都去掉为止，每一轮你可以移除具有相同颜色的连续k个盒子（k >= 1）
 * 这样一轮之后你将得到 k * k 个积分，当你将所有盒子都去掉之后，求你能获得的最大积分和
 */
// Leetcode题目：https://leetcode.com/problems/remove-boxes/
public class Demo03_Try_RemoveBox {
    public int removeBoxes(int[] arr) {
        return dp(0, arr.length - 1, 0, arr);
    }

    // 在 l 左侧有 k 个和 arr【l】 相同颜色盒子的前提下
    // 把 k 个盒子和 l ～ r  消除后，最大得分是多少
    private int dp(int l, int r, int k, int[] arr) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return (k + 1) * (k + 1);
        }
//        if (arr[l + 1] == arr[l - 1]) {
//            return dp(l + 1, r, k + 1, arr);
//        }
//        // 此时 l != l + 1 颜色不同
        int ans = (k + 1) * (k + 1) + dp(l + 1, r, 0, arr);

        for (int i = l + 1; i <= r; i++) {
            // 去找 k 可以去合并的可能性
            if (arr[i] == arr[l]) {
                int cur = dp(l + 1, i - 1, 0, arr) + dp(i, r, k + 1, arr);
                ans = Math.max(ans, cur);
            }
        }

        return ans;
    }

    // 记忆化搜索
    public int removeBoxes2(int[] arr) {
        int[][][] dp = new int[arr.length][arr.length][arr.length];
        return dp2(0, arr.length - 1, 0, arr, dp);
    }

    // 在 l 左侧有 k 个和 arr【l】 相同颜色盒子的前提下
    // 把 k 个盒子和 l ～ r  消除后，最大得分是多少
    private int dp2(int l, int r, int k, int[] arr, int[][][] dp) {
        if (dp[l][r][k] != 0) {
            return dp[l][r][k];
        }
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return (k + 1) * (k + 1);
        }
//        if (arr[l + 1] == arr[l - 1]) {
//            dp[l][r][k] = dp2(l + 1, r, k + 1, arr, dp);
//            return dp[l][r][k];
//        }
//        // 此时 l != l + 1 颜色不同
        int ans = (k + 1) * (k + 1) + dp2(l + 1, r, 0, arr, dp);

        for (int i = l + 1; i <= r; i++) {
            // 去找 k 可以去合并的可能性
            if (arr[i] == arr[l]) {
                int cur = dp2(l + 1, i - 1, 0, arr, dp) + dp2(i, r, k + 1, arr, dp);
                ans = Math.max(ans, cur);
            }
        }
        dp[l][r][k] = ans;
        return ans;
    }
}
