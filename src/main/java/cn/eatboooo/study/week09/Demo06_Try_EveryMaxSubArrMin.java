package cn.eatboooo.study.week09;

/**
 * 四边形预备
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/14 15:52
 * <p>
 * 把题目一中提到的，min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：
 * S(N-1)：在arr[0…N-1]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 * 现在要求返回一个长度为N的s数组，
 * s[i] =在arr[0…i]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 * 得到整个s数组的过程，做到时间复杂度O(N)
 */
public class Demo06_Try_EveryMaxSubArrMin {
    public static int[] bestSplit3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int[] ans = new int[arr.length];
        int[] sum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        int split = 0;
        for (int boundary = 1; boundary < arr.length; boundary++) {
            // y +1 ？ 如果不加 1 最后有一个操作是 split ++，会导致切分点在边界
            while (split + 1 < boundary) {
                // 思考的点是 split 能否向右移动
                // 而不是 split 向右 和 当前位置当 split 如何选择

                // cur
                int p1 = Math.min(mysum(sum, 0, split), mysum(sum, split + 1, boundary));
                // after
                int p2 = Math.min(mysum(sum, 0, split + 1), mysum(sum, split + 2, boundary));
                if (p2 >= p1) {
                    split++;
                } else {
                    break;
                }
            }
            ans[boundary] = Math.min(mysum(sum, 0, split), mysum(sum, split + 1, boundary));
        }
        return ans;
    }

    public static int mysum(int[] arr, int l, int r) {
        return arr[r + 1] - arr[l];
    }

}
