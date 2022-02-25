package cn.eatboooo.study.week03.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 10:01
 */
public class Graph {
    // 点集 所有的城市
    public HashMap<Integer, GraphNode> city;

    // 边集 所有的路径
    public HashSet<Edge> edges;

    public Graph() {
        city = new HashMap<>();
        edges = new HashSet<>();
    }
}
