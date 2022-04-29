package cn.eatboooo.crazy.week02;

import java.util.Arrays;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/4/9 22:32
 * <p>
 * 现有司机N*2人，调度中心会将所有司机平分给A、B两区域，
 * i号司机去A可得收入为income[i][0]，
 * 去B可得收入为income[i][1]
 * 返回能使所有司机总收入最高的方案是多少钱?
 */
public class Code04 {
    public int getMax(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        return dp(income, income.length / 2, 0);
    }

    // rest : 去A地点剩余名额
    // index：当前遍历到了谁
    private int dp(int[][] income, int rest, int index) {
        if (index == income.length) {
            return 0;
        }
        if (rest == 0) {
            // 全给我去B
            return dp(income, 0, index + 1) + income[index][1];
        }
        if (income.length - index == rest) {
            // 全给我去A
            return dp(income, rest - 1, index + 1) + income[index][0];
        }
        // 去A
        int a = dp(income, rest - 1, index + 1) + income[index][0];
        int b = dp(income, rest, index + 1) + income[index][1];
        return Math.max(a, b);
    }

    // 这题有贪心策略 :
    // 假设一共有10个司机，思路是先让所有司机去A，得到一个总收益
    // 然后看看哪5个司机改换门庭(去B)，可以获得最大的额外收益

    public int getMax2(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        // 全部去 A 的收入
        int sum = 0;
        // 让他去 B 的差距
        int[] device = new int[income.length];
        for (int i = 0; i < income.length; i++) {
            int ints = income[i][1];
            sum += ints;
            device[i] = income[i][1] - income[i][0];
        }
        Arrays.sort(device);
        for (int i = 0; i < device.length / 2; i++) {
            sum -= device[i];
        }
        return sum;
    }

}
