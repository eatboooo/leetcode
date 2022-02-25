package cn.eatboooo.study.week03.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 并查集、图 专题
 * 图的深度优先遍历
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/25 11:28
 *
 * 深度优先遍历图片
 * https://upload-images.jianshu.io/upload_images/356361-d43802c88c464373.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp
 */
public class Demo07_Graph_DFS {
    public static void dfs(GraphNode node) {
        if (node == null) {
            return;
        }
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);
        Set<GraphNode> set = new HashSet<>();
        set.add(node);
        while (!stack.isEmpty()) {
            GraphNode cur = stack.pop();
            for (GraphNode nextCity : cur.nextCitys) {
                if (!set.contains(nextCity)) {
                    // ⚠️ 记得 push 父节点 ⚠️
                    // 因为如果不 push 父节点，第 n 层原本有多个节点，可能只打印了一个节点后，就开始打印第 n - 1 层了
                    // 按理说深度优先是要把 n 层全部打印完才可以的
                    stack.push(cur);

                    stack.push(nextCity);
                    set.add(nextCity);

                    System.out.println("nextCity.value = " + nextCity.value);
                    break;
                }
            }
        }
    }
}
