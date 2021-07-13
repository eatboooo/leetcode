/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week02
 * @className cn.eatboooo.study.week02.Demo03_Link_SmallerEqualBigger
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week02;

/**
 * Demo03_Link_SmallerEqualBigger
 * @description
 * @author weiZhiLin
 * @date 2021/7/13 17:56
 * @version 1.0
 * 链表根据值分割左边小中间相等右边大
 */
public class Demo03_Link_SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 拆分成三条链，分别是小于等于大于，最后再连接起来
    public static Node listPartition2(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        // less 小于区
        Node lH = null;
        Node lT = null;
        // equal 等于区
        Node eH = null;
        Node gT = null;
        // great 大于区
        Node gH = null;
        Node eT = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (lH == null) {
                    // 连表为空的时候，head 为头 为尾
                    lH = head;
                    lT = head;
                } else {
                    // 把 head 添加到尾部
                    lT.next = head; // 尾部的下一个为 head
                    lT = head; // 尾部变成了 head
                }
            } else if (head.value > pivot) {
                if (gH == null) {
                    // 连表为空的时候，head 为头 为尾
                    gH = head;
                    gT = head;
                } else {
                    // 把 head 添加到尾部
                    gT.next = head; // 尾部的下一个为 head
                    gT = head; // 尾部变成了 head
                }
            } else {
                if (eH == null) {
                    // 连表为空的时候，head 为头 为尾
                    eH = head;
                    eT = head;
                } else {
                    // 把 head 添加到尾部
                    eT.next = head; // 尾部的下一个为 head
                    eT = head; // 尾部变成了 head
                }
            }
            head = next;
        }
        if (lH != null) {
            lT.next = eH;
            // 这步不要忘记，会存在等于区为空的情况
            eT = eT == null ? lT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        if (eH != null) {
            eT.next = gH;
        }
        return lH == null ? (eH == null ? gH : eH) : lH;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
