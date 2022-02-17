package cn.eatboooo.study.week10;

/**
 * 状态压缩的动态规划
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/17 16:35
 * <p>
 * 铺砖问题（最优解其实是轮廓线dp，太难了，掌握简单的解法即可）
 * 你有无限的1*2的砖块，要铺满M*N的区域，
 * 不同的铺法有多少种?
 */
public class Demo01_Try_PavingTile {
    // todo way1 思路有问题 先搁置
    // 在每一行中，如果放瓷砖，瓷砖只有两种状态：竖着占一格，或者横着占两格
    public static int way1(int m, int n) {
        int max = m > n ? m : n;
        int min = max == m ? n : m;
        // 以 max 为列，min 为行
        return dp1(0, 0, min, max);
    }

    // use 二进制表示上一行的状态：0 表示已经填了，1 表示没有填
    // index 表示 0～ index -1 已经填完，当前来到了 index 行位置
    // min 表示总共有多少行
    // max 表示总共有多少列
    // 返回 index ～ min 有多少方法可以填满
    private static int dp1(int use, int index, int min, int max) {
        if (index == min) {
            if (use == 0) {
                // 全部填满了
                return 1;
            }
            // 有空缺
            return 0;
        }
        int way = 0;
        // use 为 1 的位置必须放一块竖着的瓷砖，否则就永远无法补救了
        use ^= ((1 << (max + 1)) - 2);
        // 此时的 use 只有 1 的位置可以摆放瓷砖
        // 我们遍历每一个 1 的位置
        for (int i = 1; i <= max; i++) {
            boolean canUse = true;
            if (canUse) {
                int put = dp1(use, index + 1, min, max);
                int unput = dp1(use, index + 1, min, max);
                way += use + unput;
            }
        }

        way += dp1(use, index + 1, min, max);
        return way;
    }


    // 简单版本
    // 先使用数组
    // 1 代表铺满了
    // 0 代表可以放置瓷砖
    public static int way2(int m, int n) {
        int max = m > n ? m : n;
        int min = max == m ? n : m;
        // 以 max 为列，min 为行
        int[] arr = new int[max];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;

        }
        return dp2(arr, 0, min);
    }

    private static int dp2(int[] arr, int index, int min) {
        if (index == min) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    return 0;
                }
            }
            return 1;
        }

        // 没到终止行
        // 可以在当前位置摆放瓷砖
        // 0 代表 从 0 列开始
        return dpMin2(getArr(arr), 0, index, min);
    }

    private static int dpMin2(int[] arr, int col, int index, int min) {
        if (col == arr.length) {
            // 此时列已经填写完毕
            // 轮到 行 + 1 去填写了
            return dp2(arr, index + 1, min);
        }

        // 如果可以的话 横着放一块
        int put = 0;
        if (col != arr.length - 1 && arr[col] == 0 && arr[col + 1] == 0) {
            // 横着放一块
            arr[col] = 1;
            arr[col + 1] = 1;
            put = dpMin2(arr, col + 2, index, min);
            arr[col] = 0;
            arr[col + 1] = 0;
        }

        // 不放
        int unput = dpMin2(arr, col + 1, index, min);

        return put + unput;
    }

    // arr 为 0 的地方必须放上瓷砖
    private static int[] getArr(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < ans.length; i++) {
            // arr 为 0 的地方必须放上瓷砖
            ans[i] = arr[i] ^ 1;
        }
        return ans;
    }


    // 状态压缩版本
    // 使用 0 1 表示是否使用位置
    // 1 代表铺满了
    // 0 代表可以放置瓷砖
    public static int way3(int m, int n) {
        int max = m > n ? m : n;
        int min = max == m ? n : m;
        // 以 max 为列，min 为行
        int arr = (1 << max) - 1;
        return dp3(arr, 0, min, max);
    }

    private static int dp3(int arr, int index, int min, int max) {
        if (index == min) {
            if (arr == (1 << max) - 1) {
                return 1;
            }
            return 0;
        }

        // 没到终止行
        // 可以在当前位置摆放瓷砖
        // 0 代表 从 0 列开始
        return dpMin3(arr ^ ((1 << max) - 1), 0, index, min, max);
    }

    private static int dpMin3(int arr, int col, int index, int min, int max) {
        if (col == max) {
            // 此时列已经填写完毕
            // 轮到 行 + 1 去填写了
            return dp3(arr, index + 1, min, max);
        }

        // 如果可以的话 横着放一块
        int put = 0;
        if (col != max - 1 && (arr & (1 << col)) == 0 && (arr & (1 << (col + 1))) == 0) {
            // 横着放一块
            // this ⚠️ 把 0 变成 1 应该是 ： arr ｜ (3 << col）
            // put = dpMin3((arr & (3 << col)), col + 2, index, min, max);
            put = dpMin3((arr | (3 << col)), col + 2, index, min, max);
        }

        // 不放
        int unput = dpMin3(arr, col + 1, index, min, max);

        return put + unput;
    }


}
