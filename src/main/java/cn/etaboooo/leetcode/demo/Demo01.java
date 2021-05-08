/**
 * @projectName leetcode
 * @package cn.etaboooo.leetcode.demo
 * @className cn.etaboooo.leetcode.demo.Demo01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.etaboooo.leetcode.demo;

import java.util.ArrayList;

import cn.etaboooo.bean.ListNode;

/**
 * Demo01
 * @description
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
    public static  void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
