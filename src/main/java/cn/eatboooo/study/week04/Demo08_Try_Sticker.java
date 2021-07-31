package cn.eatboooo.study.week04;

import java.util.HashMap;

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


    public static int minSticker2(String target, String[] stickers) {
        char[] chars = target.toCharArray();
        // 贴纸词频表
        int[][] strArr = new int[stickers.length][27];
        for (int i = 0; i < stickers.length; i++) {
            char[] what = stickers[i].toCharArray();
            for (char c : what) {
                strArr[i][c - 'a']++;
            }
        }
        int ans = process2(strArr, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(int[][] strArr, String strrr) {
        if (strrr.length() == 0) {
            return 0;
        }
        // 统计 str 词频
        char[] chars = strrr.toCharArray();
        int[] restArr = new int[26];
        for (char aChar : chars) {
            restArr[aChar - 'a']++;
        }
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < strArr.length; i++) {
            // 词频对照，如果合适就相减
            if (strArr[i][chars[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (restArr[j] > 0) {
                        // 第一次相减写反了
                        for (int k = 0; k < restArr[j] - strArr[i][j]; k++) {
                            sb.append((char) ('a' + j));
                        }
                    }
                }
                min = Math.min(min, process2(strArr, sb.toString()));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minSticker3(String target, String[] arr) {
        if (target.length() == 0 || arr.length == 0) {
            return -1;
        }
        int[][] stickers = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            char[] chars = arr[i].toCharArray();
            for (char aChar : chars) {
                stickers[i][aChar - 'a']++;
            }
        }

        int ans = process3(target, stickers, new HashMap<String, Integer>());
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process3(String target, int[][] stickers, HashMap<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        if (target.length() == 0) {
            return 0;
        }
        char[] chars = target.toCharArray();
        int[] targets = new int[26];
        for (char aChar : chars) {
            targets[aChar - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            if (stickers[i][chars[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (targets[j] > 0) {
                        for (int k = 0; k < targets[j] - stickers[i][j]; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                min = Math.min(min, process3(sb.toString(), stickers, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return ans;
    }

    public static void main(String[] args) {

        /*
                ["notice","possible"]
        "basicbasic"*/
        int thehat = minSticker("thehat", new String[]{"with", "example", "science"});
        System.out.println("thehat = " + thehat);
        thehat = minSticker2("thehat", new String[]{"with", "example", "science"});
        System.out.println("thehat = " + thehat);
        thehat = minSticker3("thehat", new String[]{"with", "example", "science"});
        System.out.println("thehat = " + thehat);

    }
}
