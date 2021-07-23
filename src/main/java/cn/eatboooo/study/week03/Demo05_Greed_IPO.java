/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week03
 * @className cn.eatboooo.study.week03.Demo05_Greed_IPO
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week03;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Demo05_Greed_IPO
 * @description
 * @author weiZhiLin
 * @date 2021/7/23 11:25
 * @version 1.0
 *
 * 首次公开募股（英语：Initial Public Offerings，缩写：IPO
 */
public class Demo05_Greed_IPO {
    // 最多K个项目
    // W是初始资金
    // Profits[] Capital[] 一定等长
    // 返回最终最大的资金
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        Queue<Info> minQueue = new PriorityQueue<>(new MyComparatorCMin());
        Queue<Info> maxQueue = new PriorityQueue<>(new MyComparatorPMax());

        for (int i = 0; i < Profits.length; i++) {
            minQueue.add(new Info(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (W >= minQueue.peek().c) {
                maxQueue.add(minQueue.poll());
            }
            if (maxQueue.isEmpty()) {
                break;
            }
            W += maxQueue.poll().p;
        }
        return W;

    }

    public static class Info {
        int p;
        int c;

        public Info(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MyComparatorPMax implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o2.p - o1.p;
        }
    }

    public static class MyComparatorCMin implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o1.c - o2.c;
        }
    }

}
