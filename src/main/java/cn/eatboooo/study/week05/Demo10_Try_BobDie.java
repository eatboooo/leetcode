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

    private static double dp(int row, int col, int k, int n, int m) {
        // [x][y][k]
        long[][][] dp = new long[n][m][k+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    long right = pick(dp, i + 1, j, rest - 1, n, m, k);
                    long left = pick(dp, i - 1, j, rest - 1, n, m, k);
                    long up = pick(dp, i, j + 1, rest - 1, n, m, k);
                    long down = pick(dp, i, j - 1, rest - 1, n, m, k);
                    dp[i][j][rest] = up + down + left + right;
                }
            }
        }
        return  (double) dp[row][col][k] / Math.pow(4, k);
    }

    private static long pick(long[][][] arr, int x, int y, int z, int n, int m, int k) {
        if (x < 0 || x >= n || y < 0 || y >= m || z < 0 || z >= k) {
            return 0;
        }
        return arr[x][y][z];
    }


    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(dp(6, 6, 10, 50, 50));
    }
}
