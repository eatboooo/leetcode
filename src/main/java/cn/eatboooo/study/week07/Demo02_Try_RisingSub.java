package cn.eatboooo.study.week07;

/**
 * 最长上升子序列的长度
 * todo 平行四边形优化
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/6 11:44
 * <p>
 * 最长上升子序列（LIS）问题：给定长度为n的序列a，从a中抽取出一个子序列，这个子序列需要单调递增。
 * 问最长的上升子序列（LIS）的长度。
 * e.g. 1,5,3,4,6,9,7,8的LIS为1,3,4,6,7,8，长度为6。
 */
public class Demo02_Try_RisingSub {
    // 我的暴力尝试
    public static int risingSub(int[] arr) {
        if (arr.length < 1) {
            return 0;
        }
        return rising(arr, 0, -1);
    }

    private static int rising(int[] arr, int index, int last) {
        if (index == arr.length) {
            return 0;
        }
        // with
        int p1 = 0;
        if (last == -1 || (index < arr.length && arr[index] > arr[last])) {
            p1 = 1 + rising(arr, index + 1, index);
        }
        // without
        int p2 = rising(arr, index + 1, last);
        return Math.max(p1, p2);
    }

    private static int myDp(int[] arr) {
        int[][] dp = new int[arr.length + 1][arr.length + 1];

        return dp[1][0];
    }

    public static void main(String[] args) {
        int[] ints = {10, 9, 2, 5, 3, 7, 101, 18};
        // int[] ints = {3, 3, 33, 1};
        // int[] ints = {1, 5, 3, 4, 6, 9, 7, 8, 10, 11, 10, 1};
        System.out.println(risingSub(ints));
        System.out.println(myDp(ints));
    }
}
