package cn.eatboooo.study.week03;

import java.util.ArrayList;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/20 00:05
 * <p>
 * 最大搜索子树的头节点
 */
public class Demo05_BT_MaxSubBSTHead {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        int maxSize;
        int min;
        int max;
        Node maxBSTHead;

        public Info(int maxSize, int min, int max, Node maxBSTHead) {
            this.maxSize = maxSize;
            this.min = min;
            this.max = max;
            this.maxBSTHead = maxBSTHead;
        }
    }

    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process1(head).maxBSTHead;
    }

    private static Info process1(Node head) {
        if (head == null) {
            return null;
        }
        int min = head.value;
        int max = head.value;
        int maxSize = 0;
        Node maxBSTHead = null;
        Info left = process1(head.left);
        Info right = process1(head.right);
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
            maxSize = left.maxSize;
            maxBSTHead = left.maxBSTHead;
        }
        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
            if (left == null || right.maxSize > left.maxSize) {
                maxSize = right.maxSize;
                maxBSTHead = right.maxBSTHead;
            }
        }
        if (  // 搜索二叉树不需要等于
                // 括号要记得！！！
                (left == null ? (true) : (left.maxBSTHead == head.left && left.max < head.value))
                        &&
                      (right == null ? (true) : (right.maxBSTHead == head.right && right.min > head.value))
        ) {
            // 这里需要非空判断
            // size 不是高度！！！
//            maxSize = Math.max(left == null ? 0 : left.maxSize, right == null ? 0 : right.maxSize) + 1;
            maxSize = (left == null ? 0 : left.maxSize) + (right == null ? 0 : right.maxSize) + 1;
            maxBSTHead = head;
        }
        return new Info(maxSize, min, max, maxBSTHead);
    }


    // copy for test
    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
