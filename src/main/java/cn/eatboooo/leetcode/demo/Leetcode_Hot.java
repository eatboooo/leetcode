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

    // 5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        char[] charArray = s.toCharArray();
        StringBuilder sCP = new StringBuilder();
        for (char c : charArray) {
            sCP.append(c).append("-");
        }
        charArray = sCP.toString().toCharArray();
        int max = 0;
        String reslut = "";
        for (int mid = 1; mid < charArray.length - 1; mid++) {
            int length = 1;
            StringBuilder temp = new StringBuilder(String.valueOf(charArray[mid]));
            for (int left = mid - 1, right = mid + 1; left >= 0 && right < charArray.length && charArray[left] == charArray[right]; left--, right++) {
                length = right - left + 1;
                temp = new StringBuilder(charArray[left] + temp.toString() + charArray[right]);
            }
            if (length > max) {
                max = length;
                reslut = temp.toString();
            }
        }
        return reslut.replace("-", "");
    }

    @Test
    public void test05() {
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("ab"));
        System.out.println(longestPalindrome("aa"));
        System.out.println(longestPalindrome("abcc"));
        System.out.println(longestPalindrome("abccbaaaaaaaa"));
    }

    // 10. 正则表达式匹配
    // 大刷 12题
    // TODO：DP 版本、记忆版本
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }
        return isMatch(0, 0, s.toCharArray(), p.toCharArray());
    }

    public boolean isMatch(int sIndex, int pIndex, char[] sArr, char[] pArr) {
        if (pIndex >= pArr.length) {
            // 字符串匹配结束
            // 同时正则也结束
            return sIndex == sArr.length;
        }
        // ⚠️这里不能提前判断，因为 s 遍历完成后，可能pArr只剩下了 * ，也是可以忽略的
/*        if (sIndex == sArr.length) {
            return false;
        }*/
        // 下一个不是 *
        if (pIndex + 1 == pArr.length || pArr[pIndex + 1] != '*') {
            return (sIndex != sArr.length && (sArr[sIndex] == pArr[pIndex] || pArr[pIndex] == '.')) && isMatch(sIndex + 1, pIndex + 1, sArr, pArr);
        }

        // 下一个是 *
        // 错误版 ⚠️       while (pArr[pIndex + 1] == '*' && sIndex < sArr.length) {
        // 原因： 首先，下一个不是*的情况已经被过滤，所以，下一个必定是*。其次，即便下一个是*，也需要判断 pArr[pIndex]能否变身
        while (sIndex < sArr.length && (pArr[pIndex] == sArr[sIndex] || pArr[pIndex] == '.')) {
            if (isMatch(sIndex, pIndex + 2, sArr, pArr)) {
                return true;
            }
            sIndex++;
        }
        // ⚠️，有可能下一个是*，但是不相等，只能变成0个
        // 还有可能上述的 while 结束了还没返回，此时就是：pArr 已经变身为可以变身的最长字符了
        return isMatch(sIndex, pIndex + 2, sArr, pArr);
    }

    @Test
    public void test06() {
        System.out.println(isMatch("a", "ab*"));
        System.out.println(isMatch("mississippi", "mis*is*ip*."));
        System.out.println(isMatch("abccc", ".*"));
        System.out.println(isMatch("abccc", ".*a"));
        System.out.println(isMatch("abccc", ".*c"));
        System.out.println(isMatch("abccc", ".*bccc"));
        System.out.println(isMatch("abccc", ".*b*cc"));
        System.out.println(isMatch("abccc", ".*ccc"));
    }

    // 11. 盛最多水的容器 - 根据数据量猜测解法
    // 大刷8
    // n == height.length
    //  2 <= n <= 105
    // 至少是 O（log n） 自然想到双指针
    // TODO 单调栈版本

    // 思想先进
    // 左右各一个指针，计算面积后，向中间移动值比较小的指针
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            max = Math.max(Math.min(height[l], height[r]) * (r - l), max);
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }


}
