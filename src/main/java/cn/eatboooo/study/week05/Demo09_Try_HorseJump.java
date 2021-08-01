package cn.eatboooo.study.week05;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/1 21:45
 * <p>
 * // 测试链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * <p>
 * 跳马问题
 * <p>
 * 自行搜索或者想象一个象棋的棋盘，(0,0) ~ (9,8)
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 * <p>
 * 范围上的尝试模型
 */
public class Demo09_Try_HorseJump {
    public static int jump(int x, int y, int k) {
        if (x > 9 || y > 10 || k < 0 || x < 0 || y < 0) {
            return -1;
        }
        return process(0, 0, k, x, y);
    }

    private static int process(int a, int b, int rest, int x, int y) {
        if (a < 0 || a > 9 || b < 0 || b > 8) {
            return 0;
        }
        if (rest == 0) {
            return x == a && y == b ? 1 : 0;
        }
       /* int ways = process(curx + 1, cury + 2, x, y, rest - 1);
        ways += process(curx + 1, cury - 2, x, y, rest - 1);
        ways += process(curx + 2, cury + 1, x, y, rest - 1);
        ways += process(curx + 2, cury - 1, x, y, rest - 1);
        ways += process(curx - 1, cury + 2, x, y, rest - 1);
        ways += process(curx - 1, cury - 2, x, y, rest - 1);
        ways += process(curx - 2, cury + 1, x, y, rest - 1);
        ways += process(curx - 2, cury - 1, x, y, rest - 1);*/
        int ways = process(a + 2, b + 1, rest - 1, x, y);
        ways += process(a + 2, b - 1, rest - 1, x, y);
        ways += process(a + 1, b + 2, rest - 1, x, y);
        ways += process(a + 1, b - 2, rest - 1, x, y);
        ways += process(a - 1, b + 2, rest - 1, x, y);
        ways += process(a - 2, b + 1, rest - 1, x, y);
        ways += process(a - 2, b - 1, rest - 1, x, y);
        ways += process(a - 1, b - 2, rest - 1, x, y);
        return ways;
    }

    public static int dp(int a, int b, int k) {

        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest < k +1; rest++) {
            for (int i = 0; i <= 9; i++) {
                for (int o = 0; o <= 8; o++) {
                    int ways = check(i + 2, o + 1, rest - 1, dp);
                    ways += check(i + 2, o - 1, rest - 1, dp);
                    ways += check(i - 2, o + 1, rest - 1, dp);
                    ways += check(i - 2, o - 1, rest - 1, dp);
                    ways += check(i + 1, o + 2, rest - 1, dp);
                    ways += check(i + 1, o - 2, rest - 1, dp);
                    ways += check(i - 1, o + 2, rest - 1, dp);
                    ways += check(i - 1, o + -2, rest - 1, dp);
                    dp[i][o][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    private static int check(int x, int y, int k, int[][][] dp) {
        if (x > 9 || y > 8 || k < 0 || x < 0 || y < 0) {
            return 0;
        }
        return dp[x][y][k];
    }


    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
//        System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));

        System.out.println(jump(x, y, step));
    }
}
