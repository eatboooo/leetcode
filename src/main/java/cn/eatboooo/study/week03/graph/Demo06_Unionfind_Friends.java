package cn.eatboooo.study.week03.graph;

import java.util.*;

/**
 * 并查集、图 专题
 * 朋友圈问题
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/24 17:53
 * <p>
 * 一群朋友中，有几个不相交的朋友圈
 * <p>
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Demo06_Unionfind_Friends {
    public static int findCircleNum(int[][] arr) {
        int length = arr.length;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            list.add(i);
        }
        Union<Integer> union = new Union<>(list);

        for (int i = 0; i < arr.length; i++) {
            int[] ints = arr[i];
            for (int j = 0; j < ints.length; j++) {
                int anInt = ints[j];
                if (anInt == 1) {
                    union.unionNode(j, i);
                }
            }
        }
        return union.nodeSize.size();
    }

    public static class Union<V> {
        // 每一个点的位置
        Map<V, Node<V>> nodeMap = new HashMap();
        // 每一个点的代表节点
        Map<Node<V>, Node<V>> nodeFather = new HashMap();
        // 每一坨的 size
        Map<Node<V>, Integer> nodeSize = new HashMap();

        public Union(List<V> list) {
            // 普通初始化
            for (V v : list) {
                Node<V> node = new Node<>(v);
                // 找到每一个节点
                nodeMap.put(v, node);
                // 每一个节点父亲都是自己
                nodeFather.put(node, node);
                // 一开始所有节点都是独立的，每一坨都是 1
                nodeSize.put(node, 1);
            }
        }

        // 两个点的连接功能
        public void unionNode(V node1, V node2) {
            // 判断父亲是否相同，相同直接滚蛋
            Node<V> father1 = findFather(node1);
            Node<V> father2 = findFather(node2);
            if (father1 == father2) {
                return;
            }

            // 找到两个的代表谁大谁小
            Node<V> max = nodeSize.get(father1) > nodeSize.get(father2) ? father1 : father2;
            Node<V> min = father1 == max ? father2 : father1;

            // 把 size 小的代表 father 改成 size 大的
            nodeFather.put(min, max);

            // 修改大的 size，并且移除小的
            nodeSize.put(max, nodeSize.get(max) + nodeSize.get(min));
            nodeSize.remove(min);
        }

        // 查询点的最终代表
        // 查询后的扁平化
        public Node<V> findFather(V node) {
            Node<V> son = nodeMap.get(node);
            if (son == null) {
                return null;
            }

            Stack<Node<V>> stack = new Stack<>();
            Node<V> father = nodeFather.get(son);
            while (father != son) {
                stack.push(son);
                son = father;
                father = nodeFather.get(son);
            }

            // 查询后的扁平化
            while (!stack.isEmpty()) {
                nodeFather.put(stack.pop(), father);
            }
            return father;
        }
    }


    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }
}
