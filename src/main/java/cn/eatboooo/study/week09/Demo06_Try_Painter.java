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
    // bad
    public static int splitArray1(int[] arr, int num) {
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < sum.length - 1; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return dp1(sum, arr.length - 1, num);
    }

    // 0~index 分成 num 份，返回所有份中的最大值
    private static int dp1(int[] arr, int index, int num) {
        if (num == 1) {
            return mysum(arr, 0, index);
        }
        if (index == 0) {
            return arr[0];
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

    //

}
