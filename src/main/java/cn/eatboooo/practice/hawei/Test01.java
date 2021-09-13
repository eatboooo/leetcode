package cn.eatboooo.practice.hawei;

import java.util.Scanner;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/12 21:16
 */
public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split(",");
            System.out.println(car(split, 0));
        }
    }

    private static int car(String[] split, int i) {
        if (i == split.length) {
            return 0;
        }
        if (i < split.length && "1".equals(split[i])) {
            if (i + 1 < split.length && "1".equals(split[i + 1])) {
                if (i + 2 < split.length && "1".equals(split[i + 2])) {
                    return 1 + car(split, i + 3);
                }
                return 1 + car(split, i + 2);
            }
            return 1 + car(split, i + 1);
        }
        return car(split, i + 1);
    }
}
