package cn.eatboooo.study.week10;

/**
 * DC3 应用 最长公用子串
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/20 01:00
 * <p>
 * 最长公共子串问题
 * 连续的相等的部分, 最长
 */
public class Demo02_DC3_LongestCommon {
    // 最长公共子串问题是面试常见题目之一
    // 假设str1长度N，str2长度M
    // 因为最优解的难度所限，一般在面试场上回答出O(N*M)的解法已经是比较优秀了
    // 因为得到O(N*M)的解法，就已经需要用到动态规划了
    // 但其实这个问题的最优解是O(N+M)，为了达到这个复杂度可是不容易
    // 首先需要用到DC3算法得到后缀数组(sa)
    // 进而用sa数组去生成height数组
    // 而且在生成的时候，还有一个不回退的优化，都非常不容易理解
    // 这就是后缀数组在面试算法中的地位 : 德高望重的噩梦

    // copy for study
    public static int lcs2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int min = str1[0];
        int max = str1[0];
        for (int i = 1; i < N; i++) {
            min = Math.min(min, str1[i]);
            max = Math.max(max, str1[i]);
        }
        for (int i = 0; i < M; i++) {
            min = Math.min(min, str2[i]);
            max = Math.max(max, str2[i]);
        }
        int[] all = new int[N + M + 1];
        int index = 0;
        for (int i = 0; i < N; i++) {
            all[index++] = str1[i] - min + 2;
        }
        all[index++] = 1;
        for (int i = 0; i < M; i++) {
            all[index++] = str2[i] - min + 2;
        }
        DC3 dc3 = new DC3(all, max - min + 2);
        int n = all.length;
        int[] sa = dc3.sa;
        int[] height = dc3.height;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int Y = sa[i - 1];
            int X = sa[i];
            if (Math.min(X, Y) < N && Math.max(X, Y) > N) {
                ans = Math.max(ans, height[i]);
            }
        }
        return ans;
    }
}
