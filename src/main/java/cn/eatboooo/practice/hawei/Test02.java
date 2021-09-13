package cn.eatboooo.practice.hawei;

import java.util.Scanner;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/12 21:32
 */
public class Test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int i = sc.nextInt();
            System.out.println(getArr("1", i, 0));
        }
    }

    private static String getArr(String str, int i, int index) {
        if (i <= index) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char[] split = str.toCharArray();
        int cur = 0;
        int next = 1;
        int num = 1;
        while (next <= split.length) {
            if (next != split.length && split[cur] == split[next]) {
                num++;
            } else {
                sb.append(num);
                sb.append(split[cur]);
                num = 1;
            }
            cur++;
            next++;
        }
        return getArr(sb.toString(), i, index + 1);
    }
}
