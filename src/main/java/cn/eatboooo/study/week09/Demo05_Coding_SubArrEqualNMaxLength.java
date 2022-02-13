package cn.eatboooo.study.week09;

/**
 * coding
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/12 21:58
 * <p>
 * 正整数组成的无序数组arr
 * 给定一个正整数组成的无序数组arr，给定一个正整数值K，
 * 找到arr的所有子数组里，哪个子数组的累加和等于K
 * 并且是长度最大的，返回其长度
 */
public class Demo05_Coding_SubArrEqualNMaxLength {
    // 累加和肯定是越来越大，
    // 所以可以不存在回退的
    public static int max1(int[] arr, int k) {
        int sum = arr[0];
        int l = 0;
        int r = 0;
        int ans = 0;
        // Condition 'r < arr.length' is always 'true'
        while (l < arr.length) {
            // 注意 if 顺序
            if (sum == k) {
                ans = Math.max(ans, r - l + 1);
                sum += arr[++r];
                continue;
            }
            if (sum > k) {
                sum -= arr[l++];
                continue;
            }
            // 此时 sum < k
            if (r < arr.length - 1) {
                sum += arr[++r];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {0, 0, 0, 0};
        int k = 0;
        max1(a, k);
    }
}
