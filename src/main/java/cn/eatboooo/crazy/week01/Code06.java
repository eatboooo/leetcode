package cn.eatboooo.crazy.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/18 21:02
 *
 * 给定两个非负数组x和hp，长度都是N，再给定一个正数range
 * x有序，x[i]表示i号怪兽在x轴上的位置
 * hp[i]表示i号怪兽的血量
 * 再给定一个正数range，表示如果法师释放技能的范围长度
 * 被打到的每只怪兽损失1点血量。返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
 *
 * 思路：线段树
 * 贪心：从最左正数范围开始，放完 AOE 再找最左正数...
 */
// ⚠️ range 指的是，选定一只怪兽，距离他左边 range 和 右边 range 的都会收到伤害
// TODO 线段树版本
public class Code06 {
    // 贪心策略：永远让最左边缘以最优的方式(AOE尽可能往右扩，最让最左边缘盖住目前怪的最左)变成0，也就是选择：
    // 一定能覆盖到最左边缘, 但是尽量靠右的中心点
    // 等到最左边缘变成0之后，再去找下一个最左边缘...
    public static int minAoe2(int[] x, int[] hp, int range) {
        int ans = 0;
        int N = x.length;
        for (int i = 0; i < N; i++) {
            if (hp[i] > 0) {
                // 找到释放 aoe 的中心位置
                int mid = i;
                while (mid < N && x[mid] - x[i] <= range) {
                    mid++;
                }
                ans += hp[i];
                // 释放技能
                aoe(x, hp, i, mid - 1, range);
            }
        }
        return ans;
    }

    private static void aoe(int[] x, int[] hp, int left, int mid, int range) {
        int N = x.length;
        int right = mid;
        while (right < N && x[right] - x[mid] <= range) {
            right++;
        }
        // ⚠️ 必须提取出来，因为 hp left 会发生改变
        int i1 = hp[left];
        for (int i = left; i < right; i++) {
            hp[i] = Math.max(0, hp[i] - i1);
        }
    }
}
