package cn.etaboooo.leetcode.demo;

import cn.etaboooo.bean.ListNode;

/**
 * https://leetcode-cn.com/problems/min-stack/
 * 155. 最小栈
 * 主要是有个 getMin ，所以每次 pop 完之后都要遍历一波
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/6/17 16:58
 */
public class Demo01_Stack {
    /**
     * initialize your data structure here.
     */
    ListNode listNode;
    ListNode preNode;
    ListNode minNode;
    ListNode head;
    int size;

    public Demo01_Stack() {
        this.size = 0;
    }

    public void push(int val) {
        ListNode node = new ListNode(val);
        if (size == 0) {
            this.minNode = node;
            this.listNode = node;
            this.preNode = node;
            this.head = node;
        } else {
            if (this.minNode.val > node.val) {
                minNode = node;
            }
            this.preNode = listNode;
            this.listNode = node;
            this.preNode.next = listNode;
        }
        size++;
    }

    public void pop() {
        size--;
        if (listNode.val == minNode.val) {
            removeLast();
            findMin();
            return;
        }
        removeLast();
    }

    private void removeLast() {
        ListNode temp = head;
        if (temp.next == null) {
            listNode = temp;
            temp.next = null;
            return;
        }
        while (temp.next.next != null) {
            temp = temp.next;
        }
        listNode = temp;
        temp.next = null;
    }

    private void findMin() {
        ListNode min = head;
        ListNode temp = head;
        while (temp != null) {
            if (min.val >= temp.val) {
                min = temp;
            }
            temp = temp.next;
        }
        minNode = min;
    }

    public int top() {
        return this.listNode.val;
    }

    public int getMin() {
        return this.minNode.val;
    }
}
