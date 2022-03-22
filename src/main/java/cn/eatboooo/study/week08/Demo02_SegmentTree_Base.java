package cn.eatboooo.study.week08;

/**
 * 线段树
 * <p>
 * 线段树是一种支持范围整体修改和范围整体查询的数据结构
 * 线段树解决的问题范畴：
 * 大范围信息可以只由左、右两侧信息加工出，而不必遍历左右两个子范围的具体状况
 * 关键思想：lazy 、范围性揽活 、 数组表示二叉树（堆）...
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/22 17:31
 * <p>
 * Segment给定一个数组arr，用户希望你实现如下三个方法
 * 1）void add(int L, int R, int V) :  让数组arr[L…R]上每个数都加上V
 * 2）void update(int L, int R, int V) :  让数组arr[L…R]上每个数都变成V
 * 3）int sum(int L, int R) :让返回arr[L…R]这个范围整体的累加和
 * 怎么让这三个方法，时间复杂度都是O(logN)
 */
public class Demo02_SegmentTree_Base {
    public static class SegmentTree {
        // lazy[] 需要 增加/减少 的值
        // sum[] 总和, O never young
        // needUpdate[] 是否需要更新
        // update[] 更新的值
        // arr[] 原始数组
        int[] lazy;
        int[] sum;
        int[] update;
        int[] arr;
        boolean[] needUpdate;

        public SegmentTree(int[] origin) {
            // arr 代表下标从 1 开始的原始数组，noY
            int size = origin.length;
            arr = new int[size + 1];
            for (int i = 0; i < size; i++) {
                arr[i + 1] = origin[i];
            }
            // 初始化长度
            // y？ 手动画图，最坏情况是 size * 4（二叉树节点个数）
            lazy = new int[(size + 1) << 2];
            sum = new int[(size + 1) << 2];
            update = new int[(size + 1) << 2];
            needUpdate = new boolean[(size + 1) << 2];
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(mid + 1, r, rt << 1 | 1);
            build(l, mid, rt << 1);
            sum(rt);
        }

        // 计算和
        private void sum(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        // 把 L～R 的值增加 value，当前走到了 l～r，并且l～r的根节点是 rt
        // 所以第一次调用时，l：1、r：arr.length、rt：1
        public void add(int L, int R, int value, int l, int r, int rt) {
            if (L <= l && r <= R) {
                lazy[rt] += value;
                sum[rt] += value * (r - l + 1);
                return;
            }
            int mid = (l + r) >> 1;
            doLazy(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, value, l, mid, rt << 1);
            }
            if (mid < R) {
                add(L, R, value, mid + 1, r, rt << 1 | 1);
            }
            sum(rt);
        }

        public void update(int L, int R, int value, int l, int r, int rt) {
            if (L <= l && r <= R) {
                lazy[rt] = 0;
                needUpdate[rt] = true;
                update[rt] = value;
                // ⚠️ r - l !!!!
                sum[rt] = value * (r - l + 1);
                return;
            }
            int mid = (l + r) >> 1;
            doLazy(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, value, l, mid, rt << 1);
            }
            if (mid < R) {
                update(L, R, value, mid + 1, r, rt << 1 | 1);
            }
            sum(rt);
        }

        // L、R 表示任务
        // l、r 表示当前来到了哪里
        // rt 表示 l～r 的根节点
        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            // 中点、区分左右
            int mid = (l + r) >> 1;
            // 如果有任务就立刻给我办了
            doLazy(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

        // rt：根节点
        // lSize: 左子树元素结点个数
        // rSize: 右子树元素结点个数
        private void doLazy(int rt, int lSize, int rSize) {
            // 先办完更新的任务、再补充修改的任务
            // 左树头节点和右树头节点
            int leftRoot = rt << 1;
            int rightRoot = leftRoot | 1;

            if (needUpdate[rt]) {
                needUpdate[rt] = false;
                needUpdate[leftRoot] = true;
                needUpdate[rightRoot] = true;
                update[leftRoot] = update[rt];
                update[rightRoot] = update[rt];
                lazy[leftRoot] = 0;
                lazy[rightRoot] = 0;
                sum[leftRoot] = update[rt] * lSize;
                sum[rightRoot] = update[rt] * rSize;
            }
            if (lazy[rt] != 0) {
                // ⚠️ lazy[leftRoot] += lazy[rt];  += ！！！！
                lazy[leftRoot] += lazy[rt];
                lazy[rightRoot] += lazy[rt];
                sum[leftRoot] += lazy[rt] * lSize;
                sum[rightRoot] += lazy[rt] * rSize;
                lazy[rt] = 0;
            }
        }
    }
}
