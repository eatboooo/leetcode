package cn.etaboooo.leetcode.demo;

import org.junit.jupiter.api.Test;

import cn.etaboooo.bean.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}