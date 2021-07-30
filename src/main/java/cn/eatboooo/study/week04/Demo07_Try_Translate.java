package cn.eatboooo.study.week04;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/30 12:22
 * <p>
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * 从左往右的尝试模型
 */
public class Demo07_Try_Translate {
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process1(str.toCharArray(), 0);
    }

    private static int process1(char[] toCharArray, int i) {
        // 成功走到了最后一个节点，证明之前没出错
        if (toCharArray.length == i) {
            return 1;
        }
        // 单独来到了 0 肯定有问题
        if (toCharArray[i] == '0') {
            return 0;
        }
        int ans = process1(toCharArray, i + 1);
        // 能否和下一位组合
        if (i < toCharArray.length - 1 && (toCharArray[i] - '0') * 10 + toCharArray[i + 1] - '0' < 27) {
            ans += process1(toCharArray, i + 2);
        }
        return ans;
    }

    // 根据尝试模型改的！
    public static int dp1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[str.length() + 1];
        dp[str.length()] = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                dp[i] = dp[i + 1];
                if (i < chars.length - 1 && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
                    dp[i] += dp[i + 2];

                }
            }
        }
        return dp[0];
    }


    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp1(s);
//            int ans2 = dp2(s);
            if (ans0 != ans1 /*|| ans0 != ans2*/) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
//                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
