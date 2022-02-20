package cn.eatboooo.study.week10;

/**
 * 动态规划中外部信息简化
 * 恢复神秘代码
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/20 22:37
 *
 * 整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是<=200的正数且满足如下条件：
 * 1. 0位置的要求：arr[0]<=arr[1]
 * 2. n-1位置的要求：arr[n-1]<=arr[n-2]
 * 3. 中间i位置的要求：arr[i]<=max(arr[i-1],arr[i+1])
 * 但是在arr有些数字丢失了，比如k位置的数字之前是正数，丢失之后k位置的数字为0
 * 请你根据上述条件，计算可能有多少种不同的arr可以满足以上条件
 * 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1，即[6,9,9]达标
 */
public class Demo03_Try_RestoreWays {
    // s==0，代表arr[i] < arr[i+1] 右大
    // s==1，代表arr[i] == arr[i+1] 右=当前
    // s==2，代表arr[i] > arr[i+1] 右小
    public static int ways0(int[] arr) {
        int N = arr.length;
        if (arr[N - 1] != 0) {
            // 为什么状态是 2 ？
            // 根据题意，当边界状态为 2 时，其他位置才可以作出合理判断
            return process1(arr, N - 1, arr[N - 1], 2);
        }
        int ways = 0;
        for (int i = 1; i <= 200; i++) {
            ways += process1(arr, N - 1, i, 2);
        }
        return ways;
    }

    // 如果i位置的数字变成了v,
    // 并且arr[i]和arr[i+1]的关系为s，
    // s==0，代表arr[i] < arr[i+1] 右大
    // s==1，代表arr[i] == arr[i+1] 右=当前
    // s==2，代表arr[i] > arr[i+1] 右小
    // 返回0...i范围上有多少种有效的转化方式
    private static int process1(int[] arr, int index, int v, int status) {
        if (index == 0) {
            // status ==0 || status==1 代表 [0] <= [1]
            // (arr[0] == 0 || arr[0] == v) 代表 0 位置能不能填写，或者 0 位置填写的对不对
            return ((status == 0 || status == 1) && (arr[index] == 0 || arr[index] == v)) ? 1 : 0;
        }
        if (arr[index] != 0 && arr[index] != v) {
            return 0;
        }
        if (status == 0 || status == 1) {
            // i 已经是 <= i+1 位置的值了
            // 所以 i - 1 就可以在 1～200 肆无忌惮
            int ways = 0;
            for (int i = 1; i <= 200; i++) {
                ways += process1(arr, index - 1, i, i < v ? 0 : (i == v ? 1 : 2));
            }
            return ways;
        }
        // i > i+1 位置的值
        // 所以 i - 1 必须要大于等于 i 才可以
        int ways = 0;
        for (int i = v; i <= 200; i++) {
            ways += process1(arr, index - 1, i, i == v ? 1 : 2);
        }
        return ways;
    }
}
