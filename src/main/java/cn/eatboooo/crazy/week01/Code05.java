package cn.eatboooo.crazy.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/18 20:54
 * <p>
 * 给定一个二维数组matrix，你可以从任何位置出发，
 * 走向上、下、左、右四个方向，返回能走出来的最长的递增链长度
 * <p>
 * 思路：醉汉尝试
 * ⚠️：递增链！！！不需要考虑走没走过！因为是递增的
 */
public class Code05 {
    public static int comeOn(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            int[] ints = matrix[i];
            for (int j = 0; j < ints.length; j++) {
                max = Math.max(max, dp1(i, j, matrix));
            }
        }
        return max;
    }

    private static int dp1(int x, int y, int[][] matrix) {
        int up = x + 1 < matrix.length && matrix[x][y] < matrix[x + 1][y] ? dp1(x + 1, y, matrix) : 0;
        int down = x - 1 >= 0 && matrix[x][y] < matrix[x - 1][y] ? dp1(x - 1, y, matrix) : 0;
        int left = y + 1 < matrix[0].length && matrix[x][y] < matrix[x][y + 1] ? dp1(x, y + 1, matrix) : 0;
        int right = y - 1 >= 0 && matrix[x][y] < matrix[x][y - 1] ? dp1(x, y - 1, matrix) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }


    public static int comeOn2(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            int[] ints = matrix[i];
            for (int j = 0; j < ints.length; j++) {
                max = Math.max(max, dp2(i, j, matrix, dp));
            }
        }
        return max;
    }

    private static int dp2(int x, int y, int[][] matrix, int[][] dp) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        int up = x + 1 < matrix.length && matrix[x][y] < matrix[x + 1][y] ? dp1(x + 1, y, matrix) : 0;
        int down = x - 1 >= 0 && matrix[x][y] < matrix[x - 1][y] ? dp1(x - 1, y, matrix) : 0;
        int left = y + 1 < matrix[0].length && matrix[x][y] < matrix[x][y + 1] ? dp1(x, y + 1, matrix) : 0;
        int right = y - 1 >= 0 && matrix[x][y] < matrix[x][y - 1] ? dp1(x, y - 1, matrix) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[x][y] = ans;
        return ans;
    }
}
