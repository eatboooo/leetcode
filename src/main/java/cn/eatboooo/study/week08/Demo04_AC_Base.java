package cn.eatboooo.study.week08;

import java.util.*;

/**
 * AC 自动机
 * 传入一篇文章，检测文章中有那些敏感词汇
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/27 14:21
 * <p>
 * 在前缀树上玩 kmp
 */
public class Demo04_AC_Base {
    // 前缀树
    public static class Node {
        // 用于存储下一个字符
        Node[] next;
        // 类似与 kmp 中的 next 数组
        Node fail;
        // 以他结尾的字符串是什么
        String end;
        // 有没有使用过
        boolean endUsed;

        public Node() {
            endUsed = false;
            end = null;
            fail = null;
            next = new Node[26];
        }
    }

    public static class AC {
        Node root;

        public AC() {
            this.root = new Node();
        }

        // 每有一个敏感词，调用一次该方法
        public void insert(String word) {
            char[] chars = word.toCharArray();
            Node cur = root;
            for (char aChar : chars) {
                int index = aChar - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.end = word;
        }

        // 一层一层的去设置 fail
        public void buildFail() {
            Node cur;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                cur = queue.poll();
                // 26 个字母，按层遍历
                for (int i = 0; i < 26; i++) {
                    Node son = cur.next[i];
                    if (son != null) {
                        son.fail = root;
                        Node fatherFail = cur.fail;
                        while (fatherFail != null) {
                            if (fatherFail.next[i] != null) {
                                son.fail = fatherFail.next[i];
                                break;
                            }
                            fatherFail = fatherFail.fail;
                        }
                        queue.add(son);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            List<String> ans = new ArrayList<>();
            char[] chars = content.toCharArray();
            Node cur = root;
            Node follow = null;
            for (char aChar : chars) {
                int index = aChar - 'a';
                // 为什么有 cur != root ？ 如果 cur == root 的话，再循环一次就会空指针异常
                while (cur.next[index] == null && cur != root) {
                    cur = cur.fail;
                }
                // 1) cur == root
                // 2) cur.next[index] != null
                cur = cur.next[index] == null ? root : cur.next[index];
                follow = cur;
                // 这里是 ！= root
                while (follow != root) {
                    if (follow.endUsed) {
                        break;
                    }
                    // ⚠️ 这里是直接判断，而不是 follow.next[index].end != null
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.endUsed = true;
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        AC ac = new AC();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.buildFail();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
