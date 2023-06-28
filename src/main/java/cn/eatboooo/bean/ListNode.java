package cn.eatboooo.bean;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2019/11/11 16:57
 */

public class ListNode {

    public ListNode next;
    public Integer val;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int... x) {
        ListNode head = new ListNode(x[0]);
        ListNode cpNext = head;
        for (int i = 1; i < x.length; i++) {
            cpNext.next = new ListNode(x[i]);
            cpNext = cpNext.next;
        }
        this.val = head.val;
        this.next = head.next;
    }

    /**
     * ListNode
     *
     * @param x
     * @return cn.eatboooo.bean.ListNode
     * @description 把数组转化为 list
     * @date 2021/5/8 17:51
     * @author weiZhiLin
     * @version 1.0
     */
    public static ListNode asList(int[] x) {
        ListNode list = new ListNode();
        for (int i = x.length - 1; i >= 0; i--) {
            list = setNext(list, x, i);
        }
        return list;
    }

    /**
     * ListNode
     *
     * @param list
     * @param x
     * @param index
     * @return cn.eatboooo.bean.ListNode
     * @description 设置下一个节点，用于有参构造
     * @date 2021/5/8 17:50
     * @author weiZhiLin
     * @version 1.0
     */
    private static ListNode setNext(ListNode list, int[] x, int index) {
        ListNode listNode = new ListNode(x[index]);
        if (null == list.val) {
            return listNode;
        }
        listNode.next = list;
        return listNode;
    }

    @Override
    public String toString() {
        ListNode cpNext = this;
        String s = "";
        while (cpNext != null) {
            s += cpNext.val + ",";
            cpNext = cpNext.next;
        }
        return s;
    }

}
