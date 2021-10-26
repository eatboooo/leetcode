package cn.eatboooo.work.test;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/17 19:34
 */
public class OverLoad {
    public void test01(String a) {

    }

    public void test01(int a) {

    }

    // 只有返回值不同是不可以重载的
    /*public String test01(int a) {
        return "";
    }*/

}
