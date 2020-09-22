package com.smietao.gdut;

import com.smietao.gdut.keyword.Keyword;
import com.smietao.gdut.keyword.TFIDFAnalyzer;
import com.smietao.gdut.util.CalculateCosSim;

import java.util.List;


public class App {
    public static void main(String[] args) {
        String original = "今天是星期天，天气晴，今天晚上我要去看电影";
        String plagiarize = "今天是周天，天气晴朗，我晚上要去看电影";
        System.out.println("原文——" + original);
        System.out.println("抄袭文——" + plagiarize);
        int topN = 5;
        System.out.println("提取出两段话中TF-IDF值最高的5个关键词");
        TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();

        List<Keyword> originalList = tfidfAnalyzer.analyze(original, topN);
        List<Keyword> plagiarizeList = tfidfAnalyzer.analyze(plagiarize, topN);
        for (int i = 0; i < originalList.size(); i++) {
            System.out.println("原文关键词" + i + originalList.get(i).getName());
            System.out.println("抄袭文关键词" + i + plagiarizeList.get(i).getName());
        }

        System.out.println("两段话的重复率为" + CalculateCosSim.calculateCosineSimilarity(originalList, plagiarizeList));
    }
}
