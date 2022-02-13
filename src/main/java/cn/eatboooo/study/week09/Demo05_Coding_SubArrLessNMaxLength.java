package cn.eatboooo.study.week09;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/12 22:03
 * <p>
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0，给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的，返回其长度
 * <p>
 * 有 O（n） 的方法
 */
public class Demo05_Coding_SubArrLessNMaxLength {
    // 场景：此时累加和为 sum , index
    // find 累加和小于等于K的最长 ？ -》 find 累加和大于等于 sum - k 最早出现的位置 l
    // ans = Math.max(ans,index - l)
    // ⚠️ 有更好的方法
    public static int max1(int[] arr, int k) {
        int sum = 0;
        int l = 0;
        int ans = 0;
        while (l < arr.length) {
            sum += arr[l];
            if (sum <= k) {
                ans = Math.max(ans, l + 1);
            } else if (findLess(arr, sum - k) != -1) {
                // sum - k 而不是 k - sum
                ans = Math.max(ans, l - findLess(arr, sum - k) + 1);
            }
            l++;
        }
        return ans;
    }

    public static int findLess(int[] arr, int num) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum >= num && (i <= arr.length - 1 && arr[i + 1] >= 0)) {
                return i;
            }
        }
        return -1;
    }

    // 先遍历一遍生成 minSum、minSumEnd
    public static int max2(int[] arr, int k) {
        // minSum[i] 代表从 i 开始最小累加和是多少
        int[] minSum = new int[arr.length];
        // minSumEnd[i] 代表从 i 开始最小累加和的右边界下标
        int[] minSumEnd = new int[arr.length];

        // 遍历一遍生成这两个数组
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] <= 0) {
                // 此时往右扩张，累加和会变小
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                // 此时往右扩张，累加和会变大，所以只有我自己的时候是最小
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }

        // 扩充过程的累加和
        int sum = 0;
        // 扩充后的边界 + 1
        int end = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // i 代表从 i 开头的数组
            // 从 i 开始根据 minSum 开始扩充到最长 <= k 的位置
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            // 扩充完毕，再往右扩会遭
            ans = Math.max(ans, end - i);

            // 这个 if 是难点
            if (end > i) {
                // 看看把 i 移除了之后，end 下次能不能扩充
                // 如果不能扩充 while 不会进入，同时答案也不会被影响
                // 而不是把 sum = 0； 重新计算
                sum -= arr[i];
            } else {
                // 此时 end == i
                // 下次循环 i++，end 需要跟上 i
                end = i + 1;
            }

        }
        return ans;
    }
}
