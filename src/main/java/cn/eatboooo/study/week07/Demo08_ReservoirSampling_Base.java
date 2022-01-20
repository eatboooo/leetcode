package cn.eatboooo.study.week07;

/**
 * 蓄水池算法
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/17 20:08
 * <p>
 * 一个管道一直出球，一个池子里面只能装固定的球 为 size
 * 怎么样设计，让管子出的球都等概率进池子
 * 出的球编号从1递增为 i
 * 每个球进管子的概率为 size / i  及为答案
 */
public class Demo08_ReservoirSampling_Base {
    public static int[] comeOn(int size, int ball) {
        int index = 1;
        int[] ints = new int[size];
        while (index <= ball) {
            if (index <= size) {
                ints[index - 1] = index;
            } else if (canPush(size, index)) {
                int poll = (int) (Math.random() * size);
                ints[poll] = index;
            }
            index++;
        }
        return ints;
    }

    // size/index 的几率返回 true
    private static boolean canPush(int size, int index) {
        // 等概率返回 1～index
        int i = (int) (Math.random() * index + 1);
        return i <= size;
    }

    public static void main(String[] args) {
        int test = 100000;
        int size = 10;
        int index = 20;
        int[] count = new int[index + 1];
        for (int i = 0; i < test; i++) {
            int[] ints = comeOn(size, index);
            for (int anInt : ints) {
                count[anInt]++;
            }
        }
        for (int i = 0; i < count.length; i++) {
            int i1 = count[i];
            System.out.println(i + " 的中奖次数：" + i1);
        }
    }
}
