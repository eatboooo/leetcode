package cn.eatboooo.study;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/6 23:48
 */
public class Demo02_heap {
    // 大根堆
    public static class maxHeap {
        int size;
        int[] heap;
        int limit;

        public maxHeap(int limit) {
            this.limit = limit;
            size = 0;
            heap = new int[limit];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int i) {
            if (isFull()) {
                return;
            }
            heap[size] = i;
            heapInset(heap, size++);
        }

        public int pop() throws Exception {
            if (isEmpty()) {
                throw new Exception("null");
            }
            int ans = heap[0];
            // 这里要先交换，一开始到时候妹想到，其实放 heapify 里面交换也可以
            swap(heap, 0, --size);
            heapify(heap, 0, size);
            return ans;
        }

        private void heapify(int[] heap, int i, int size) {
            // 和左右孩子比
            int left = (i << 1) | 1;
            // 判断是否越界
            while (left < size) {
                // 判断是否有右孩子,同时获取较大下标
                // 这个第一时间没有想到 需要巩固
                int la = (left + 1 < size) && heap[left + 1] > heap[left] ? left + 1 : left;
                la = heap[i] > heap[la] ? i : la;
                // 一个优化 一开始忘了
                if (la == i) {
                    break;
                }
                swap(heap, la, i);
                // 这个一开始忘了，需要巩固
                i = la;
                left = (left << 1) | 1;
            }
        }

        private void heapInset(int[] heap, int i) {
            //              0
            //        1           2
            //     3     4     5     6
            //   7  8  9  10
            // 画个完全二叉树
            // 不可以使用 (i - 1) >> 1，,当 i 为 0 的时候，这个会出现负数的情况
            // 下标 size 对应的父节点 （size -1）/2
            // int f = ((i - 1)/2);
            // 到最后 肯定是 f = 0 i = 0，所以一定会跳出循环
            while (heap[((i - 1) / 2)] < heap[i]) {
                swap(heap, ((i - 1) / 2), i);
                i = ((i - 1) / 2);
            }
        }


    }

    // 堆排序
    // 堆排序额外空间复杂度O(1)
    public static void heapSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        // 先把数组搞成大根堆,从后往前构造
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        // 然后一个一个往外丢 ， 这个必须是 length 否则会出大问题 （ 第一个值会出问题）
        int size = arr.length;
        // 先丢一个
        swap(arr,0,--size);
        // 直到把堆中的东西丢完
        while (size > 0) {// O(N)
            // 因为丢了一个，所以往下沉
            heapify(arr, 0, size);// O(logN)
            // 沉完接着丢
            swap(arr,0,--size);
        }
    }

    private static void heapify(int[] arr, int head, int size) {
        if (head >= size) {
            return;
        }
        // 和左节点比较
        int left = (head >> 1 | 1);
        while (left < size) {
            // 从左右孩子中选出最大的
            int la = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            // 比较自己和左右，如果没比过则往下落
            la = arr[head] > arr[la] ? head : la;
            if (la == head) {
                break;
            }
            swap(arr, head, la);
            head = la;
            // 交换完后继续寻找自己的左孩子
            left = head << 1 | 1;
        }

    }

    public static void main(String[] args) throws Exception {
        maxHeap maxHeap = new maxHeap(5);
        maxHeap.push(1);
        maxHeap.push(2);
        maxHeap.push(6);
        maxHeap.push(4);
        maxHeap.push(5);
        while (!maxHeap.isEmpty()) {
            int pop = maxHeap.pop();
            System.out.println("pop = " + pop);
        }
    }


    public static void swap(int[] arr, int l, int r) {
        int i = arr[l];
        arr[l] = arr[r];
        arr[r] = i;
    }
}
