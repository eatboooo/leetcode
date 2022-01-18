package cn.eatboooo.study.week07;

/**
 * bfprt 算法
 * 查询无序数组中第 k 小的数
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/17 19:57
 * <p>
 * 快排每次可以确定一个数字的位置
 * 所以确定之后看看下标是否对应
 * 如果不对应就在两侧中再选一个进行快拍
 * <p>
 * 选谁 ？随机选可能会遇到很坏的情况
 * bfprt 就是解决这个问题的
 */
public class Demo07_BFPRT_Base {
    // 改写快排的版本
    public static int topK(int[] arr, int k) {
        if (k > arr.length) {
            return -1;
        }
        int[] copy = copy(arr);
        return myTop(copy, k - 1, 0, arr.length - 1);
    }

    private static int[] copy(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    private static int myTop(int[] arr, int index, int l, int r) {
        if (l == r) {
            // 经过测试，这个判断删除对这道题不影响
            // 不排序
            return arr[l];
        }
        int pivot = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] range = fast(arr, l, r, pivot);

        // if (range[0] == index) { 这么写不行，可能有相等的情况
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        }
        if (index < range[0]) {
            // 左侧递归
            return myTop(arr, index, l, range[0] - 1);
        }
        // 右侧递归
        return myTop(arr, index, range[1] + 1, r);

    }

    private static int[] fast(int[] arr, int l, int r, int point) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            // ⚠️ 排查半天，大小写写反了
            if (arr[cur] < point) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > point) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int i1) {
        int temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;
    }

    // BFPRT bfprt 算法
    public static int topKBFPRT(int[] arr, int k) {
        if (k > arr.length) {
            return -1;
        }
        int[] copy = copy(arr);
        return myBFPRTTop(copy, k - 1, 0, arr.length - 1);
    }

    private static int myBFPRTTop(int[] arr, int index, int l, int r) {

        if (l == r) {
            // 经过测试，这个判断删除对这道题不影响
            // 不排序
            return arr[l];
        }
        // bfprt 就是干这个的
        int pivot = bfprt(arr, l, r);
        int[] range = fast(arr, l, r, pivot);

        // if (range[0] == index) { 这么写不行，可能有相等的情况
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        }
        if (index < range[0]) {
            // 左侧递归
            return myBFPRTTop(arr, index, l, range[0] - 1);
        }
        // 右侧递归
        return myBFPRTTop(arr, index, range[1] + 1, r);
    }

    // 5个一组
    // 全部排好序
    // 每一组中位数组成数组
    // 返回中位数组的中位数
    private static int bfprt(int[] arr, int l, int r) {
        // midArr
        // ⚠️
        int size = r - l + 1;
        int[] midArr = new int[size / 5 + size % 5 == 0 ? 0 : 1];
        for (int i = 0; i < midArr.length; i++) {
            // 5 group
            int groupFirst = i * 5 + l;
            midArr[i] = findMid(arr, groupFirst, Math.min(groupFirst + 4, r));
        }
        // Y ? 调用递归不 findMid？
        // 因为 5个一组，可能分组之后数量还是非常多，所以调用大方法
        // myBFPRTTop 相信他可以找到从 l～r 上 第 index 小/大 的位置
        return myBFPRTTop(midArr, midArr.length / 2, 0, midArr.length - 1);
    }

    // 找到数组中位数
    private static int findMid(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
}
