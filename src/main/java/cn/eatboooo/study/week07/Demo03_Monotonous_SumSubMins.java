package cn.eatboooo.study.week07;

import java.util.Stack;

/**
 * 单调栈
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/13 15:20
 * <p>
 * 给定一个数组arr，返回所有子数组最小值的累加和
 */
// 测试链接：https://leetcode.com/problems/sum-of-subarray-minimums/
public class Demo03_Monotonous_SumSubMins {
    // 思路：以当前位置作为最小值的子数组有多少个
    public static int subArrayMinSum1(int[] arr) {
        int[] left = getLeft(arr);
        int[] right = getRight(arr);
        int count = 0;
        for (int i = 0; i < right.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            count += start * end * (long) arr[i];
            count %= 1000000007;
        }
        return count;
    }

    private static int[] getLeft(int[] arr) {
        int[] ints = new int[arr.length];
        int[] stack = new int[arr.length];
        int index = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            while (index != -1 && arr[stack[index]] >=arr[i]) {
                int pop = stack[index--];
                ints[pop] = i;
            }
            stack[++index] = i;
        }
        while (index != -1) {
            int pop = stack[index--];
            ints[pop] = -1;
        }
        return ints;
    }

    private static int[] getRight(int[] arr) {
        int[] ints = new int[arr.length];
        int[] stack = new int[arr.length];
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            while (index != -1 && arr[stack[index]] > arr[i]) {
                int pop = stack[index--];
                ints[pop] = i;
            }
            stack[++index] = i;
        }
        while (index != -1) {
            int pop = stack[index--];
            ints[pop] = arr.length;
        }
        return ints;
    }
}
