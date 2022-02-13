package cn.eatboooo.study.week09;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/12 22:03
 * <p>
 * 转圈打印星号*问题
 */
public class Demo05_Coding_PrintStar {
    public static void printStar(int n) {
        char[][] arr = new char[n][n];
        for (int i = 0; i <= n / 2; i+=2) {
            printMatrix(arr, i, n - i - 1);
            System.out.println("i = " + i);
            System.out.println("n-i-1 = " + (n - i - 1));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMatrix(char[][] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            arr[l][i] = '*';
        }
        for (int i = l + 1; i <= r; i++) {
            arr[i][r] = '*';
        }
        for (int i = r - 1; i > l; i--) {
            arr[r][i] = '*';
        }
        for (int i = r - 1; i > l + 1; i--) {
            arr[i][l + 1] = '*';
        }
    }
    public static void main(String[] args) {
        printStar(5);
    }

}
