package cn.eatboooo.study.week07;

import java.util.Stack;

/**
 * 单调栈
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/12 17:25
 * <p>
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 */
// 测试链接：https://leetcode.com/problems/largest-rectangle-in-histogram
// 一遍过 dddd
public class Demo03_Monotonous_LargestRectangleInHistogram {
    public static int largestRectangleArea1(int[] height) {
        // 返回
        int max = Integer.MIN_VALUE;
        // 单调栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int i1 = height[i];
            while (!stack.isEmpty() && height[stack.peek()] > i1) {
                Integer pop = stack.pop();
                // 注意边界计算
                max = Math.max(max, (stack.isEmpty() ? i : (i - stack.peek() - 1)) * height[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? height.length : (height.length - stack.peek() - 1)) * height[pop]);
        }
        return max;
    }
}
