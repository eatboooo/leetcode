package cn.eatboooo.practice.al;

import java.util.*;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/9/14 14:39
 */
/*
  题目：给出一组扑克比较。每张扑克有特定花色和数值，且没有两张完全一样的扑克。
  花色有“红桃”、“黑桃”、“方片”、“梅花”四种，大小分为“A, K, Q, J, T（代表10）, 9, 8, 7, 6, 5, 4, 3, 2”（由大到小）。
  五张完全不同的牌构成一幅手牌，我们需要比较两手牌的大小，两手牌可能部分牌是公用牌，
  不同类型手牌大小规则由大到小如下：
  10 Royal Flush: 只能是AKQJT，且同花
  9 Flush Straight: 是同花，且是顺子（5432A是最小的顺子，最大的为AKQJT）
  8 Four of a Kind: AAAAx（x表示任意牌, A表示四张一样大小的牌，如果两手牌都是Four of a Kind，则比较A的大小）
  7 Full House: AAABB（A表示3张一样大小的牌，B表示2张一样大小的牌，如果两手牌都是Full House，则比较A的大小，如果两手牌都是Full Hose且A相同，则比较B的大小）
  6 Straight：顺子（5432A是最小的顺子，最大的为AKQJT），但非同花
  5 Flush: （5张牌花色相同，如果两手牌都是Flush，则比较最大的那张牌的大小，如果仍然一样，则以此类推，直到完全一样）
  4 Three of a Kind: AAAxy （A表示3张一样大小的牌，xy表示任意牌，且x>y, A表示3张一样大小的牌，如果两首牌都是Three of a Kind，则比较A的大小，如果A相同，比较x，如果x相同，比较y）
  3 Two Pairs: AABBx （A表示两张一样大的牌，B表示两张一样大的牌，x表示任意牌，且A>B，如果两手牌都是Two pairs，则比较A的大小，如果A相同，比较B，如果B相同，比较x）
  2 Pair: AAxyz（A表示两张一样大的牌，x>y>z，两手牌首先比较A的大小，如果相同，则依次比较x, y, z的大小）
  1 High Cards：abcde（a>b>c>d>e，以此比较大小）

  输入：两手牌（数据结构自己定义，不考虑数据输入不合法，如，不用考虑输入为“黑桃X”。考察重点是比较逻辑本身），
  然后比较大小。输出较大的手牌，如果相等则返回相等
  */
public class Test01 {

    /*
     * 整体思路：
     * 先算出两手牌的 level ， 同时把对应 level 权重的分数计算出来
     * 比较 level 大小，如果不同直接返回结果
     * 如果 level 相同，比较之前计算的权重分数，返回结果
     * */


    public static void main(String[] args) {
        System.out.println(check("黑桃A,黑桃K,黑桃Q,黑桃J,黑桃T", "红桃A,红桃K,红桃Q,红桃J,红桃T"));
//        System.out.println(check("黑桃A,黑桃K,黑桃Q,黑桃J,黑桃2", "红桃A,红桃K,红桃Q,红桃J,红桃T"));
//        System.out.println(check("黑桃2,黑桃K,黑桃2,黑桃2,黑桃2", "红桃A,红桃K,红桃Q,红桃J,红桃T"));
//        System.out.println(check("黑桃K,黑桃K,黑桃2,黑桃2,黑桃2", "红桃A,红桃K,红桃Q,红桃J,红桃T"));
//        System.out.println(check("黑桃1,黑桃3,黑桃4,黑桃5,黑桃2", "红桃2,红桃3,红桃4,红桃5,红桃6"));
//        System.out.println(check("黑桃1,黑桃3,黑桃4,黑桃5,黑桃2", "绿桃2,红桃3,红桃4,红桃5,红桃6"));
    }

    public static class Info {
        // 优先比较级别，级别相同的情况下比较分数
        // 级别
        int level;
        // 分数 - 通过规则的权重计算出来
        int num;
        // 全部牌
        int[] arr;

        public Info(int level, int num) {
            this.level = level;
            this.num = num;
        }

        public Info(int level, int[] arr) {
            this.level = level;
            this.arr = arr;
        }
    }

