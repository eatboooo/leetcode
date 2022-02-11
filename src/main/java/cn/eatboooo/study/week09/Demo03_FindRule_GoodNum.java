package cn.eatboooo.study.week09;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:20
 *
 * 定义一种数：可以表示成若干（数量>1）连续正数和的数
 * 比如，5=2+3，5就是这样的数；12=3+4+5，12就是这样的数
 * 2=1+1，2不是这样的数，因为等号右边不是连续正数
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
 */
public class Demo03_FindRule_GoodNum {
    public static boolean goodNum(int num) {
        int temp = 0;
        for (int i = 1; i < num; i++) {
            temp = 0;
            for (int j = i; j < num; j++) {
                temp += j;
                if (temp == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean myRules(int num) {
        return (num & (-num)) != num;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            boolean b = goodNum(i);
            boolean m = myRules(i);
            if (m != b) {
                System.out.println(i + "!!!!!!");
            }
//            if (!b) {
//                System.out.println(i + " = " + b);
//            }
        }
    }
}
