package cn.eatboooo.study.week07;

import cn.eatboooo.leetcode.demo.Base01;

import java.util.Stack;

/**
 * 单调栈
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/12 17:29
 * <p>
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
 */
// 测试链接：https://leetcode.com/problems/maximal-rectangle/
public class Demo03_Monotonous_Arr2MaximalRectangle {
    public static int maximalRectangle(char[][] map) {
        // 数组压缩
        int height = map.length;
        int weight = map[0].length;
        int[][] arr = new int[height][weight];
        for (int i = 0; i < map[0].length; i++) {
            arr[0][i] = map[0][i] == '1' ? 1 : 0;
        }
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // 上面是零就重新开始计算
                arr[i][j] = map[i][j] == '1' ? arr[i - 1][j] + 1 : 0;
            }
        }
        Base01.printDoubleArr(arr);
        // 数组压缩完成

        // 最大值
        int max = Integer.MIN_VALUE;
        // 单调栈，寻找压缩数组后的最大值
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int[] ints = arr[i];
            for (int j = 0; j < ints.length; j++) {
                while (!stack.isEmpty() && ints[stack.peek()] > ints[j]) {
                    Integer pop = stack.pop();
                    // 注意边界，需要实际画图
                    int tempMax = stack.isEmpty() ? 0 : stack.peek() + 1;
                    max = Math.max(max, (j - tempMax) * ints[pop]);
                }
                stack.push(j);
            }
            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                int tempMax = stack.isEmpty() ? 0 : stack.peek() + 1;
                max = Math.max(max, (ints.length - tempMax) * ints[pop]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // ans = 6
        char[][] c = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        // char[][] c = {{'1'}};
        // 9
        // char[][] c = {{'0', '0', '1', '0'}, {'0', '0', '1', '0'}, {'0', '0', '1', '0'}, {'0', '0', '1', '1'}, {'0', '1', '1', '1'}, {'0', '1', '1', '1'}, {'1', '1', '1', '1'}};
        int i = maximalRectangle(c);
        System.out.println("i = " + i);
    }
}
