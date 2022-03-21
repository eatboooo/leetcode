package cn.eatboooo.crazy.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/18 20:33
 *
 * 给定一个数组arr，你可以在每个数字之前决定+或者-但是必须所有数字都参与，再给定一个数target
 * 请问最后算出target的方法数
 *
 * 思路：暴力 -》缓存
 * 最优：
 * 1、target > sum ->gg
 * 2、target 与 sum 奇偶性
 * 3、两个集合， p、n，P 代表正、N 代表负数
 * 有 P - N = target
 * 所以有 2P = target + N
 * 所以 P = （target +N）/2
 * 所以就只是求 数组搞成 P 的背包问题
 */
public class Code07 {
}
