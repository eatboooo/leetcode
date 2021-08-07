package cn.eatboooo.study.week06;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/7 23:13
 * <p>
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Demo11_Try_KillMonster {
    public static double killMonster(int blood, int power, int times) {
        if (power <= 0 || times <= 0 || blood <= 0) {
            return 0;
        }
        return (double) process1(blood, power, times) / (double) Math.pow(power + 1, times);
    }

    private static int process1(int blood, int power, int times) {
        if (times == 0) {
            return blood <= 0 ? 1 : 0;
        }
        int ways = 0;
        for (int i = 0; i <= power; i++) {
            ways += process1(blood - i, power, times - 1);
        }
        return ways;
    }

    private static double dp(int blood, int power, int times) {
        if (power <= 0 || times <= 0 || blood <= 0) {
            return 0;
        }
        long[][] dp = new long[blood + 1][times + 1];
        dp[0][0] = 1;
        for (int i = 0; i < blood + 1; i++) {
            for (int rest = 1; rest < times + 1; rest++) {
                long ways = 0;
                for (int k = 0; k <= power; k++) {
                    if (i - k < 0) {
                        ways += Math.pow(power + 1, rest - 1);
                    }else {
                        ways += dp[i - k][rest - 1];
                    }
                }
                dp[i][rest] = ways;
            }
        }
        return (double) dp[blood][times] / (double) Math.pow(power + 1, times);
    }


    // todo 空间压缩
    // copy for test
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = killMonster(N, M, K);
//            double ans2 = dp1(N, M, K);
            double ans3 = dp(N, M, K);
            if (ans1 != ans3) {
//            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
