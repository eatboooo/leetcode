package cn.eatboooo.study.week02;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/16 23:19
 *
 * 二叉树递归套路
 * 1）假设以X节点为头，假设可以向X左树和X右树要任何信息
 * 2）在上一步的假设下，讨论以X为头节点的树，得到答案的可能性（最重要）
 * 3）列出所有可能性后，确定到底需要向左树和右树要什么样的信息
 * 4）把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
 * 5）递归函数都返回S，每一棵子树都这么要求
 * 6）写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
 */
public class Demo04_BT_02_IsBalance {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    // 是否平衡二叉树
    public static boolean isBalanced2(Node head) {
        return process(head).isBalanced;
    }

    // 套路实现
    private static Info process(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean balance = true;
        if (!left.isBalanced | !right.isBalanced) {
            balance = false;
        }
        if (Math.abs(left.height - right.height) > 1) {
            balance = false;
        }
        return new Info(balance, height);
    }

    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }




    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
