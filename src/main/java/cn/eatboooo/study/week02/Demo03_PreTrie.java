package cn.eatboooo.study.week02;

import java.util.HashMap;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/10 12:08
 *  前缀树
 */
public class Demo03_PreTrie {
    // char
    public static class Node01 {
        // 第一次 忘记这个怎么写了
        public Node01[] nexts;
        int end;
        int pass;

        public Node01() {
            end = 0;
            pass = 0;
            nexts = new Node01[26];
        }
    }

    public static class Trie01 {
        public Node01 root;

        public Trie01() {
            root = new Node01();
        }

        // 插入一个单词
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node01 head = root;
            // 根节点 pass 记得要先 ++
            head.pass++;
            // int 提取出来不需要每在循环里次都 new
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (head.nexts[path] == null) {
                    head.nexts[path] = new Node01();
                }
                head = head.nexts[path];
                head.pass++;
            }
            head.end++;
        }

        public void delete(String word) {
            if (word == null || search(word) == 0) {
                return;
            }
            Node01 head = root;
            char[] chars = word.toCharArray();
            head.pass--;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (--head.nexts[path].pass == 0) {
                    head.nexts[path] = null;
                    return;
                }
                head = head.nexts[path];
            }
            // 别忘了这个
            head.end--;
        }

        // 统计一个单词出现了多少次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node01 head = root;
            char[] chars = word.toCharArray();
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (head.nexts[path] == null) {
                    return 0;
                }
                head = head.nexts[path];
            }
            return head.end;
        }

        // 统计多少个前缀，和 search 基本一样，复制过来
        public int searchPre(String word) {
            if (word == null) {
                return 0;
            }
            Node01 head = root;
            char[] chars = word.toCharArray();
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (head.nexts[path] == null) {
                    return 0;
                }
                head = head.nexts[path];
            }
            // 和 search 唯一不同的地方
            return head.pass;
        }
    }

    // hashMap
    public static class Node02 {
        public HashMap<Integer, Node02> nexts;
        int end;
        int pass;

        public Node02() {
            nexts = new HashMap();
            end = 0;
            pass = 0;
        }
    }

    public static class Trie02 {
        public Node02 root;

        public Trie02() {
            root = new Node02();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node02 head = root;
            char[] chars = word.toCharArray();
            int path;
            root.pass++;
            for (int i = 0; i < chars.length; i++) {
                path = (int) chars[i];
                if (!head.nexts.containsKey(path)) {
                    head.nexts.put(path, new Node02());
                }
                head = head.nexts.get(path);
                head.pass++;
            }
            head.end++;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            Node02 head = root;
            int path;
            head.pass--;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                path = (int) chars[i];
                if (--head.nexts.get(path).pass == 0) {
                    // 第一次这个 remove 忘记了
                    head.nexts.remove(path);
                    return;
                }
                head = head.nexts.get(path);
            }
            head.end--;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            int path;
            Node02 head = root;
            for (int i = 0; i < chars.length; i++) {
                path = (int) chars[i];
                if (!head.nexts.containsKey(path)) {
                    return 0;
                }
                head = head.nexts.get(path);
            }
            return head.end;
        }

        public int searchPre(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            int path;
            Node02 head = root;
            for (int i = 0; i < chars.length; i++) {
                path = (int) chars[i];
                if (!head.nexts.containsKey(path)) {
                    return 0;
                }
                head = head.nexts.get(path);
            }
            return head.pass;
        }
    }

    // for test

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie01 trie1 = new Trie01();
            Trie02 trie2 = new Trie02();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
//                    if (ans1 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.searchPre(arr[j]);
                    int ans2 = trie2.searchPre(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
//                    if (ans1 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
