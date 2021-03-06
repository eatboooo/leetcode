package cn.eatboooo.study.week08;

/**
 * Morris 遍历
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/20 20:57
 * <p>
 * 二叉树之前的遍历方式有空间浪费的问题
 * <p>
 * Morris遍历时间复杂度O(N)，额外空间复杂度O(1)，通过利用原树中大量空闲指针的方式，达到节省空间的目的
 * <p>
 * 假设来到当前节点cur，开始时cur来到头节点位置
 * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
 * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
 * a.如果mostRight的右指针指向空，让其指向cur，
 * 然后cur向左移动(cur = cur.left)
 * b.如果mostRight的右指针指向cur，让其指向null，
 * 然后cur向右移动(cur = cur.right)
 * 3）cur为空时遍历停止
 */
public class Demo01_Morris_Base {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }
    public static void process(Node root) {
        if (root == null) {
            return;
        }
        process(root.left);
        process(root.right);
        System.out.print(root + " ");
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node real = null;
        while (cur != null) {
            real = cur.left;
            if (real != null) {
                while (real.right != null && real.right != cur) {
                    real = real.right;
                }
                System.out.print(cur + " ");
                if (real.right == null) {
                    real.right = cur;
                    cur = cur.left;
                } else {
                    real.right = null;
                    cur = cur.right;
                }
                continue;
            }
            System.out.print(cur + " ");
            cur = cur.right;
        }
        System.out.println();
    }
    public static void preMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node real = null;
        while (cur != null) {
            real = cur.left;
            if (real != null) {
                while (real.right != null && real.right != cur) {
                    real = real.right;
                }
                if (real.right == null) {
                    System.out.print(cur + " ");
                    real.right = cur;
                    cur = cur.left;
                } else {
                    real.right = null;
                    cur = cur.right;
                }
                continue;
            }
            System.out.print(cur + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    public static void midMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node real = null;
        while (cur != null) {
            real = cur.left;
            if (real != null) {
                while (real.right != null && real.right != cur) {
                    real = real.right;
                }
                if (real.right == null) {
                    real.right = cur;
                    cur = cur.left;
                } else {
                    System.out.print(cur + " ");
                    real.right = null;
                    cur = cur.right;
                }
                continue;
            }
            System.out.print(cur + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    // test
    public static void main(String[] args) {
        System.out.println("Morris 遍历");
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.right.left = new Node(8);
        morris(head);
        // preMorris(head);
        System.out.println();
        process(head);
        System.out.println();
        midMorris(head);

    }
}
