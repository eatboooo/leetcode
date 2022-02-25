package cn.eatboooo.study.week03.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 并查集、图 专题
 * 最短路径之 Dijkstra 算法
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 21:31
 * <p>
 * Dijkstra 算法
 * <p>
 * 权重：一个城市 去到 另一个城市 的消耗
 * <p>
 * 一个点一个点分析
 * 这个点可以去哪里
 * 去自己权重是 0，去其他的是边的 长度，去不了的是最大值
 * 遍历该点的边，看看能否更新去其他点的权重
 * 更新完后把这条边锁住
 * 所有人不再更新个点的权重
 * <p>
 * 来到下一个、没被锁住、权重最低的点
 * 看看这个点能去到哪里
 * 去其他地方的权重是多少 （ 记得要加上自己之前记录的权重！）
 * 看看权重能否比之前一个点的要少
 * 如果权重更少就要更新
 * 遍历完后把这个点锁住
 * 去下一个点
 * …
 * <p>
 * 最后表中我们用到了那些边，就是答案
 */
public class Demo08_Graph_D {
    public static HashMap<GraphNode, Integer> dijkstra1(GraphNode from) {
        HashMap<GraphNode, Integer> ans = new HashMap<>();
        // 自己到自己肯定是 0
        ans.put(from, 0);

        // 打过对号的点,那些点被锁住了
        HashSet<GraphNode> done = new HashSet<>();

        // 在没打对号的点中找到权重最小点
        GraphNode min = findMin(ans, done);

        while (min != null) {
            // 原始点到这个城市的损耗
            Integer value = ans.get(min);

            for (Edge nextEdge : min.nextEdges) {
                if (done.contains(nextEdge.to)) {
                    // 打过对号
                    continue;
                }
                if (ans.get(nextEdge.to) > nextEdge.value + value) {
                    // 是不是有更优解法
                    ans.put(nextEdge.to, nextEdge.value + value);
                }
            }
            // 遍历完毕 打上对号
            done.add(min);
            // 去下一个地方
            min = findMin(ans, done);
        }
        return ans;
    }

    private static GraphNode findMin(HashMap<GraphNode, Integer> ans, HashSet<GraphNode> done) {
        GraphNode min = null;
        int minValue = Integer.MAX_VALUE;
        for (GraphNode graphNode : ans.keySet()) {
            if (done.contains(graphNode)) {
                continue;
            }
            Integer curValue = ans.get(graphNode);
            if (minValue > curValue) {
                minValue = curValue;
                min = graphNode;
            }
        }
        return min;
    }

}
