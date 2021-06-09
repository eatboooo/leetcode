/**
 * @projectName leetcode
 * @package cn.etaboooo.leetcode.demo
 * @className cn.etaboooo.leetcode.demo.Demo01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.etaboooo.leetcode.demo;


import cn.etaboooo.bean.ListNode;
import cn.etaboooo.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo01
 *
 * @author weiZhiLin
 * @version 1.0
 * @description LeetCode 精选 TOP 面试题
 * @date 2021/5/8 16:47
 */
public class Demo01 {
    /**
     * Demo01
     * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
     *
     * @return java.lang.String
     * @description 237. 删除链表中的节点
     * @date 2021/5/8 17:10
     * @author weiZhiLin
     * @version 1.0
     */
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * Demo01
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     *
     * @param root
     * @return int
     * @description 104. 二叉树的最大深度
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
     * Demo01
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
     *
     * @param arr
     * @return cn.etaboooo.bean.TreeNode
     * @description 108. 将有序数组转换为二叉搜索树
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
     * Demo01
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     *
     * @param root
     * @return java.util.List<java.lang.Integer>
     * @description 94. 二叉树的中序遍历
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
     * Demo01
     * https://leetcode-cn.com/problems/reverse-string/
     *
     * @param s
     * @description 344. 翻转字符串
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
     * Demo01
     * https://leetcode-cn.com/problems/number-of-1-bits/
     *
     * @param n
     * @return int
     * @description 191. 位1的个数
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
     * Demo01
     * https://leetcode-cn.com/problems/reverse-linked-list/
     *
     * @param head
     * @return cn.etaboooo.bean.ListNode
     * @description 206. 反转链表
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
     * @param nums
     * @return int
     * @description 136. 只出现一次的数字
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
     * Demo01
     * https://leetcode-cn.com/problems/pascals-triangle/
     *
     * @param numRows
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @description 118. 杨辉三角
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
                } else {
                    row.add(list.get(i - 1).get(k - 1) + list.get(i - 1).get(k));
                }
                list.add(row);
            }
        }
        return list;
    }


    /**
     * https://leetcode-cn.com/problems/excel-sheet-column-number/
     * 相当于 26 进制转 10 进制
     *
     * @Description: 171. Excel表列序号
     * @Param: [columnTitle]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/4 18:44
     */
    public static int titleToNumber(String columnTitle) {
        int index = 0;
        char[] chars = columnTitle.toCharArray();
        int temp = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            char aChar = chars[i];
            int num = Integer.valueOf(aChar) - 64;
            int pow = (int) Math.pow(26, temp++);
            index += pow * num;
        }
        return index;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-bits/
     *
     * @Description: 190. 颠倒二进制位
     * @Param: [n]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/4 22:04
     */
    public static int reverseBits(int n) {
        int s = 0;
        for (int i = 0; i <= 31; i++) {
            s += ((n >> i) & 1) << (31 - i);
        }
        return s;
    }

    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     *
     * @Description: 122. 买卖股票的最佳时机 II
     * @Param: [prices]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/9 11:52
     */
    public static int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int now = prices[i];
            int feature = prices[i + 1];
            if (now < feature) {
                sum += feature - now;
            }
        }
        return sum;
    }

    /**
     * https://leetcode-cn.com/problems/fizz-buzz/
     *
     * @Description: 412. Fizz Buzz
     * @Param: [n]
     * @return: java.util.List<java.lang.String>
     * @Author: weiZhiLin
     * @Date: 2021/6/9 12:10
     */
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuffer sb = new StringBuffer("");
            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            if ("".contentEquals(sb)) {
                sb.append(i);
            }
            list.add(sb.toString());
        }
        return list;
    }

    /**
     * https://leetcode-cn.com/problems/majority-element/
     *
     * @Description: 169. 多数元素
     * @Param: [nums]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/9 17:12
     */
    public int majorityElement(int[] nums) {
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            count = 1;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            if (count > nums.length / 2) {
                return nums[i];
            }
        }
        return nums[0];
    }
}
