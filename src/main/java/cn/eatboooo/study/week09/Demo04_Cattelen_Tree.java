package cn.eatboooo.study.week09;

/**
 * 卡特兰数
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/2/8 20:29
 * <p>
 * 有N个二叉树节点，每个节点彼此之间无任何差别，
 * 返回由N个二叉树节点，组成的不同结构数量是多少？
 */
public class Demo04_Cattelen_Tree {
    //	k(0) = 1, k(1) = 1
    //
    //	k(n) = k(0) * k(n - 1) + k(1) * k(n - 2) + ... + k(n - 2) * k(1) + k(n - 1) * k(0)
    //	或者
    //	k(n) = c(2n, n) / (n + 1)
    //	或者
    //	k(n) = c(2n, n) - c(2n, n-1)
}
