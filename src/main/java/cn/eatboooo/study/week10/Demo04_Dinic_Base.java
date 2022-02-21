package cn.eatboooo.study.week10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 网络流算法
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/21 10:03
 * <p>
 * 测试链接：https://lightoj.com/problem/internet-bandwidth
 */
public class Demo04_Dinic_Base {
    // 关键要素：
    // 任务下发
    // 同一层的路径不走
    // 走的路径补充一个反向边，防止反悔
    // 路径如果没有额度，用数组记录，加快路径遍历
    // copy for study

    public static class Edge {
        // 从哪里来
        public int from;
        // 到哪里去
        public int to;
        // 剩余额度
        public int available;

        public Edge(int a, int b, int c) {
            from = a;
            to = b;
            available = c;
        }
    }

    public static class Dinic {
        private int N;

        // 为什么两个嵌套？
        // 第一层是城市，第二层是有那些边
        private ArrayList<ArrayList<Integer>> nexts;

        // 每个边的坐标
        private ArrayList<Edge> edges;

        // 深度
        // 同一层的路径不走
        private int[] depth;

        // 路径如果没有额度，用数组记录，加快路径遍历
        private int[] cur;

        public Dinic(int nums) {
            N = nums + 1;
            nexts = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                nexts.add(new ArrayList<>());
            }
            edges = new ArrayList<>();
            depth = new int[N];
            cur = new int[N];
        }

        // u : from
        // v : to
        // r : available 额度
        public void addEdge(int u, int v, int r) {
            int m = edges.size();
            edges.add(new Edge(u, v, r));
            nexts.get(u).add(m);
            edges.add(new Edge(v, u, 0));
            nexts.get(v).add(m + 1);
        }

        public int maxFlow(int s, int t) {
            int flow = 0;
            // 能不能从 s 走到 t
            while (bfs(s, t)) {
                // 把 cur 每个位置改为 0
                Arrays.fill(cur, 0);

                flow += dfs(s, t, Integer.MAX_VALUE);

                // 把 depth 每个位置改为 0
                Arrays.fill(depth, 0);
            }
            return flow;
        }

        // 能不能从 s 走到 t
        private boolean bfs(int s, int t) {
            // 借助队列 按层遍历
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addFirst(s);

            // visited[v] 代表 s 能否抵达 v 城市
            boolean[] visited = new boolean[N];
            visited[s] = true;
            while (!queue.isEmpty()) {
                // 按层遍历每一个城市
                int u = queue.pollLast();

                for (int i = 0; i < nexts.get(u).size(); i++) {
                    // 遍历所有边
                    Edge e = edges.get(nexts.get(u).get(i));

                    int v = e.to;
                    if (!visited[v] && e.available > 0) {
                        // 没去过 v，并且连接 v 的道路有额度
                        visited[v] = true;
                        depth[v] = depth[u] + 1;
                        if (v == t) {
                            break;
                        }
                        queue.addFirst(v);
                    }
                }
            }
            return visited[t];
        }

        // 当前来到了s点，s可变
        // 最终目标是t，t固定参数
        // r，收到的任务
        // 收集到的流，作为结果返回，ans <= r
        private int dfs(int s, int t, int r) {
            if (s == t || r == 0) {
                return r;
            }
            int f = 0;
            int flow = 0;
            // s点从哪条边开始试 -> cur[s]
            for (; cur[s] < nexts.get(s).size(); cur[s]++) {
                int ei = nexts.get(s).get(cur[s]);
                Edge e = edges.get(ei);
                // ⚠️非常nb⚠️ 抑或 1 拿到反向边的下标
                Edge o = edges.get(ei ^ 1);
                if (depth[e.to] == depth[s] + 1 && (f = dfs(e.to, t, Math.min(e.available, r))) != 0) {
                    e.available -= f;
                    o.available += f;
                    flow += f;
                    r -= f;
                    if (r <= 0) {
                        break;
                    }
                }
            }
            return flow;
        }
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int cases = cin.nextInt();
        for (int i = 1; i <= cases; i++) {
            int n = cin.nextInt();
            int s = cin.nextInt();
            int t = cin.nextInt();
            int m = cin.nextInt();
            Dinic dinic = new Dinic(n);
            for (int j = 0; j < m; j++) {
                int from = cin.nextInt();
                int to = cin.nextInt();
                int weight = cin.nextInt();
                dinic.addEdge(from, to, weight);
                dinic.addEdge(to, from, weight);
            }
            int ans = dinic.maxFlow(s, t);
            System.out.println("Case " + i + ": " + ans);
        }
        cin.close();
    }

}
