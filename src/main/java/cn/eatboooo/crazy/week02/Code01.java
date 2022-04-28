package cn.eatboooo.crazy.week02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/4/9 20:43
 *
 * 给定数组hard和money，长度都为N，hard[i]表示i号工作的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力，每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班。返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 *
 * 思路：利用有序表中的 floorKey 来找到最邻近且 《= 它的 key
 * 入表思路：如果难度比我高，挣得比我少，呢我直接不要了
 */
public class Code01 {
    class Job implements Comparator<Job> {
        int hard;
        int money;

        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard == o2.hard ? o2.money - o1.money : o1.hard - o2.hard;
        }
    }

    public int[] get(Job[] jobs, int[] ability) {
        int[] ans = new int[ability.length];
        Arrays.sort(jobs);
        // key: ability; value: money
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 代表上个工作的工资
        int pre = jobs[0].money;
        map.put(jobs[0].hard, pre);
        for (int i = 1; i < jobs.length; i++) {
            Job job = jobs[i];
            // 工资不如上一个工作呢就不要了
            if (pre < job.money) {
                map.put(job.hard, job.money);
                pre = job.money;
            }
        }
        for (int i = 0; i < ability.length; i++) {
            int i1 = ability[i];
            // 找到适合他的难度，如果没有找到比他小离他最近的,反之 map.ceilingKey() 这个函数
            ans[i] = map.floorKey(i1);
        }
        return ans;
    }
}
