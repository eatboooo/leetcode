package cn.eatboooo.study.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/4 16:53
 */
public class Test {
    @org.junit.Test
    public void demoTest01() {
        int[] ints = Demo01.myByte01(new int[]{2, 2, 3, 3, 2, 9});
        System.out.println("ints = " + ints[0]);
        System.out.println("ints = " + ints[1]);
    }

}
