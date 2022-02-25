package cn.eatboooo.study.week03.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 并查集、图 专题
 * 图的拓扑排序
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 14:24
 * <p>
 * 拓扑排序：移除入度为 0 的城市，打印移除的城市，继续移除入度为 0 的城市，打印移除的城市.....直到没有城市
 * BFS ：只需要一个 hashMap 记录入度，然后使用 bfs 即可拿下
 */
// OJ链接：https://www.lintcode.com/problem/topological-sorting
public class Demo07_Graph_TopologicalOrderBFS {
    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 提交下面的
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // map 记录 入度
        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();

        // 初始化节点
        for (DirectedGraphNode node : graph) {
            map.put(node, 0);
        }

        // 初始化入度
        for (DirectedGraphNode key : map.keySet()) {
            for (DirectedGraphNode toCity : key.neighbors) {
                // 去到的城市 入度 +1
                map.put(toCity, map.get(toCity) + 1);
            }
        }

        ArrayList<DirectedGraphNode> ans = new ArrayList<>();

        // bfs
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode key : map.keySet()) {
            if (map.get(key) == 0) {
                queue.add(key);
            }
        }

        while (!queue.isEmpty()) {
            DirectedGraphNode poll = queue.poll();
            ans.add(poll);
            for (DirectedGraphNode neighbor : poll.neighbors) {
                // ⚠️ 走了一个，入度 -1
                map.put(neighbor, map.get(neighbor) - 1);
                // ⚠️ if 判断很重要
                if (map.get(neighbor) == 0) {
                    // 入度为 0 的优先打印
                    queue.add(neighbor);
                }
            }
        }
        return ans;
    }

}
