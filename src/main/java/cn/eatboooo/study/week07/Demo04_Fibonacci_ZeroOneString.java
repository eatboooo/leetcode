package cn.eatboooo.study.week07;

/**
 * 斐波那契数列优化
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/15 22:04
 * <p>
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 * 如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标
 * 返回有多少达标的字符串
 */
public class Demo04_Fibonacci_ZeroOneString {
    // 长度为 length 的字符串
    // 只由 0 、1 组成
    // 任何 0 的左边都是 1
    // 返回达标字符串

    // f（n） -》 解决长度为 n 的字符串有多少达标的 (前面必须是1）
    // 所以要算 n 长度的需要调用 f（n - 1）
    //      则有两种情况
    //         1）第一个位置填 1 ：有 F（n -1）个达标的
    //         2）第一个位置填 0，则第二个位置必须填 1 ： 有 F（n-2）个达标的
    // 所以 F（n） = F（n-1） + F（n-2）
    // 所以这是以 1、2 开头的斐波那契数列
    public static int getMyNumber(int length) {
        if (length < 1) {
            return 0;
        }
        if (length == 1) {
            return 1;
        }
        if (length == 2) {
            return 2;
        }

        // [ 1 ,1 ]
        // [ 1, 0 ]
        // 得到的固定矩阵
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        // 算出固定矩阵的 n - 2 次方
        int[][] res = matrixPower1(base, length - 2);
        // \n  n-1\ = \1  1\ * res
        // so: n = res[0][0] + res[1][0];
        // 不会矩阵乘法计算的移步到 b 站
        return 2 * res[0][0] + res[1][0];
    }

    // 矩阵的次方计算
    // 假设是 13 次方： 8 + 4 + 1 -》 1101
    // 对应该方法中的次方优化
    private static int[][] matrixPower1(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        // res = 矩阵中的1
        int[][] t = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix1(res, t);
            }
            t = muliMatrix1(t, t);
        }
        return res;
    }

    // 矩阵的乘法计算
    // 画图得出结论
    private static int[][] muliMatrix1(int[][] t, int[][] t1) {
        int[][] ans = new int[t.length][t1[0].length];
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t1[0].length; j++) {
                for (int k = 0; k < t1.length; k++) {
                    ans[i][j] += t[i][k] * t1[k][j];
                }
            }
        }
        return ans;
    }
}
