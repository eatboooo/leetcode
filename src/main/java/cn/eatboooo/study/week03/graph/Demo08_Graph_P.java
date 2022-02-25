package cn.eatboooo.study.week03.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 并查集、图 专题
 * 最短路径之 Prim 算法
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 20:52
 * <p>
 * 与 k 不同的是：
 * p 是由 点 依靠边 解锁新的点，看看点有没有被解锁过
 * k 是由 边 解锁 两个点，看看两个点在不在一个集合里
 * <p>
 * 随机挑一个城市
 * 把连接这个城市的所有路放入小根堆
 * 小根堆弹出路
 * 如果目的地没被解锁 我们就要这条边
 * 城市来到刚刚解锁的目的地
 * 把路放入小根堆
 * 继续弹出
 * 直到尽头
 */
public class Demo08_Graph_P {
    public static Set<Edge> primMST(Graph graph) {
        // 小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);

        // 城市是否解锁过
        Set<GraphNode> done = new HashSet<>();

        // ans
        Set<Edge> ans = new HashSet<>();

        for (GraphNode city : graph.city.values()) {
            // 随机来到一个 city

            priorityQueue.addAll(city.nextEdges);
            while (!priorityQueue.isEmpty()) {
                Edge poll = priorityQueue.poll();
                if (!done.contains(poll.to)) {
                    // 没解锁过，要了！
                    ans.add(poll);
                    // 记录一下证明解锁过了
                    done.add(poll.to);

                    // 准备去下一个城市咯
                    priorityQueue.addAll(poll.to.nextEdges);
                }
            }

            // 按理说需要 break，因为如果没有森林会发生性能浪费
            // 但是后面可能出现森林
            // 去掉 break 可以防止森林
            // break;
        }

        return ans;
    }


    // copy for study
    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distances = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];
        }
        int sum = 0;
        for (int i = 1; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] < minPath) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true;
            sum += minPath;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }
}
