package cn.etaboooo.leetcode.demo;

import java.util.List;

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

    @Test
    void inorderTraversal() {
        TreeNode treeNode = TreeNode.arrAsTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        List<Integer> integers = Demo01.inorderTraversal(treeNode);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
    @Test
    void forTest(){
        int i = 0;
        for (int k = 0; k <= i; ++k) {
            System.out.println(i);
        }
    }

    @Test
    void titleToNumber() {
        int ab = Demo01.titleToNumber("ZY");
        System.out.println(Integer.valueOf(ab));
    }

    @Test
    void reverseBits() {
        int i = Demo01.reverseBits(-3);
        System.out.println(i);
    }

    @Test
    // 位运算 - 次方 , temp 的 n 次方 ，wx 收藏
    void pow() {
        int sum = 1;
        int tmp = 2;
        int n = 6;// 110
        while(n != 0){
            if((n & 1) == 1){
                sum *= tmp;
            }
            tmp *= tmp;
            n = n >> 1;
        }

        System.out.println(sum);
    }

    @Test
    void missingNumber() {
        int i = Demo01.missingNumber(new int[]{1, 2, 3,0});
        System.out.println(i);
    }
}
