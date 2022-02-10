package cn.eatboooo.study.week09;

/**
 * 找规律
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:16
 *
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量
 * 1）能装下6个苹果的袋子
 * 2）能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，
 * 且使用的每个袋子必须装满，给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
 */
public class Demo03_FindRule_BuyApple {
    public static int apple(int apple) {
        if (apple == 0) {
            return 0;
        }
        if (apple < 0) {
            return Integer.MAX_VALUE;
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        // use 6
        int apple1 = apple(apple - 6);
        if (apple1 != Integer.MAX_VALUE) {
            p1 = 1 + apple1;
        }
        // use 8
        int apple2 = apple(apple - 8);
        if (apple2 != Integer.MAX_VALUE) {
            p2 = 1 + apple2;
        }
        return Math.min(p1, p2);
    }

    public static int appleRules(int apple) {
        if (apple % 2 != 0) {
            return -1;
        }
        if (apple < 12) {
            switch (apple) {
                case 0:
                    return 0;
                case 6:
                case 8:
                    return 1;
                default:
                    return -1;
            }
        }
        if (apple % 8 != 0) {
            return apple / 8 + 1;
        } else {
            return apple / 8;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int apple = apple(i);
            int appleRules = appleRules(i);
            System.out.println(i + ": " + appleRules);
            if (apple != Integer.MAX_VALUE) {
                System.out.println(i + ": " + apple);
                System.out.println(i + ": " + appleRules);
                if (apple != appleRules) {
                    System.out.println("!!!!");
                    return;
                }
            }
        }
    }
}
