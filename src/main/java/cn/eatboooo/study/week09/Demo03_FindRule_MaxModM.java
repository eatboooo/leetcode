package cn.eatboooo.study.week09;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:23
 * <p>
 * 给定一个非负数组arr，和一个正数m，返回arr的所有子序列中累加和%m之后的最大值
 */
public class Demo03_FindRule_MaxModM {
    // dp 中两个变量：数组的 index，累加和 sum，dp【index】【sum】 代表能否组成 sum
    public static int max1(int[] arr, int m) {
        int arrSum = 0;
        for (int i : arr) {
            arrSum += i;
        }
        boolean[][] dp = new boolean[arr.length][arrSum + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;

        }
        for (int index = 1; index < dp.length; index++) {
            for (int sum = 1; sum < dp[0].length; sum++) {
                // ⚠️ 这里是从右往左尝试，所以一开始 index 需要减 1
                // use
                boolean p1 = false;
                if (sum >= arr[index]) {
                    p1 = dp[index - 1][sum - arr[index]];
                }
                // unused
                boolean p2 = dp[index - 1][sum];
                dp[index][sum] = p1 | p2;
            }
        }
        int ans = 0;
        for (int j = 0; j <= arrSum; j++) {
            if (dp[arr.length - 1][j]) {
                ans = Math.max(ans, j % m);
            }
        }
        return ans;
    }

    // dp 中两个变量：数组的 index，余数 j，
    // dp【index】【j】 在 0～index 上，累加和 mod m 之后能否等于 j
    public static int max2(int[] arr, int m) {
        boolean[][] dp = new boolean[arr.length][m];

        // dddd
        for (int j = 0; j < arr.length; j++) {
            dp[j][0] = true;
        }
        // 遍历 ？ no，只有一个是 true
        dp[0][arr[0] % m] = true;

        for (int index = 1; index < dp.length; index++) {
            for (int j = 1; j < dp[0].length; j++) {
                // use
                boolean b1 = false;
                int i = j - arr[index] % m;
                if (i >= 0) {
                    b1 = dp[index - 1][i];
                }else {
                    // 2 种情况哦，注意！！！
                    b1 = dp[index - 1][m + i];
                }
                // unused
                boolean b2 = false;
                b2 = dp[index - 1][j];
                dp[index][j] = b1 | b2;
            }
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            if (dp[arr.length - 1][i]) {
                ans = i;
            }
        }
        return ans;
    }
}
