package cn.eatboooo.practice.hawei;

import java.util.*;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/12 19:31
 * https://www.nowcoder.com/practice/3245215fffb84b7b81285493eae92ff0?tpId=37&tags=&title=&difficulty=0&judgeStatus=0&rp=1
 * HJ3 明明的随机数
 */
public class MingRandom {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = 0;
        while (in.hasNext()) {
            if (N == 0) {
                N = in.nextInt();
                Set<Integer> set = new TreeSet<>();
                while (N > 0) {
                    set.add(in.nextInt());
                    N--;
                }
                set.forEach(System.out::println);
            }
        }
    }
}
