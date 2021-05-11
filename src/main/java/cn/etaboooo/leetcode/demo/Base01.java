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

    // 选择排序
    // 0~1
    // 0~2
    // 0~3
    // 1~2
    // 1~3
    // 1~4
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swapArr(arr, i, j);
                }
            }
        }
    }

    // 数组交换
    private static void swapArr(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    // 打印数组
    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
