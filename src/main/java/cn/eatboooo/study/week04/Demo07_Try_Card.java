package cn.eatboooo.study.week04;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/28 00:57
 * <p>
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 *
 * 范围上的尝试模型
 */
public class Demo07_Try_Card {
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 先手拿牌的最好分数
        int i1 = f1(arr, 0, arr.length - 1);
        // 后手拿牌的最好分数
        int i = s1(arr, 0, arr.length - 1);
        return Math.max(i1, i);
    }

    private static int s1(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        // 后手拿，就相当于 先手拿 -1 之后的牌
        // 敌人拿了左边
        int a = f1(arr, start + 1, end);
        // 敌人拿了右边
        int b = f1(arr, start, end - 1);

        // 你是后手，敌人会让你的分数最小
        return Math.min(a, b);
    }

    private static int f1(int[] arr, int start, int end) {
        if (start == end) {
            return arr[start];
        }
        // 拿左边牌的分数
        int a = arr[start] + s1(arr, start + 1, end);
        // 拿右边牌的分数
        int b = arr[end] + s1(arr, start, end - 1);
        // 返回最大值
        return Math.max(a, b);
    }

    // 缓存优化
    public static int win2(int[] arr) {
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] smap = new int[N][N];
        System.out.println("fmap = " + fmap.length);
        for (int i = 0; i < smap.length; i++) {
            for (int j = 0; j < smap.length; j++) {
                fmap[i][j] = -1;
                smap[i][j] = -1;
            }
        }
        int f = f2(arr, 0, arr.length - 1, fmap, smap);
        int s = s2(arr, 0, arr.length - 1, fmap, smap);
        return Math.max(f, s);
    }

    private static int s2(int[] arr, int start, int end, int[][] fmap, int[][] smap) {
        if (smap[start][end] != -1) {
            return smap[start][end];
        }
        int ans = 0;
        if (start == end) {
            ans = 0;
        } else {
            // 后手拿，就相当于 先手拿 -1 之后的牌
            // 敌人拿了左边
            int a = f1(arr, start + 1, end);
            // 敌人拿了右边
            int b = f1(arr, start, end - 1);

            // 你是后手，敌人会让你的分数最小
            ans = Math.min(a, b);
        }
        smap[start][end] = ans;
        return ans;
    }

    private static int f2(int[] arr, int start, int end, int[][] fmap, int[][] smap) {
        if (fmap[start][end] != -1) {
            return fmap[start][end];
        }
        int ans = 0;
        if (start == end) {
            ans = arr[start];
        } else {
            // 拿左边牌的分数
            int a = arr[start] + s1(arr, start + 1, end);
            // 拿右边牌的分数
            int b = arr[end] + s1(arr, start, end - 1);
            // 返回最大值
            ans = Math.max(a, b);
        }
        fmap[start][end] = ans;
        return ans;
    }

    // 动态规划
    public static int win3(int[] arr) {
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] smap = new int[N][N];
        for (int i = 0; i < arr.length; i++) {
            fmap[i][i] = arr[i];
            smap[i][i] = 0;
        }

        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            while (R < N) {
                fmap[L][R] = Math.max(arr[L] + smap[L + 1][R], arr[R] + smap[L][R - 1]);
                smap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                L++;
                R++;
            }
        }


        return Math.max(fmap[0][N - 1], smap[0][N - 1]);
    }

    // 动态规划 v2 温故知新
    public static int win4(int[] arr) {
        int N = arr.length;
        int[][] fdp = new int[N][N];
        int[][] sdp = new int[N][N];
        for (int i = 0; i < N; i++) {
            fdp[i][i] = arr[i];
            sdp[i][i] = 0;
        }
        for (int end = 1; end < N; end++) {
            for (int start = end - 1; start >= 0; start--) {
                fdp[start][end] = Math.max(sdp[start + 1][end] + arr[start], sdp[start][end - 1] + arr[end]);
                sdp[start][end] = Math.min(fdp[start + 1][end], fdp[start][end - 1]);
            }
        }
        return Math.max(fdp[0][N - 1], sdp[0][N - 1]);
    }
    // test
    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
//        int[] arr = {1, 100, 1};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }
}
