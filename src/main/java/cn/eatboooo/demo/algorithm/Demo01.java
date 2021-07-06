package cn.eatboooo.demo.algorithm;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2019/11/11 16:29
 */

/*
    109. 有序链表转换二叉搜索树
    给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
    本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     */
public class Demo01 {
    /*public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode p = head.next, q = head.next.next, pre = head;
        while (q != null && q.next != null) {
            pre = pre.next;
            p = p.next;
            q = q.next.next;
        }
        // p 一次前进一格，q 前进两格，pre 是 p 前一个
        //当 q 走到头的时候，q 在 链表的中间节点


        pre.next = null;
        // java 中都是值传递，该操作不会影响 p 的值
        // 链表被分成三段，再递归构造
        TreeNode root = new TreeNode(p.val);

        root.left = sortedListToBST(head);

        root.right = sortedListToBST(p.next);

        return root;
    }*/

}
