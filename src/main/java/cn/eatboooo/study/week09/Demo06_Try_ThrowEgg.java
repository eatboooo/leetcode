package cn.eatboooo.study.week09;

/**
 * 四边形
 * 扔蛋问题 - 典中典
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/15 20:02
 * <p>
 * 一座大楼有0~N层，地面算作第0层，最高的一层为第N层
 * 已知棋子从第0层掉落肯定不会摔碎，从第i层掉落可能会摔碎，也可能不会摔碎(1≤i≤N)
 * 给定整数N作为楼层数，再给定整数K作为棋子数
 * 返回如果想找到棋子不会摔碎的最高层数，即使在最差的情况下扔的最少次数
 * 一次只能扔一个棋子
 * N=10，K=1
 * 返回10
 * 因为只有1棵棋子，所以不得不从第1层开始一直试到第10层
 * 在最差的情况下，即第10层是不会摔坏的最高层，最少也要扔10次
 * N=3，K=2
 * 返回2
 * 先在2层扔1棵棋子，如果碎了试第1层，如果没碎试第3层
 * N=105，K=2
 * 返回14
 * 第一个棋子先在14层扔，碎了则用仅存的一个棋子试1~13
 * 若没碎，第一个棋子继续在27层扔，碎了则用仅存的一个棋子试15~26
 * 若没碎，第一个棋子继续在39层扔，碎了则用仅存的一个棋子试28~38
 * 若没碎，第一个棋子继续在50层扔，碎了则用仅存的一个棋子试40~49
 * 若没碎，第一个棋子继续在60层扔，碎了则用仅存的一个棋子试51~59
 * 若没碎，第一个棋子继续在69层扔，碎了则用仅存的一个棋子试61~68
 * 若没碎，第一个棋子继续在77层扔，碎了则用仅存的一个棋子试70~76
 * 若没碎，第一个棋子继续在84层扔，碎了则用仅存的一个棋子试78~83
 * 若没碎，第一个棋子继续在90层扔，碎了则用仅存的一个棋子试85~89
 * 若没碎，第一个棋子继续在95层扔，碎了则用仅存的一个棋子试91~94
 * 若没碎，第一个棋子继续在99层扔，碎了则用仅存的一个棋子试96~98
 * 若没碎，第一个棋子继续在102层扔，碎了则用仅存的一个棋子试100、101
 * 若没碎，第一个棋子继续在104层扔，碎了则用仅存的一个棋子试103
 * 若没碎，第一个棋子继续在105层扔，若到这一步还没碎，那么105便是结果
 */
public class Demo06_Try_ThrowEgg {
    // 返回 l 层，n 个蛋，最少扔多少次才能测出蛋的硬度
    public static int superEggDrop1(int n, int l) {
        if (n < 1 || l < 1) {
            return 0;
        }
        if (n == 1) {
            return l;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i != l + 1; i++) {
            // 这个蛋包揽了 i ～ l 层
            // ans = 在这个蛋包揽了 i ～ l 层之后，要扔多少次
            // 碎了
            int bad = superEggDrop1(n - 1, i - 1);
            // 没碎
            int good = superEggDrop1(n, l - i);
            ans = Math.min(ans, Math.max(bad, good));
        }
        return ans + 1;
    }

    // dp
    public static int superEggDrop2(int n, int l) {
        if (n < 1 || l < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][l + 1];
        for (int i = 0; i < l + 1; i++) {
            dp[1][i] = i;
        }

//        for (int index = l; index >= 1; index--) {

        for (int num = 2; num < n + 1; num++) {

            for (int index = 1; index < l + 1; index++) {
                int ans = Integer.MAX_VALUE;
                // +1 可还行 ⚠️
                for (int i = 1; i < index + 1; i++) {
                    // 这个蛋包揽了 i ～ l 层
                    // ans = 在这个蛋包揽了 i ～ l 层之后，要扔多少次
                    // 碎了
                    int bad = dp[num - 1][i - 1];
                    // 没碎
                    int good = dp[num][index - i];
                    ans = Math.min(ans, Math.max(bad, good));
                }
                // +1 可还行 ⚠️
                dp[num][index] = ans + 1;
            }
        }
        return dp[n][l];
    }

