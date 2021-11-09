package cn.eatboooo.work.test;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.google.common.base.Joiner;
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
        String file = "SQLen";
        String[] qids = new String[]{"1190959669279981569", "1190959669279981570"};
        List<List<String>> varchar = findVarcharEn();
//        List<List<String>> varchar = findVarchar();
        for (String qid : qids) {
            BufferedReader optionBr = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/insert" + file)));
            BufferedReader questionBr = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/" + file)));
            BufferedReader translateBr = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/" + file + "T")));
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
            String translateSQL = translateBr.readLine();
            System.out.println("\n\n\n\n\n\n");
            int index = 0;
            String t = null;
            for (String oid : iList) {
                t = varchar.get(0).get(index);
                System.out.println(translateSQL.replace("?oid?", oid).replace("?t?", t).replace("?lan?", "zh_CN"));
                t = varchar.get(1).get(index++);
                System.out.println(translateSQL.replace("?oid?", oid).replace("?t?", t).replace("?lan?", "en_US"));
            }
            System.out.println("\n\n\n\n\n\n");
            optionBr.close();
            questionBr.close();
        }
    }

    @Test
    List<List<String>> findVarchar() throws IOException {
        String file = "SQLcn";
        BufferedReader optionBr = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/insert" + file)));
        List<String> iList = new ArrayList<>();
        List<String> tList = new ArrayList<>();
        String data = null;
        while ((data = optionBr.readLine()) != null) {
            int end = data.indexOf("厘米");
            if (end == -1) {
                break;
            }
            String varchar = data.substring(185, end + 2);
            iList.add(varchar);
            tList.add(varchar.replace("厘米", "cm"));
        }
        ArrayList<List<String>> list = new ArrayList<>();
        iList.add("不能完成");
        tList.add("Could not complete");
        list.add(iList);
        list.add(tList);
        return list;
    }

    @Test
    List<List<String>> findVarcharEn() throws IOException {
        String file = "SQLen";
        BufferedReader optionBr = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/insert" + file)));
        List<String> iList = new ArrayList<>();
        List<String> tList = new ArrayList<>();
        String data = null;
        while ((data = optionBr.readLine()) != null) {
            int end = data.indexOf("inches");
            if (end == -1) {
                break;
            }
            String varchar = data.substring(186, end + 6);
            tList.add(varchar);
            iList.add(varchar.replace("inches", "英寸"));
        }
        ArrayList<List<String>> list = new ArrayList<>();
        iList.add("不能完成");
        tList.add("Could not complete");
        list.add(iList);
        list.add(tList);
        return list;
    }
}
