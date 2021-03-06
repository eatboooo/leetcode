package cn.eatboooo.study.week06;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/8 00:36
 * <p>
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class Demo11_Try_SplitNumber {
    public static int split(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    private static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }

    // 不需要循环的尝试
    private static int process2(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        // 使用 pre
        int ways = process(pre, rest - pre);
        // 不使用 pre
        if (pre < rest) {
            ways += process(pre + 1, rest);
        }
        return ways;
    }

    // process2 的动态规划版本
    private static int myDp2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prel = n + 1;
        int restl = n + 1;
        int[][] dp = new int[prel][restl];
        for (int i = 0; i < prel; i++) {
            dp[i][0] = 1;
        }
        for (int pre = prel - 1; pre >= 0; pre--) {
            for (int rest = 1; rest < restl; rest++) {
                // 使用 pre
                int ways = rest - pre < 0 ? 0 : dp[pre][rest - pre];
                // 不使用 pre
                if (pre < rest) {
                    ways += dp[pre + 1][rest];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    // 没有斜率优化的 dp
    private static int dp(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // [pre][rest]
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        for (int rest = 1; rest < n + 1; rest++) {
            for (int pre = 0; pre < n + 1; pre++) {
                int ways = 0;
                for (int i = pre; i <= rest; i++) {
                    ways += dp[i][rest - i];
                }
                dp[pre][rest] = ways;
            }
        }
//        for (int x = 0; x < dp.length; x++) {
//            int[] ints = dp[x];
//            for (int y = 0; y < ints.length; y++) {
//                int anInt = ints[y];
//                System.out.println(x + "," + y + ":= " + anInt);
//            }
//        }
        return dp[1][n];
    }

    // 有斜率优化的 dp
    // 终于搞好了 泪目
    private static int smallDp(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //  dp[pre][rest]
        int dp[][] = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        dp[n][n] = 1;
        for (int rest = 1; rest < n + 1; rest++) {
            for (int pre = rest; pre >= 0; pre--) {
                dp[pre][rest] = pre + 1 > n ? 1 : dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    // copy for test
    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 80;
        System.out.println(split(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
        System.out.println(dp(test));
        System.out.println(myDp2(test));
        System.out.println(smallDp(test));
    }
}
