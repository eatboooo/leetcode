package cn.eatboooo.study.week10;

/**
 * DC3 应用 str2整体插入到str1中的某个位置
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/19 22:58
 * <p>
 * 给定两个字符串str1和str2，
 * 想把str2整体插入到str1中的某个位置，形成最大的字典序，返回字典序最大的结果
 */
public class Demo02_DC3_MaxStr2InsertToStr1 {
    // copy for study
    // 第一步是你得建立str1从i位置出发及其后面所有的字符串与str2是从0出发及其后面所有字符串的字典序比较机制,
    // 还要比较快，能够查出来字典序谁能分出大小(DC3)。
    // 第二步: 依次在str1, 0位置, 1位置, 直到找到i位置发现后面字符串字典序小于str2了, 定位出i 找到一个最左的可能性,
    // 第三步: 顺着i跟str2找到是哪个字符串分出胜负的, 就找到了最右的可能性。
    // 第四步就是我截取出一个从最左到到最后的局部串找到最大的字典序, 就知道str2该插到哪儿了。
    public static String max1(String s1, String s2) {
        // base
        if (s1 == null || s1.length() == 0) {
            return s2;
        }
        if (s2 == null || s2.length() == 0) {
            return s1;
        }

        // DC3 前置设置
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int min = str1[0];
        int max = str1[0];
        for (int i = 1; i < N; i++) {
            min = Math.min(min, str1[i]);
            max = Math.max(max, str1[i]);
        }
        for (int i = 0; i < M; i++) {
            min = Math.min(min, str2[i]);
            max = Math.max(max, str2[i]);
        }
        int[] all = new int[N + M + 1];
        int index = 0;
        // 数组改小，方便 DC3 内部桶排序
        // +2 为了s1 和 s2 中间插入 1 ascii 时保证 1 是最小的
        for (int i = 0; i < N; i++) {
            all[index++] = str1[i] - min + 2;
        }
        all[index++] = 1;
        for (int i = 0; i < M; i++) {
            all[index++] = str2[i] - min + 2;
        }
        // all 代表：s1 和 s2 连接在一起，同时中间插入一个最小的 ascii 1
        // 这样 rank 中的排名就可以作为 s1 和 s2 的排名
        DC3 dc3 = new DC3(all, max - min + 2);
        // 拿到排名
        int[] rank = dc3.rank;

        // comp 代表 s2 的排名坐标
        int comp = N + 1;
        // 第二步: 依次在str1, 0位置, 1位置, 直到找到i位置发现后面字符串字典序小于str2了, 定位出i 找到一个最左的可能性,
        for (int i = 0; i < N; i++) {
            // rank[comp] 即为 s2 的字典序排名
            if (rank[i] < rank[comp]) {
                // 找到最好切割点
                int best = bestSplit(s1, s2, i);
                return s1.substring(0, best) + s2 + s1.substring(best);
            }
        }

        // 此时 s1 均比 s2 大，s2 只能拍到最后面
        return s1 + s2;
    }

    // 找到最好切割点
    // 从 first 位置开始尝试
    public static int bestSplit(String s1, String s2, int first) {
        // 此时 s1 从 i 开始的字典序 是小于 s2 的
        int N = s1.length();
        int M = s2.length();
        // 第三步: 顺着i跟str2找到是哪个字符串分出胜负的, 就找到了最右的可能性。
        int end = N;
        for (int i = first, j = 0; i < N && j < M; i++, j++) {
            // 此时 s1 从 i 开始的字典序 是小于 s2 的
            if (s1.charAt(i) < s2.charAt(j)) {
                end = i;
                break;
            }
        }

        // 第四步就是我截取出一个从最左到到最后的局部串找到最大的字典序, 就知道str2该插到哪儿了。
        // 此时先假装把 s2 当作前缀
        String bestPrefix = s2;
        int bestSplit = first;

        // 再从 first ~ end 去一个一个找
        // 有没有必 s2 当前缀更好的结果
        for (int i = first + 1, j = M - 1; i <= end; i++, j--) {
            String curPrefix = s1.substring(first, i) + s2.substring(0, j);
            if (curPrefix.compareTo(bestPrefix) >= 0) {
                bestPrefix = curPrefix;
                bestSplit = i;
            }
        }
        return bestSplit;
    }

}
