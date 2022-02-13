package cn.eatboooo.study.week09;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/12 22:03
 *
 * 给定一个正方形或者长方形矩阵matrix，实现zigzag打印
 * zigzag 曲折
 */
public class Demo05_Coding_ZigZagPrintMatrix {
    // 思路：写个函数，传入两个点，实现两点连线斜线打印
    // 两个点 a，b；
    // a 往右走到头往下
    // b 往下走到头往有
    // 疯狂调用  func（a，b）

    // this is copy for study
    public static void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1) {
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
        if (f) {
            while (tR != dR + 1) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
