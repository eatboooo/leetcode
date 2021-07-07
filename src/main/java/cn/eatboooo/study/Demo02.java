package cn.eatboooo.study;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/4 15:37
 * <p>
 * 归并排序和随机快排
 * 比较器与堆
 */
public class Demo02 {
    // 归并排序 - 左边排好序，右边排好序，两边一结合
    // 归并排序 - 无递归方法：2个数合并 -》4个数合并 -》8个数合并 -》。。。直到搞完
    // 归并排序 - O（N*logN）
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processMergeSort(arr, 0, arr.length - 1);
    }

    public static void processMergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        // 左边排好序
        processMergeSort(arr, l, mid);
        // 右边排好序
        processMergeSort(arr, mid + 1, r);
        // 然后一合并
        merge(arr, l, mid, r);
    }

    // 合并两个排好序的数组
    private static void merge(int[] arr, int l, int mid, int r) {
        // 用于临时存储，最后统一写回去
        int[] help = new int[r - l + 1];
        // 临时数组的下标
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        // 比较两个数组，先把小的存到临时数组总
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        // 第一次这里写错了 写成了 while (p2 <= mid) ，一定要细心
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
    }


    // 归并排序精髓 - 数组小和 - 每一次合并的时候都产生小和
    // 归并排序应用：降序对、右边有多少数比你小 这类问题


    // 快速排序
    // 荷兰国旗问题 作为快排的基础
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
                //less++;  这句不需要 因为等于指标的数放中间   （小于的，等于的，大于的）
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --more);
            } else {
                swap(arr, index++, ++less);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }


    // 快排 1.0，O（N的平方）

    // 快排 3.0，O（logN * N），搞个随机数
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSortProcess(arr,0,arr.length-1);
    }

    // 块排 3.0 具体实现
    private static void quickSortProcess(int[] arr, int l, int r) {
        // 这边是大于等于，第一次写成了 == 导致了数组越界，需要注意
        if (l >= r) {
            return;
        }
//        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        swap(arr, (int) (Math.random()*(r-l+1)) + l,r);
        // 使用荷兰国旗问题，帮助确定中间数的位置（确定下标为ints[0]~int[1]的位置）
        int[] ints = netherlandsFlag(arr, l, r);
        quickSortProcess(arr,l,ints[0]-1);
        quickSortProcess(arr,ints[1]+1,r);
    }

    // 堆结构 - 逻辑上是完全二叉树结构
    // 大根堆
    // 小根堆

    // 静态修饰符学习？


    // 比较器
    public static class Person {
        int age;
        int id;

        public Person(int age, int id) {
            this.age = age;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    public static class myPersonCompare implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o2.age - o1.age;
        }
    }


    public static void swap(int[] arr, int l, int r) {
        int i = arr[l];
        arr[l] = arr[r];
        arr[r] = i;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
         testMerge();
//        testCompare();
    }

    // 测试比较器
    private static void testCompare() {
        Person p1 = new Person(12, 0);
        Person p2 = new Person(11, 0);
        Person p3 = new Person(13, 0);
        Person[] ps = {p1, p2, p3};
        System.out.println("===========排序前===========");
        for (Person p : ps) {
            System.out.print("p = " + p);
        }
        System.out.println();
        Arrays.sort(ps, new myPersonCompare());
        System.out.println("===========排序后===========");
        for (Person p : ps) {
            System.out.print("p = " + p);
        }
    }

    // 测试排序
    private static void testMerge() {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
//            mergeSort1(arr1);
            Arrays.sort(arr2);
//            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
