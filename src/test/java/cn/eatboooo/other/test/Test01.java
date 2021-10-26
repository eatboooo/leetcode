package cn.eatboooo.other.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/17 10:23
 */
public class Test01 {
    @Test
    void test01() {
        System.out.println(Integer.MAX_VALUE + 1);
    }

    // list to array 测试
    @Test
    void test02() {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        // 等于 0，动态创建与 size 相同的数组，性能最好
        String[] array = list.toArray(new String[0]);
        System.out.println(array.length);
    }

    // switch null 测试, 结果是直接抛异常
    @Test
    void test03() {
        String abc = null;
        switch (abc) {
            case "sth":
                System.out.println("it's sth");
                break;
            case "null":
                System.out.println("it's null");
                break;
            default:
                System.out.println("default");
        }
    }
}
