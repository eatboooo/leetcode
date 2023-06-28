package cn.eatboooo.leetcode.demo;

import cn.eatboooo.bean.ListNode;
import org.junit.Test;

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
}
