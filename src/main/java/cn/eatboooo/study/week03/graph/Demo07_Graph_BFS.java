package cn.eatboooo.study.week03.graph;

import java.util.*;

/**
 * 并查集、图 专题
 * 图的宽度优先遍历
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 11:10
 */
public class Demo07_Graph_BFS {
    public static void bfs(GraphNode start) {
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(start);
        // ⚠️ 图可能会产生环，所以使用 set 检查是否重复
        Set<GraphNode> set = new HashSet();
        set.add(start);
        while (!queue.isEmpty()) {
            // 队列里出一个，打印一个
            GraphNode cur = queue.poll();
            ArrayList<GraphNode> nextCitys = cur.nextCitys;
            for (GraphNode nextCity : nextCitys) {
                if (set.contains(nextCity)) {
                    // 防止环打印产生
                    set.add(nextCity);
                    queue.add(nextCity);
                }
            }
        }
    }
}
