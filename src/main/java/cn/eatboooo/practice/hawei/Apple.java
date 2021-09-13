package cn.eatboooo.practice.hawei;

import java.util.Scanner;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/12 20:28
 * HJ61 放苹果
 */
public class Apple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] s1 = s.split(" ");
            System.out.println(apple(Integer.valueOf(s1[0]), Integer.valueOf(s1[1])));
        }
    }

    private static int apple(int apple, int space) {
        if (apple == 0 || space == 1) {
            return 1;
        }
        if (space > apple) {
            return apple(apple, apple);
        }
        return apple(apple, space - 1) + apple(apple - space, space);
    }
}
