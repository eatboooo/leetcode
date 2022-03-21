package cn.eatboooo.crazy.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/17 21:14
 * <p>
 * 给定一个有序数组arr，代表坐落在X轴上的点，
 * 给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 * <p>
 * 思路：小贪心：绳子末尾处如果在一个点，能盖住多少个点
 * 两个指针
 */
public class Code01 {
    public static int maxPoint1(int[] arr, int L) {
        int max = 0;
        int l = 0;
        int r = 0;
        while (l < arr.length) {
            while (r < arr.length && arr[r] - arr[l] <= L) {
                r++;
            }
            max = Math.max(max, r - l++);
        }
        return max;
    }
}
