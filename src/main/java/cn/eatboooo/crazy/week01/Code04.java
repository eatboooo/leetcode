package cn.eatboooo.crazy.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/17 21:40
 * <p>
 * 一个数组中只有两种字符'G'和'B'，可以让所有的G都放在左侧，
 * 所有的B都放在右侧
 * 或者可以让所有的G都放在右侧，所有的B都放在左侧，
 * 但是只能在相邻字符之间进行交换操作，返回至少需要交换几次
 * <p>
 * 思路：两个指针，一个代表 G 未来的位置，一个用来遍历
 * 再来 两个指针，一个代表 B 未来的位置，一个用来遍历
 * 取最小的
 */
public class Code04 {
    public static int minSteps1(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int bi = 0;
        int gi = 0;
        int gswap = 0;
        int bswap = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'G') {
                gswap += i - gi++;
            }else {
                bswap += i - bi++;
            }
        }
        return Math.min(gswap, bswap);
    }
}
