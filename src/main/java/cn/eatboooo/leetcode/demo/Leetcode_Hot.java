package cn.eatboooo.leetcode.demo;

import cn.eatboooo.bean.ListNode;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * leetcode
 * https://leetcode.cn/problemset/all/?listId=2cktkvj&page=1
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2023/6/28 13:23
 */
public class Leetcode_Hot {
    // 2. 两数相加
    // 这个题目有坑，两个数相加的结果可能是无穷大，所以不能用简便方法运算
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result;
        ListNode l3 = new ListNode();
        result = l3;
        int carry = 0;
        while ((l1 != null || l2 != null)) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int i = a + b;
            if (i + carry >= 10) {
                l3.next = new ListNode((i + carry) - 10);
                carry = 1;
            } else {
                l3.next = new ListNode(i + carry);
                carry = 0;
            }
            l3 = l3.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry != 0) {
            l3.next = new ListNode(carry);
        }
        return result.next;
    }

    @Test
    public void test_01() {
        ListNode listNode = addTwoNumbers(new ListNode(9, 9, 9, 9, 9, 9, 9), new ListNode(9, 9, 9, 9));
        System.out.println("listNode = " + listNode);
    }

    // 3. 无重复字符的最长子串
    // 滑动时间窗口的简单思路
    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        if (arr.length == 1) {
            return 1;
        }
        windowTo3 win = new windowTo3();
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, win.put(arr[i]));
        }
        max = Math.max(max, win.getSize());
        return max;
    }

    // 滑动时间窗口，特供版
    public class windowTo3 {
        HashSet<Character> set = new HashSet();
        LinkedList<Character> list = new LinkedList();

        int getSize() {
            return list.size();
        }

        int put(char a) {
            int beforeSize = list.size();
            while (set.contains(a)) {
                Character character = list.removeFirst();
                set.remove(character);
            }
            set.add(a);
            list.add(a);
            return beforeSize;
        }
    }

    @Test
    public void test02_3() {
        System.out.println(lengthOfLongestSubstring("au"));
    }

    // 4, 寻找两个正序数组的中位数
    // 整几个指针完事了，挺简单
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int a = 0;
        int b = 0;
        int index = 0;
        int[] arr = new int[nums1.length + nums2.length];
        int mid = (nums1.length + nums2.length) >> 1;
        boolean need2 = (nums1.length + nums2.length) % 2 == 0;
        for (int i = a; i < nums1.length; i++, a++, index++) {
            for (int j = b; j < nums2.length && nums1[a] > nums2[b]; j++, b++, index++) {
                if (nums1[a] <= nums2[b]) {
                    break;
                }
                arr[index] = nums2[b];
            }
            arr[index] = nums1[a];
        }
        for (int j = b; j < nums2.length; j++, b++, index++) {
            arr[index] = nums2[b];
        }
        return need2 ? (double) (arr[mid] + arr[mid - 1]) / 2 : arr[mid];
    }

    @Test
    public void test04() {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 7}));
        System.out.println(findMedianSortedArrays(new int[]{2, 3}, new int[]{1}));
    }

}