    // a、b 对应两手牌
    public static String check(String a, String b) {
        // 获取 a b 对应的 info，再比较
        Info aInfo = getNum(a);
        Info bInfo = getNum(b);

        // 先比较级别
        if (aInfo.level > bInfo.level) {
            return "第一手牌比较大";
        }
        if (aInfo.level < bInfo.level) {
            return "第二手牌比较大";
        }

        // 此时级别相同，需根据不同规则去比较
        return checkSameLevel(aInfo, bInfo);
    }

    // 相同 leve 下比较
    private static String checkSameLevel(Info aInfo, Info bInfo) {
        int checkNum = 0;
        switch (aInfo.level) {
            case 5 | 1:
                checkNum = checkOne(aInfo.arr, bInfo.arr);
                break;
            default:
                checkNum = aInfo.num - bInfo.num;
        }
        return checkNum == 0 ? "两手排相等" : checkNum > 0 ? "第一手牌比较大" : "第二手牌比较大";
    }

    // 一个一个去比较 - 对应级别 5 / 1 的比较
    private static int checkOne(int[] arr1, int[] arr2) {
        for (int i = arr1.length - 1; i >= 0; i--) {
            if (arr1[i] > arr2[i]) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

    /*
       整体计算思路：
            检查顺子通过：
                检查同花通过：
                    检查同花顺
                    检查同花单牌
                检查同花失败：
                    计算权重返回
            检查顺子失败：
                检查四带一通过：计算权重返回
                检查四带一失败：
                    检查三带二通过：计算权重返回
                    检查三带二失败：
                        检查三带一通过：计算权重返回
                        检查三带一失败：
                            检查双对通过：计算权重返回
                            检查双对失败：
                                检查单对通过：计算权重返回
                                检查单对失败：计算权重返回
    */
    private static Info getNum(String a) {
        // 把输入拆开
        String[] split = a.split(",");
        // 把我们的牌存到数组里面
        int[] aInt = new int[5];

        for (int i = 0; i < aInt.length; i++) {
            aInt[i] = getStringNum(split[i]);
            // System.out.println("aInt = " + aInt[i]);
        }

        // 帮用户排好序，方便后续检查
        Arrays.sort(aInt);

        // 检查顺子
        boolean sortCheck = checkSort(aInt);
        // System.out.println(sortCheck);
        if (sortCheck) {

            // 是否同花
            boolean sameCheck = checkSame(split);
            if (sameCheck) {
                // 同花顺，对应等级 10 / 9
                // 10 级别和 9 级别其实一样，只不过需要比较数组大小
                return new Info(9, sumArr(aInt));
            }

            // 此时是顺子 但不同花 - 对应级别 6
            return new Info(6, sumArr(aInt));
        }

        // 此时不是顺子

        // 检查同花 - 对应级别 5
        boolean sameCheck = checkSame(split);
        if (sameCheck) {
            return new Info(5, aInt);
        }


        // 检查四带一 - 对应级别 8
        int fourAndOneCheck = checkFourOrThree(aInt, 4);
        if (fourAndOneCheck != -1) {
            return new Info(8, fourAndOneCheck);
        }
        // 检查三带二 - 对应级别 7
        int threeAndTwoCheck = checkThreeAndTwo(aInt);
        // System.out.println("三带二 = " + threeAndTwoCheck);
        if (threeAndTwoCheck != -1) {
            return new Info(7, threeAndTwoCheck);
        }
        // 检查三带一 - 对应级别 4
        int threeAndOneCheck = checkFourOrThree(aInt, 3);
        if (threeAndOneCheck != -1) {
            return new Info(4, threeAndOneCheck);
        }

        // 检查 AABBx - 对应级别 3
        int twoPairsCheck = checkTwoPairs(aInt);
        if (twoPairsCheck != -1) {
            return new Info(3, twoPairsCheck);
        }

        // 检查 AAxyz -对应级别 2
        int pairCheck = checkPair(aInt);
        if (pairCheck != -1) {
            return new Info(2, pairCheck);
        }

        // 对应级别 1
        return new Info(1, sumArr(aInt));
    }

    // 检查 AAxyz -对应级别 2
    private static int checkPair(int[] aInt) {
        int same = -1;
        Set<Integer> removeSame = new HashSet<>();
        for (int i = 0; i < aInt.length; i++) {
            removeSame.add(aInt[i]);
            for (int j = i + 1; j < aInt.length; j++) {
                if (aInt[i] == aInt[j]) {
                    same = aInt[i];
                }
            }
        }
        if (same == -1) {
            return -1;
        }
        removeSame.remove(same);
        int[] reArr = new int[3];
        int index = 0;
        for (Integer integer : removeSame) {
            reArr[index++] = integer;
        }
        ;
        Arrays.sort(reArr);

        return same * 1000000 + aInt[2] * 10000 + aInt[1] * 100 + aInt[0];
    }

    // 检查 AABBx - 对应级别 3
    private static int checkTwoPairs(int[] aInt) {
        int sameA = -1;
        int sameB = -1;
        for (int i = 0; i < aInt.length; i++) {
            for (int j = i + 1; j < aInt.length; j++) {
                if (aInt[i] == aInt[j] && sameA == -1) {
                    sameA = aInt[i];
                } else {
                    sameB = aInt[i];
                }
            }
        }

        // 检查是否符合规格
        if (sameA == -1 || sameB == -1) {
            return -1;
        }

        // 找到 x
        int x = -1;
        for (int i = 0; i < aInt.length; i++) {
            if (aInt[i] != sameA && aInt[i] != sameB) {
                x = aInt[i];
            }
        }
        if (sameA > sameB) {
            return sameA * 10000 + sameB * 100 + x;
        }
        return sameB * 10000 + sameA * 100 + x;

    }

    // 返回 -1 代表不是 三带二，否则返回 3 张牌的大小
    private static int checkThreeAndTwo(int[] aInt) {
        // 是否包含三张相同的牌
        boolean checkThree = false;
        // 是否包含两张相同的牌
        boolean checkTWo = false;

        // 频率统计
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < aInt.length; i++) {
            if (map.containsKey(aInt[i])) {
                map.put(aInt[i], map.get(aInt[i]) + 1);
                continue;
            }
            map.put(aInt[i], 1);
        }

        // 返回的值
        int ans = 0;
        // 用词频的方法检查 三带二
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == 3) {
                checkThree = true;
                ans = integer;
            }
            if (map.get(integer) == 2) {
                checkTWo = true;
            }
        }

