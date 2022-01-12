package cn.eatboooo.study.week07;

import java.util.Stack;

/**
 * 单调栈
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/12 17:24
 * <p>
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */
public class Demo03_Monotonous_AllTimesMinToMax {
    public static int max1(int[] arr) {
        // 前缀和
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        int max = Integer.MIN_VALUE;
        // 单调栈，不考虑数组重复的，因为后门的数字会纠错
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int i1 = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] > i1) {
                Integer pop = stack.pop();
                max = Math.max((stack.isEmpty() ? sum[i - 1] :sum[i - 1] - sum[stack.peek()]) * arr[pop], max);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            // ⚠️ 注意 stack 空时的判断
            max = Math.max((stack.isEmpty() ? sum[arr.length - 1] :sum[arr.length - 1] - sum[stack.peek()]) * arr[pop], max);
        }
        return max;
    }
}
