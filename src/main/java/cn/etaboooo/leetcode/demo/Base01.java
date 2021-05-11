/**
 * @projectName leetcode
 * @package cn.etaboooo.leetcode.demo
 * @className cn.etaboooo.leetcode.demo.Base01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.etaboooo.leetcode.demo;

/**
 * Base01
 * @description 最基础的练习，不涉及题目
 * @author weiZhiLin
 * @date 2021/5/11 15:56
 * @version 1.0
 */
public class Base01 {
    // 打印 int 的二进制
    public static void print32(int i) {
        int l = 31;
        for (int j = l; j >= 0; j--) {
            System.out.print((i >> j) & 1);
            if (j % 4 == 0) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
