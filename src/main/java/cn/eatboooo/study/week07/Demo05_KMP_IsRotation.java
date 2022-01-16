package cn.eatboooo.study.week07;

/**
 * KMP 实践
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/1/16 14:37
 * <p>
 * 判断str1和str2是否互为旋转字符串
 */
public class Demo05_KMP_IsRotation {
    // todo
    // 旋转字符串是什么？
    // abcdefg、cdefgab 互为旋转字符串
    // defgabc、abcdefg 互为旋转字符串
    // abc ｜ defg  中间切一刀，两边互换位置则是旋转字符串
    // 所以有 ：如果 str2 是 str1 + str1 的子字符串的话，则str1和str2互为旋转字符串
}
