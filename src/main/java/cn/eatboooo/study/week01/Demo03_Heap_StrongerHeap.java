package cn.eatboooo.study.week01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 加强堆
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/28 10:53
 * <p>
 * T一定要是非基础类型，有基础类型需求包一层
 */
public class Demo03_Heap_StrongerHeap {

    private static class Heap<T> {
        // 反向索引表
        HashMap<T, Integer> indexMap = new HashMap<>();
        // 数组
        ArrayList<T> arr = new ArrayList<>();
        // 堆大小
        int size;
        // 比较器
        // 关于 ？super T 的答疑：https://www.zhihu.com/question/20400700
        Comparator<? super T> comparator;

        public Heap(Comparator<? super T> comparator) {
            indexMap = new HashMap<>();
            arr = new ArrayList<>();
            size = 0;
            this.comparator = comparator;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }

        public void push(T t) {
            arr.add(size, t);
            indexMap.put(t, size);
            heapInsert(size++);
        }

        public T peek() {
            if (isEmpty()) {
                return null;
            }
            return arr.get(0);
        }

        public T pop() {
            if (isEmpty()) {
                return null;
            }
            T ans = arr.get(0);
            // 删除顶部
            remove();
            return ans;
        }

        public void put(T t) {
            if (!indexMap.containsKey(t)) {
                return;
            }
            Integer index = indexMap.get(t);
            heapify(index);
            heapInsert(index);
        }

        // 删除 他
        public void delete(T t) {
            if (!indexMap.containsKey(t)) {
                return;
            }
            // 获取被删除节点堆下标
            Integer index = indexMap.get(t);
            // 找替代品
            T replace = arr.get(size - 1);
            // 从反向索引表中删除 他
            indexMap.remove(t);
            // 删除替代品曾经的位置
            arr.remove(--size);

            if (index != size - 1) {
                // 把替代品放入 他 的位置，反向索引表
                indexMap.put(replace, index);
                // 把替代品放入 他 的位置，数组
                arr.set(index, replace);
                // 看看能不能往上或者往下 非常重要‼️
                heapify(index);
                heapInsert(index);
            }
        }

        // 删除顶部
        private void remove() {
            swap(0, size - 1);
            indexMap.remove(arr.get(size - 1));
            arr.remove(--size);
            heapify(0);
        }

        // 从 index 位置往上
        private void heapInsert(int index) {
            // find father
            int father;
            while (index != 0) {
                father = (index - 1) / 2;
                if (comparator.compare(arr.get(father), arr.get(index)) <= 0) {
                    return;
                }
                swap(father, index);
                index = father;
            }
        }

        // 下坠
        private void heapify(int index) {
            // 下坠左侧
            int left = index * 2 + 1;
            // 看看能否下坠
            while (left < size) {
                // 找出左右最小
                int best = left + 1 == size ? left : comparator.compare(arr.get(left), arr.get(left + 1)) > 0 ? left + 1 : left;
                // 找出上下最小
                best = comparator.compare(arr.get(best), arr.get(index)) > 0 ? index : best;
                if (best == index) {
                    return;
                }
                swap(best, index);
                index = best;
                left = index * 2 + 1;
            }
        }

        public void swap(int index1, int index2) {
            T t1 = arr.get(index1);
            T t2 = arr.get(index2);
            indexMap.put(t1, index2);
            indexMap.put(t2, index1);
            arr.set(index2, t1);
            arr.set(index1, t2);
        }
    }

    private static class HeapNode {
        int value;

        public HeapNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HeapNode[] arr = new HeapNode[]{
                new HeapNode(7),
                new HeapNode(3),
                new HeapNode(2),
                new HeapNode(6),
                new HeapNode(5),
        };
        Heap<HeapNode> heap = new Heap<>((o1, o2) -> o1.value - o2.value);
        // 测试基本功能
        System.out.println("-----------------基本功能-----------------");
        for (HeapNode node : arr) {
            heap.push(node);
        }

        while (!heap.isEmpty()) {
            System.out.println("heap.pop().value = " + heap.pop().value);
        }

        // 测试删除功能
        System.out.println("-----------------删除功能-----------------");
        for (HeapNode node : arr) {
            heap.push(node);
        }
        heap.delete(arr[1]);
        heap.delete(arr[4]);
        while (!heap.isEmpty()) {
            System.out.println("heap.pop().value = " + heap.pop().value);
        }

    }
}
