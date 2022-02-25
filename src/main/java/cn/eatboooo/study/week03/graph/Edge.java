package cn.eatboooo.study.week03.graph;

/**
 * 道路
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 10:03
 */
public class Edge {
    // 从哪里来
    public GraphNode from;

    // 到哪里去
    public GraphNode to;

    // 权重
    public int value;

    public Edge(int value, GraphNode from, GraphNode to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

}
