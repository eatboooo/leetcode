package cn.eatboooo.study.week10;

/**
 * 状态压缩的动态规划
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/17 14:28
 * <p>
 * TSP问题
 * 有N个城市，任何两个城市之间的都有距离，任何一座城市到自己的距离都为0
 * 所有点到点的距离都存在一个N*N的二维数组matrix里，也就是整张图由邻接矩阵表示
 * 现要求一旅行商从k城市出发必须经过每一个城市且只在一个城市逗留一次，最后回到出发的k城
 * 参数给定一个matrix，给定k。返回总距离最短的路的距离
 */
public class Demo01_Try_TSP {
    public static int min1(int[][] matrix, int k) {
        // dp[use][city]  use: 已经走过那些城市，city: 当前来到的城市
        int[][] dp = new int[1 << matrix.length][matrix.length + 1];
        return dp1((1 << matrix.length) - 1, 0, matrix, dp);
    }

    // use 的二进制中， 1 代表可以去的
    private static int dp1(int use, int city, int[][] matrix, int[][] dp) {
        if (dp[use][city] != 0) {
            return dp[use][city];
        }
        if (use == (use & (~use + 1))) {
            // 此时只剩一个城市了，没得选
            // 把 0 作为开始的地方，所以返回最后一座城市到他的距离
            dp[use][city] = matrix[city][0];
            return matrix[city][0];
        }
        // 把当前城市的 1 改为 0
        use &= (~(1 << city));

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            boolean canGo = (i != city) && ((use & (1 << i)) != 0);
            if (canGo) {
                // 生成的测试数组有问题，所以导致一开始 matrix[city][i] 写反时不一致
                ans = Math.min(ans, matrix[city][i] + dp1(use, i, matrix, dp));
            }
        }
        // 把当前城市的 0 改为 1
        use |= (1 << city);
        dp[use][city] = ans;
        return ans;
    }


    // 和 min 1 差不多，但是少了两步操作
    public static int min2(int[][] matrix, int k) {
        // dp[use][city]  use: 已经走过那些城市，city: 当前来到的城市
        int[][] dp = new int[1 << matrix.length][matrix.length + 1];
        return dp2((1 << matrix.length) - 2, 0, matrix, dp);
    }

    // use 的二进制中， 1 代表可以去的
    private static int dp2(int use, int city, int[][] matrix, int[][] dp) {
        if (dp[use][city] != 0) {
            return dp[use][city];
        }
        // ⚠️ 不同之处 ⚠️
        if (use == 0) {
            // 此时没有城市了，只能回家喽
            // 把 0 作为开始的地方，所以返回最后一座城市到他的距离
            dp[use][city] = matrix[city][0];
            return matrix[city][0];
        }
        int ans = Integer.MAX_VALUE;
        // ⚠️ 不同之处 ⚠️
        // 把当前城市的 1 改为 0
        // use &= (~(1 << city));

        for (int i = 0; i < matrix.length; i++) {
            boolean canGo = (i != city) && ((use & (1 << i)) != 0);
            if (canGo) {
                // ⚠️ 不同之处 ⚠️
                // 生成的测试数组有问题，所以导致一开始 matrix[city][i] 写反时不一致
                ans = Math.min(ans, matrix[city][i] + dp2(use & (~(1 << i)), i, matrix, dp));
            }
        }
        // ⚠️ 不同之处 ⚠️
        // 把当前城市的 0 改为 1
        // use |= (1 << city);
        dp[use][city] = ans;
        return ans;
    }
}
