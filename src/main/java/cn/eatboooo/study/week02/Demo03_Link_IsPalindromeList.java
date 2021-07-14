package cn.eatboooo.study.week02;

import java.util.Stack;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/12 01:24
 * 链表问题
 * 快慢指针、
 * 是否回文链表、
 */
public class Demo03_Link_IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 是否回文
    // stack
    public static boolean isPalindrome1(Node head) {
        // 下面要用到快慢指针，需要先判断，防止空指针异常
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node headT = head;
        // 把原先 node 全部压栈
        while (headT != null) {
            stack.push(headT);
            headT = headT.next;
        }
        // 弹栈，然后从头开始比较
        while (head != null) {
            // 这里比较的是 value
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 1/2 stack,need n/2 extra space
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 先找出中点的后一个
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        // 从中点的后一个 push 进栈
        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        // 注意此时的判断，应该为栈是否为空
        while (!stack.isEmpty()) {
            // 这里比较的是 value
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // reverse 1/2 link
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 慢
        Node s = head;
        // 快
        Node q = head;
        //  1 -> 2 -> 3 -> 4 -> 5
        while (q.next != null && q.next.next != null) {
            s = s.next;
            q = q.next.next;
        }
        // 循环结束后 s 在 3 的位置

        // s 作为前一个节点，q 作为当前节点 ，new 一个 作为下一个节点
        // 这个注意顺序！！
        q = s.next;
        s.next = null;
        Node next = null;
        while (q != null) {
            next = q.next;
            q.next = s;
            s = q;
            q = next;
        }
        // 翻转完成 ： 1 -> 2 -> 3 <- 4 <- 5
        // 此时 s 在 5 的位置上
        // 保存最后一个节点
        next = s;

        boolean res = true;
        q = head;
        while (s != null && q != null) {
            if (s.value != q.value) {
                res = false;
            }
            s = s.next;
            q = q.next;
        }

        // 复原被翻转的连表 , next 作为下一个
        s = null; // 前一个
        q = next; // 当前的
        while (q != null) {
            next = q.next;
            q.next = s;
            s = q;
            q = next;
        }
        return res;
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

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }

}
