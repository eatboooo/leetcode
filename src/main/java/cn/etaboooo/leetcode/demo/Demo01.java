/**
 * @projectName leetcode
 * @package cn.etaboooo.leetcode.demo
 * @className cn.etaboooo.leetcode.demo.Demo01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.etaboooo.leetcode.demo;


import cn.etaboooo.bean.ListNode;
import cn.etaboooo.bean.TreeNode;

/**
 * Demo01
 * @description LeetCode 精选 TOP 面试题
 * @author weiZhiLin
 * @date 2021/5/8 16:47
 * @version 1.0
 */
public class Demo01 {
    /**
     *
     * Demo01
     * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
     * @description 237. 删除链表中的节点
     * @return java.lang.String
     * @date 2021/5/8 17:10
     * @author weiZhiLin
     * @version 1.0
     */
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     *
     * Demo01
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * @description 104. 二叉树的最大深度
     * @param root
     * @return int
     * @date 2021/5/10 12:32
     * @author weiZhiLin
     * @version 1.0
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     *
     * Demo01
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
     * @description 108. 将有序数组转换为二叉搜索树
     * @param arr
     * @return cn.etaboooo.bean.TreeNode
     * @date 2021/5/10 16:38
     * @author weiZhiLin
     * @version 1.0
     */
    public static TreeNode sortedArrayToBST(int[] arr) {
        return helpSortedArrayToBST(arr, 0, arr.length - 1);
    }

    private static TreeNode helpSortedArrayToBST(int[] arr, int l, int r) {
        if (l > r) {
            return null;
        }
        int mind = l + ((r - l) >> 1);
        TreeNode treeNode = new TreeNode(arr[mind]);
        treeNode.left = helpSortedArrayToBST(arr, l, mind - 1);
        treeNode.right = helpSortedArrayToBST(arr, mind + 1, r);
        return treeNode;
    }

}
