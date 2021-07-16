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
 *
 * 按层打印二叉树
 * 二叉树最大宽度
 * 给你二叉树中的某个节点，返回该节点的后继节点 （后继节点是中序便利的后一个）
 * 折纸凹凸问题
 */
public class Demo04_BT_01 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        // 这个给返回后继节点的题目使用
        public Node parent;

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
        ///!!!!!! 这步不要忘记，循环执行完时还没有比较最大值
        maxWidth = Math.max(maxWidth, curWidth);
        return maxWidth;
    }

    // 借助两个节点，分别是当前层的最右，下一层的最右
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

    // 给你二叉树中的某个节点，返回该节点的后继节点 （后继节点是中序便利的后一个）
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        // 右子树不为空，则右子树的最左节点即为后继节点
        if (node.right != null) {
            return mostLeft(node.right);
        }else{
            // 此时 node 右子树为空，只能向上寻找
            Node parent = node.parent;
            // 不停寻找，直到找到 一个相对 parent 的左侧节点
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            // 此时 parent 在 node 右上方（即为后继节点），或者 parent 为空
            return parent;
        }
    }

    private static Node mostLeft(Node right) {
        if (right == null) {
            return null;
        }
        while (right != null) {
            right = right.left;
        }
        return null;
    }

    // 折纸凹凸问题
    // 想像成二叉树问题，中序遍历
    public static void  printAllFolds(int N) {
        // 第一个节点，总共有 N 层，打印左节点
        printProcess(1, N, true);
    }

    private static void printProcess(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        printProcess(i + 1, n, true);
        System.out.print(down ? "凹 " : "凸 ");
        printProcess(i + 1, n, false);
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
        printAllFolds(4);
        testMaxWidth();
    }

    private static void testMaxWidth() {
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
