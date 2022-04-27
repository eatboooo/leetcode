package cn.eatboooo.crazy.week02;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/4/9 20:59
 * 给定一个数组arr，只能对arr中的一个子数组排序，
 * 但是想让arr整体都有序，返回满足这一设定的子数组中最短的是多长
 *
 * 思路：从左往右遍历，一个变量 max，如果 max 小于等于当前数字 画对好并且更新，否则画差号
 * 从右往左遍历，一个 min，右边 min 小于当前数字 画差号，否则对好 并且更新
 * 最左画差号和最右差号的长度，这就是答案
 *
 */
// 本题测试链接 : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class Code06 {
}
