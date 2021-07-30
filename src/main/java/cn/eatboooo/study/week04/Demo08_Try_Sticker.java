package cn.eatboooo.study.week04;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/30 12:22
 * <p>
 * https://leetcode.com/problems/stickers-to-spell-word
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 */
public class Demo08_Try_Sticker {

    // 暴力递归，直接超时
    public static int minSticker(String str, String[] arr) {
        int ans = process1(str, arr);
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    private static int process1(String str, String[] arr) {
        if (str.length() == 0) {
            // 已经贴完了，成功了，没有需要贴的了，这里就是 0
            return 0;
        }
        // 还有需要贴的
        int min = Integer.MAX_VALUE;
        for (String s : arr) {
            String rest = strMinus(str, s);
            if (rest.length() != str.length()) {
                // 两个长度不一样，证明这个贴纸有用，说不定可以多次利用！
                min = Math.min(min, process1(rest, arr));
            }
        }
        // min 如果还等于最大值，说明上面就没有贴完过，就是 0
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String strMinus(String str, String s) {
        char[] c1 = str.toCharArray();
        char[] c2 = s.toCharArray();
        int[] count = new int[26];
        for (char c : c1) {
            count[c - 'a']++;
        }
        for (char c : c2) {
            count[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        return sb.toString();
    }


}
