package cn.eatboooo.study.week05;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/1 20:12
 * <p>
 * 链接：https://leetcode.com/problems/longest-common-subsequence/
 * 最长公共子序列
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * <p>
 * 两个字符串这周
 * 经验：从右往左的尝试模型
 */
public class Demo09_Try_LongestCommonSubsequence {
    public static int findLong(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 尝试
        // 经验：从右往左的尝试模型
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }


    private static int process1(char[] str1, char[] str2, int str1R, int str2R) {
        // 给最左赋值
        if (str1R == 0 && str2R == 0) {
            return str1[str1R] == str2[str2R] ? 1 : 0;
        } else if (str1R == 0) { // 分类讨论
            return str1[str1R] == str2[str2R] ? 1 : process1(str1, str2, str1R, str2R - 1);
        } else if (str2R == 0) { // 分类讨论
            return str1[str1R] == str2[str2R] ? 1 : process1(str1, str2, str1R - 1, str2R);
        } else {
            int p1 = process1(str1, str2, str1R - 1, str2R);
            int p2 = process1(str1, str2, str1R, str2R - 1);
            // 如果 str1[str1R] ！= str2[str2R] 那么也没什么好说的了， p3 肯定不如 p1 p2 大了，直接给 0
            int p3 = str1[str1R] == str2[str2R] ? (1 + process1(str1, str2, str1R - 1, str2R - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    // 根据暴力修改的动态规划
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int N1 = text1.length();
        int N2 = text2.length();
        int[][] dp = new int[N1][N2];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 0; i < N1; i++) {
            for (int k = 0; k < N2; k++) {
                if (i == 0 && k != 0) {
                    dp[i][k] = str1[i] == str2[k] ? 1 : dp[i][k - 1];
                } else if (k == 0 && i != 0) {
                    dp[i][k] = str1[i] == str2[k] ? 1 : dp[i - 1][k];
                } else if (i != 0 & k != 0) {
                    int p1 = dp[i][k - 1];
                    int p2 = dp[i - 1][k];
                    int p3 = str1[i] == str2[k] ? (1 + dp[i - 1][k - 1]) : 0;
                    dp[i][k] = Math.max(p1, Math.max(p2, p3));
                }
            }
        }
        return dp[N1 - 1][N2 - 1];
    }

}
