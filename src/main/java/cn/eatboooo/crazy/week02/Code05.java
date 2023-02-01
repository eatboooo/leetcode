package cn.eatboooo.crazy.week02;

import java.util.HashMap;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/4/9 21:20
 * <p>
 * 设计有setAll功能的哈希表，put、get、setAll方法，时间复杂度O(1)
 * <p>
 * 核心思想：使用一个变量来记录存储时间,一个变量来记录 setAll 的值，
 * get时比较存储时间，来判断是否使用 setAll 的值
 */
public class Code05 {
    public static class Value<V> {
        V v;
        long time;

        public Value(V v, long time) {
            this.v = v;
            this.time = time;
        }
    }

    public static class MyMap<K, V> {
        HashMap<K, Value<V>> map;
        Value<V> setAll;
        long time;

        public MyMap() {
            map = new HashMap<>();
            time = 0;
            setAll = new Value<>(null, 0);
        }

        public void put(K key, V value) {
            map.put(key, new Value<>(value, time++));
        }

        public void setAll(V value) {
            setAll = new Value<>(value, time++);
        }

        public V get(K key) {
            if (map.containsKey(key)) {
                if (setAll.time > map.get(key).time) {
                    return setAll.v;
                }
                return map.get(key).v;
            }
            return null;
        }
    }
}
