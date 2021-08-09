package cn.eatboooo.study.week06;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/7 23:51
 * <p>
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class Demo11_Try_MinCoinsNoLimit {
    public static int minConins(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (arr.length == 0) {
            return Integer.MAX_VALUE;
        }

        int process = process2(arr, 0, aim);
        return process == Integer.MAX_VALUE ? process : process ;
    }


    // 多一次遍历的尝试
    private static int process(int[] arr, int index, int rest) {
        if (rest == 0) {
            return 0;
        }
        if (rest < 0 || index == arr.length) {
            return Integer.MAX_VALUE;
        }
        int ways = Integer.MAX_VALUE;
        for (int i = 0; rest - arr[index] * i >= 0; i++) {
            int next = process(arr, index + 1, rest - arr[index] * i);

            if (next != Integer.MAX_VALUE) {
                // 说明之后有凑齐过
                ways = Math.min(next + i, ways);
            }
        }
        return ways;
    }

    // my 不需要空间压缩的尝试
    private static int process2(int[] arr, int index, int rest) {
        if (rest == 0) {
            return 0;
        }
        if (rest < 0 || index == arr.length) {
            return Integer.MAX_VALUE;
        }
        // use
        int p1 = Integer.MAX_VALUE;
        // 检查有没有必要用
        if (rest - arr[index] >= 0) {
            // 有必要
            int next = process(arr, index, rest - arr[index]);
            // 看看返回的结果能不能成，不是 maxValue 就可以
            p1 = next == Integer.MAX_VALUE ? next : 1 + next;
        }

        // no use
        int p2 = process(arr, index + 1, rest);

        return Math.min(p1, p2);
    }

    // 根据上面改出来的动态规划
    public static int dp2(int[] arr, int aim){
        if (aim == 0) {
            return 0;
        }
        if (arr.length == 0) {
            return Integer.MAX_VALUE;
        }
        // [index][rest]
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 1; i < aim + 1; i++) {
            dp[arr.length][i] = Integer.MAX_VALUE;
        }
        for (int i = arr.length-1; i >= 0; i--) {
            for (int rest = 1; rest < aim + 1; rest++) {
                int p1 = Integer.MAX_VALUE;
                if (rest - arr[i] >= 0) {
                    // 有必要
                    int next = dp[i][rest - arr[i]];
                    // 看看返回的结果能不能成，不是 maxValue 就可以
                    p1 = next == Integer.MAX_VALUE ? next : 1 + next;
                }
                int p2 = dp[i + 1][rest];
                dp[i][rest] = Math.min(p1, p2);
            }
        }
        int process = dp[0][aim];
        return process == Integer.MAX_VALUE ? process : process ;
    }


    // copy for test

    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0
                        && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minConins(arr, aim);
//            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans3) {
//            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans3);
//                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}
