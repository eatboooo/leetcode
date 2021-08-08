package cn.eatboooo.study.week06;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/9 00:22
 * <p>
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class Demo12_Try_SplitSumClosedSizeHalf {
    public static int minSum(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        if (arr.length % 2 == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        }

        // max 不是 min,要选择最接近平均数的值
        return Math.min(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
    }

    private static int process(int[] arr, int index, int num, int rest) {
        if (arr.length == index) {
            // 已经凑齐了
            return num == 0 ? 0 : -1;
        }
        // use
        int p1 = -1;
        if (rest >= arr[index]) {
            p1 = process(arr, index + 1, num - 1, rest - arr[index]);
        }
        if (p1 != -1) {
            p1 += arr[index];
        }
        // no use
        int p2 = process(arr, index + 1, num, rest);
        return Math.max(p1, p2);
    }

    // todo dp



    // copy for test

    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
//            int ans1 = right(arr);
            int ans2 = minSum(arr);
            int ans3 = dp2(arr);
            if (ans3 != ans2) {
//            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
//                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
