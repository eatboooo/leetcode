/**
 * @projectName leetcode
 * @package cn.eatboooo.work.test
 * @className cn.eatboooo.work.test.WorkTest01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.work.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * WorkTest01
 * @description thunisoft华宇
 * @author weiZhiLin
 * @date 2021/7/6 10:08
 * @version 1.0
 */
public class WorkTest01 {

    // 下划线分隔的单词转驼峰命名
    @Test
    void test01() {
        Map<String, Object> nao = new HashMap<>();
        Map<String, Object> nao2 = new HashMap<>();
        ArrayList<Map> objects = new ArrayList<>();
        objects.add(nao);
        objects.add(nao2);
        nao.put("abc_cca", new HashMap<>());
        nao.put("asdCCd", new HashMap<>());
        nao.put("abcdsd_cca", new HashMap<>());
        nao.put("abac_wccsaa", new HashMap<>());
        nao2.put("abc_cca", new HashMap<>());
        nao2.put("asdCCd", new HashMap<>());
        nao2.put("abcdsd_cca", new HashMap<>());
        nao2.put("abac_wccsaa", new HashMap<>());
        toHumpName(objects);
        for (Map object : objects) {
            Set<String> strings = object.keySet();
            for (String string : strings) {
                System.out.println(object.hashCode() + "string = " + string);
            }
        }

    }

    private void toHumpName(List<Map> content) {
        List<Map> arrayList = new ArrayList();
        for (int i = 0; i < content.size(); i++) {
            Map map = content.get(i);
            Map<String, Object> rmap = new HashMap<>();
            Set set = map.keySet();
            for (Object o : set) {
                String s = String.valueOf(o);
                if (s.contains("_")) {
                    String humpName = toHumpNameProcess(s);
                    rmap.put(humpName, map.get(s));
                } else {
                    rmap.put(s, map.get(s));
                }
            }
            arrayList.add(rmap);
            content.remove(i--);
        }
        content.addAll(arrayList);
    }

    private String toHumpNameProcess(String s) {
        String[] s1 = s.split("_");
        if (s1.length < 2) {
            return s1[0];
        }
        for (int i = 1; i < s1.length; i++) {
            String s2 = s1[i];
            s1[0] += s2.substring(0, 1).toUpperCase() + s2.substring(1);
        }
        return s1[0];
    }

    // 字符串分隔
    @Test
    void test02() {
        String[] split = "year\\文书类\\永久".split("\\\\");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            System.out.println("s = " + s);
        }

    }

    // if (a == (Integer) 1 && a == (Integer) 2 && a == (Integer) 3) 是否可能成立
    @Test
    void test03() {
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = null;
        try {
            c = cache.getDeclaredField("cache");
            c.setAccessible(true);
            Integer[] array = (Integer[]) c.get(cache);
            // array[129] is 1
            array[130] = array[129];
            // Set 2 to be 1
            array[131] = array[129];
            // Set 3 to be 1
            Integer a = 1;
            if (a == (Integer) 1 && a == (Integer) 2 && a == (Integer) 3) {
                System.out.println("Success");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
