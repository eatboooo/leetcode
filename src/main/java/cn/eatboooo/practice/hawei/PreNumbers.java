package cn.eatboooo.practice.hawei;

import java.util.Scanner;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/12 19:55
 * HJ6 质数因子
 */
public class PreNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        if (number <= 1) {
            System.out.println(number);
            return;
        }
        int num = 2;
        int sq = (int) Math.sqrt(number);
        while (number >= num) {
            if (num > sq) {
                System.out.println(number + "");
                break;
            }
            if (number % num != 0) {
                num++;
                continue;
            }
            System.out.print(num + " ");
            number /= num;
        }
    }
}
