package cn.eatboooo.study.week08;

/**
 * indexTree 实现
 * 单点更新强于线段树的一种结构、
 * 有点二进制的感觉，长度一样的凑成一对
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/22 17:55
 *
 * IndexTree
 * 1）支持区间查询
 * 2）没有线段树那么强，但是非常容易改成一维、二维、三维的结构
 * 3）只支持单点更新
 *
 */
public class Demo03_IndexTree_Base {
    public static class IndexTree {
        int tree[];

        public IndexTree(int size) {
            // 下标从 1 开始，否则之后的位置不好算
            tree = new int[size + 1];
        }

        // 初始化
        public void build(int[] origin) {
            for (int i = 0; i < origin.length; i++) {
                add(i + 1, origin[i]);
            }
        }

        public void add(int index, int value) {
            while (index < tree.length) {
                tree[index] += value;
                // 提取最右侧的 1， -index 相当于 ～index + 1
                index += index & -index;
            }
        }

        // 1 ~ index 的累加合
        public int sum(int index) {
            int ans = 0;
            while (index >= 1) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

        public int sum(int l, int r) {
            if (l > r) {
                return Integer.MIN_VALUE;
            }
            return sum(r) - sum(l);
        }
    }
}
