/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week03
 * @className cn.eatboooo.study.week03.Demo05_Greed_Light
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week03;

import java.util.HashSet;

/**
 * Demo05_Greed_Light
 * @description
 * @author weiZhiLin
 * @date 2021/7/21 13:29
 * @version 1.0
 *
 * .XXXX..X.X.
 * . 为道路，X 为墙
 * 一盏灯只能放到道路上，照亮左右
 * 全部照亮最少放多少灯
 */
public class Demo05_Greed_Light {
    public static int minLight2(String road) {
        int light = 0;
        char[] chars = road.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('X' == chars[i]) {
                continue;
            } else {
                light++;
                if (i + 1 == chars.length) {
                    break;
                }
                if ('X' == chars[i + 1]) {
                    // 下次循环跳到 i + 2 的位置
                    i = i + 1;
                } else {
                    // 下次循环跳到 i + 3 的位置
                    i = i + 2;
                }
            }
        }
        return light;
    }


    // copy for test
    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }
    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
