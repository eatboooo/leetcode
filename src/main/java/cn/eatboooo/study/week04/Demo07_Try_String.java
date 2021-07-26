package cn.eatboooo.study.week04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/24 20:01
 * <p>
 * 暴力尝试
 * 打印一个字符串的全部子序列
 * <p>
 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 * <p>
 * 打印一个字符串的全部排列
 * <p>
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class Demo07_Try_String {
    // 打印一个字符串的全部子序列
    public static List<String> printAllStr1(String str) {
        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process1(chars, 0, ans, "");
        return ans;
    }

    // 没有重复的
    public static List<String> printAllStr2(String str) {
        char[] chars = str.toCharArray();
        Set<String> ans = new HashSet<>();
        process2(chars, 0, ans, "");
        List<String> ans2 = new ArrayList<>();
        for (String an : ans) {
            ans2.add(an);
        }
        return ans2;
    }

    private static void process1(char[] chars, int i, List<String> ans, String s) {
        if (i == chars.length) {
            ans.add(s);
            return;
        }
        process1(chars, i + 1, ans, s);
        process1(chars, i + 1, ans, s + chars[i]);
    }

    private static void process2(char[] chars, int i, Set<String> ans, String s) {
        if (i == chars.length) {
            ans.add(s);
            return;
        }
        process2(chars, i + 1, ans, s);
        process2(chars, i + 1, ans, s + chars[i]);
    }

    public static void swap(int x, int y, char[] chars) {
        char aChar = chars[x];
        chars[x] = chars[y];
        chars[y] = aChar;
    }

    // 打印一个字符串的全部排列
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        // rest - 其余
        List<Character> rest = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            rest.add(aChar);
        }
        process3(ans, rest, "");
        return ans;
    }

    private static void process3(List<String> ans, List<Character> rest, String path) {
        if (rest.size() == 0) {
            ans.add(path);  // 返回拼接答案
        }
        int N = rest.size();
        for (int i = 0; i < N; i++) {
            Character character = rest.get(i);
            // 这个位置的字符被用过了，所以以后不应该出现了
            rest.remove(i);
            // 这个是打印全部排列，所以字符串必须加
            process3(ans, rest, path + character);
            // 恢复现场
            rest.add(i,character);
        }

    }

    // 数组实现
    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] rest = s.toCharArray();
        process4(0, rest,ans);
        return ans;
    }

    private static void process4(int index, char[] ans, List<String> s) {
        if (index == ans.length) {
            s.add(String.valueOf(ans));
        }
        for (int i = 0; i < ans.length; i++) {
            // 字符串字母的排列，本质上是 char【】数组间的位置交换
            swap(i, index, ans);
            process4(index + 1, ans, s);
            swap(i, index, ans);
        }
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = printAllStr1(test);
//        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
       /* for (String str : ans2) {
            System.out.println(str);
        }*/
        System.out.println("=================");

    }
}
