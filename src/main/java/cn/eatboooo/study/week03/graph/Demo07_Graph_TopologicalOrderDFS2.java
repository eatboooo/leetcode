package cn.eatboooo.study.week03.graph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 并查集、图 专题
 * 图的拓扑排序
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 15:52
 *
 * DFS 版本2：与版本 1 的不同之处在于，
 *  版本 1：如果可以去的城市越深，说明拓扑序越靠前
 *  版本 2：如果可以去的城市越多，说明拓扑序越靠前
 */
public class Demo07_Graph_TopologicalOrderDFS2 {
    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 用来装载 deep
        HashMap<DirectedGraphNode, CityDeep> map = new HashMap<>();
        // 初始化每个城市的 deep
        for (DirectedGraphNode city : graph) {
            f(city, map);
        }

        ArrayList<CityDeep> arr = new ArrayList<>();
        for (CityDeep value : map.values()) {
            arr.add(value);
        }
        // deep 深的在前
        arr.sort((o1, o2) -> o2.deep == o1.deep ? 0 : (o2.deep > o1.deep ? 1 : -1));

        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (CityDeep cityDeep : arr) {
            ans.add(cityDeep.city);
        }

        return ans;
    }

    // 初始化 deep
    private static CityDeep f(DirectedGraphNode city, HashMap<DirectedGraphNode, CityDeep> map) {
        if (map.containsKey(city)) {
            return map.get(city);
        }
        long count = 1;
        for (DirectedGraphNode go : city.neighbors) {
            count += f(go, map).deep;
        }
        CityDeep ans = new CityDeep(city, count);
        map.put(city, ans);
        return ans;
    }

    public static class CityDeep{
        public DirectedGraphNode city;
        public long deep;

        public CityDeep(DirectedGraphNode city, long deep) {
            this.city = city;
            this.deep = deep;
        }
    }
}
