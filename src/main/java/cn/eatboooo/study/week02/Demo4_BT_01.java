/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week02
 * @className cn.eatboooo.study.week02.Demo4_BT_01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week02;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Demo4_BT_01
 * @description
 * @author weiZhiLin
 * @date 2021/7/16 10:32
 * @version 1.0
 * 按层打印二叉树
 * 二叉树最大宽度
 */
public class Demo4_BT_01 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 借助队列，先进先出，实现按层打印
    public static void level(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }

    // 二叉树最大宽度
    // 借助 map，思路类似于按层打印
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        int curWidth = 0; // 在循环中，当前层的宽度
        int maxWidth = 0; // 最大宽度，返回值
        int level = 1; // 遍历到第几层了
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> map = new HashMap<>();
        queue.add(head);
        map.put(head, level);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int curLevel = map.get(poll);
            if (poll.left != null) {
                queue.add(poll.left);
                map.put(poll.left, curLevel + 1);
            }
            if (poll.right != null) {
                queue.add(poll.right);
                map.put(poll.right, curLevel + 1);
            }
            if (curLevel == level) {
                curWidth++;
            } else {
                // 此时位于下一层的第一个节点
                // curWidth++; 因为已经是下一层了
                maxWidth = Math.max(curWidth, maxWidth);
                curWidth = 1; // 要算上现在这个节点，所以是 1
                level++;
            }
        }
        ///!!!!!!
        maxWidth = Math.max(maxWidth, curWidth);
        return maxWidth;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curLeft = head;
        Node nextLeft = null;
        int curWidth = 0;
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            curWidth++;
            if (poll.left != null) {
                queue.add(poll.left);
                nextLeft = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nextLeft = poll.right;
            }
            if (poll == curLeft) {
                curLeft = nextLeft;
                maxWidth = Math.max(maxWidth, curWidth);
                curWidth = 0;
            }
        }
        return maxWidth;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

}
