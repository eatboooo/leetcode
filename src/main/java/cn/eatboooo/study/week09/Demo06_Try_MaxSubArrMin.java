package cn.eatboooo.study.week09;

/**
 * 四边形优化
 * 基础题目 思考很重要
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/14 14:39
 * <p>
 * 给定一个非负数组arr，长度为N，
 * 那么有N-1种方案可以把arr切成左右两部分
 * 每一种方案都有，min{左部分累加和，右部分累加和}
 * 求这么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
 * 整个过程要求时间复杂度O(N)
 */
public class Demo06_Try_MaxSubArrMin {

    // 每个点都当一次分割点，记录结果
    // 求最大的
    public static int max1(int[] arr) {
        int allSum = 0;
        for (int i : arr) {
            allSum += i;
        }
        int ans = 0;
        int sum = 0;
        for (int j : arr) {
            sum += j;
            ans = Math.max(ans, Math.min(sum, allSum - sum));
        }
        return ans;
    }
}
