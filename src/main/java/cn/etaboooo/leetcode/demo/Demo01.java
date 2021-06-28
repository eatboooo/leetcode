/**
 * @projectName leetcode
 * @package cn.etaboooo.leetcode.demo
 * @className cn.etaboooo.leetcode.demo.Demo01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.etaboooo.leetcode.demo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import cn.etaboooo.bean.ListNode;
import cn.etaboooo.bean.TreeNode;

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
     * <p>
     * 害有一种排序思路
     * public int majorityElement(int[] nums) {
     * Arrays.sort(nums);
     * return nums[nums.length >> 1];
     * }
     * 还有摩尔投票法，参考官网
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
            for (int j = i + 1; j < nums.length; j++) {
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

    /**
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     *
     * @Description: 21. 合并两个有序链表
     * @Param: [l1, l2]
     * @return: cn.etaboooo.bean.ListNode
     * @Author: weiZhiLin
     * @Date: 2021/6/13 22:47
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        // 先找出第一个节点小的作为 first
        ListNode head = l1.val < l2.val ? l1 : l2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == l1 ? l2 : l1;
        ListNode pre = head;

        while (cur1 != null && cur2 != null) {
            if (cur1.val > cur2.val) {
                pre.next = cur2;
                cur2 = cur2.next;
            } else {
                pre.next = cur1;
                cur1 = cur1.next;
            }
            // 不能忘！
            pre = pre.next;
        }
        pre.next = cur1 == null ? cur2 : cur1;

        return head;
    }

    /**
     * https://leetcode-cn.com/problems/valid-anagram/
     *
     * @Description: 242. 有效的字母异位词
     * @Param: [s, t]
     * @return: boolean
     * @Author: weiZhiLin
     * @Date: 2021/6/13 23:54
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] chars = s.toCharArray();
        char[] chars1 = t.toCharArray();
        int check = 0;
        for (int i = 0; i < chars1.length; i++) {
            b:
            for (int j = 0; j < chars.length; j++) {
                if (chars1[i] == chars[j]) {
                    chars[j] = ' ';
                    check++;
                    break b;
                }
            }
        }
        return check == s.length();
    }

    /**
     * https://leetcode-cn.com/problems/move-zeroes/
     *
     * @Description: 283. 移动零
     * @Param: [nums]
     * @return: void
     * @Author: weiZhiLin
     * @Date: 2021/6/14 23:08
     */
    public void moveZeroes(int[] nums) {
        // 记录零的最后的位置
        int t = nums.length;
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t - 1; j++) {
                if (nums[j] == 0) {
                    swapArr(nums, j, j + 1);
                }
            }
        }
    }

    public void swapArr(int[] arr, int x, int y) {
        arr[x] = arr[y] + arr[x];
        arr[y] = arr[x] - arr[y];
        arr[x] = arr[x] - arr[y];
    }

    /**
     * https://leetcode-cn.com/problems/roman-to-integer/
     *
     * @Description: 13. 罗马数字转整数
     * @Param: [s]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/16 20:47
     */
    public int romanToInt(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            switch (aChar) {
                case 'I':
                    if (i < chars.length - 1 && chars[i + 1] == 'V') {
                        sum += 4;
                        i++;
                        break;
                    }
                    if (i < chars.length - 1 && chars[i + 1] == 'X') {
                        sum += 9;
                        i++;
                        break;
                    }
                    sum += 1;
                    break;
                case 'V':

                    sum += 5;
                    break;
                case 'X':
                    if (i < chars.length - 1 && chars[i + 1] == 'L') {
                        sum += 40;
                        i++;
                        break;
                    }
                    if (i < chars.length - 1 && chars[i + 1] == 'C') {
                        sum += 90;
                        i++;
                        break;
                    }
                    sum += 10;
                    break;
                case 'L':
                    sum += 50;
                    break;
                case 'C':
                    if (i < chars.length - 1 && chars[i + 1] == 'D') {
                        sum += 400;
                        i++;
                        break;
                    }
                    if (i < chars.length - 1 && chars[i + 1] == 'M') {
                        sum += 900;
                        i++;
                        break;
                    }
                    sum += 100;
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;
            }
        }
        return sum;
    }

    /**
     * https://leetcode-cn.com/problems/missing-number/
     * 等差数列求和 Sn=n(a1+an)/2
     *
     * @Description: 268. 丢失的数字
     * @Param: [nums]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/16 21:43
     */
    public static int missingNumber(int[] nums) {
        int sum = ((nums.length + 1) * nums.length) >> 1;
        int tmep = 0;
        for (int i = 0; i < nums.length; i++) {
            tmep += nums[i];
        }
        return sum - tmep;
    }

    /**
     * https://leetcode-cn.com/problems/happy-number/submissions/
     *
     * @Description: 202. 快乐数
     * @Param: [n]
     * @return: boolean
     * @Author: weiZhiLin
     * @Date: 2021/6/17 11:59
     */
    public static boolean isHappy(int n) {
        int i = checkHappy(n);
        return i == 1;
    }

    private static int checkHappy(int n) {
        if (n <= 3) {
            return n;
        }
        int sum = 0;
        while (n > 0) {
            int n1 = n % 10;
            sum += n1 * n1;
            n = (n - n1) / 10;
        }
        // 很关键，防止无限循环
        if (sum == 4) {
            return -1;
        }
        return checkHappy(sum);
    }

    /**
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * 还有一种双指针写法必须复习
     *
     * @Description: 160. 相交链表
     * @Param: [headA, headB]
     * @return: cn.etaboooo.bean.ListNode
     * @Author: weiZhiLin
     * @Date: 2021/6/17 16:34
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     * 暴力法多次都超时了。循环一次都方法美只只
     *
     * @Description: 121. 买卖股票的最佳时机
     * @Param: [prices]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/18 17:56
     */
    public int maxProfit1(int[] prices) {
        int maxprofit = 0;
        // 记录今天之前的最低点
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
            } else if (maxprofit < prices[i] - min) {
                // 假设我在最低点买的，今天卖出能挣多少¥
                maxprofit = prices[i] - min;
            }
        }
        return maxprofit;
    }

    /**
     * https://leetcode-cn.com/problems/contains-duplicate/
     * 暴力方法超时了 所以借助一下hashset
     * 还有先排序，然后和前一个元素比较
     *
     * @Description: 217. 存在重复元素
     * @Param: [nums]
     * @return: boolean
     * @Author: weiZhiLin
     * @Date: 2021/6/18 19:21
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> objects = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!objects.add(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/symmetric-tree/
     * 需要复习
     *
     * @Description: 101. 对称二叉树
     * @Param: [root]
     * @return: boolean
     * @Author: weiZhiLin
     * @Date: 2021/6/21 22:25
     */
    public boolean isSymmetric(TreeNode root) {
        return checkIsSymmetric(root.left, root.right);
    }

    private boolean checkIsSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && checkIsSymmetric(left.left, right.right) && checkIsSymmetric(left.right, right.left);
    }

    /**
     * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
     * 先排序，然后使用两个指针找交集 - 感觉有点蠢
     *
     * @Description: 350. 两个数组的交集 II
     * @Param: [nums1, nums2]
     * @return: int[]
     * @Author: weiZhiLin
     * @Date: 2021/6/21 22:46
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] temp = nums1.length <= nums2.length ? nums1 : nums2;
        int[] temp2 = nums1.length > nums2.length ? nums1 : nums2;
        /*
        其实完全可以这样 - 呆b了
         if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        还有一种 hashmap 的方法 ，主要是使用 map.getOrDefault(num, 0) + 1
        * */
        int size = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < temp.length; i++) {
            if (size >= temp2.length) {
                break;
            }
            if (temp[i] > temp2[size]) {
                size++;
                i--;
                continue;
            }
            if (temp[i] == temp2[size]) {
                size++;
                list.add(temp[i]);
            }
        }
        return convertListToArray(list);
    }

    public static int[] convertListToArray(List<Integer> listResult) {
        int[] result = new int[listResult.size()];
        int i = 0;
        for (int num : listResult) {
            result[i++] = num;
        }
        return result;
    }


    /**
     * https://leetcode-cn.com/problems/maximum-subarray/
     * 动态规划，需要复习
     *
     * @Description: 53. 最大子序和
     * @Param: [nums]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/22 10:33
     */
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
     * 和前一个比就完事了，注意边界条件
     *
     * @Description: 26. 删除有序数组中的重复项
     * @Param: [nums]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/22 11:12
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i + 1) == nums.length || nums[i] != nums[i + 1]) {
                nums[size++] = nums[i];
            }
        }
        nums = Arrays.copyOfRange(nums, 0, size - 1);
        return size;
    }

    /**
     * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
     * 效率很低，需要更换
     * 记得使用relaceFirest方法
     *
     * @Description: 387. 字符串中的第一个唯一字符
     * @Param: [s]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/22 11:49
     */
    public static int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            String replace = s.replaceFirst(String.valueOf(s.charAt(i)), String.valueOf((char) (s.charAt(i) + 1)));
            if (!replace.contains(String.valueOf(s.charAt(i)))) {
                return i;
            }
        }
        return -1;
    }
    /*
    这个方法快一点，还有一种hashmap的 重要是 map.getOrDefault(ch, 0) + 1
    public int firstUniqChar(String s) {
       int[] arr = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i)-'a']++ ;
        }
        for (int i = 0; i < n; i++) {
            if (arr[s.charAt(i)-'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
     */

    /**
     * https://leetcode-cn.com/problems/climbing-stairs/
     * 动态规划直接超时 需要复习
     * 这里使用滚动数组 需要复习 看官网图解
     *
     * @Description: 70. 爬楼梯
     * @Param: [n]
     * @return: int
     * @Author: weiZhiLin
     * @Date: 2021/6/22 17:35
     */
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
    /*public static int climbStairs(int n) {
        if (n < 4) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }*/

    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 使用了双循环暴力方法，还有一种hashMap方法 参考官网
     *
     * @Description: 1. 两数之和
     * @Param: [nums, target]
     * @return: int[]
     * @Author: weiZhiLin
     * @Date: 2021/6/22 17:52
     */
    public int[] twoSum(int[] nums, int target) {
        int[] temp = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    temp[0] = i;
                    temp[1] = j;
                    return temp;
                }
            }
        }
        return temp;
    }

    /**
     * https://leetcode-cn.com/problems/linked-list-cycle/
     * 使用 hashSet 完事
     * 还有一种快慢指针，之前的题也遇到过 需要复习
     *
     * @Description: 141. 环形链表
     * @Param: [head]
     * @return: boolean
     * @Author: weiZhiLin
     * @Date: 2021/6/22 18:14
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetco-rrb0/
     * 直接合并再排序 - 最容易想到的
     * 需要复习 官网的逆向双指针
     *
     * @Description: 88. 合并两个有序数组
     * @Param: [nums1, m, nums2, n]
     * @return: void
     * @Author: weiZhiLin
     * @Date: 2021/6/23 18:35
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = 0;
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[index++];
        }
        Arrays.sort(nums1);
    }

    /**
     * https://leetcode-cn.com/problems/power-of-three/
     * 自己用的循环，太土了不满意
     * 参考网络转换为三进制然后使用正则 - 需要复习
     *
     * @Description: 326. 3的幂
     * @Param: [n]
     * @return: boolean
     * @Author: weiZhiLin
     * @Date: 2021/6/24 17:49
     */
    public static boolean isPowerOfThree(int n) {
        while (n > 3) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return n == 3 || n == 1;
    }

    // 对于10进制数来说，10的n次幂表达为10，100，100 对于2进制数来说，2的n次幂的二进制表达为 10,100,100 3进制同理
    public static boolean isPowerOfThree02(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    /**
     * https://leetcode-cn.com/problems/palindrome-linked-list/
     * 边界条件真的烦
     * @Description: 234. 回文链表
     * @Param: [head]
     * @return: boolean
     * @Author: weiZhiLin
     * @Date: 2021/6/24 18:48
     */
    public static boolean isPalindrome(ListNode head) {
        // 算出长度
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        if (size <= 1) {
            return true;
        }
        Stack<Integer> integers = new Stack<>();
        int index = 0;
        while (head != null) {
            if (index < size / 2) {
                // 循环到一半，每次放入栈
                integers.push(head.val);
            } else if (size % 2 != 0 && index == size / 2) {
                head = head.next;
                index++;
                continue;
            } else {
                // 和栈里面的对比
                if (integers.pop() != head.val) {
                    return false;
                }
            }
            head = head.next;
            index++;
        }
        return true;
    }

    /*
     * https://leetcode-cn.com/problems/valid-palindrome/
     * @description 125. 验证回文串
     * @param s
     * @return boolean
     * @date 2021/6/28 18:28
     * @author weizhilin
     * @version 1.0
     */
    public static boolean isPalindrome(String s) {
        /*  a-z：97-122
            A-Z：65-90
            0-9：48-57
         */
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            if (isNumAndLetter(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
            i++;
        }
        s = sb.toString();
        int length = s.length();
        int l = s.length() / 2 - 1;
        int r = s.length() % 2 == 0 ? s.length() / 2 : s.length() / 2 + 1;
        while (l >= 0 && r <= length - 1) {
            if (s.charAt(l) != s.charAt(r) && (!((String.valueOf(s.charAt(l))).equalsIgnoreCase(String.valueOf(s.charAt(r)))))) {
                return false;
            }
            l--;
            r++;
        }
        return true;
    }

    public static boolean isNumAndLetter(char a) {
        /*  a-z：97-122
            A-Z：65-90
            0-9：48-57
         */
        if (a >= 48 && a <= 57) {
            return true;
        }
        if (a >= 65 && a <= 90) {
            return true;
        }
        if (a >= 97 && a <= 122) {
            return true;
        }
        return false;
    }
}
