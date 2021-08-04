/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week05
 * @className cn.eatboooo.study.week05.Demo10_Try_BobDie
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week05;

/**
 * Demo10_Try_BobDie
 * @description
 * @author weiZhiLin
 * @date 2021/8/4 20:40
 * @version 1.0
 *
 * 醉汉鲍勃
 * 走出棋盘就 die
 * 出生点 （row,col）
 * 棋盘大小 （0,0） ~ （N,M)
 * 走 k 步
 * 活下来的概率
 */
public class Demo10_Try_BobDie {
    public static double livePosibility1(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    private static long process(int row, int col, int k, int n, int m) {
        if (row < 0 || row == n || col < 0 || col == m) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        long up = process(row, col + 1, k - 1, n, m);
        long dowm = process(row, col - 1, k - 1, n, m);
        long left = process(row - 1, col, k - 1, n, m);
        long right = process(row + 1, col, k - 1, n, m);

        return up + dowm + left + right;
    }

    private static long dp(int row, int col, int k, int n, int m) {
        // [x][y][k]
        int[][][] dp = new int[n][m][k];
        dp[]

    }



    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(dp(6, 6, 10, 50, 50));
    }
}
