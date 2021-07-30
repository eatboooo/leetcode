package cn.eatboooo.study.week04;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/30 16:48
 *
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class Demo07_Try_NQueens {
    public static int numbs(int numbs) {
        if (numbs <= 0) {
            return -1;
        }
        //col = ans【row】 表示，在第 row 行 col 列上有皇后
        int[] ans = new int[numbs];
        return process(ans, 0);
    }
    // 当前来到i行，一共是0~N-1行
    // 在i行上放皇后，所有列都尝试
    // 必须要保证跟之前所有的皇后不打架
    // int[] record record[x] = y 之前的第x行的皇后，放在了y列上
    // 返回：不关心i以上发生了什么，i.... 后续有多少合法的方法数
    // my：cur 代表当前行是多少，循环每一列，递归每一行
    private static int process(int[] ans, int cur) {
        if (cur == ans.length) {
            // 走到了最后，且没有发生问题，算是一种解法
            return 1;
        }
        int r = 0;
        for (int col = 0; col < ans.length; col++) {
            if (canPut(ans, cur, col)) {
                // 每次循环都要覆盖这个值，所以不需要恢复现场
                ans[cur] = col;
                // 当前行放上了，我们去下一行
                // 这里是 cur +1 不是 cur ++
                r += process(ans, cur + 1);
            }
        }
        return r;
    }

    private static boolean canPut(int[] ans, int curRow, int curCol) {
        for (int row = 0; row < curRow; row++) {
            int col = ans[row];
            // 是否同 col 列
            // 是否在一条斜线上，（存在皇后列 - 当前列） == （存在皇后行 - 当前行） 就说明在统一斜线
            if (col == curCol || Math.abs(row - curRow) == Math.abs(col - curCol)) {
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        int n = 10;

        long start = System.currentTimeMillis();
//        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(numbs(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
