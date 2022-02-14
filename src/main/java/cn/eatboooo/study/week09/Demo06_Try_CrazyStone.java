package cn.eatboooo.study.week09;

/**
 * 四边形
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/14 17:22
 * <p>
 * 摆放着n堆石子。现要将石子有次序地合并成一堆，规定每次只能选相邻的2堆石子合并成新的一堆
 * 并将新的一堆石子数记为该次合并的得分，求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
 */
public class Demo06_Try_CrazyStone {
    // arr【i】 代表 合并 i 石子要付出的代价，合并后的石头变大，代价变大
    // 返回 在只能相邻两两合并的前提下的最小代价
    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return dp1(sum, 0, arr.length - 1);
    }

    // 返回从 l ～ r 合并的最小代价
    private static int dp1(int[] sum, int l, int r) {
        if (l == r) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int mid = l; mid < r; mid++) {
            // 相信 dp1 函数会给我们答案
            // 返回从 l ～ r 合并的最小代价
            // 所以我们遍历所有可能作为中心点的位置
            // 挨个去尝试，找到最小的
            ans = Math.min(ans, dp1(sum, l, mid) + dp1(sum, mid + 1, r));
        }
        return ans + sum(sum, l, r);
    }

    private static int sum(int[] arr, int l, int r) {
        return arr[r + 1] - arr[l];
    }

    // 根据上面改的，一次过
    public static int min2(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        for (int L = arr.length - 2; L >= 0; L--) {
            for (int R = L + 1; R <= arr.length - 1; R++) {
                int ans = Integer.MAX_VALUE;
                for (int mid = L; mid < R; mid++) {
                    ans = Math.min(ans, dp[L][mid] + dp[mid + 1][R]);
                }
                dp[L][R] = ans + sum(sum, L, R);
            }
        }
        return dp[0][arr.length - 1];
    }

    // 根据上面改的，一次过
    public static int min3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int[][] dp = new int[arr.length][arr.length];
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        // 四边形不等式的需求
        // 记录最优情况下的切割位置
        int[][] dpIndex = new int[arr.length][arr.length];

        // 填充首条对角线
        for (int i = 0; i < dpIndex.length; i++) {
            dp[i][i + 1] = sum(sum, i, i + 1);
            dpIndex[i][i + 1] = i;
        }

        for (int L = arr.length - 3; L >= 0; L--) {
            for (int R = L + 2; R <= arr.length - 1; R++) {
                int ans = Integer.MAX_VALUE;
                // 注意不出错
                for (int mid = dpIndex[L][R - 1]; mid <= dpIndex[L + 1][R]; mid++) {
                    // 尝试 >=
                    if (ans > dp[L][mid] + dp[mid + 1][R]) {
                        ans = dp[L][mid] + dp[mid + 1][R];
                        dpIndex[L][R] = mid;
                    }
                }
                dp[L][R] = ans + sum(sum, L, R);
            }
        }
        return dp[0][arr.length - 1];
    }

}
