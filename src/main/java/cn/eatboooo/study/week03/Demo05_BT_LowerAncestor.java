/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week03
 * @className cn.eatboooo.study.week03.Demo05_BT_LowerAncestor
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Demo05_BT_LowerAncestor
 * @description
 * @author weiZhiLin
 * @date 2021/7/20 13:47
 * @version 1.0
 *
 * 二叉树中两个节点最低公共祖先
 */
public class Demo05_BT_LowerAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public Node ans;
        public boolean findA;
        public boolean findB;

        public Info(Node ans, boolean findA, boolean findB) {
            this.ans = ans;
            this.findA = findA;
            this.findB = findB;
        }
    }

    public static Node lowestAncestor2(Node head, Node a, Node b) {
        return process1(head, a, b).ans;
    }

    private static Info process1(Node head, Node a, Node b) {
        if (head == null) {
            return new Info(null, false, false);
        }
        Info left = process1(head.left, a, b);
        Info right = process1(head.right, a, b);
        Node ans = null;
        // boolean findA = a == head ? true : (left.findA || right.findA);
        // boolean findB = b == head ? true : (left.findB ||right.findB); 优化一下
        boolean findA = a == head || left.findA || right.findA;
        boolean findB = b == head || left.findB || right.findB;
        if (left.ans != null) {
            ans = left.ans;
        } else if (right.ans != null) {
            ans = right.ans;
        } else if (findA && findB) {
            ans = head;
        }
        return new Info(ans, findA, findB);
    }




    // test
    // copy for test
    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
