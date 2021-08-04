/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week05
 * @className cn.eatboooo.study.week05.Demo10_Try_MinPathSum
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week05;

import cn.eatboooo.leetcode.demo.Base01;

/**
 * Demo10_Try_MinPathSum
 * @description
 * @author weiZhiLin
 * @date 2021/8/3 18:22
 * @version 1.0
 *
 * 最短路径
 * 从（0,0）走到（N-1，N-1）
 * 从 42 走到 60
 *     ----------> y
 *     ↓  42 87 98
 *     ↓  2 53 27
 *     ↓  94 31 60
 *     x
 */
public class Demo10_Try_MinPathSum {
    public static int minPathSum(int[][] arr) {
        int x = arr.length;
        int y = arr[0].length;
        int[][] dp = new int[x][y];
        dp[x - 1][y - 1] = arr[x - 1][y - 1];
        for (int i = x - 2; i >= 0; i--) {
            dp[i][y - 1] = arr[i][y - 1] + dp[i + 1][y - 1];
        }
        for (int i = y - 2; i >= 0; i--) {
            dp[x - 1][i] = arr[x - 1][i] + dp[x - 1][i + 1];
        }
        for (int curX = x - 2; curX >= 0; curX--) {
            for (int curY = y - 2; curY >= 0; curY--) {
                dp[curX][curY] = Math.min(dp[curX + 1][curY], dp[curX][curY + 1]) + arr[curX][curY];
            }
        }
        return dp[0][0];
    }



    // copy for test
    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        return dp[col - 1];
    }
    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }
    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int rowSize = 3;
        int colSize = 3;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        Base01.printDoubleArr(m);
        System.out.println(minPathSum(m));
        System.out.println(minPathSum2(m));

    }
}
