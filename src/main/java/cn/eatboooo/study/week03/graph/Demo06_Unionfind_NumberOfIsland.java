package cn.eatboooo.study.week03.graph;

import java.util.*;

/**
 * 并查集、图 专题
 * 岛屿的数量
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/24 19:42
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Demo06_Unionfind_NumberOfIsland {
    public int numIslands(char[][] grid) {
        // 初始化
        LinkedList<Dot> list = new LinkedList<>();
        Dot[][] arr = new Dot[grid.length][grid[0].length];
        // 采取感染方案，所有的 1 向四周感染
        // 也就是向四周 unionNode
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    // ⚠️ 这边只有 1 的时候才可以创建，否则 0 也会算成是岛屿
                    arr[i][j] = new Dot();
                    list.add(arr[i][j]);
                }
            }
        }
        Union<Dot> union = new Union<>(list);

        // 处理我们无法触及的边
        // 最后竖起来的
        for (int i = grid.length - 1; i >= 1; i--) {
            if (grid[i][grid[0].length - 1] == '1' && grid[i - 1][grid[0].length - 1] == '1') {
                union.unionNode(arr[i][grid[0].length - 1], arr[i - 1][grid[0].length - 1]);
            }
        }
        // 最后横着的
        for (int j = grid[0].length - 1; j >= 1; j--) {
            if (grid[grid.length - 1][j] == '1' && grid[grid.length - 1][j - 1] == '1') {
                union.unionNode(arr[grid.length - 1][j], arr[grid.length - 1][j - 1]);
            }
        }

        for (int i = 0; i < grid.length -1; i++) {
            char[] chars = grid[i];
            for (int j = 0; j < chars.length -1; j++) {
                char aChar = chars[j];
                if (aChar == '1') {
                    // 去感染别人
                    if (grid[i][j + 1] == '1') {
                        union.unionNode(arr[i][j], arr[i][j + 1]);
                    }
                    if (grid[i+1][j] == '1') {
                        union.unionNode(arr[i][j], arr[i + 1][j]);
                    }
                }
            }
        }
        return union.nodeSize.size();
    }

  public static class Dot{

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
