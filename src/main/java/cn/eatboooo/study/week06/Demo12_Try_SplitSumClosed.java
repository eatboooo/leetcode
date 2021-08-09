package cn.eatboooo.study.week06;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/9 00:06
 * <p>
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class Demo12_Try_SplitSumClosed {
    public static int minSum(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return process(arr, sum / 2, 0);
    }

    private static int process(int[] arr, int rest, int index) {
        if (rest == 0 || index == arr.length) {
            return 0;
        }
        int p1 = -1;
        // use
        if (rest - arr[index] >= 0) {
            // 记住要相加
            p1 = arr[index] + process(arr, rest - arr[index], index + 1);
        }
        // no use
        int p2 = process(arr, rest, index + 1);
        return Math.max(p1, p2);
    }

    // my dp
    public static int dp(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        // [rest][index]
        int[][] dp = new int[(sum / 2) + 1][arr.length + 1];
        for (int rest = 0; rest < (sum / 2) + 1; rest++) {
            for (int i = arr.length-1; i >= 0; i--) {
                int p1 = -1;
                // use
                if (rest - arr[i] >= 0) {
                    // 记住要相加
                    p1 = arr[i] + dp[rest - arr[i]][i + 1];
                }
                // no use
                int p2 = dp[rest][i + 1];
                dp[rest][i] = Math.max(p1, p2);
            }
        }
        return dp[sum / 2][0];
    }


    // copy for test
    public static int dp1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                // 可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = minSum(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
