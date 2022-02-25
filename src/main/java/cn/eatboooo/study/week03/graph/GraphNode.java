package cn.eatboooo.study.week03.graph;

import java.util.ArrayList;

/**
 * 城市
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 10:04
 */
public class GraphNode {
    // 有多少个边连接进来
    public int in;

    // 从该点出发的有多少条边
    public int out;

    // 点的权重
    public int value;

    // 从该点出发下一个可以到达的城市
    public ArrayList<GraphNode> nextCitys;

    // 从该点出发可以走的下一条边
    public ArrayList<Edge> nextEdges;

    public GraphNode(int value) {
        this.in = 0;
        this.out = 0;
        this.value = value;
        this.nextCitys = new ArrayList<>();
        this.nextEdges = new ArrayList<>();
    }
}
