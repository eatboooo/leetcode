package cn.eatboooo.study.week09;

/**
 * 有序表改造练习
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:11
 * <p>
 * 有一个滑动窗口：
 * 1）L是滑动窗口最左位置、R是滑动窗口最右位置，一开始LR都在数组左侧
 * 2）任何一步都可能R往右动，表示某个数进了窗口
 * 3）任何一步都可能L往右动，表示某个数出了窗口
 * 想知道每一个窗口状态的中位数
 *
 * 只是在原有有序表的基础上包装了一层
 * 以此来接收重复数字
 * 包装类需要重写比较方法，优先比较值，值相同的情况下比较下标
 */
public class Demo02_Table_SlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 4, 5, 6, 1};
        System.out.println("nums.length = " + nums.length);
        double[] doubles = medianSlidingWindow(nums, 3);
        for (double aDouble : doubles) {
            System.out.println("aDouble = " + aDouble);
        }
    }

    // nums ： 原数组，k ： 窗口大小
    public static double[] medianSlidingWindow(int[] nums, int k) {
        SizeBalancedTreeMap<Node> map = new SizeBalancedTreeMap();
        double[] ans = new double[nums.length - k + 1];
        for (int i = 0; i < k - 1; i++) {
            map.add(new Node(i, nums[i]));
        }
        for (int i = k - 1; i < nums.length; i++) {
            // 以 i 作为数组的最后一个数字，开始遍历

            // 第一遍写的时候这里忘记 add 了
            map.add(new Node(i, nums[i]));

            int temp = 0;
            if (map.size() % 2 != 0) {
                temp = map.getIndexKey(map.size() / 2).value;
            } else {
                temp = (map.getIndexKey(map.size() / 2).value + map.getIndexKey(map.size() / 2 - 1).value) / 2;
            }
            ans[i - k + 1] = temp;

            map.remove(new Node(i - k + 1, nums[i - k + 1]));
        }
        return ans;
    }

    public static class Node implements Comparable<Node> {
        // 包装
        public int index;
        // real
        public int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return value != o.value ? value - o.value : index - o.index;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>> {
        private SBTNode<K> root;

        private SBTNode<K> rightRotate(SBTNode<K> cur) {
            SBTNode<K> leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return leftNode;
        }

        private SBTNode<K> leftRotate(SBTNode<K> cur) {
            SBTNode<K> rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return rightNode;
        }

        private SBTNode<K> maintain(SBTNode<K> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
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

        private SBTNode<K> findLastIndex(K key) {
            SBTNode<K> pre = root;
            SBTNode<K> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        private SBTNode<K> add(SBTNode<K> cur, K key) {
            if (cur == null) {
                return new SBTNode<K>(key);
            } else {
                cur.size++;
                if (key.compareTo(cur.key) < 0) {
                    cur.l = add(cur.l, key);
                } else {
                    cur.r = add(cur.r, key);
                }
                return maintain(cur);
            }
        }

        private SBTNode<K> delete(SBTNode<K> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) > 0) {
                cur.r = delete(cur.r, key);
            } else if (key.compareTo(cur.key) < 0) {
                cur.l = delete(cur.l, key);
            } else {
                if (cur.l == null && cur.r == null) {
                    // free cur memory -> C++
                    cur = null;
                } else if (cur.l == null && cur.r != null) {
                    // free cur memory -> C++
                    cur = cur.r;
                } else if (cur.l != null && cur.r == null) {
                    // free cur memory -> C++
                    cur = cur.l;
                } else {
                    SBTNode<K> pre = null;
                    SBTNode<K> des = cur.r;
                    des.size--;
                    while (des.l != null) {
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if (pre != null) {
                        pre.l = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
                    // free cur memory -> C++
                    cur = des;
                }
            }
            return cur;
        }

        private SBTNode<K> getIndex(SBTNode<K> cur, int kth) {
            if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
                return cur;
            } else if (kth <= (cur.l != null ? cur.l.size : 0)) {
                return getIndex(cur.l, kth);
            } else {
                return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
            }
        }

        public int size() {
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
        }

        public void add(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K> lastNode = findLastIndex(key);
            if (lastNode == null || key.compareTo(lastNode.key) != 0) {
                root = add(root, key);
            }
        }

        public void remove(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }

        public K getIndexKey(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).key;
        }

    }

    public static class SBTNode<K extends Comparable<K>> {
        public K key;
        public SBTNode<K> l;
        public SBTNode<K> r;
        public int size;

        public SBTNode(K k) {
            key = k;
            size = 1;
        }
    }

}
