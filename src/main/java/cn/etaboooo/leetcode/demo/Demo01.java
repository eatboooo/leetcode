/**
 * @projectName leetcode
 * @package cn.etaboooo.leetcode.demo
 * @className cn.etaboooo.leetcode.demo.Demo01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.etaboooo.leetcode.demo;


import java.util.ArrayList;
import java.util.List;

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
        // 使用 (l+r)>>1 两个接近 int 最大值相加就会会报错
        int mind = l + ((r - l) >> 1);
        TreeNode treeNode = new TreeNode(arr[mind]);
        treeNode.left = helpSortedArrayToBST(arr, l, mind - 1);
        treeNode.right = helpSortedArrayToBST(arr, mind + 1, r);
        return treeNode;
    }


    /**
     *
     * Demo01
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * @description 94. 二叉树的中序遍历
     * @param root
     * @return java.util.List<java.lang.Integer>
     * @date 2021/5/21 14:21
     * @author weiZhiLin
     * @version 1.0
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> arrayList = new ArrayList();
        helpInorderTraversal(root, arrayList);
        return arrayList;
    }

    private static void helpInorderTraversal(TreeNode root, List<Integer> arrayList) {
        if (null == root) {
            return;
        }
        helpInorderTraversal(root.left, arrayList);
        arrayList.add(root.val);
        helpInorderTraversal(root.right, arrayList);
    }

    /**
     *
     * Demo01
     * https://leetcode-cn.com/problems/reverse-string/
     * @description 344. 翻转字符串
     * @param s
     * @date 2021/5/21 15:09
     * @author weiZhiLin
     * @version 1.0
     */
    public void reverseString(char[] s) {
        helpReverseString(s, 0, s.length - 1);
    }

    private void helpReverseString(char[] s, int l, int r) {
        if (l >= r) {
            return;
        }
        char temp = s[l];
        s[l] = s[r];
        s[r] = temp;
        helpReverseString(s, l + 1, r - 1);
    }

    /**
     *
     * Demo01
     * https://leetcode-cn.com/problems/number-of-1-bits/
     * @description 191. 位1的个数
     * @param n
     * @return int
     * @date 2021/5/21 15:48
     * @author weiZhiLin
     * @version 1.0
     */
    public int hammingWeight(int n) {
        int sum = 0;
        for (int i = 31; i >= 0; i--) {
            sum += ((n >> i) & 1);
        }
        return sum;
    }

    /**
     *
     * Demo01
     * https://leetcode-cn.com/problems/reverse-linked-list/
     * @description 206. 反转链表
     * @param head
     * @return cn.etaboooo.bean.ListNode
     * @date 2021/5/21 16:00
     * @author weiZhiLin
     * @version 1.0
     */
    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode listNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }

    /**
     * https://leetcode-cn.com/problems/single-number/
     * Demo01
     *
     * @description 136. 只出现一次的数字
     * @param nums
     * @return int
     * @date 2021/5/21 16:30
     * @author weiZhiLin
     * @version 1.0
     */
    public int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i == nums.length - 1) {
                return nums[i];
            }
            for (int j = 0; j < nums.length; j++) {
                if (i == j) {
                    continue;
                }
                if (nums[i] == nums[j]) {
                    num = nums[i];
                    break;
                }
                num = nums[j];
            }
            if (num != nums[i]) {
                return nums[i];
            }
        }
        return -1;
    }

    // 用 异或 解决
    // 其余每个元素均出现两次。 两个相同的数异或为0,0和任何数异或都为任何数
    public int singleNumber2(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }

    /**
     *
     * Demo01
     * https://leetcode-cn.com/problems/pascals-triangle/
     * @description 118. 杨辉三角
     * @param numRows
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @date 2021/5/21 17:34
     * @author weiZhiLin
     * @version 1.0
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int k = 0; k <= i; k++) {
                if (k == 0 || i == k) {
                    row.add(1);
                }else{
                    row.add(list.get(i - 1).get(k - 1) + list.get(i - 1).get(k));
                }
                list.add(row);
            }
        }
        return list;
    }
}
