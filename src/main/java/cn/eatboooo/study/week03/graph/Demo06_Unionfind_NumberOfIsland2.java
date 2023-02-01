package cn.eatboooo.study.week03.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 并查集、图 专题
 * 岛屿的数量
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/24 20:39
 * <p>
 * 岛屿问题的版本 2
 * 从一开始的全部给你，变成了一个个去空降
 */
public class Demo06_Unionfind_NumberOfIsland2 {
    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind union = new UnionFind(m, n);
        LinkedList<Integer> ans = new LinkedList<>();
        for (int[] position : positions) {
            ans.add(union.connet(position[0], position[1]));
        }
        return ans;
    }

    // 数组版本
    public static class UnionFind {
        // 父亲是谁
        int[] parent;

        // 每坨内部数量
        int[] size;

        int[] help;

        // 用来计算下标的
        final int row;
        final int col;

        // 有几坨
        int sets;

        public UnionFind(int row, int col) {
            this.row = row;
            this.col = col;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        public int index(int row, int col) {
            return row * this.col + col;
        }

        public int findFather(int i) {
            int stackIndex = 0;
            while (i != parent[i]) {
                help[stackIndex++] = i;
                i = parent[i];
            }
            while (stackIndex != 0) {
                parent[help[--stackIndex]] = i;
            }
            return i;
        }

        public void union(int row1, int col1, int row2, int col2) {
            int index1 = index(row1, col1);
            int index2 = index(row2, col2);
            if (index1 > row * col || index2 > row * col) {
                // 越界
                return;
            }
            int f1 = findFather(index1);
            int f2 = findFather(index2);

            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    parent[f2] = f1;
                    size[f1] += size[f2];
                    // size 用来标记是否初始化过，所以本次就不清零了
                    // size[f2] = 0;
                } else {
                    parent[f1] = f2;
                    size[f2] += size[f1];
                    // size 用来标记是否初始化过，所以本次就不清零了
                    // size[f1] = 0;
                }
                sets--;
            }
        }

        public int connet(int r, int c) {
            int index = index(r, c);
            if (size[index] != 0) {
                return sets;
            }
            // 此时说明没初始化过
            parent[index] = index;
            size[index] = 1;
            sets++;

            // 开始感染！
            union(r - 1, c, r, c);
            union(r + 1, c, r, c);
            union(r, c - 1, r, c);
            union(r, c + 1, r, c);

            return sets;
        }
    }
}
