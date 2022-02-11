package cn.eatboooo.study.week09;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:21
 * <p>
 * int[] d，d[i]：i号怪兽的能力
 * int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你
 * 他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力
 * 你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你
 * 他的能力直接累加到你的能力上
 * 返回通过所有的怪兽，需要花的最小钱数
 * <p>
 * 根据不同数据量写不同的解决办法
 */
public class Demo03_FindRule_Monster {
    // 变量：能力，怪物序号；返回：最少花多少钱
    public static int dp1(int[] d, int[] p, int index, int power) {
        if (index == d.length) {
            return 0;
        }
        int i = p[index] + dp1(d, p, index + 1, power + d[index]);
        if (power < d[index]) {
            return i;
        } else {
            int j = dp1(d, p, index + 1, power);
            return Math.min(i, j);
        }
    }

    // 变量：严格花多少钱，怪物序号；返回：通过这些怪兽后的最大能力值
    // 后续从 1 开始遍历 money，第一个不是 -1 的即为答案
    public static int dp2(int[] d, int[] p, int money, int index) {
        if (index == d.length) {
            // 钱是严格控制的
            if (money == 0) {
                return 0;
            }
            return -1;
        }
        // buy
        int i = dp2(d, p, money - p[index], index + 1);
        int p1 = i + d[index];
        // ignore
        int p2 = dp2(d, p, money, index + 1);
        if (i == -1) {
            return p2;
        }
        if (p2 == -1) {
            return p1;
        }
        return Math.max(p1, p2);
    }
}
