/**
 * @projectName leetcode
 * @package cn.eatboooo.leetcode.demo
 * @className cn.eatboooo.leetcode.demo.Ohter01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.leetcode.demo;

/**
 * Other01
 * @description
 * @author weiZhiLin
 * @date 2021/7/9 10:04
 * @version 1.0
 */
public class Other01 {
    public static int[] findDoubleMin(int num, int money, String price) {
        String[] splited = price.split("\\s+");
        for (int i = 0; i < splited.length; i++) {
            String s = splited[i];
            Integer integer = Integer.valueOf(s);
            for (int j = i + 1; j < splited.length; j++) {
                String s2 = splited[j];
                Integer integer2 = Integer.valueOf(s2);
                if (integer + integer2 == money) {
                    return new int[]{integer, integer2};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] doubleMin = findDoubleMin(1, 500, "100 200 300 101 102");
        for (int i = 0; i < doubleMin.length; i++) {
            int i1 = doubleMin[i];
            System.out.println("i1 = " + i1);
        }
    }
}
