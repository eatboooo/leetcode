package cn.eatboooo.study.week10;

/**
 * 动态规划中外部信息简化
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/20 13:55
 * <p>
 * 有n个气球，编号为0到n-1，每个气球上都标有一个数字，这些数字存在数组nums中
 * 现在要求你戳破所有的气球。戳破第i个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1] 枚硬币
 * 这里的i-1和i+1代表和i相邻的、没有被戳爆的！两个气球的序号
 * 如果i-1或i+1超出了数组的边界，那么就当它是一个数字为1的气球
 * 求所能获得硬币的最大数量
 */
// Leetcode题目：https://leetcode.com/problems/burst-balloons/
public class Demo03_Try_Balloons {
    public static int maxCoins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        // 生成 help 数组 放置越界
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }

        return dp(1, arr.length, help);
    }


    // 🙅 此时这个情况我们发现，尝试是错的，所以需要默认气球没爆来尝试
    // 默认 l - 1 和 r + 1 位置的气球爆了
    // 返回最大得分
    private static int dp(int l, int r, int[] arr) {
        if (l == r) {
            // 两侧爆了
            // return arr[l];

            // 两侧没爆
            return arr[l - 1] * arr[l] * arr[l + 1];
        }
        // 先打爆
        // int left = arr[l] * arr[l + 1] + dp(l + 1, r, arr);
        // int right = arr[r] * arr[r - 1] + dp(l, r - 1, arr);
        // 最后打爆
        // 因为 R + 1 和 L -1 也没爆炸
        // 所以把 arr[R+1] arr[L-1] 算上
        int left = arr[l - 1] * arr[l] * arr[r + 1] + dp(l + 1, r, arr);
        int right = arr[r + 1] * arr[r] * arr[l - 1] + dp(l, r - 1, arr);
        int ans = Math.max(left, right);
        for (int i = l + 1; i < r; i++) {
            // 遍历每一个气球爆炸的位置
            // 取得分最大的
            int cur = dp(l, i - 1, arr) + (arr[l - 1] * arr[i] * arr[r + 1]) + dp(i + 1, r, arr);
            ans = Math.max(cur, ans);
        }
        return ans;
    }

    public static int maxCoins1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        // 生成 help 数组 放置越界
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }
        int[][] dp = new int[help.length][help.length];

        for (int i = 1; i <= arr.length; i++) {
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
        }
        for (int l = arr.length; l >= 1; l--) {
            for (int r = l + 1; r <= arr.length; r++) {
                int left = help[l - 1] * help[l] * help[r + 1] + dp[l + 1][r];
                int right = help[r + 1] * help[r] * help[l - 1] + dp[l][r - 1];
                int ans = Math.max(left, right);
                for (int i = l + 1; i < r; i++) {
                    // 遍历每一个气球爆炸的位置
                    // 取得分最大的
                    int cur = dp[l][i - 1] + (help[l - 1] * help[i] * help[r + 1]) + dp[i + 1][r];
                    ans = Math.max(cur, ans);
                }
                dp[l][r] = ans;
            }
        }

        return dp[1][arr.length];
    }
}
