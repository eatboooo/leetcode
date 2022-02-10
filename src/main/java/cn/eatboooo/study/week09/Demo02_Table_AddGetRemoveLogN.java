package cn.eatboooo.study.week09;

import java.util.ArrayList;

/**
 * 有序表改造练习
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:13
 * <p>
 * 设计一个结构包含如下两个方法：
 * void add(int index, int num)：把num加入到index位置
 * int get(int index) ：取出index位置的值
 * void remove(int index) ：把index位置上的值删除
 * 要求三个方法时间复杂度O(logN)
 */
public class Demo02_Table_AddGetRemoveLogN {
    public static class SBTNode<V> {
        public V value;
        public SBTNode<V> l;
        public SBTNode<V> r;
        public int size; // 不同的key的数量

        public SBTNode(V value) {
            this.value = value;
            size = 1;
        }
    }

    public static class SbtList<V> {
        private SBTNode<V> root;

        public void add(int index, V num) {
            // 边界条件很重要
            SBTNode<V> cur = new SBTNode<V>(num);
            if (root == null) {
                root = cur;
            } else {
                if (index <= root.size) {
                    root = add(cur, index, root);
                }
            }
        }

        public V get(int index) {
            return getIndex(root, index).value;
        }

        public void remove(int index) {
            if (index >= 0 && size() > index) {
                root = delete(root, index);
            }
        }

        private SBTNode<V> rightRotate(SBTNode<V> cur) {
            SBTNode<V> leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return leftNode;
        }

        private SBTNode<V> leftRotate(SBTNode<V> cur) {
            SBTNode<V> rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return rightNode;
        }


        // 和 avl 违规很像，四种
        // LL: 左孩子的左孩子 size > 右孩子 size
        // LR: 左孩子的右孩子 size > 右孩子 size
        private SBTNode<V> maintain(SBTNode<V> cur) {
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

        // 现在，以cur为头的树上，新增，加(key, value)这样的记录
        // 加完之后，会对cur做检查，该调整调整
        // 返回，调整完之后，整棵树的新头部
        private SBTNode<V> add(SBTNode<V> cur, int index, SBTNode<V> root) {
            if (root == null) {
                return cur;
            }
            root.size++;
            int leftSize = (root.l != null ? root.l.size : 0) + 1;
            // 一定是 >= !!!,排查老半天
            // 如果 下标等于左侧size + 1，则证明是 root 的第一个右孩子
            if (index >= leftSize) {
                // 第一次传参 顺序写反了
                root.r = add(cur, index - leftSize, root.r);
            } else {
                root.l = add(cur, index, root.l);
            }
            // yyy?
            return  maintain(root);
        }

        // 在cur这棵树上，删掉key所代表的节点
        // 返回cur这棵树的新头部
        private SBTNode<V> delete(SBTNode<V> cur, int index) {
            cur.size--;
            int rootIndex = cur.l != null ? cur.l.size : 0;
            if (index != rootIndex) {
                if (index > rootIndex) {
                    cur.r = delete(cur.r, index - rootIndex - 1);
                } else {
                    cur.l = delete(cur.l, index);
                }
                return cur;
            }

            // 此时需要删除 index
            if (cur.l == null && cur.r == null) {
                return null;
            }
            if (cur.l == null) {
                return cur.r;
            }
            if (cur.r == null) {
                return cur.l;
            }

            // 有左有右
            SBTNode<V> pre = null;
            SBTNode<V> des = cur.r;
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
            // cur = des;
            // cur = maintain(cur);
            return des;
        }

        private SBTNode<V> getIndex(SBTNode<V> cur, int kth) {
            int rootIndex = cur.l == null ? 0 : cur.l.size;
            if (kth == rootIndex) {
                return cur;
            } else if (kth < rootIndex) {
                return getIndex(cur.l, kth);
            } else {
                // 多减 1，减去跟节点本身
                return getIndex(cur.r, kth - rootIndex - 1);
            }
        }

        public int size() {
            return root == null ? 0 : root.size;
        }

    }


    // 通过以下这个测试，
    // 可以很明显的看到LinkedList的插入、删除、get效率不如SbtList
    // LinkedList需要找到index所在的位置之后才能插入或者读取，时间复杂度O(N)
    // SbtList是平衡搜索二叉树，所以插入或者读取时间复杂度都是O(logN)
    public static void main(String[] args) {
        // 功能测试
        int test = 50000;
        int max = 1000000;
        boolean pass = true;
        ArrayList<Integer> list = new ArrayList<>();
        SbtList<Integer> sbtList = new SbtList<>();
        for (int i = 0; i < test; i++) {
            if (list.size() != sbtList.size()) {
                pass = false;
                System.out.println("大小不一样");
                break;
            }
            if (list.size() > 1 && Math.random() < 0.5) {
                int removeIndex = (int) (Math.random() * list.size());
                list.remove(removeIndex);
                sbtList.remove(removeIndex);
                // System.out.println("remove:" + (list.size() == sbtList.size()));
                for (int j = 0; j < list.size(); j++) {
                    if (!list.get(j).equals(sbtList.get(j))) {
                        pass = false;
                        System.out.println("remove 最终不一致");
                        return;
                    }
                }
                System.out.println("remove success");
            } else {
                int randomIndex = (int) (Math.random() * (list.size() + 1));
                int randomValue = (int) (Math.random() * (max + 1));
                list.add(randomIndex, randomValue);
                sbtList.add(randomIndex, randomValue);
                // System.out.println("add:" + (list.size() == sbtList.size()));
                for (int j = 0; j < list.size(); j++) {
                    if (!list.get(j).equals(sbtList.get(j))) {
                        pass = false;
                        System.out.println("add 最终不一致");
                        return;
                    }
                }
                System.out.println("add success");
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(sbtList.get(i))) {
                pass = false;
                System.out.println("最终不一致");
                break;
            }
        }
        System.out.println("功能测试是否通过 : " + pass);

        // 性能测试
        test = 500000;
        list = new ArrayList<>();
        sbtList = new SbtList<>();
        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (list.size() + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            list.add(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList插入总时长(毫秒) ： " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            list.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList读取总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * list.size());
            list.remove(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList删除总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (sbtList.size() + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            sbtList.add(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList插入总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            sbtList.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList读取总时长(毫秒) :  " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * sbtList.size());
            sbtList.remove(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList删除总时长(毫秒) :  " + (end - start));

    }

}
