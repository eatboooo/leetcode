package cn.eatboooo.study.week05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/12/14 20:42
 * <p>
 * 补充题目
 * 用数组里的面值 组成 aim
 * 相同面值的钱视为一样的
 * 返回有多少种方法
 */
public class Demo10_Try_CoinsWaySameValueSamePapper {
    // key - 面值， value - 张数
    private static Map toMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }
    public static int coinsWayMe(int[] arr, int aim) {
        if (arr.length < 1) {
            return 0;
        }
        Map<Integer, Integer> map = toMap(arr);
        int[] ints = Arrays.stream(arr).distinct().toArray();
        return process(map, ints, 0, aim);
    }

    private static int process(Map<Integer, Integer> map, int[] arr, int i, int aim) {
        if (aim == 0) {
            return 1;
        }
        if (i == arr.length || aim < 0) {
            return 0;
        }
        int without = process(map, arr, i + 1, aim);
        for (int k = 1; k <= map.get(arr[i]); k++) {
            without += process(map, arr, i + 1, aim - (k * arr[i]));
        }
        return without;
    }

}
