package cn.eatboooo.study.week09;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/12 22:03
 * <p>
 * 无序数组arr，值可能正、可能负、可能0
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0，给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的，返回其长度
 */
public class Demo05_Coding_SubArrEqualNMaxLengthNoLimt {
    // 场景：此时累加和为 sum , index
    // find 累加和等于K的最长 ？ -》 find 累加和 sum - k 最早出现的位置 l
    // ans = Math.max(ans,index - l)
    public static int max1(int[] arr, int k) {
        int sum = arr[0];
        int l = 0;
        int ans = 0;
        // 存储最早出现位置
        Map<Integer, Integer> map = new TreeMap<>();
        while (l < arr.length) {
            sum += arr[l];
            if (sum == k) {
                ans = Math.max(ans, l + 1);
            } else if (map.containsKey(sum - k)) {
                // sum - k 而不是 k - sum
                ans = Math.max(ans, l - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, l);
            }
            l++;
        }
        return ans;
    }

}
