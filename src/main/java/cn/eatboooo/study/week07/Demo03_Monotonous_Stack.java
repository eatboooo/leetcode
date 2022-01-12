package cn.eatboooo.study.week07;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 单调栈
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/11 10:52
 * <p>
 * 返回数组中，每个下标，左侧比他小的～右侧比他小的 （木桶原理）
 * eg:
 * {3,6,1,8,9}
 * 0 1 2 3 4
 * return:
 * [
 * 0:[-1, 3]
 * 1:[0,  2]
 * 2:[-1,-1]
 * 3:[2, -1]
 * 4:[3, -1]
 * ]
 */
public class Demo03_Monotonous_Stack {
    // 不考虑重复的情况下
    public static int[][] monotonous(int[] arr) {
        // 存下标
        Stack<Integer> stack = new Stack<>();
        int[][] result = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            // 木桶，最小的值应该影响最广（存活时间最长）
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                // 所以弹出的都是大的值
                Integer pop = stack.pop();
                result[pop][0] = stack.isEmpty() ? -1 : stack.peek();
                result[pop][1] = i;
            }
            stack.push(i);
        }
        // 最后处理单调栈里剩余的值
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            result[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            result[pop][1] = -1;
        }
        return result;
    }

    public static int[][] monotonousRepeat(int[] arr) {
        Stack<LinkedList<Integer>> stack = new Stack<>();
        int[][] result = new int[arr.length][2];
        // 加
        for (int i = 0; i < arr.length; i++) {
            int i1 = arr[i];
            // 弹出小的
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > i1) {
                LinkedList<Integer> pop = stack.pop();
                for (Integer integer : pop) {
                    // ⚠️ 注意，是获取最后面的，因为list 存的是下标，不是值，stack.peek().getLast()
                    result[integer][0] = !stack.isEmpty() ? stack.peek().getLast() : -1;
                    result[integer][1] = i;
                }
            }
            // 加入
            if (!stack.isEmpty() && arr[stack.peek().getLast()] == i1) {
                // 重复的跟在连表后面
                stack.peek().add(i);
            } else {
                LinkedList<Integer> list = new LinkedList<>();
                list.addLast(i);
                stack.push(list);
            }
        }

        // 加完之后处理剩余的
        while (!stack.isEmpty()) {
            LinkedList<Integer> pop = stack.pop();
            for (Integer integer : pop) {
                result[integer][0] = !stack.isEmpty() ? stack.peek().getLast() : -1;
                result[integer][1] = -1;
            }
        }
        return result;
    }
}
