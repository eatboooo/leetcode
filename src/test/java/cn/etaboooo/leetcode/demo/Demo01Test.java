package cn.etaboooo.leetcode.demo;

import cn.etaboooo.bean.ListNode;
import cn.etaboooo.bean.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/5/8 16:48
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
        TreeNode x = Demo01.sortedArrayToBST(new int[]{1, 2, 3, 4, 5});
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
    void forTest() {
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
        while (n != 0) {
            if ((n & 1) == 1) {
                sum *= tmp;
            }
            tmp *= tmp;
            n = n >> 1;
        }

        System.out.println(sum);
    }

    @Test
    void missingNumber() {
        int i = Demo01.missingNumber(new int[]{1, 2, 3, 0});
        System.out.println(i);
    }

    @Test
    void isHappy() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            boolean happy = Demo01.isHappy(i);
            System.out.println(happy);
        }
    }

    @Test
    void stack() {
        Demo01_Stack stack = new Demo01_Stack();
        stack.push(2);
        stack.push(0);
        stack.push(3);
        stack.push(0);
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
        System.out.println(stack.top());
      /*  stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());*/
    }

    @Test
    void myTest01() {
        HashSet<Integer> objects = new HashSet<>();
        System.out.println(objects.add(1));
        // 打印 false
        System.out.println(objects.add(1));
    }

    @Test
    void intersect() {
        int[] intersect = Demo01.intersect(new int[]{1, 1, 3, 4, 54, 2}, new int[]{1, 1, 7, 8, 3, 21});
        Base01.printArr(intersect);
    }
}
