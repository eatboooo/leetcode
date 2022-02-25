package cn.eatboooo.study.week03.graph;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 10:26
 */
public class GraphGenerator {
    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    //
    // [ 5 , 0 , 7]
    // [ 3 , 0,  1]

    public static Graph createGraph(int[][] arr) {
        Graph graph = new Graph();
        for (int[] ints : arr) {
            int from = ints[1];
            int to = ints[2];

            if (!graph.city.containsKey(from)) {
                graph.city.put(from, new GraphNode(0));
            }

            if (!graph.city.containsKey(to)) {
                graph.city.put(to, new GraphNode(0));
            }

            GraphNode toCity = graph.city.get(to);
            GraphNode fromCity = graph.city.get(from);
            Edge edge = new Edge(ints[0],fromCity,toCity);

            toCity.in++;

            fromCity.out++;
            fromCity.nextCitys.add(toCity);
            fromCity.nextEdges.add(edge);
        }
        return graph;
    }
}
