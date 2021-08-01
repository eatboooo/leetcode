package cn.eatboooo.study.week05;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/1 20:57
 * <p>
 * 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
 * 最长回文子序列
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * <p>
 * 相当于把 str 逆序 生成 str2
 * 找 str 和 str2 的最长公共子序列
 * <p>
 * 现在我们用另一种方法
 * 范围上的尝试模型
 * 画图画图画图
 */
public class Demo09_Try_PalindromeSubsequence {
    public static int findLong(String string) {
        if (string.length() == 0) {
            return 0;
        }
        char[] str = string.toCharArray();
        return process1(str, 0, str.length - 1);
    }

    private static int process1(char[] str, int l, int r) {
        // 由中心扩大
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return str[l] == str[r] ? 2 : 1;
        }
        int p1 = process1(str, l + 1, r);
        int p2 = process1(str, l + 1, r - 1);
        int p3 = process1(str, l, r - 1);
        int p4 = str[l] == str[r] ? (2 + process1(str, l + 1, r - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // dp
    public int longestPalindromeSubseq(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = s.length();
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
            if (i + 1 < N) {
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
            }
        }
        for (int l = N - 1; l >= 0; l--) {
            for (int r = l + 2; r < N; r++) {
                int p1 = dp[l + 1][r];
                int p2 = dp[l + 1][r - 1];
                int p3 = dp[l][r - 1];
                int p4 = str[l] == str[r] ? (2 + dp[l + 1][r - 1]) : 0;
                dp[l][r] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }
}
