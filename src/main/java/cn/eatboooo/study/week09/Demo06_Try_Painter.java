package cn.eatboooo.study.week09;

/**
 * 四边形
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/14 20:25
 * <p>
 * 给定一个整型数组 arr，数组中的每个值都为正数，表示完成一幅画作需要的时间，再给定一个整数num
 * 表示画匠的数量，每个画匠只能画连在一起的画作
 * 所有的画家并行工作，返回完成所有的画作需要的最少时间
 * arr=[3,1,4]，num=2。
 * 最好的分配方式为第一个画匠画3和1，所需时间为4
 * 第二个画匠画4，所需时间为4
 * 所以返回4
 * arr=[1,1,1,4,3]，num=3
 * 最好的分配方式为第一个画匠画前三个1，所需时间为3
 * 第二个画匠画4，所需时间为4
 * 第三个画匠画3，所需时间为3
 * 返回4
 */
public class Demo06_Try_Painter {
    public static int splitArray1(int[] arr, int num) {
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < sum.length - 1; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return dp1(sum, arr.length - 1, num);
    }

    // 0~index 分成 num 份，返回所有份中的最大值
    private static int dp1(int[] arr, int index, int num) {
        if (index == 0) {
            return arr[1];
        }
        if (num == 1) {
            return mysum(arr, 0, index);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = index; i >= 0; i--) {
            // i 代表我要负责 i ~ index 位置的画
            // 全部尝试，找出最小的
            int l = dp1(arr, i, num - 1);
            int r = mysum(arr, i + 1, index);
            int cur = Math.max(l, r);
            if (cur < ans) {
                ans = cur;
            }
        }
        return ans;
    }

    public static int mysum(int[] arr, int l, int r) {
        return arr[r + 1] - arr[l];
    }


    public static int splitArray2(int[] arr, int num) {
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < sum.length - 1; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        int indexL = arr.length;
        int numL = num + 1;
        int[][] dp = new int[indexL][numL];
        for (int i = 0; i < numL; i++) {
            dp[0][i] = sum[1];
        }
        for (int i = 0; i < indexL; i++) {
            dp[i][1] = mysum(sum, 0, i);
        }

        // for (int index = 1; index < indexL; index++) {
        for (int n = 2; n < numL; n++) {
            for (int index = indexL - 1; index >= 1; index--) {
                int ans = Integer.MAX_VALUE;
                for (int i = index; i >= 0; i--) {
                    // i 代表我要负责 i ~ index 位置的画
                    // 全部尝试，找出最小的
                    int l = dp[i][n - 1];
                    int r = mysum(sum, i + 1, index);
                    int cur = Math.max(l, r);
                    if (cur < ans) {
                        ans = cur;
                    }
                }
                dp[index][n] = ans;
            }
        }
        return dp[arr.length - 1][num];
    }


    // 四边形优化
    public static int splitArray3(int[] arr, int num) {
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < sum.length - 1; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        int indexL = arr.length;
        int numL = num + 1;
        int[][] dp = new int[indexL][numL];
        int[][] dpIndex = new int[indexL][numL];
        for (int i = 0; i < numL; i++) {
            dp[0][i] = sum[1];
        }
        for (int i = 0; i < indexL; i++) {
            dp[i][1] = mysum(sum, 0, i);
        }

        // for (int index = 1; index < indexL; index++) {
        for (int n = 2; n < numL; n++) {
            for (int index = indexL - 1; index >= 1; index--) {
                int ans = Integer.MAX_VALUE;
                int chose = 0;
                for (int i = index == indexL - 1 ? index : dpIndex[index + 1][n]; i >= dpIndex[index][n - 1]; i--) {
                    // i 代表我要负责 i ~ index 位置的画
                    // 全部尝试，找出最小的
                    int l = dp[i][n - 1];
                    int r = mysum(sum, i + 1, index);
                    int cur = Math.max(l, r);
                    if (cur <= ans) {
                        ans = cur;
                        chose = i;
                    }
                }
                dpIndex[index][n] = chose;
                dp[index][n] = ans;
            }
        }
        return dp[arr.length - 1][num];
    }


    // test
    public static void main(String[] args) {
        int time = 1000;
        int maxL = 10;
        int maxV = 10;
        for (int i = 0; i < time; i++) {
            int length = (int) (Math.random() * maxL) + 1;
            int param = (int) (Math.random() * maxL) + 1;
            int[] arr = createArr(length, maxV);
            int a1 = splitArray1(arr, param);
            int a2 = splitArray3(arr, param);
            // int a2 = splitArray2(arr, param);
            System.out.println(a1 == a2);
        }
    }

    private static int[] createArr(int length, int value) {
        int[] ans = new int[length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value);
        }
        return ans;
    }

    private static void printArr(int[] arr) {
        System.out.print("arr: ");
        for (int i1 : arr) {
            System.out.print(i1 + " ");
        }
        System.out.print("\n");
    }

}
