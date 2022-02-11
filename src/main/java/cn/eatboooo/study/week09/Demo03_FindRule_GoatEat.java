package cn.eatboooo.study.week09;

/**
 * 找规律
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:17
 * <p>
 * 给定一个正整数N，表示有N份青草统一堆放在仓库里，有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * 不管是牛还是羊，每一轮能吃的草量必须是：1，4，16，64…(4的某次方)
 * 谁最先把草吃完，谁获胜，假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定。根据唯一的参数N，返回谁会赢
 */
public class Demo03_FindRule_GoatEat {
    // 返回在剩余 rest 草的情况下，先吃草的动物会不会获胜
    private static boolean win(int rest) {
        if (rest < 5) {
            // 剩余 5 份草了，节约一下时间
            return rest != 0 && rest != 2;
        }
        int n = 1;
        while (rest - n >= 0) {
            // win（rest - n） 返回的是
            // 在剩余 rest - n 的情况下，先吃草能不能获胜
            // b 代表牛先吃了 n 份草，羊会不会获胜
            boolean b = win(rest - n);

            if (!b) {
                // 此时羊（尝试了吃各种数量的草之后）不会获胜
                // 返回 true 代表牛获胜
                return true;
            }
            n = n * 4;
        }
        // 此时牛尝试了各种吃法，发现羊都不会失败（都没有进入到 if 里面去）
        // 所以返回牛失败
        return false;
    }

    private static boolean myRules(int rest) {
        switch (rest % 10) {
            case 1:
            case 3:
            case 4:
            case 6:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            boolean win = win(i);
            boolean myRules = myRules(i);
            if (win != myRules) {
                System.out.println("!!!!!");
            }
            // boolean myRules = myRules(i);
            // if (myRules) {
            //     System.out.println(i + ":" + myRules);
            // }
            // if (win) {
            //     System.out.println(i + " = " + win);
            // }
            // System.out.println(i + " = " + win);
        }
    }
}
