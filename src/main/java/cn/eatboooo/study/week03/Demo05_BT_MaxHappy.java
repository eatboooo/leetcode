/**
 * @projectName leetcode
 * @package cn.eatboooo.study.week03
 * @className cn.eatboooo.study.week03.Demo05_BT_MaxHappy
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.study.week03;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo05_BT_MaxHappy
 * @description 最大快乐值
 * @author weiZhiLin
 * @date 2021/7/20 14:33
 * @version 1.0
 *
 * 公司开派对，上级来了的话，直属下级就不能来，
 * 每个人来了都可以带来一些快乐值，求最大快乐值
 */
public class Demo05_BT_MaxHappy {
    public static class Employee {
        // 快乐值
        public int happy;
        // 下级
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    public static class Info {
        int yes;
        int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    private static int process1(Employee boss, boolean up) {
        if (up) {
            // 上级来我只能不来
            int ans = 0;
            for (Employee next : boss.nexts) {
                ans += process1(next, false);
            }
            return ans;
        }
        // 上级不来，取我来和不来
        int p1 = boss.happy;
        int p2 = 0;
        for (Employee next : boss.nexts) {
            p1 += process1(next, true);
            p2 += process1(next, false);
        }
        return Math.max(p1, p2);
    }

    public static int maxHappy2(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return (Math.max(process2(boss).no, process2(boss).yes));
    }

    public static Info process2(Employee boss) {
        if (boss == null) {
            return new Info(0, 0);
        }
        int yes = boss.happy;
        int no = 0;
        for (Employee next : boss.nexts) {
            Info nextInfo = process2(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.no, nextInfo.yes);
        }
        return new Info(yes, no);
    }


    // test
    // copy for test
    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
