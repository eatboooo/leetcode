package cn.eatboooo.study.week01;

import cn.eatboooo.bean.ListNode;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/4 12:25
 * <p>
 * 基础排序、二叉树、对数器、位运算
 * 链表结构、栈、队列、递归行为、哈希表、有序表
 */
public class Demo01 {
    // 题 -》浮现 ABCD 想法 -》沉默 -》错误
    // 题 -》浮现 ABCD 想法 -》碎碎念 -》正确

    // 位运算
    // 异或(^)相当于无进位相加
    // 一个数组，有两个数出现了奇数次，其他数字出现了偶数次，求这两个数
    public static int[] myByte01(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        // 假设两个奇数次的数为 A、B
        // 此时，xor = A^B

        // 用于获取二进制最右侧的 1 ，需要记住 xor & (~xor + 1)
        int xorT = xor & (~xor + 1);

        return new int[]{1, 2};
    }


    // 链表结构

    // 单双链表如何反转
    // 单链表反转 - 建议 理解 + 死记硬背 + 疯狂练习 - 因为太基础了
    public static ListNode myLink01Reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            // 注意这里的顺序
            // 保存原先下一个节点 - 下一个节点改为上一个 - 上一个变成现在 - 现在变成原先下一个
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 单链表 - 删除的指定的值
    public static ListNode myLink02Remove(ListNode head, int num) {

        // head 来到第一个不需要删除的位置
        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {
            if (cur.val == null) {
                pre.next = cur.next;
            } else {
                // todo
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }

    // 栈、队列结构
    // 栈、队列双向链表实现
    public class MyStackByList {

    }

    public class MyQueueByList {

    }

    // 栈、队列数组实现
    public class MyStackByArr {

    }

    public class MyQueueByArr {
        // putIndex
        // popIndex
        // size
        // limit
    }

    // 实现一个特殊栈、要求可以弹出最小元素
    // 思路：两个栈 data、min，min 和 data 同步上升、同步弹出，min 只记录当前最小值
    public class MyStackMin {
    }

    // 实现一个栈、内部只能用队列实现
    // 思路：两个队列 data、help，两个队列相互导数据 每次都留一个值用于弹出
    public class MyStackByQueue {
    }

    // 实现一个队列、内部只能用栈实现
    // 思路：两个栈，相互导数据
    public class MyQueueByStack {
    }

    // 递归
    // 从思想上理解 - 大事分解成小事
    // 递归实际上利用都事系统栈
    // 任何递归都可以改成非递归行为

    // 求 arr「L，R」中最大值，用递归实现

    // 对于某一类递归行为，它的时间复杂对可以直接确定的
    // 自问题的规模 * 自问题的数量


    // 哈希表、有序表 先不谈
}