        if (checkThree && checkTWo) {
            return ans;
        }
        return -1;
    }

    // 返回 -1 代表不是 四带一，否则返回 4 张牌的大小
    // num 表示 用户希望检查 num 带一（四带一还是三带一）
    private static int checkFourOrThree(int[] aInt, int num) {
        // 三带一、四带一 只需要拿排好序的前两个数进行便利即可
        // 前两个都不满足 三带一、四带一，则直接返回 false
        for (int i = 0; i < 2; i++) {
            int checkNum = 1;
            for (int j = i + 1; j < aInt.length; j++) {
                if (aInt[j] == aInt[i]) {
                    checkNum++;
                }
            }
            if (checkNum == num) {
                return aInt[i];
            }
        }
        return -1;
    }

    // 检查是否是同花色
    private static boolean checkSame(String[] split) {
        for (int i = 1; i < split.length; i++) {
            if (!split[i - 1].substring(0, 2).equals(split[i].substring(0, 2))) {
                return false;
            }
        }
        return true;
    }

    // 检查是否是顺子
    private static boolean checkSort(int[] aInt) {
        for (int i = 1; i < aInt.length; i++) {
            if (aInt[i - 1] != aInt[i] - 1) {
                return false;
            }
        }
        return true;
    }


    // 把牌转化成分数
    private static int getStringNum(String a) {
        String substring = a.substring(2);
        switch (substring) {
            case "A":
                return 14;
            case "K":
                return 13;
            case "Q":
                return 12;
            case "J":
                return 11;
            case "T":
                return 10;
        }
        return (int) Integer.valueOf(substring);
    }

    // 数组求和
    public static int sumArr(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }
}
