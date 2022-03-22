package cn.eatboooo.crazy.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/18 20:33
 *
 * 给定一个数组arr，你可以在每个数字之前决定+或者-但是必须所有数字都参与，再给定一个数target
 * 请问最后算出target的方法数
 *
 * 思路：暴力 -》缓存
 * 最优：
 * 1、target > sum ->gg
 * 2、target 与 sum 奇偶性
 * 3、两个集合， p、n，P 代表正、N 代表负数
 * 有 P - N = target
 * 所以有 2P = target + 数组全部累加和
 * 所以 P = （target + 数组全部累加和）/2
 * 所以就只是求 数组搞成 P 的背包问题
 */
public class Code07 {
    // leetcode 494题

    // 你可以认为arr中都是非负数
    // 因为即便是arr中有负数，比如[3,-4,2]
    // 因为你能在每个数前面用+或者-号
    public static int findTargetSumWays(int[] arr, int s) {
        int sum = 0;
        // 变成正数
        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];
        }
        if (sum < s) {
            return 0;
        }
        if (sum % 2 != s % 2) {
            return 0;
        }
        return process1(0, (s + sum) / 2, arr);
    }

    private static int process1(int index, int rest, int[] arr) {
        if (rest == 0 && index == arr.length) {
            return 1;
        }
        if (index == arr.length) {
            return 0;
        }
        // with
        int p1 = process1(index + 1, rest - arr[index], arr);
        // without
        // ⚠️！我们现在是凑数字，而不是搞加减
        int p2 = process1(index + 1, rest, arr);
        return p1 + p2;
    }
}
