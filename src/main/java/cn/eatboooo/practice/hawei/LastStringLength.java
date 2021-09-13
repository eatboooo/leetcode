package cn.eatboooo.practice.hawei;

import java.util.Scanner;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/12 19:00
 * HJ1 字符串最后一个单词的长度
 * https://www.nowcoder.com/practice/8c949ea5f36f422594b306a2300315da?tpId=37&&tqId=21224&rp=1&ru=/ta/huawei/&qru=/ta/huawei/question-ranking
 */
public class LastStringLength {
    public static int doIt(String string) {
        String substring = string.substring(string.lastIndexOf(" ") + 1, string.length());
        System.out.println("substring = " + substring);
        return substring.length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(doIt(str));
    }
}
