package cn.eatboooo.study.week01;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 堆相关练习
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/27 13:13
 *
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class Demo03_Heap_CoverMax {
    // 暴力版本：找到线段最早开始，和最晚结束，遍历这段区间中的每一个 0.5、1.5、2.5等，看看有多少个

    // 聪明版本：
    // 创建小根堆，用于放置线段的结束位置
    // 把所有线段从开始位置排序 -》 arr
    // 遍历 arr，得到开始位置和结束位置
    // 小根堆 peek ，如果有比开始位置还小的，弹出，直到没有
    // 把结束位置放入小根堆
    // 此时小根堆里的数量 即是一个答案
    // 遍历找到最大的答案即可
    // 原理是：假设遍历到 i 边，我们本次遍历求的就是 以 i 边作为重合的地基时，有多少个重合的边

    public static int maxCover1(int[][] lines) {
        ArrayList<Line> list = new ArrayList<>();
        for (int[] line : lines) {
            Line e = new Line(line[0], line[1]);
            list.add(e);
        }
        list.sort((o1, o2) -> o1.start - o2.start);

        PriorityQueue<Integer> pr = new PriorityQueue<>();
        int ans = 0;
        for (Line line : list) {
            while (!pr.isEmpty() && pr.peek() <= line.start) {
                pr.poll();
            }
            pr.add(line.end);
            ans = Math.max(ans, pr.size());
        }
        return ans;
    }
    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

}
