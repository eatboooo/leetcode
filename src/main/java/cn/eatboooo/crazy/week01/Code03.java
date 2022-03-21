package cn.eatboooo.crazy.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/17 21:33
 * <p>
 * 给定一个非负整数num，如何不用循环语句，
 * 返回>=num，并且离num最近的，2的某次方
 * <p>
 * 思路：最高位往后全部变成 1
 * 最后 +1 拿下
 */
public class Code03 {
    public static final int tableSizeFor(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }
}
