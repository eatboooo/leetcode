package cn.eatboooo.study.week09;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/12 22:03
 * <p>
 * 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子
 *
 * 写成逆时针了 算了就这样了哈
 */
public class Demo05_Coding_RotateMatrix {
    // 首先搞一个反转最外圈的方法
    // 然后循环向内
    // 即可实现
    public static void rotate(int[][] arr) {
        int n = arr.length;
        int a = 0;
        int b = n - 1;
        int c = 0;
        int d = n - 1;
        while (a <= b && c <= d) {
            rotate(arr, a++, b--, c++, d--);
        }
    }

    public static void rotate(int[][] arr, int a, int b, int c, int d) {
        for (int i = 0; i < b - a; i++) {
            // 四条边依次来
            int temp = arr[c][a + i];
            arr[c][a + i] = arr[c + i][b];
            arr[c + i][b] = arr[d][b - i];
            arr[d][b - i] = arr[d - i][a];
            arr[d - i][a] = temp;
        }

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
