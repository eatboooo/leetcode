package cn.eatboooo.study.week09;

/**
 * 四边形
 * 邮局问题
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/15 15:27
 * <p>
 * 一条直线上有居民点，邮局只能建在居民点上
 * 给定一个有序正数数组arr，每个值表示 居民点的一维坐标，再给定一个正数 num，表示邮局数量
 * 选择num个居民点建立num个邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离
 * arr=[1,2,3,4,5,1000]，num=2
 * 第一个邮局建立在3位置，第二个邮局建立在1000位置
 * 那么1位置到邮局的距离为2，2位置到邮局距离为1，3位置到邮局的距离为0，4位置到邮局的距离为1，5位置到邮局的距离为2
 * 1000位置到邮局的距离为0
 * 这种方案下的总距离为6，其他任何方案的总距离都不会比该方案的总距离更短，所以返回6
 */
public class Demo06_Try_PostOffice {
    // ⚠️ 最关键的点思考在于：当只有一个邮局时，邮局建在中心坐标一定是最优的
    // 关键数据是生成一个 w[][] 数组，w[1][5] 代表 1 ～ 5 位置，邮局创建到中心点，返回的总距离是多少
    public static int min1(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int[][] w = new int[arr.length + 1][arr.length + 1];
        for (int L = 0; L < w.length - 1; L++) {
            for (int R = L + 1; R < w.length - 1; R++) {
                // 注意这里中心下标是 （L + R）/2
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) / 2];

            }
        }
        return dp1(w, num, arr.length - 1);
    }

    // 0 ~ index ，创建 n 个邮局
    // 返回最短的总距离
    private static int dp1(int[][] w, int num, int index) {
        if (num == 1) {
            return w[0][index];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= index; i++) {
            ans = Math.min(ans, dp1(w, num - 1, i) + w[i + 1][index]);
        }
        return ans;
    }

    // 改dp
    // 一遍过
    public static int min2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int[][] w = new int[arr.length + 1][arr.length + 1];
        for (int L = 0; L < w.length - 1; L++) {
            for (int R = L + 1; R < w.length - 1; R++) {
                // 注意这里中心下标是 （L + R）/2
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) / 2];

            }
        }
        int[][] dp = new int[num + 1][arr.length];
        for (int i = 1; i < arr.length; i++) {
            dp[1][i] = w[0][i];
        }
        for (int n = 2; n < num + 1; n++) {
            for (int index = 0; index < arr.length; index++) {
                int ans = Integer.MAX_VALUE;
                for (int i = 0; i <= index; i++) {
                    ans = Math.min(ans, dp[n-1][i] + w[i + 1][index]);
                }
                dp[n][index] = ans;
            }
        }
        return dp[num][arr.length - 1];
    }

    // 改四边形 todo
    public static int min3(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int[][] w = new int[arr.length + 1][arr.length + 1];
        for (int L = 0; L < w.length - 1; L++) {
            for (int R = L + 1; R < w.length - 1; R++) {
                // 注意这里中心下标是 （L + R）/2
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) / 2];

            }
        }
        int[][] dp = new int[num + 1][arr.length];
        int[][] dpIndex = new int[num + 1][arr.length];
        for (int i = 1; i < arr.length; i++) {
            dp[1][i] = w[0][i];
        }
        for (int n = 2; n < num + 1; n++) {
            for (int index = arr.length-1; index >=0 ; index--) {
                int ans = Integer.MAX_VALUE;
                int chose = 0;
                for (int i = dpIndex[n - 1][index]; i <= dpIndex[n - 1][index + 1]; i++) {
                    if (ans < dp[n - 1][i] + w[i + 1][index]) {
                        ans = dp[n - 1][i] + w[i + 1][index];
                        chose = i;
                    }
                }
                dpIndex[n][index] = chose;
                dp[n][index] = ans;
            }
        }
        return dp[num][arr.length - 1];
    }
}

