package cn.etaboooo.leetcode.demo;

import org.junit.jupiter.api.Test;

import cn.etaboooo.bean.ListNode;
import cn.etaboooo.bean.TreeNode;

/**
 * @author weiZhiLin
 * @date 2021/5/8 16:48
 * @version 1.0
 */
class Demo01Test {
    @Test
    void deleteNode() {
        ListNode listNode = ListNode.asList(new int[]{1, 2, 3, 4});
        Demo01.deleteNode(listNode.next.next);
        System.out.println(listNode);
    }

    @Test
    void maxDepth() {
        TreeNode treeNode = TreeNode.arrAsTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(Demo01.maxDepth(treeNode));
    }

    @Test
    void sortedArrayToBST() {
        TreeNode x = Demo01.sortedArrayToBST(new int[]{1,2,3,4,5});
        System.out.println(x);
    }
}