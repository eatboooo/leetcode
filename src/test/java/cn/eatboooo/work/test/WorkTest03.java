package cn.eatboooo.work.test;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/10/26 10:00
 */
public class WorkTest03 {

    /**
     * 生成自增的数字 id 到 sql 中
     *
     * @author weiZhiLin
     */
    @Test
    void testBufferedReader() throws IOException {
        String file = "SQLcn";
//        String qid = "1190959669279981569";
        String qid = "1190959669279981570";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/eatboooo/Documents/parctise/java/leetcode/src/main/resources/insert" + file)));
        String data = null;
        StringBuilder sb = new StringBuilder();
//        long i = 1227884772458641180L;
        long i = 1227884772458641280L;
        List<Long> iList = new ArrayList<>();
        while ((data = br.readLine()) != null) {
//            sb.append(data + " ");
            iList.add(i);
            System.out.println(data.replace("???", String.valueOf(i++)));
        }
        System.out.println("\n\n\n\n\n\n");
        br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/eatboooo/Documents/parctise/java/leetcode/src/main/resources/" + file)));
        String sql = br.readLine();
        for (Long aLong : iList) {
            System.out.println(sql.replace("?oid?", String.valueOf(aLong)).replace("?qid?", qid));
        }
    }

    // 使用雪花算法生成
    @Test
    void testBuffer2() throws IOException {
        String file = "SQLcn";
        String[] qids = new String[]{"1190959669279981569", "1190959669279981570"};
        for (String qid : qids) {
            BufferedReader optionBr = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/insert" + file)));
            BufferedReader questionBr = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/" + file)));
            Snowflake snowflake = IdUtil.getSnowflake();
            String data = null;
            List<String> iList = new ArrayList<>();
            while ((data = optionBr.readLine()) != null) {
                String id = String.valueOf(snowflake.nextId());
                iList.add(id);
                System.out.println(data.replace("???", id));
            }
            System.out.println("\n\n\n\n\n\n");
            String sql = questionBr.readLine();
            for (String oid : iList) {
                System.out.println(sql.replace("?oid?", oid).replace("?qid?", qid));
            }
            System.out.println("\n\n\n\n\n\n");

            optionBr.close();
            questionBr.close();
        }
    }
}
