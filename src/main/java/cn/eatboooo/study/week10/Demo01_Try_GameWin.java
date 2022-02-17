package cn.eatboooo.study.week10;

/**
 * 状态压缩的动态规划
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/17 10:06
 * <p>
 * 在"100 game"这个游戏中，两名玩家轮流选择从1到10的任意整数，累计整数和
 * 先使得累计整数和达到或超过100的玩家，即为胜者，如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从1到15的整数（不放回），直到累计整数和 >= 100
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和）
 * 判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
 * Leetcode题目：https://leetcode.com/problems/can-i-win/
 */
public class Demo01_Try_GameWin {
    // 1 ~ n , 凑齐 rest
    // 先手能不能获胜
    public boolean canIWin(int n, int rest) {
        if (rest == 0) {
            return true;
        }
        if ((n * (n + 1) >> 1) < rest) {
            return false;
        }

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
        }

        return dp(arr, rest);
    }

    // 先手能不能获胜
    private boolean dp(int[] arr, int rest) {
        if (rest <= 0) {
            // 没有数子了，说明上一个人获胜了
            return false;
        }
        boolean ans;
        for (int i = 1; i < arr.length; i++) {
            // 尝试所有可能性，有一种能赢就ok
            if (arr[i] != -1) {
                // 此时说明 这个数还在，可以尝试
                int temp = arr[i];
                arr[i] = -1;
                // dp(arr, rest - temp) 代表对手能不能赢
                // 对手失败时 !dp(arr, rest - temp) 返回 true
                ans = dp(arr, rest - temp);
                // 还原现场，很重要
                arr[i] = temp;
                if (!ans) {
                    return true;
                }
            }
        }
        return false;
    }


    // 状态压缩
    public boolean canIWin2(int n, int rest) {
        if (rest == 0) {
            return true;
        }
        if ((n * (n + 1) >> 1) < rest) {
            return false;
        }
        // 原先的 arr 数组 使用 bit 位的 0 1 来代替有没有用到
        // 在题目描述中 n 《= 20 ，所以一个 int 长度的数就可以搞定
        int use = 0;

        return dp2(use, rest, n);
    }

    // 先手能不能获胜
    private boolean dp2(int use, int rest, int n) {
        if (rest <= 0) {
            // 没有数子了，说明上一个人获胜了
            return false;
        }
        for (int i = 1; i <= n; i++) {
            // 尝试所有可能性，有一种能赢就ok
            if ((use & (1 << i)) == 0) {
                // 此时说明 这个数还在，可以尝试
                if (!dp2(use | (1 << (i)), rest - i, n)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 状态压缩 + 动态规划
    public boolean canIWin3(int n, int rest) {
        if (rest == 0) {
            return true;
        }
        if ((n * (n + 1) >> 1) < rest) {
            return false;
        }
        // 使用 dp 来记录该位置是否计算过、计算过的答案
        // 为什么用 int ？ 因为需要三种状态：没计算过、能赢、不能赢
        // 为什么是 1维 ？ 因为可以通过 dp 来算出 rest
        int[] dp = new int[1 << (n + 1)];
        int use = 0;
        return dp3(dp, use, rest, n);
    }

    // 先手能不能获胜
    private boolean dp3(int[] dp, int use, int rest, int n) {
        if (dp[use] != 0) {
            // 此时说明计算过
            return dp[use] == 1;
        }
        if (rest <= 0) {
            dp[use] = -1;
            return false;
        }
        boolean ans = false;
        for (int i = 1; i <= n; i++) {
            // 尝试所有可能性，有一种能赢就ok
            if ((use & (1 << i)) == 0) {
                // 此时说明 这个数还在，可以尝试
                if (!dp3(dp, use | (1 << (i)), rest - i, n)) {
                    ans = true;
                    break;
                }
            }
        }
        dp[use] = ans ? 1 : -1;
        return ans;
    }

    public static void main(String[] args) {

    }
}
