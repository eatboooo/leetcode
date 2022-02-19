package cn.eatboooo.study.week10;

/**
 * DC3 应用
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/20 00:16
 * <p>
 * 给两个长度分别为M和N的整型数组nums1和nums2，其中每个值都不大于9，再给定一个正数K。
 * 你可以在nums1和nums2中挑选数字，要求一共挑选K个，
 * 并且要从左到右挑。返回所有可能的结果中，代表最大数字的结果
 */
public class Demo02_DC3_ChoseMaxNum {
    // copy for study
    public static int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (k < 0 || k > len1 + len2) {
            return null;
        }
        int[] res = new int[k];
        int[][] dp1 = getdp(nums1); // 生成dp1这个表，以后从nums1中，只要固定拿N个数，
        int[][] dp2 = getdp(nums2);
        // get1 从arr1里拿的数量
        // K - get1 从arr2里拿的数量
        for (int get1 = Math.max(0, k - len2); get1 <= Math.min(k, len1); get1++) {
            // arr1 挑 get1个，怎么得到一个最优结果
            int[] pick1 = maxPick(nums1, dp1, get1);
            int[] pick2 = maxPick(nums2, dp2, k - get1);
            int[] merge = merge(pick1, pick2);
            res = preMoreThanLast(res, 0, merge, 0) ? res : merge;
        }
        return res;
    }
    public static int[] merge(int[] nums1, int[] nums2) {
        int k = nums1.length + nums2.length;
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r) {
            ans[r] = preMoreThanLast(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return ans;
    }
    public static boolean preMoreThanLast(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    public static int[][] getdp(int[] arr) {
        int size = arr.length; // 0~N-1
        int pick = arr.length + 1; // 1 ~ N
        int[][] dp = new int[size][pick];
        // get 不从0开始，因为拿0个无意义
        for (int get = 1; get < pick; get++) { // 1 ~ N
            int maxIndex = size - get;
            // i~N-1
            for (int i = size - get; i >= 0; i--) {
                if (arr[i] >= arr[maxIndex]) {
                    maxIndex = i;
                }
                dp[i][get] = maxIndex;
            }
        }
        return dp;
    }


    public static int[] maxPick(int[] arr, int[][] dp, int pick) {
        int[] res = new int[pick];
        for (int resIndex = 0, dpRow = 0; pick > 0; pick--, resIndex++) {
            res[resIndex] = arr[dp[dpRow][pick]];
            dpRow = dp[dpRow][pick] + 1;
        }
        return res;
    }

}
