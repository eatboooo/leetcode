package cn.eatboooo.practice.hawei;

import java.util.*;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/12 22:07
 */
public class Test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            Service service = new Service();
            // 依赖
            String yl = sc.nextLine();
            // 故障
            String gz = sc.nextLine();

            String[] ylSplit = yl.split(",");
            // 第一遍用来初始化
            for (String s : ylSplit) {
                String[] curYl = s.split("-");
                service.add(curYl[0])  ;
                service.add(curYl[1]);
            }
            // 第二遍用来建立依赖关系
            for (String s : ylSplit) {
                String[] curYl = s.split("-");
                service.setFather(curYl[0], curYl[1]);
            }

            String[] gzSplit = gz.split(",");
            System.out.println(service.fatherDie(gzSplit));
        }
    }

    public static class Service {
        // 我对应的父亲
        HashMap<String, String> father;
        // 我对应的孩子
        HashMap<String, String> children;

        public Service() {
            father = new HashMap<>();
            children = new HashMap<>();
        }

        public void add(String string) {
            father.put(string, string);
            children.put(string, "");
        }

        public void setFather(String c, String f) {
            father.put(c, f);
//            father.put(c, children.get(c)+ f);
            children.put(f, children.get(f) + "," + c);
        }

        public String fatherDie(String[] gzSplit) {
            HashSet<String> hasDie = new HashSet<>();
            HashSet<String> ding = new HashSet<>();

            for (String s : gzSplit) {
                remove(s,hasDie,ding);
            }
            for (String s : hasDie) {
                children.remove(s);
            }
            StringBuilder sb = new StringBuilder();
            Set<String> strings = children.keySet();
            TreeSet<String> ts = new TreeSet<>();
            Stack<String> stack = new Stack<>();
            for (String t : strings) {
                ts.add(t);
            }
            for (String t : ts) {
                stack.add(t);
            }
            while (!stack.isEmpty()) {
                sb.append(",");
                sb.append(stack.pop());
            }
            if (sb.length() == 0) {
                return ",";
            }
            return sb.toString().substring(1);
        }

        private void remove(String s,Set hasDie,Set ding) {
            //  已经便利到最后了
            if (children.get(s).equals("")) {
                hasDie.add(s);
                return;
            }

            //没有便利到最后
            String[] split = children.get(s).substring(1).split(",");
            for (String s1 : split) {
                if (hasDie.contains(s1)) {
                    continue;
                }
                if (ding.contains(s1)) {
                    hasDie.add(s1);
                }
                ding.add(s1);
                remove(s1,hasDie,ding);
                hasDie.add(s1);
            }
            hasDie.add(s);
        }
    }
}
