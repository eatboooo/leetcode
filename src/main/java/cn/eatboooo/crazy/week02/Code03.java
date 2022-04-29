package cn.eatboooo.crazy.week02;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/4/9 21:21
 * <p>
 * 已知一个消息流会不断地吐出整数1~N，但不一定按照顺序依次吐出，如果上次打印的序号为i， 那么当i+1出现时
 * 请打印i+1及其之后接收过的并且连续的所有数，直到1~N全部接收并打印完，请设计这种接收并打印的结构
 * <p>
 * 思路：wait 等待的节点，头表（任何连续链表的头都需要在头表中体现）、尾表（同理）、单链表（node 有个指针，本身能构成单链表）
 * 每次来 node 时，检查头尾信息，如果删掉的 node，就放入单链表中
 */
public class Code03 {

    static class Node {
        String message;
        Node next;
    }

    static class MessageBox {
        int wait;
        Map<Integer, Node> head;
        Map<Integer, Node> tail;

        public MessageBox() {
            wait = 1;
            head = new TreeMap<>();
            tail = new TreeMap<>();
        }

        public void receive(int num, String message) {
            if (num < 1) {
                return;
            }
            Node node = new Node();
            node.message = message;

            head.put(num, node);
            tail.put(num, node);

            // 有没有 num - 1 节点作为结尾的
            if (head.containsKey(num - 1)) {
                Node node1 = head.get(num - 1);
                node1.next = node;
                // ⚠️ 现在 num - 1 不是作为结尾的了
                head.remove(num - 1);
            }
            // 有没有 num + 1 节点作为开头的
            if (tail.containsKey(num + 1)) {
                Node node1 = tail.get(num + 1);
                node.next = node1;
                // ⚠️ 现在 num + 1 不是作为开头的了
                tail.remove(num + 1);
            }
            if (wait == num) {
                print();
            }
        }

        private void print() {
            Node node = head.remove(wait);
            while (node != null) {
                System.out.println(node.message);
                wait++;
                node = node.next;
            }
            tail.remove(wait - 1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MessageBox box = new MessageBox();
        box.receive(1, "first");
        box.receive(3, "3");
        box.receive(2, "2");
        box.receive(4, "4");
    }


}
