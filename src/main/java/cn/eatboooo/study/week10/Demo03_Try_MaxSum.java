package cn.eatboooo.study.week10;

import java.util.LinkedList;

/**
 * 动态规划中外部信息简化
 * 用到了滑动时间窗口
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/20 18:01
 * <p>
 * 给定一个数组arr，和一个正数M，
 * 返回在arr的子数组在长度不超过M的情况下，
 * 最大的累加和
 */
public class Demo03_Try_MaxSum {
    public static int max(int[] arr, int m) {
        if (arr == null || arr.length == 0 || m < 1) {
            return 0;
        }
        int[] help = new int[arr.length];
        help[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            help[i] = arr[i] + help[i - 1];
        }
        LinkedList<Integer> windows = new LinkedList<>();
        int size = Math.min(m, arr.length);
        // 生成窗口初始大小
        for (int i = 0; i < size; i++) {
            while (!windows.isEmpty() && help[windows.peekLast()] <= help[i]) {
                windows.pollLast();
            }
            windows.add(i);
        }

        int max = help[windows.peekFirst()];
        int L = 0;
        // 窗口不断进入数字
        for (int i = size; i < help.length; i++,L++) {
            if (windows.peekFirst() == L) {
                windows.pollFirst();
            }
            while (!windows.isEmpty() && help[windows.peekLast()] <= help[i]) {
                windows.pollLast();
            }
            windows.add(i);
            max = Math.max(max, help[windows.peekFirst()] - help[L]);
        }

        // 窗口中还有数字
        for (int i = L; i < help.length -1; i++) {
            if (windows.peekFirst() == i) {
                windows.pollFirst();
            }
            max = Math.max(max, help[windows.peekFirst()] - help[i]);
        }

        return max;
    }
}
