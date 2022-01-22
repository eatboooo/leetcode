package cn.eatboooo.study.week08;

/**
 * 线段树 - 俄罗斯方块
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/22 17:54
 *
 * 想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
 * 下面是这个游戏的简化版：
 * 1）只会下落正方形积木
 * 2）[a,b] -> 代表一个边长为b的正方形积木，积木左边缘沿着X = a这条线从上方掉落
 * 3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
 * 4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。
 * 给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，
 * 返回每一次掉落之后的最大高度
 * Leetcode题目：https://leetcode.com/problems/falling-squares/
 */
public class Demo02_SegmentTree_Falling {
    // todo
}
