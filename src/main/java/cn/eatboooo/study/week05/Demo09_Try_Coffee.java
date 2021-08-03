package cn.eatboooo.study.week05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/8/1 23:19
 *
 * // 题目
 * // 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * // 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * // 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * // 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * // 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * // 四个参数：arr, n, a, b
 * // 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 */
public class Demo09_Try_Coffee {
    public static int minTime1(int[] arr, int n, int a, int b) {
        // 什么时候能喝完？
        int[] dirnks = forceDrink(arr, n);
        // 喝完了去洗吧
        return bestTime(dirnks, a, b, 0, 0);
    }

    private static int bestTime(int[] dirnks, int wave, int air, int index, int free) {
        if (index == dirnks.length) {
            return 0;
        }
        // 洗
        int waveTime = Math.max(free, dirnks[index]) + wave;
        // 剩下杯子需要的时间
        int restwaveTime = bestTime(dirnks, wave, air, index + 1, waveTime);
        // why ? 洗到最后一杯的下一杯，越界了不要时间，所以求 max
        int p1 = Math.max(waveTime, restwaveTime);

        // 晒
        int airTime = dirnks[index] + air;
        // 剩下杯子需要的时间
        int restairTime = bestTime(dirnks, wave, air, index + 1, free);
        int p2 = Math.max(airTime, restairTime);

        return Math.min(p1, p2);
    }

    // dp 版本
    public static int minTime2(int[] arr, int n, int a, int b) {
        // 什么时候能喝完？
        int[] dirnks = forceDrink(arr, n);
        // 喝完了去洗吧
        return dp(dirnks, a, b);
    }

    private static int dp(int[] dirnks, int wave, int air) {
        int N = 0;
        for (int dirnk : dirnks) {
            N = Math.max(dirnk + wave, N + wave);
        }
        int[][] dp = new int[dirnks.length + 1][N + 1];
        for (int curIndex = dirnks.length - 1; curIndex >= 0; curIndex--) {
            for (int curFree = N; curFree >= 0; curFree--) {
                // 洗
                int waveTime = Math.max(curFree, dirnks[curIndex]) + wave;
                if (waveTime > N) {
                    continue;
                }
                // 剩下杯子需要的时间
                int restwaveTime = dp[curIndex + 1][waveTime];
                int p1 = Math.max(waveTime, restwaveTime);
                // 晒
                int airTime = dirnks[curIndex] + air;
                // 剩下杯子需要的时间
                int restairTime = dp[curIndex + 1][curFree];
                int p2 = Math.max(airTime, restairTime);
                dp[curIndex][curFree] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    public static class Info {
        int workTime;
        int canWork;

        public Info(int workTime, int canWork) {
            this.workTime = workTime;
            this.canWork = canWork;
        }
    }

    public static class MyComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return (o1.canWork + o1.workTime) - (o2.canWork + o2.workTime);
        }
    }

    private static int[] forceDrink(int[] arr, int n) {
        PriorityQueue<Info> heap = new PriorityQueue<>(new MyComparator());
        for (int i : arr) {
            heap.add(new Info(i, 0));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Info poll = heap.poll();
            poll.canWork += poll.workTime;
            drinks[i] = poll.canWork;
            heap.add(poll);
        }
        return drinks;
    }


    // copy

    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTime1(arr, n, a, b);
            int ans3 = minTime2(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }
}
