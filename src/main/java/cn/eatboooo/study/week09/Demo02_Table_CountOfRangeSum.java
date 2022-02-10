package cn.eatboooo.study.week09;

import java.util.HashSet;

/**
 * 有序表改造练习
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:09
 * <p>
 * 给定一个数组arr，和两个整数a和b（a<=b）。
 * 求arr中有多少个子数组，累加和在[a,b]这个范围上。返回达标的子数组数量
 */
public class Demo02_Table_CountOfRangeSum {
    public static int countRangeSum2(int[] nums, int lower, int upper) {
        long sum = 0;
        long ans = 0;
        SizeBalancedTreeMap sb = new SizeBalancedTreeMap();
        sb.add(0);
        for (int i = 0; i < nums.length; i++) {
            // 以 i 为结尾开始尝试
            sum += nums[i];
            // +1 加上自己
            // 累加和在[a,b]这个范围上 转化为 之前有哪些累加和在 [sum - b,sum - a]这个范围上
            long a = sb.findLastNoBigIndex(sum - lower + 1);
            long b = sb.findLastNoBigIndex(sum - upper);
            ans += a - b;
            sb.add(sum);
        }
        return (int) ans;
    }

    public static class SBTNode {
        public long key;
        public SBTNode l;
        public SBTNode r;
        public long size; // 不同的key的数量，用作平衡因子
        public long allSize; // 真实的全部数量

        public SBTNode(long key) {
            this.key = key;
            allSize = 1;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap {
        private SBTNode root;
        private HashSet<Long> set = new HashSet<>();

        private SBTNode rightRotate(SBTNode cur) {
            // 我节点下藏了多少个和我一样的
            long mySame = cur.allSize - (cur.l == null ? 0 : cur.l.allSize) - (cur.r == null ? 0 : cur.r.allSize);

            SBTNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            leftNode.allSize = cur.allSize;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;

            // 我藏的小弟 + l.allSize + r.allSize
            cur.allSize = (cur.l != null ? cur.l.allSize : 0) + (cur.r != null ? cur.r.allSize : 0) + mySame;
            return leftNode;
        }

        private SBTNode leftRotate(SBTNode cur) {
            // 我节点下藏了多少个和我一样的
            long mySame = cur.allSize - (cur.l == null ? 0 : cur.l.allSize) - (cur.r == null ? 0 : cur.r.allSize);

            SBTNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            rightNode.allSize = cur.allSize;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;

            // 我藏的小弟 + l.allSize + r.allSize
            cur.allSize = (cur.l != null ? cur.l.allSize : 0) + (cur.r != null ? cur.r.allSize : 0) + mySame;
            return rightNode;
        }

        // 和 avl 违规很像，四种
        // LL: 左孩子的左孩子 size > 右孩子 size
        // LR: 左孩子的右孩子 size > 右孩子 size
        private SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            long leftSize = cur.l != null ? cur.l.size : 0;
            long leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            long leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            long rightSize = cur.r != null ? cur.r.size : 0;
            long rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            long rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            if (leftLeftSize > rightSize) {
                // LL 违规
                cur = rightRotate(cur);
                // Y 递归调 ？ 还债罢了。。很久不调整，一次给他干完
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                // LR 违规
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        private long findLastNoBigIndex(long key) {
            long ans = 0;
            SBTNode cur = root;
            while (cur != null) {
                if (key - cur.key == 0) {
                    ans += cur.l == null ? 0 : cur.l.allSize;
                    return ans;
                } else if (key - cur.key < 0) {
                    cur = cur.l;
                } else {
                    ans += cur.allSize - (cur.r == null ? 0 : cur.r.allSize);
                    cur = cur.r;
                }
            }
            return ans;
        }

        // 现在，以cur为头的树上，新增，加(key, value)这样的记录
        // 加完之后，会对cur做检查，该调整调整
        // 返回，调整完之后，整棵树的新头部
        private SBTNode add(SBTNode cur, long value, boolean c) {
            if (cur == null) {
                return new SBTNode(value);
            } else {
                cur.allSize++;
                if (cur.key == value) {
                    // 至关重要，此时 allSize 已经加入，不需要再添加新节点了
                    return cur;
                }
                if (!c) {
                    cur.size++;
                }
                if (value - cur.key < 0) {
                    cur.l = add(cur.l, value, c);
                } else {
                    cur.r = add(cur.r, value, c);
                }
                return maintain(cur);
            }


        }

        private SBTNode add(long value) {
            boolean contains = set.contains(value);
            set.add(value);
            // 接住 头可能会发生变化，至关重要！！
            root = add(root, value, contains);
            return root;
        }
    }


}
