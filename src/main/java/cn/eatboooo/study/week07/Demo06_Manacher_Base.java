package cn.eatboooo.study.week07;

/**
 * Manacher 算法实现
 * 解决回文字符串
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/16 15:12
 */
public class Demo06_Manacher_Base {
    // 一些关键元素
    // 当前最长回文的左侧下标与右侧下标： L - R；中心点是 C
    // 下一个字符是否在 L～R 范围中
    //  在范围中：则这个字符的 rArr【i】 = rArr【对称的i】
    //  在 R 上：rArr【i】= 1 他自己
    //  不在范围中： 暴力去扩充！

    // 返回最长回文字符串的长度
    public static int manacher(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        // 给每个字符的左右边加一个特殊字符，作为虚轴
        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // 回文半径大小
        int[] rArr = new int[str.length];
        // 中心点
        int c = -1;
        // 最右边界 + 1,与实质相差1
        int r = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            //  在范围中：则这个字符的 rArr【i】 = rArr【对称的i】
            //  在 R 上：rArr【i】= 1 他自己
            rArr[i] = r > i ? Math.min(rArr[2 * c - r], r - i) : 1;

            // 开始暴力扩充 r
            // 当 r 在范围内时一致扩容
            // rArr[i] + i ： 右侧的下标
            // i - rArr[i] ： 左侧的下标，即为以 i 为中心的对称
            while (rArr[i] + i < str.length && i - rArr[i] > -1) {
                if (str[i + rArr[i]] == str[i - rArr[i]])
                    rArr[i]++;
                else {
                    break;
                }
            }

            // 扩充的比 r 大了
            // r 代表最右边界，所以要改 r 和中心点 c 的值
            if (rArr[i] + i > r) {
                r = rArr[i] + i;
                c = i;
            }
            max = Math.max(max, rArr[i]);
        }
        // 添加了特殊字符，所以半径 - 1 即为原始回文的长度
        return max - 1;
    }

    private static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
}
