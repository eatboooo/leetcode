package cn.eatboooo.study.week03.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集、图 专题
 * 并查集基础实现
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/24 16:22
 */
public class Demo06_Unionfind_Base {
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
