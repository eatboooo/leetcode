package cn.eatboooo.study.week07;

import cn.eatboooo.leetcode.demo.Base01;

import java.util.Stack;

/**
 * 单调栈
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/12 17:31
 * <p>
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的子矩形数量
 */
public class Demo03_Monotonous_CountSubmatricesWithAllOnes {
    // 测试链接：https://leetcode.com/problems/count-submatrices-with-all-ones
    // 思路：数组压缩+单调栈，以弹出为最低高度的矩形数量
    public static int numSubmat(int[][] mat) {
        Base01.printDoubleArr(mat);
        // 数组压缩
        int[][] arr = new int[mat.length][mat[0].length];
        for (int i = 0; i < arr[0].length; i++) {
            arr[0][i] = mat[0][i];
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = mat[i][j] == 0 ? 0 : arr[i - 1][j] + mat[i][j];
            }
        }
        // 数组压缩完成

        Base01.printDoubleArr(arr);
        // 单调栈
        Stack<Integer> stack = new Stack<>();
        // 矩形数量
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int[] ints = arr[i];
            for (int j = 0; j < ints.length; j++) {
                int anInt = ints[j];
                while (!stack.isEmpty() && ints[stack.peek()] > anInt) {
                    Integer pop = stack.pop();
                    // 注意写法
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    int size = j - left - 1;
                    int down = Math.max(left == -1 ? 0 : ints[left], ints[j]);
                    // 以弹出为最低高度的矩形数量
                    count += Math.abs(ints[pop] - down) * ((size) * (size + 1) / 2);
                }
                stack.push(j);
            }
            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int size = ints.length - left - 1;
                int down = left == -1 ? 0 : ints[stack.peek()];
                // 以弹出为最左侧边的矩形数量
                count += Math.abs(ints[pop] - down) * ((size) * (size + 1) / 2);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // ans = 13
        // int[][] arr = {{1, 1, 1, 1, 1, 1}};
        int[][] arr = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        int i = numSubmat(arr);
        System.out.println("i = " + i);
    }

}
