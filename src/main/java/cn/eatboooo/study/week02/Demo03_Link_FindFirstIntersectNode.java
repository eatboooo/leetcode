/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week02
 * @className cn.eatboooo.study.week02.Demo03_Link_FindFirstIntersectNode
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week02;

/**
 * Demo03_Link_FindFirstIntersectNode
 * @description
 * @author weiZhiLin
 * @date 2021/7/14 11:17
 * @version 1.0
 * 返回链表相交的第一个节点
 */
public class Demo03_Link_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 先判断他们是不是有环的，如果有环则拿到入环节点
        Node loop1 = getLoop(head1);
        Node loop2 = getLoop(head2);

        // 两个都是无环的情况
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        // 两个都是有环的情况
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, head2, loop1, loop2);
        }

        // 两个一个有环，一个无环，不可能香蕉
        return null;
    }

    private static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        if (loop1 == loop2) {
            // 此时必香蕉了
            // 长度
            int l = 0;
            Node h1 = head1, h2 = head2;
            while (h1 != loop1) {
                l++;
                h1 = h1.next;
            }
            while (h2 != loop2) {
                l--;
                h2 = h2.next;
            }
            // 选出长连表
            h1 = l > 0 ? head1 : head2;
            h2 = l > 0 ? head2 : head1;
            l = Math.abs(l);
            while (--l >= 0) {
                h1 = h1.next;
            }
            // 找到香蕉
            while (h1 != h2) {
                h1 = h1.next;
                h2 = h2.next;
            }
            return h1;
        }else {
            // 否则让 loop1 在环上巡逻一圈，看看能不能找到 loop2
            // 能到找则证明香蕉，返回任意一个
            Node loop = loop1.next;
            while (loop != loop1) {
                if (loop == loop2) {
                    return loop1;
                }
                loop = loop.next;
            }
            // 此时说明不是一个环了
            return null;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        // 判空任何时候不能忘
        if (head1 == null || head1.next == null || head1.next.next == null) {
            return null;
        }
        // 长度
        int l = 0;
        Node h1 = head1, h2 = head2;
        // 这里是 next，否则之后的判断有问题
        while (h1.next != null) {
            l++;
            h1 = h1.next;
        }
        while (h2.next != null) {
            l--;
            h2 = h2.next;
        }
        // 此时 l 为长度差值
        // 最后一个节点都不想等，他们必不香蕉
        if (h2 != h1) {
            return null;
        }

        // 此时必香蕉了
        // 选出长连表
        h1 = l > 0 ? head1 : head2;
        h2 = l > 0 ? head2 : head1;
        l = Math.abs(l);
        // 这里是大于等于！
        while (--l >= 0) {
            h1 = h1.next;
        }

        // 找到香蕉
        while (h1 != h2) {
            h1 = h1.next;
            h2 = h2.next;
        }

        return h1;
    }

    private static Node getLoop(Node head1) {
        if (head1 == null || head1.next == null || head1.next.next == null) {
            return null;
        }
        Node q = head1.next.next;
        Node s = head1.next;
        // 在循环中判断是否为空，以此推算是否为环
        while (q != s) {
            if (q.next == null || q.next.next == null) {
                return null;
            }
            q = q.next.next;
            s = s.next;
        }
        // s q 第一次相遇

        // 两个节点，选择一个回到头，一步一步走，下一次相遇即为入环节点（奥数）
        q = head1;
        while (q != s) {
            q = q.next;
            s = s.next;
        }
        return q;
    }
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