    // dp 填格子的顺序发生变化，方便后续改成四边形优化版本
    public static int superEggDrop3(int n, int l) {
        if (n < 1 || l < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][l + 1];
        for (int i = 0; i < l + 1; i++) {
            dp[1][i] = i;
        }

//        for (int index = l; index >= 1; index--) {

        for (int index = 1; index < l + 1; index++) {
            for (int num = n; num >= 2; num--) {
                int ans = Integer.MAX_VALUE;
                // +1 可还行 ⚠️
                for (int i = 1; i < index + 1; i++) {
                    // 这个蛋包揽了 i ～ l 层
                    // ans = 在这个蛋包揽了 i ～ l 层之后，要扔多少次
                    // 碎了
                    int bad = dp[num - 1][i - 1];
                    // 没碎
                    int good = dp[num][index - i];
                    ans = Math.min(ans, Math.max(bad, good));
                }
                // +1 可还行 ⚠️
                dp[num][index] = ans + 1;
            }
        }
        return dp[n][l];
    }

    // 四边形
    public static int superEggDrop3_1(int n, int l) {
        if (n < 1 || l < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][l + 1];
        int[][] dpIndex = new int[n + 1][l + 1];

        // ⚠️ 两个循环把能填的都填了
        for (int i = 0; i < l + 1; i++) {
            dp[1][i] = i;
            dpIndex[1][i] = 1;
        }
        for (int i = 0; i < n + 1; i++) {
            dpIndex[i][1] = 1;
            dp[i][1] = 1;
        }

        for (int index = 2; index < l + 1; index++) {
            for (int num = n; num >= 2; num--) {
                int ans = Integer.MAX_VALUE;
                int chose = 0;
                // +1 可还行 ⚠️
                // int down = 1;
                // int up = index + 1;
                int down = dpIndex[num][index - 1];
                // ❕❕❕❕❕边界❕❕❕❕❕❕
                int up = (num == n ? index + 1 : dpIndex[num + 1][index] + 1);
                for (int i = down; i < up; i++) {
                    // 这个蛋包揽了 i ～ l 层
                    // ans = 在这个蛋包揽了 i ～ l 层之后，要扔多少次
                    // 碎了
                    int bad = dp[num - 1][i - 1];
                    // 没碎
                    int good = dp[num][index - i];
                    // ❓❓❓❓❓❓ 第一遍这个忘记注释了，排查了很久❓❓❓❓
                    // ans = Math.min(ans, Math.max(bad, good));
                    if (ans >= Math.max(bad, good)) {
                        ans = Math.max(bad, good);
                        chose = i;
                    }
                }
                // +1 可还行 ⚠️
                dp[num][index] = ans + 1;
                dpIndex[num][index] = chose;
            }
        }
        return dp[n][l];
    }

    // 换个思路，固定 n 个瓶子，仍 t 次，至少能确定多少层楼
    // dp[n][t] = 可以确定的楼层
    // 根据已有方法画图分析得到：dp[n][t] = dp[n][t - 1] + dp[n - 1][t - 1] +1
    // 配合数组压缩，一个一纬表可以搞定
    public static int superEggDrop4(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        int[] dp = new int[kChess + 1];
        int ans = 0;
        while (true) {
            // 扔的次数
            ans++;
            int offet = 0;
            for (int i = 1; i < dp.length; i++) {
                // rember 代表平行时空中的 dp [i] 也就是 dp[n][t - 1]
                int rember = dp[i];

                //      dp[n][t - 1] + dp[n - 1][t - 1] +1
                dp[i] = rember + offet + 1;

                // 在下一次赋值之前 offet 代表平行时空中的 dp [i] 也就是 dp[n - 1][t - 1]
                offet = rember;

                if (dp[i] >= nLevel) {
                    return ans;
                }
            }
        }

    }

    public static int superEggDrop44(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        int[] dp = new int[kChess];
        int res = 0;
        while (true) {
            res++;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= nLevel) {
                    return res;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(superEggDrop4(2, 100));
        System.out.println(superEggDrop44(2, 100));
       /* for (int j = 1; j < 100; j++) {
            int i = superEggDrop3(3, j);
            System.out.print("楼层 = " + j + " - ");
            System.out.println("仍的次数= " + i);
        }*/
    }
}
