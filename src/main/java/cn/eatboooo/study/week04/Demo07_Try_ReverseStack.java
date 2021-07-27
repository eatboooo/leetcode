/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week04
 * @className cn.eatboooo.study.week04.Demo07_Try_ReverseStack
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week04;

import java.util.Stack;

/**
 * Demo07_Try_ReverseStack
 * @description
 * @author weiZhiLin
 * @date 2021/7/27 13:30
 * @version 1.0
 *
 * 好的尝试
 * 使用递归翻转 Stack
 */
public class Demo07_Try_ReverseStack {
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 抽出栈底元素
        Integer bottom = bottom(stack);
        reverse(stack);
        stack.push(bottom);
    }

    private static Integer bottom(Stack<Integer> stack) {
        Integer cur = stack.pop();
        if (stack.isEmpty()) {
            // 证明我是底部元素
            return cur;
        }
        // cur 不是底部元素

        // 此时返回肯定为底部元素，否则不会返回的
        Integer last = bottom(stack);
        // 把 cur 装进去，cur 不是底部元素，底部元素已经抽离出来了
        stack.push(cur);
        return last;
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}
