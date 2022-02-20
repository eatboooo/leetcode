package cn.eatboooo.study.week10;

/**
 * 动态规划中外部信息简化
 * 开心消消乐 困难版本
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/20 16:19
 * <p>
 * 如果一个字符相邻的位置没有相同字符，那么这个位置的字符出现不能被消掉
 * 比如:"ab"，其中a和b都不能被消掉
 * 如果一个字符相邻的位置有相同字符，就可以一起消掉
 * 比如:"abbbc"，中间一串的b是可以被消掉的，消除之后剩下"ac"
 * 某些字符如果消掉了，剩下的字符认为重新靠在一起
 * 给定一个字符串，你可以决定每一步消除的顺序，目标是请尽可能多的消掉字符，返回最少的剩余字符数量
 * 比如："aacca", 如果先消掉最左侧的"aa"，那么将剩下"cca"，然后把"cc"消掉，剩下的"a"将无法再消除，返回1
 * 但是如果先消掉中间的"cc"，那么将剩下"aaa"，最后都消掉就一个字符也不剩了，返回0，这才是最优解。
 * 再比如："baaccabb"，
 * 如果先消除最左侧的两个a，剩下"bccabb"，如果再消除最左侧的两个c，剩下"babb"，最后消除最右侧的两个b，剩下"ba"无法再消除，返回2
 * 而最优策略是：
 * 如果先消除中间的两个c，剩下"baaabb"，如果再消除中间的三个a，剩下"bbb"，最后消除三个b，不留下任何字符，返回0，这才是最优解
 */
public class Demo03_Try_RemoveBoxHard {
    public static int restMin1(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        return dp(0, s.length() - 1, false, s.toCharArray());
    }

    // b 代表 l - 1 位置的字符是否等于 l
    // l ~ r 顺带把 l - 1 算上，开心消消乐之后还能剩下几个
    private static int dp(int l, int r, boolean b, char[] arr) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            if (b) {
                return 0;
            }
            return 1;
        }

        // 找找连续的有多少个
        int index = l;
        int sum = b ? 1 : 0;
        while (index <= r && arr[index] == arr[l]) {
            index++;
            sum++;
        }
        // ans1 : 先把前面的全部消除
        int ans1 = (sum > 1 ? 0 : 1) + dp(index, r, false, arr);

        // ans2 : 前面的保留到 i 再消除
        int ans2 = Integer.MAX_VALUE;
        for (int i = index; i <= r; i++) {
            // arr[i] == arr[l] 是否相等
            // arr[i - 1] != arr[i] 是否为 index 之后第一个相等的位置
            if (arr[i] == arr[l] && arr[i - 1] != arr[i]) {
                // 如果 index ～ i -1 位置消不掉，呢中间就会有其他字符乱入
                // index - 1 也无法和 i 位置合并了！！
                if (dp(index, i - 1, false, arr) == 0) {
                    ans2 = Math.min(ans2, dp(i, r, sum != 0, arr));
                }
            }
        }

        return Math.min(ans1, ans2);
    }
}
