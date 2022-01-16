package cn.eatboooo.study.week07;

/**
 * KMP 算法实现
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/16 13:29
 * <p>
 * 关键：
 * nextArr：记录下一个要跳到的地方（下标之前，前缀和后缀相等的情况）
 * 如何计算？ 0位置一定是-1，1位置一定是0
 * i 位置的信息能不能由之前的 next 信息加速得到？
 */
public class Demo05_KMP_Base {
    public static int kmp(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] nextArr = getNextArr(c2);
        int s1Index = 0;
        int s2Index = 0;
        while (s1Index < c1.length && s2Index < c2.length) {
            if (c1[s1Index] == c2[s2Index]) {
                // 此时相等，一起往后移动
                s1Index++;
                s2Index++;
            } else if (nextArr[s2Index] == -1) {
                // 此时 s2Index 归零了才会等于 -1
                // 说明这个 s1 是真的不合适，下一个
                s1Index++;
            } else {
                // s2Index 没有归零，还可以优化求解速度
                s2Index = nextArr[s2Index];
            }
        }
        return s2Index == c2.length ? s1Index - s2Index : -1;
    }

    public static int[] getNextArr(char[] str) {
        // 存的是下标
        int[] nextArr = new int[str.length];
        // 0位置一定是-1，1位置一定是0，所以直接从2位置开始
        int index = 2;
        // 和 index 比较的位置
        int compare = 0;
        while (index < str.length) {
            // 此时建议手动画图分析
            if (str[index] == str[compare]) {
                // 新来的与之前的中心的相等，可以继承之前的
                nextArr[index++] = ++compare;
            } else if (compare > 0) {
                // 不相等，但是中心位置可以加速
                compare = nextArr[compare];
            } else {
                // 中心位置已经到了 0 位置，并且0与他都不相等，说明无缘
                nextArr[index++] = 0;
            }
        }
        return nextArr;
    }
}
