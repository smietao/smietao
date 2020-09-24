package com.smietao.gdut;

import static org.junit.Assert.assertTrue;

import com.smietao.gdut.keyword.Keyword;
import com.smietao.gdut.keyword.TFIDFAnalyzer;
import com.smietao.gdut.util.CalculateCosSim;
import com.smietao.gdut.util.ReadTxt;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testTfidfAnalyzer() {
        String content = "今天是星期天，天气晴，今天晚上我要去看电影";
        // 定义关键词个数
        int topN = 5;
        // 提取文章中的20个关键词和词频
        TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();
        List<Keyword> keywordList = tfidfAnalyzer.analyze(content, topN);
        if (keywordList != null && keywordList.size() != 0) {
            for (Keyword keyword : keywordList) {
                System.out.println("关键词为【" + keyword.getName() + "】，词频为【" + keyword.getTfidfvalue() + "】");
            }
        }
    }

    @Test
    public void testTxtRead() {
        File orgFile = new File("D:/org.txt");
        // 读取txt文件中的内容 转化为String类型
        String original = ReadTxt.txt2String(orgFile).trim();
        System.out.println(original);
    }

    @Test
    public void testCalculate() {
        String original = "今天是星期天，天气晴，今天晚上我要去看电影";
        String plagiarize = "今天是周天，天气晴朗，我晚上要去看电影";
        // 定义关键词个数
        int topN = 5;
        // 提取文章中的5个关键词和词频
        TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();
        List<Keyword> originalList = tfidfAnalyzer.analyze(original, topN);
        List<Keyword> plagiarizeList = tfidfAnalyzer.analyze(plagiarize, topN);
        Double repetitiveRate = CalculateCosSim.calculateCosineSimilarity(originalList, plagiarizeList);
        System.out.println("两段文本的余弦相似度为：" + repetitiveRate);
    }


}
