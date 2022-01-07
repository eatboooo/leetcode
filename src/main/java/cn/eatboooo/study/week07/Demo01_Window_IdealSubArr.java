package cn.eatboooo.study.week07;

import java.util.LinkedList;

/**
 * 窗口内最大值或最小值的更新结构
 * 理想的字数组
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/5 20:44
 * <p>
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class Demo01_Window_IdealSubArr {
    // mine
    private static int num(int[] arr, int sum) {
        if (arr.length < 1) {
            return 0;
        }
        int count = 0;
        // 左指针
        int l = 0;
        LinkedList<Integer> max;
        LinkedList<Integer> min;
        while (l < arr.length) {
            // 右指针初始化
            int r = l;
            // 两个双端队列，用来寻找滑动窗口内的最大值和最小值
            max = new LinkedList<>();
            min = new LinkedList<>();
            max.add(l);
            min.add(l);
            // 固定 l，移动 r
            for (int i = l + 1; i < arr.length; i++) {
                int i1 = arr[i];
                // 来数据了，更新两个双端队列
                while (!max.isEmpty() && arr[max.peekLast()] <= i1) {
                    max.pollLast();
                }
                while (!min.isEmpty() && arr[min.peekLast()] >= i1) {
                    min.pollLast();
                }
                max.addLast(i);
                min.addLast(i);
                if (arr[max.peekFirst()] - arr[min.peekFirst()] > sum) {
                    // 此时子数组不满足需求了，r 回退一格，退出
                    r = i - 1;
                    break;
                }
                // 更新 r 的位置
                r = i;
            }
            // 此时 r 再往右移动，子数组将不满足需求
            count += r - l + 1;
            // l 右移，达到遍历效果
            l++;
        }
        return count;
    }

    // copy for test
    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
