package cn.etaboooo.leetcode.demo;

import org.junit.jupiter.api.Test;

/**
 * @author weiZhiLin
 * @date 2021/5/11 15:59
 * @version 1.0
 */
class Base01Test {
    @Test
    void print32() {
        Base01.print32(-1);
        Base01.print32(~-1+1);
        Base01.print32(Integer.MIN_VALUE);
    }
}