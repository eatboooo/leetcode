package cn.eatboooo.study.week09;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/12 22:03
 * 给定一个正方形或者长方形矩阵matrix，实现转圈打印
 */
public class Demo05_Coding_PrintMatrixSpiralOrder {
    // 首先搞一个打印最外圈的方法
    // 然后循环向内
    // 即可实现
    public static void print(int[][] arr) {
        int n = arr.length;
        int a = 0;
        int b = n - 1;
        int c = 0;
        int d = n - 1;
        while (a <= b && c <= d) {
            printMatrix(arr, a++, b--, c++, d--);
        }
    }

    public static void printMatrix(int[][] arr, int a, int b, int c, int d) {
        // 画图
        // 打印 A ～ B
        int index = a;
        while (index != d) {
            System.out.print(arr[a][index] + " ");
            index++;
        }

        // 打印 B ～ C
        index = c;
        while (index != b) {
            System.out.print(arr[index][b] + " ");
            index++;
        }

        // 打印 C ～ D
        index = d;
        while (index > a) {
            System.out.print(arr[d][index] + " ");
            index--;
        }

        // 打印 D ～ A
        index = b;
        while (index > c) {
            System.out.print(arr[index][c] + " ");
            index--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        print(matrix);
    }
}
