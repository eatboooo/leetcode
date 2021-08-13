package cn.eatboooo.study.week02;

import java.util.ArrayList;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/17 00:34
 * <p>
 * 最大搜索子树的大小
 */
public class Demo04_BT_MaxBSTSize {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public int max;
        public int min;
        public int allSize;
        public int maxBst;

        public Info(int max, int min, int allSize, int maxBst) {
            this.max = max;
            this.min = min;
            this.allSize = allSize;
            this.maxBst = maxBst;
        }
    }

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBst;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int min = head.value;
        int max = head.value;
        int allSize = 1;
        int lMax = -1; // 左树最大搜索树大小
        int rMax = -1; // 右树最大搜索树大小
        int cMax = -1; // 当前树最大搜索树大小
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
            allSize += left.allSize;
            lMax = left.maxBst;
        }
        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
            allSize += right.allSize;
            rMax = right.maxBst;
        }
        boolean lBST = left == null ? true : (left.maxBst == left.allSize);
        boolean rBST = right == null ? true : (right.maxBst == right.allSize);
        if (lBST && rBST) {
            // 搜索二叉树和 没有键值相等的节点！
            boolean lMaxLessMin = left == null ? true : left.max < head.value;
            boolean rMinGreatMax = right == null ? true : right.min > head.value;
            if (lMaxLessMin & rMinGreatMax) {
                int lSize = left == null ? 0 : left.allSize;
                int rSize = right == null ? 0 : right.allSize;
                cMax = lSize + rSize + 1;
            }
        }
        return new Info(max, min, allSize, Math.max(cMax, Math.max(rMax, lMax)));
    }

    // 后面写的新方法
    private static Info process2(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process2(head.left);
        Info right = process2(head.right);
        boolean lisBST = left == null ? true : (left.allSize == left.maxBst);
        boolean risBST = right == null ? true : (right.allSize == right.maxBst);
        int cAllS = 1;
        int cMaxB = 1;
        int cMax = head.value;
        int cMin = head.value;
        int lMax = Integer.MIN_VALUE;
        int rMin = Integer.MAX_VALUE;
        int rMaxB = 0;
        int lMaxB = 0;
        if (left != null) {
            lMaxB = left.maxBst;
            lMax = left.max;
            cAllS += left.allSize;
            cMax = Math.max(cMax, left.max);
            cMin = Math.min(cMin, left.min);
        }
        if (right != null) {
            rMaxB = right.maxBst;
            rMin = right.min;
            cAllS += right.allSize;
            cMax = Math.max(cMax, right.max);
            cMin = Math.min(cMin, right.min);
        }
        if (lisBST && risBST) {
            if (head.value > lMax && head.value < rMin) {
                cMaxB = cAllS;
            }
        }

        return new Info(cMax, cMin, cAllS, Math.max(cMaxB, Math.max(lMaxB, rMaxB)));
    }

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

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
