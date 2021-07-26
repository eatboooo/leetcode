/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week03
 * @className cn.eatboooo.study.week03.Demo06_Graph_UnionFind
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week03;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Demo06_Graph_UnionFind
 * @description
 * @author weiZhiLin
 * @date 2021/7/26 13:21
 * @version 1.0
 *
 * 并查集
 */
public class Demo06_Graph_UnionFind {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        HashMap<V, Node> nodes = new HashMap();
        HashMap<Node, Node> parents = new HashMap();
        HashMap<Node, Integer> mapSize = new HashMap();

        public UnionFind(List<V> list) {
            for (V v : list) {
                Node<V> value = new Node(v);
                nodes.put(v, value);
                parents.put(value, value);
                mapSize.put(value, 1);
            }
        }

        public Node<V> findFather(Node<V> node) {
            Stack<Node> path = new Stack<>();
            while (node != parents.get(node)) {
                // 特别注意,先 push 再  parents.get(node)
                path.push(node);
                node = parents.get(node);
            }
            // 路径压缩
            while (!path.isEmpty()) {
                parents.put(path.pop(), node);
            }
            return node;
        }

        public void union(V a, V b) {
            Node<V> aFather = findFather(nodes.get(a));
            Node<V> bFather = findFather(nodes.get(b));
            if (aFather != bFather) {
                Integer aSize = mapSize.get(nodes.get(a));
                Integer bSize = mapSize.get(nodes.get(b));
                Node<V> big = aSize >= bSize ? aFather : bFather;
                Node<V> small = big == aFather ? bFather : aFather;
                parents.put(small, big);
                mapSize.put(big, aSize + bSize);
                mapSize.remove(small);
            }
        }
    }
}
