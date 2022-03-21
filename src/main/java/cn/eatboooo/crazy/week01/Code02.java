package cn.eatboooo.crazy.week01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/17 21:28
 * <p>
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，
 * 隐藏文件也算，但是文件夹不算
 * <p>
 * 思路：图算法，使用队列宽度优先遍历一下完事
 */
public class Code02 {
    // 注意这个函数也会统计隐藏文件
    public static int getFileNumber2(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Queue<File> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            File poll = queue.poll();
            for (File file : poll.listFiles()) {
                if (file.isDirectory()) {
                    queue.add(file);
                } else {
                    ans++;
                }

            }
        }
        return ans;
    }
}
