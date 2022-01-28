package cn.eatboooo.study.week07;

import java.util.LinkedList;

/**
 * 加油站
 * // 测试链接：https://leetcode.com/problems/gas-station
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/9 13:30
 * <p>
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明: 
 * <p>
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 */
public class Demo01_Window_GasStation {
    // 假设：    gas      1   2   3
    //          cost：   2   3   3
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        // 生成 gas[i] - cost[i] 数组，用来判断能否达到下一个加油站
        int[] myGas = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            myGas[i] = gas[i] - cost[i];
        }
        // 用 myGas 生成数组累加和
        int[] myGasPre = new int[myGas.length * 2];
        for (int i = 0; i < myGasPre.length; i++) {
            int i2 = myGas[i % myGas.length];
            myGasPre[i] = i - 1 >= 0 ? i2 + myGasPre[i - 1] : i2;
        }
        // 根据前缀累加和，使用滑动时间窗口判断有没有负数
        // 窗口大小为 gas 数组大小
        LinkedList<Integer> windows = new LinkedList<>();
        int windowSize = myGasPre.length / 2;
        for (int i = 0; i < windowSize; i++) {
            // 小的存在最前面
            while (!windows.isEmpty() && myGasPre[windows.peekLast()] >= myGasPre[i]) {
                windows.pollLast();
            }
            windows.addLast(i);
        }
        // 至关重要开始
        for (int offset = 0, index = 0, windowR = windowSize; windowR < myGasPre.length; offset = myGasPre[index++], windowR++) {
            // 窗口减去偏移的
            if (myGasPre[windows.peekFirst()] - offset >= 0) {
                return index % windowSize;
            }
            if (windows.peekFirst() == index) {
                windows.pollFirst();
            }
            // 偏移完成，开始全新版本
            // 小的存在最前面
            // 滑动的是最新的
            while (!windows.isEmpty() && myGasPre[windows.peekLast()] >= myGasPre[windowR]) {
                windows.pollLast();
            }
            // 添加的是最新的
            windows.addLast(windowR);
        }
        return -1;
    }

    // test
    public static void main(String[] args) {
        // int[] a1 = {1,2,3,4,3,2,4,1,5,3,2,4};
        // int[] a2 = {1,1,1,3,2,4,3,6,7,4,3,1};
        // System.out.println("a1.length = " + a1.length);
       int[] a1 = {1, 5, 3};
       int[] a2 = {2, 3, 3};
        //[1,2,3,4,3,2,4,1,5,3,2,4]
        //[1,1,1,3,2,4,3,6,7,4,3,1]
        //  -1,2,0
        // 0~2: -1,1,0
        // 2~1: 0,-1,1
        int i = canCompleteCircuit(a1, a2);
        System.out.println("i = " + i);
    }
}
