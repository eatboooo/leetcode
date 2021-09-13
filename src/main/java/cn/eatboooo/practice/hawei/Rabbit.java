package cn.eatboooo.practice.hawei;

import java.util.Scanner;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/12 20:17
 * HJ37 统计每个月兔子的总数
 */
public class Rabbit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int month = sc.nextInt();
            System.out.println(doIt(month));
        }
    }

    public static int doIt(int month) {
        if (month < 3) {
            return 1;
        }
        return doIt(month - 1) + doIt(month - 2);
    }
}
