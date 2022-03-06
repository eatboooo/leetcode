package cn.eatboooo.study.week07;

/**
 * 斐波那契数列
 * 优化
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/15 15:21
 * <p>
 * 1，1，2，3，5，8。。。。
 * F（n) = F(n-1) + F(n-2)
 * 矩阵（n,n-1) = 矩阵（n-1,n-2) * （2*2 固定矩阵）
 * 得出 2*2 固定矩阵为：「1  1
 *                     1  0 」
 * 也可以推导出： |F(n) F(n-1)| = |F(1) F(2)| * 固定矩阵的 n-2 次方
 */
public class Demo04_Fibonacci_Base {
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // [ 1 ,1 ]
        // [ 1, 0 ]
        // 得到的固定矩阵
        int[][] base = {
                { 1, 1 },
                { 1, 0 }
        };
        // 算出固定矩阵的 n - 2 次方
        int[][] res = matrixPower1(base, n - 2);
        // \n  n-1\ = \1  1\ * res
        // so: n = res[0][0] + res[1][0];
        // 不会矩阵乘法计算的移步到 b 站
        return res[0][0] + res[1][0];
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


    // copy for test
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    // O(logN)
    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // [ 1 ,1 ]
        // [ 1, 0 ]
        int[][] base = {
                { 1, 1 },
                { 1, 0 }
        };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        // res = 矩阵中的1
        int[][] t = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, t);
            }
            t = muliMatrix(t, t);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return s1(n - 1) + s1(n - 2);
    }

    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {
                { 1, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 } };
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    public static void main(String[] args) {
        int n = 9;
        // int n = 10000000;
        long start;
        long end;
        start = System.currentTimeMillis();
        System.out.println(f1(n));
        end = System.currentTimeMillis();
        System.out.println("矩阵乘法的耗时为 ：" + (end - start));

        start = System.currentTimeMillis();
        System.out.println(f2(n));
        end = System.currentTimeMillis();
        // System.out.println(f3(n));
        System.out.println("动态规划+数组压缩的耗时为 ：" + (end - start));

        System.out.println("===");

        // System.out.println(s1(n));
        // System.out.println(s2(n));
        // System.out.println(s3(n));
        System.out.println("===");

        // System.out.println(c1(n));
        // System.out.println(c2(n));
        // System.out.println(c3(n));
        System.out.println("===");

    }
}
